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
	private ArrayList<Mob> engagedMob;
	
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
			out.append("Thank you for contacting the MUD help desk. These are the following possible commands... \n");
			out.append("go <direction> (north,south,east,west,up,down). Only works for valid exits \n");
			out.append("get <item_name>  \n");
			out.append("drop <item_name>\n");
			out.append("attack \n");
			out.append("help \n");
			break;
		case("start"):
			out.append("Enjoy your stay.");
			break;
		case("go"):
			switch(commandValue){
			case("north"):
				if(mainGuy.getLocation().getExits()[0] != null) mainGuy.goNorth();
				else out.append("You can not go in that direction." + "\n");
				break;
			case("south"):
				if(mainGuy.getLocation().getExits()[1] != null) mainGuy.goSouth();
				else out.append("You can not go in that direction." + "\n");
				break;
			case("east"):
				if(mainGuy.getLocation().getExits()[2] != null) mainGuy.goEast();
				else out.append("You can not go in that direction." + "\n");
				break;
			case("west"):
				if(mainGuy.getLocation().getExits()[3] != null) mainGuy.goWest();
				else out.append("You can not go in that direction." + "\n");
				break;
			case("up"):
				if(mainGuy.getLocation().getExits()[4] != null) mainGuy.goUp();
				else out.append("You can not go in that direction." + "\n");
				break;
			case("down"):
				if(mainGuy.getLocation().getExits()[5] != null) mainGuy.goDown();
				else out.append("You can not go in that direction." + "\n");
				break;
			default:
				out.append("That is not a valid direction." + "\n");
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
			/*
			 * 
			 * PUT STUFF HERE NICO
			 * IT IS REAL IMPORTANT
			 * DON'T FORGET THIS IS WHERE YOU WERE CODING STUFF
			 * LIKE FOR FUCKS SAKE DON'T FORGET
			 * 
			 */
			 
			if(sameRoom()){
				for(int i = 0; i<engagedMob.size();i++){
				engagedMob.get(i).engage();
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
		list = list + "Health: " + mainGuy.getHealth() +"/" + mainGuy.getMaxHealth() + "\n";
		list = list + "Stamina: " + mainGuy.getStamina() +"/"+ mainGuy.getMaxStamina() + "\n";
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
		
		
		out.append((mainGuy.getLocation()).getDescription() +"\n");
		out.append("The room contains the following items: ");
		out.append(mainGuy.getLocation().getInventory().toString() + "\n");
		out.append(mainGuy.getLocation().listExits() + "\n");
		
	}//end of actionPreformed()
	
	public boolean sameRoom(){	//checks to see if any mobs are in the room and if they are add them to an arrayList of mobs engaged in combat.
		for(int i =0 ; i< mobList.size();i++){
			if(mainGuy.getLocation()==mobList.get(i).getLocation()){
				engagedMob.add(mobList.get(i));
			}
		}
		if(!engagedMob.isEmpty()){	//if the array list of mobs in the room is not empty return true 
			return true;
		}
		else{
			return false;	//else return false
		}
	}
		
	
	public void attack(){
		while(mainGuy.getHealth()>=0){						//while player's health is greater than 0...
			for(int i = 0; i < engagedMob.size() ; i++){		//for each mob player is engaged in...
				while(engagedMob.get(i).getHealth() > 0){			//while the mobs health is above 0...
						out.append(engagedMob.get(i).getName() + "got hit for" + engagedMob.get(i).intGetKneed(5, 0) + "hit points"); //player attacks mob
						out.append("You got hit for " + mainGuy.intGetKneed(5, 0) + " hit points");									  //mob then attacks player
				}
				out.append("You have killed " + engagedMob.get(i).getName());
				engagedMob.get(i).makeDead();
				engagedMob.remove(i);
				/*
				 * YOU NEED TO DO SOMETHING TO REMOVE THE MOB COMPLETELY
				 */
			}
		}
	}
	
}