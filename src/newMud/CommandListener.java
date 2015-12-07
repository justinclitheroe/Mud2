package newMud;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CommandListener implements ActionListener {

	private JTextArea out;
	private GameCharacter mainGuy;
	private JLabel imLabel;
	private ImageIcon roomPic;
	private JTextArea statsList;
	private Pattern commandRegex = Pattern.compile("(\\S+)(\\s+)(.+)"); 
	private String commandType = null;
	private String commandValue = null;
	private ArrayList<Mob> mobList;
	private ArrayList<Mob> engagedMob = new ArrayList<Mob>();
	
	
	
	
	public CommandListener(JTextArea out, GameCharacter pc, JLabel label, JTextArea sList,ArrayList<Mob> m){
		this.out = out;
		mainGuy = pc;
		imLabel = label;
		statsList = sList;
		mobList = m;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JTextField source = (JTextField) e.getSource();
		String s = source.getText().toLowerCase();
		out.append(s + "\n");
		source.setText("");

		
		Matcher commandMatcher = commandRegex.matcher(s);
		//commandMatcher.matches();
		
		if(commandMatcher.matches()){			//if command type gets matched with pattern...
			commandType = commandMatcher.group(1);
			commandValue = commandMatcher.group(3);
		}
		else commandType = s;	//otherwise set commandType to the whole command entered
		
		switch(commandType){
		case("help"):
			out.append("\n \n Thank you for contacting the MUD help desk. These are the following possible commands... \n");
			out.append("go <direction> (north,south,east,west,up,down) => moves the player to any valid room connected to the player's current location \n");
			out.append("get <item_name>  => adds item to player's inventory \n");
			out.append("drop <item_name> => removes item from players inventory \n");
			out.append("attack => attacks every mob in the room \n");
			out.append("exit => exits the game \n");
			out.append("help=> displays a list of given commands");
			break;
		case("start"):
			out.append("Enjoy your stay.");
			break;
		case("go"):
			switch(commandValue){
			case("north"):
				if(mainGuy.getLocation().getExits()[0] != null) mainGuy.goNorth();
				else out.append("/n You can not go in that direction." + "\n");
				break;
			case("south"):
				if(mainGuy.getLocation().getExits()[1] != null) mainGuy.goSouth();
				else out.append("\n You can not go in that direction." + "\n");
				break;
			case("east"):
				if(mainGuy.getLocation().getExits()[2] != null) mainGuy.goEast();
				else out.append("\n You can not go in that direction." + "\n");
				break;
			case("west"):
				if(mainGuy.getLocation().getExits()[3] != null) mainGuy.goWest();
				else out.append("\n You can not go in that direction." + "\n ");
				break;
			case("up"):
				if(mainGuy.getLocation().getExits()[4] != null) mainGuy.goUp();
				else out.append("\n You can not go in that direction." + "\n");
				break;
			case("down"):
				if(mainGuy.getLocation().getExits()[5] != null) mainGuy.goDown();
				else out.append("\n You can not go in that direction." + "\n");
				break;
			default:
				out.append("\n That is not a valid direction." + "\n");
			}
			break;
		case("get"):
			if((mainGuy.getLocation()).checkItem(commandValue)){
				mainGuy.plusOne();
				mainGuy.pickUp((mainGuy.getLocation()).returnItem(commandValue));
				out.append("Got it!" + "\n");
			}
			else out.append("That item is not in the room." + "\n");
			break;
		case("drop"):
			if (mainGuy.checkItem(commandValue)){						
				mainGuy.drop(mainGuy.returnItem(commandValue));
				out.append("Tossed " + commandValue + ", hope you dont need it later." + "\n");
			}
			else out.append("You can't drop what you dont have." + "\n");
			break;
		case("attack"):
			if(sameRoom()){		//if mobs are in the same room as player
				for(int i = 0; i<engagedMob.size();i++){//for each mob in the engagedMobs list					
					if (engagedMob.get(i).getName().toLowerCase().equals(commandValue.toLowerCase())){	//if the name entered in the command is the same name as in the array
						if(!engagedMob.get(i).isDead()){	//if the mob's health is >0
							engagedMob.get(i).engage();	//engage mob(stops mob from moving)
							out.append(engagedMob.get(i).getName() + " has been put in combat \n");
							out.append("The value for" + engagedMob.get(i).getName() + " isDead is " + engagedMob.get(i).isDead());
							attack(commandValue); //attacks mob using name in the entered command
							if(engagedMob.get(i).getHealth() <=0){		//if mob is dead remove the mob from engaged mob
								engagedMob.remove(i);				
								for(int j = 0; j< mobList.size();j++){		//then delete the mob from the actual mobList						
									if(mobList.get(i).getName().equalsIgnoreCase(commandValue)){
										mobList.remove(j);
									}
								}
							}
						}
						else out.append("That mob is already dead. Please stop beating a dead body");
					}
				}
			}
			else{
				out.append("You swing your fists but manage to punch yourself in the face becuase there's nothing to hit. ");
				out.append("\n you have sustained 1 damage in the process.\n");
				mainGuy.minusHealth(1);
			}
			break; 
			
		default:
			out.append("That is not a valid command." + "\n");
			break;
		}
		
		//creates a string of Stats for the player )
		String list = mainGuy.getName() + " Stats \n";
		list = list +  "Score: " + mainGuy.getScore() + " \n";
		list = list + "Health: " + mainGuy.getHealth() +"/" + mainGuy.getMaxHealth();
		list = list + "		Stamina: " + mainGuy.getStamina() +"/"+ mainGuy.getMaxStamina() + "\n";
		list =list + "Armour class: " + mainGuy.getArmour();
		list =list + "	Base Damage: " + mainGuy.getBaseDamage() +"\n";
		
		String inventory = "Inventory: \n";
		for(int i = 0; i<mainGuy.getInventory().size();i++){
			inventory = inventory + mainGuy.getInventory().get(i).getName() +"  ";
			}
		list = list + inventory;
		statsList.setText(list);	//sets the stats list (inventory,score, ect.) at the top of the GUI
		
		out.selectAll();//makes console auto scroll to the bottom when entering a new command
	
				//scales the room photo to the same size
		roomPic = mainGuy.getLocation().getImage();	
		Image img = roomPic.getImage();
		Image newimg = img.getScaledInstance(230, 310, java.awt.Image.SCALE_SMOOTH);
		roomPic = new ImageIcon(newimg);
		imLabel.setIcon(roomPic);
		
		
		out.append("\n \n"+(mainGuy.getLocation()).getDescription() +"\n");
		out.append("The room contains the following items: ");
		out.append(mainGuy.getLocation().getInventory().toString() + "\n");
		out.append(mainGuy.getLocation().listExits() + "\n\n\n");
		
	}//end of actionPreformed()
	
	public boolean sameRoom(){	//checks to see if any mobs are in the room and if they are add them to an arrayList of mobs engaged in combat.
		int count = 0;
		
		System.out.println("the mobList array size is: " + mobList.size());
				
		for(int i =0 ; i< mobList.size();i++){
			
			if(mainGuy.getLocation() == mobList.get(i).getLocation()){				
				//engagedMob.add(mobList.get(i));
				engagedMob.add(count, mobList.get(i));
				count++;
				}
		}
		return !engagedMob.isEmpty();
	}//end of sameRoom method
	
	
	
	/*
	 * 		Attack Methods
	 * 			Attack
	 *------------------------
	 * 1) takes in the name of a mob (String m)
	 * 2) if m = name of mob in ArrayList<Mob> 
	 * 		- engage the mob in combat (stops mob thread from moving)
	 * 		-attack the mob dealing damage
	 * 		-mob gets to attack you
	 * 3) if mob has no health-> for that mob...
	 * 		-set mobs isDead boolean to true
	 * 			-stops mob thread
	 * 		-deletes mob from the arrayList
	 *
	 *			AttackALl
	 *-------------------------
	 * same process as above except instead of taking in a String it automatically puts every
	 *  mob in the same room in combat
	 *
	 */
	public void attack(String m){		 
	if(m.equalsIgnoreCase("all")){
		attackAll();
	}
	else{	
	for (int i = 0; i < mobList.size(); i++) {
			if(m.equals(mobList.get(i).getName().toLowerCase())) {
				int damage;
				if (mobList.get(i).getLocation().equals(mainGuy.getLocation())){
					damage = mainGuy.damage(mobList.get(i));
					out.append("Damage = " + damage + "\n");
					out.append("Main guy hits " + mobList.get(i).getName()+ " For: " + damage + "\n");
					mobList.get(i).minusHealth(damage);
					if(mobList.get(i).getHealth() <=0){
						out.append(mobList.get(i).getName() + " got #REKT and is # DED \n");;
						mobList.get(i).isDead();
						mobList.remove(i);	
					}//end of if (mob has no health)
				}//end of if (player location = mob location)
			}//end of if (name of mob = name of mob in arrayList)
			else if(m.equals(null)){
				System.out.println("attack all method runs");
				attackAll();
			}//end of else if()
		}//end of i for loop
	}
	}
	
	
	public void attackAll(){
		int damage;
		while(mainGuy.getHealth() > 0){						//while player's health is greater than 0...	
			for(int i = 0; i < engagedMob.size() ; i++){		//for each mob player is engaged in...
				while(engagedMob.get(i).getHealth() > 0){			//while the mobs health is above 0...
						damage = mainGuy.damage(engagedMob.get(i));
						out.append(engagedMob.get(i).getName() + " got hit for " + damage + " hit points \n");
						engagedMob.get(i).minusHealth(damage);
				    	out.append("You got hit for " + mainGuy.intGetKneed(5, 0) + " hit points \n");									  //mob then attacks player
						}
				out.append("You have killed " + engagedMob.get(i).getName());
				engagedMob.get(i).makeDead();
				System.out.println("engagedMob" + engagedMob.get(i).getName()+ "Has Died");

				for(int q =0; q < mobList.size(); q++){		//removes dead Mob from the system
					for(int j =0;j<engagedMob.size();j++){
						if(mobList.get(q)==engagedMob.get(q)){
							mobList.remove(q);
							engagedMob.remove(j);
						}//end of if statement
					}//end of for loop j
				}//end of for loop q   			
			}//end of for loop i
		}//end of while loop
	}//end of attackAll method
	
}