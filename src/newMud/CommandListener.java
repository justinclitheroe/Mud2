package newMud;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("unused")
public class CommandListener implements ActionListener {

	private JTextArea out;
	private JTextArea secondaryOut;
	private GameCharacter mainGuy;
	private GameCharacter secondaryGuy;
	private JLabel imLabel;
	private ImageIcon roomPic;
	private JTextArea statsList;
	private Pattern commandRegex = Pattern.compile("(\\S+)(\\s+)(.+)"); 
	private String commandType = null;
	private String commandValue = null;
	private ArrayList<Mob> mobList;
	private ArrayList<Mob> engagedMob = new ArrayList<Mob>();
	
	private boolean hasStarted = false;
	
	
	
	//creating final boss and the master key used to unlock the final room to win the game
	Item masterKey = new Item("Master Key","Unlocks the final door",0,0,true);
	private ArrayList<Item> mobLoot = new ArrayList<Item>(Arrays.asList(masterKey));
	private Boss win = new Boss("Win","A large monster wearing black armor with gold trim",null,160,100,25,50,50,null);	
	private int secretNumberCount = 0;
	
	private boolean inBossFight = false;
	private int bossRound = -1;
	private int temBossRound;
	private int questionNum = 1;
	private int right = 0;
	private int wrong = 0;
	
	public CommandListener(JTextArea out, JTextArea observe, GameCharacter pc, GameCharacter pc2, JLabel label, JTextArea sList,ArrayList<Mob> m){
		this.out = out;
		mainGuy = pc;
		imLabel = label;
		statsList = sList;
		mobList = m;
		secondaryGuy = pc2;
		secondaryOut = observe;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField source = (JTextField) e.getSource();
		String s = source.getText().toLowerCase();
		out.append(s + "\n");
		source.setText("");
		Matcher commandMatcher = commandRegex.matcher(s);
		if(commandMatcher.matches()){	//if command type gets matched with pattern...
			commandType = commandMatcher.group(1);
			commandValue = commandMatcher.group(3);
		}
		else commandType = s;	//otherwise set commandType to the whole command entered
	
		
		if(!hasStarted){
			switch(commandType){
				case("start"):
					out.append("Enjoy your stay.");
					hasStarted = true;
					for(int u = 0; u <mobList.size(); u++){
						mobList.get(u).upd();
					}
					/*
					 * This is a copy and paste of the code at the bottom of this method. See below
					 * for a commented version and neater looking version of this
					 */
					String list = mainGuy.getName() + " Stats \n"+ "You are in the " + mainGuy.getLocation().getName() + "\n"+  "Score: "+ mainGuy.getScore()+ "\n" + "Level: " + mainGuy.getLevel();
					list = list + "		XP: "+ mainGuy.getXP()+ "/100 \n";
					list = list+ "Health: " + mainGuy.getHealth() +"/" + mainGuy.getMaxHealth()+"		Stamina: " + mainGuy.getStamina() +"/"+ mainGuy.getMaxStamina() + "\n" + "Armor class: " + mainGuy.getArmor() + "	Base Damage: " + mainGuy.getBaseDamage() +"\n";
					String inventory = "Inventory: \n";
					for(int i = 0; i<mainGuy.getInventory().size();i++){
						inventory = inventory + mainGuy.getInventory().get(i).getName() +"  ";
						}
					list = list + inventory;
					statsList.setText(list);	
					out.selectAll();
					roomPic = mainGuy.getLocation().getImage();	
					Image img = roomPic.getImage();
					Image newimg = img.getScaledInstance(230, 310, java.awt.Image.SCALE_SMOOTH);
					roomPic = new ImageIcon(newimg);
					imLabel.setIcon(roomPic);
					out.append("\n \n"+(mainGuy.getLocation()).getDescription() +"\n");
					out.append("The room contains the following items: ");
					out.append(mainGuy.getLocation().getInventory().toString() + "\n");
					out.append("Exits \n ---------------\n" + mainGuy.getLocation().listExits() + "\n\n\n");
					out.append("Locked Exits \n ---------------\n" + mainGuy.getLocation().listLockedExits() + "\n\n\n");
					break;
			default:
			out.append("Still have to type start \n");
				break;
				}
		} 
		
			/*
			 * 			BOSS FIGHT 
			 * during a boss fight, player goes into a turn based strategy game rather than a dungeon crawler. 
			 * Depending on how many rounds have gone by during the boss fight various things will happen. 
			 * Once player defeats the boss player will get to pickup mob loot
			 *
			 * 
			 */
		else if(inBossFight){
			if(bossRound == -1){
				switch(commandValue){
				case("yes"):
					out.append("good job young warrior. I will give you the strength to defeat this monster");
					bossRound++;
					break;
				
				case("no"):
					out.append("so be it young warrior. May the spirits guide you to victory");
					bossRound++;
					break;
				default:
					out.append("I am sorry, I do not know what that means. Please accept my help by typing 'yes' or reject my help by typing 'no'");
					break;
			}
		}//end of if bossround = -1 (fighting has not started)
			
			
			
			/*
			 * 
			 * 
			 * THIS IS WHERE YOU WERE WORKING LAST
			 * CONTINUE CODING HERE
			 * 
			 * 		  vvv
			 * 		  vvv
			 * 		  vvv	
			 * 		vvvvvvv
			 * 		 vvvvv
			 * 		  vvv
			 * 		   v
			 *
			 *
			 */
			else if(bossRound == -1){
				
						//BELOW ARE THE QUESTIONS TO NEGOTIATION ROUND\\
				if(questionNum == 1 && wrong != 4) out.append("Mortal, for your first question answer me this. How many mobs were alive at the beggining of the game?");
				else if(questionNum ==2 && wrong != 4 && right<6) out.append("What was my father's name?");
				else if(questionNum ==3 && wrong != 4 && right<6) out.append("True or False? Emacs is the best");
				else if(questionNum ==4 && wrong != 4 && right<6) out.append("What is the Linux kernel’s official mascot?");
				else if(questionNum ==5 && wrong != 4 && right<6) out.append("What is the name of the linux mascot?");
				else if(questionNum ==6 && wrong != 4 && right<6) out.append("IBM's super computer Watson appears on what major TV gameshow?");
				else if(questionNum ==7 && wrong != 4 && right<6) out.append("In the popular series Lord of the Rings, who is the fearless wizard who leads the party to destroy the one ring?");
				else if(questionNum ==8 && wrong != 4 && right<6) out.append("When did the robot known as Tom Kelliher recieve his PHD in computer science from Penn State?");
				else if(questionNum ==9 && wrong != 4 && right<6) out.append("");
				else if(questionNum ==10 && wrong != 4){
					out.append("");
				}
				
				switch(commandType){
					case("yes"):
						out.append("very well. I will ask you a series of questions that you must answer. To pass this trial you "
								+ "must answer 6/10 of these questions");
						break;
					case("no"):
						break;
					//BELOW ARE THE ANWERS TO THE NEGOTIATION QUESTIONS\\
					case("5"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n");
						out.append("On to question " + questionNum + "\n");
						break;
					case("Dows"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right.\n ");
						out.append("On to question " + questionNum + "\n");
						break;
					case("false"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right.\n");
						out.append("On to question " + questionNum + "\n");
						break;
					case("penguin"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right.\n ");
						out.append("On to question " + questionNum + "\n");
						break;
					case("tux"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n ");
						out.append("On to question " + questionNum + "\n");
						break;
					case("jeopardy"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n ");
						out.append("On to question " + questionNum + "\n");
						break;
					case("gandalf"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n ");
						out.append("On to question " + questionNum + "\n");
						break;	
					case("1993"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n ");
						out.append("On to question " + questionNum + "\n");
						break;
					default:
						wrong ++;
						out.append("I'm sorry that is not the right answer. You have " + wrong + "missed questions. Do not let it get to 4");
						out.append("you have " + (10-questionNum) + "questions left \n");
						break;				
				}
			}
			
			else{
				switch(commandType){
				case("attack"):
					attackBoss(win);
					bossRound++;
					break;
				case("delete"):
					if(commandValue.equalsIgnoreCase("system32")){
					out.append("MORTAL HOW DARE YOU....WHO HAS TOLD YOU MY WEAKNESS... \n");
					out.append("The demon falls to his knees and a blackish-red smoke begins to pour off his body. You can hear him mumbling about some sort"
							+ "of lost power. you watch as he looks straight at you and takes his helmet off.");
					for(int i = 0; i<win.getMobLoot().size();i++){
						mainGuy.pickUp(win.getMobLoot().get(i));
						out.append("mainGuy picked up a " + win.getMobLoot().get(i).getName());
					}
					}
					else out.append("you cannot delete the " + commandValue);
					break;
				case("help"):
					out.append("Boss fights work differently than normal play. You cannot escape a fight and the boss will actively attack you "
							+ "even if you do not attack it. The following are commands you can enter... \n");
					out.append("attack => attacks the boss \n");
					out.append("help => brings up the command list for the boss fight");
					out.append("exit => eventhough you've made it this far you can still exit the game");
					out.append("<?????> this is a secret command. Upon entering this command boss will instantly die \n");
					break;
				case("negotiate"):
					out.append("Do you wish to beg for your forgiveness mortal? \n");
					out.append("Type 'yes' to beg for mercy \n");
					out.append("Type 'no' to continue violent fighting");
					temBossRound = bossRound;
					bossRound = -1;
				}
			}
	}		
		
		
		
else if(hasStarted){	
	switch(commandType){
		case("help"):
			out.append("\n\nThank you for contacting the MUD help desk. These are the following possible commands... \n");
			out.append("go <direction> (north,south,east,west,up,down) => moves the player to any valid room connected to the player's current location \n");
			out.append("gives a description of what the room looks like. Not sure why you would need this since it reprints the description after every command...");
			out.append("get <item_name>  => adds item to player's inventory \n");
			out.append("drop <item_name> => removes item from players inventory \n");
			out.append("attack <mob_name> => attacks the mob with the given name assuming you are in the same room\n");
			out.append("exit => exits the game \n");
			out.append("help=> displays a list of given commands \n");
			out.append("<????> this is a secret command. It involves the greatest number in history \n\n");
			break;
		case("look"):
			out.append(mainGuy.getLocation().getDescription());
			break;
		case("42"):
			switch(secretNumberCount){
				case(0):
					mainGuy.addScore(1000000);
					out.append("\n\nCONGRATULATIONS! YOU WON A LOT OF POINTS. DO YOU FEEL ALIVE? \n\n");
					secretNumberCount++;
					break;
				case(1):
					out.append("okay looks like you really like points. I'm not giving you a alot of points but have a more");
					mainGuy.addScore(100);
					secretNumberCount++;
					break;
				case(2):
					out.append("Okay seriously....stop taking all the points. Have one more point, but this is the last one. Please don't ask again");
					mainGuy.plusOne();
					secretNumberCount++;
					break;
				case(42):
					out.append("Persistant aren't you? I'll award you with some points becuase you clearly had some dedication");
					mainGuy.setScore(42);
					secretNumberCount++;
					break;
				default:
					out.append("You have put this command in" + secretNumberCount + " times. Stop being so god damn greedy. I warned you");
					mainGuy.setScore(-100000);
					secretNumberCount++;
					break;
				}
			break;
		case("go"):
			switch(commandValue){
			case("north"):
				if(mainGuy.getLocation().getExits()[0] != null && mainGuy.getLocation().getLockedExits()[0] == null) mainGuy.goNorth();
				else if(mainGuy.getLocation().getExits()[0] == null && mainGuy.getLocation().getLockedExits()[0] != null) out.append("that door is locked, you cannot pass.");
				else {
					out.append("\n You can not go in that direction." + "\n");
					secondaryOut.append(mainGuy.getName() + " walks into a wall");
				}
				break;
			case("south"):
				if(mainGuy.getLocation().getExits()[1] != null && mainGuy.getLocation().getLockedExits()[1] == null) mainGuy.goSouth();
				else if(mainGuy.getLocation().getExits()[1] == null && mainGuy.getLocation().getLockedExits()[1] != null) out.append("that door is locked, you cannot pass.");
				else {
					out.append("\n You can not go in that direction." + "\n");
					secondaryOut.append(mainGuy.getName() + " walks into a wall");
				}
				break;
			case("east"):
				if(mainGuy.getLocation().getExits()[2] != null && mainGuy.getLocation().getLockedExits()[2] == null) mainGuy.goEast();
				else if(mainGuy.getLocation().getExits()[2] == null && mainGuy.getLocation().getLockedExits()[2] != null) out.append("that door is locked, you cannot pass.");
				else {
					out.append("\n You can not go in that direction." + "\n");
					secondaryOut.append(mainGuy.getName() + " walks into a wall");
				}
				break;
			case("west"):
				if(mainGuy.getLocation().getExits()[3] != null && mainGuy.getLocation().getLockedExits()[3] == null) mainGuy.goWest();
				else if(mainGuy.getLocation().getExits()[3] == null && mainGuy.getLocation().getLockedExits()[3] != null) out.append("that door is locked, you cannot pass.");
				else {
					out.append("\n You can not go in that direction." + "\n");
					secondaryOut.append(mainGuy.getName() + " walks into a wall");
				}
				break;
			case("up"):
				if(mainGuy.getLocation().getExits()[4] != null && mainGuy.getLocation().getLockedExits()[4] == null) mainGuy.goUp();
				else if(mainGuy.getLocation().getExits()[4] == null && mainGuy.getLocation().getLockedExits()[4] != null) out.append("that door is locked, you cannot pass.");
				else {
					out.append("\n You can not go in that direction." + "\n");
					secondaryOut.append(mainGuy.getName() + " walks into a wall");
				}
				break;
			case("down"):
				if(mainGuy.getLocation().getExits()[5] != null && mainGuy.getLocation().getLockedExits()[5] == null) mainGuy.goDown();
				else if(mainGuy.getLocation().getExits()[5] == null && mainGuy.getLocation().getLockedExits()[5] != null) out.append("that door is locked, you cannot pass.");
				else {
					out.append("\n You can not go in that direction." + "\n");
					secondaryOut.append(mainGuy.getName() + " walks into a wall");
				}
				break;
			default:
				out.append("\n That is not a valid direction." + "\n");
			}
			break;
		case("get"):
			if(mainGuy.getLocation().checkItem(commandValue)){
				mainGuy.plusOne();
				mainGuy.pickUp(mainGuy.getLocation().returnItem(commandValue));
				out.append("Got it!" + "\n");
				secondaryOut.append(mainGuy.getName() + " got the " + commandValue + " shiiiit");
			}
			else out.append("That item is not in the room." + "\n");
			break;
		case("drop"):
			if (mainGuy.checkItem(commandValue)){						
				mainGuy.drop(mainGuy.returnItem(commandValue));
				out.append("Tossed " + commandValue + ", hope you dont need it later." + "\n");
				secondaryOut.append(mainGuy.getName() + " dropped " + commandValue + " Score!");
			}
			else out.append("You can't drop what you dont have." + "\n");
			break;
		case("use"):
			System.out.println("");
			if(mainGuy.checkItem(commandValue)){
				if(mainGuy.getLocation().getKeyItem().equals(mainGuy.returnItem(commandValue))){
						if(mainGuy.getLocation().getLockedExits()[0] != null){
							bothOut(mainGuy.getName() +" unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[0].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[1] != null){
							bothOut(mainGuy.getName() +" unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[1].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[2] != null){
							bothOut(mainGuy.getName() +" unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[2].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[3] != null){
							bothOut(mainGuy.getName() +" unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[3].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[4] != null){
							bothOut(mainGuy.getName() +" unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[4].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[5] != null){
							bothOut(mainGuy.getName() +" unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[5].getName() );
						}	
					mainGuy.plusOne();
					mainGuy.addXP(20);
					mainGuy.getLocation().unlockExit();
				}
			}
			else{
				out.append("You don't have that item dummy");
			}
			break;
		case("attack"):
			if(sameRoom()){		//if mobs are in the same room as player
					for(int i = 0; i<engagedMob.size();i++){//for each mob in the engagedMobs list					
						if (engagedMob.get(i).getName().toLowerCase().equals(commandValue.toLowerCase())){	//if the name entered in the command is the same name as in the array
							if(!engagedMob.get(i).isDead()){	//if the mob's health is >0
								engagedMob.get(i).engage();	//engage mob(stops mob from moving)
								out.append(engagedMob.get(i).getName() + " has been put in combat \n");							
								attack(commandValue); //attacks mob using name in the entered command
								if(engagedMob.get(i).getHealth() <=0){		//if mob is dead remove the mob from engaged mob
									out.append(engagedMob.get(i).getDeathMessage() + "\n");
									engagedMob.get(i).makeDead();
									engagedMob.remove(i);
									for(int j = 0; j< mobList.size();j++){		//then delete the mob from the actual mobList						
										if(mobList.get(j).getName().equalsIgnoreCase(commandValue)){
											mobList.get(j).makeDead();
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
				secondaryOut.append("That was dumb, your counterpart seems to have punched themself in the face");
				mainGuy.minusHealth(1);
			}
			break; 
		default:
			out.append("That is not a valid command." + "\n");
			break;
		}//END OF SWITCH STATEMENT	
	
	
	for(int u = 0; u <mobList.size(); u++){
		mobList.get(u).upd();
	}
		//creates a string of Stats for the player )
		String list = mainGuy.getName() + " Stats \n";
		list = list + "You are in the " + mainGuy.getLocation().getName() + "\n";
		list = list + "Score: " + mainGuy.getScore() + " \n";
		list = list + "Level: " + mainGuy.getLevel();
		list = list + "		XP: "+ mainGuy.getXP()+ "/100 \n";
		list = list + "Health: " + mainGuy.getHealth() +"/" + mainGuy.getMaxHealth();
		list = list + "		Stamina: " + mainGuy.getStamina() +"/"+ mainGuy.getMaxStamina() + "\n";
		list = list + "Armor class: " + mainGuy.getArmor();
		list = list + "	Base Damage: " + mainGuy.getBaseDamage() +"\n";
		
		String inventory = "Inventory: \n";
		for(int b = 0; b< mainGuy.getInventory().size();b++){
			inventory = inventory + mainGuy.getInventory().get(b).getName() + "  ";
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
		out.append((mainGuy.getLocation()).getInventory().toString() + "\n");
		out.append("Exits \n ---------------\n" + mainGuy.getLocation().listExits() + "\n\n\n");
		out.append("Locked Exits \n ---------------\n" + mainGuy.getLocation().listLockedExits() + "\n\n\n");
		}//end of else if	
		
		
		
}//end of actionPreformed()
	
	public boolean sameRoom(){	//checks to see if any mobs are in the room and if they are add them to an arrayList of mobs engaged in combat.
		int count = 0;	
		for(int i =0 ; i< mobList.size();i++){
			if(mainGuy.getLocation() == mobList.get(i).getLocation()){
				engagedMob.add(count, mobList.get(i));
				count++;
			}
		}
		return !engagedMob.isEmpty();
	}//end of sameRoom method
	
	/*
	 * 				Attack Methods
	 * 					Attack
	 *------------------------------
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
	 * 
	 * 					Boss Fight
	 * -------------------------
	 * 1) Locks all the exits so player cannot escape
	 * 2) engage in friendly conversation with player
	 * 3) Attack player anyways.
	 * 
	 * 
	 */
	public void attack(String m){		 
		for (int i = 0; i < mobList.size(); i++) {
			if(m.equals(mobList.get(i).getName().toLowerCase())) {		
				int damage;
				if (mobList.get(i).getLocation().equals(mainGuy.getLocation())){
					damage = mainGuy.damage(mobList.get(i));
					bothOut("Main guy hits " + mobList.get(i).getName()+ " For: " + damage + "\n");
					mobList.get(i).minusHealth(damage);
					mobList.get(i).upd();
					int mobDamage = mobList.get(i).getBaseDamage();
					out.append(mobList.get(i).getAttackMessage() + "\n");
					out.append(mobList.get(i).getName() + " attacks you for " + mobDamage + " hp \n");
					mainGuy.minusHealth(mobDamage);
					if(mobList.get(i).getHealth() <=0){
						out.append(mobList.get(i).getName() + " got #REKT and is # DED \n");;
						mainGuy.addScore(10);
						mainGuy.addXP(40);
						mainGuy.gitGud();
						mobList.get(i).isDead();
						mobList.remove(i);	
					}//end of if (mob has no health)
				}//end of if (player location = mob location)
			}//end of if (name of mob = name of mob in arrayList)
		}//end of i for loop
	}
	
	public void bossFight(Boss b){
		mainGuy.getLocation().lockAll();
		out.append("You walk into the room and see a large entity in a suit of black armor with a nice golden trim.\n 'Hello '" + mainGuy.getName()
		+ ". You've traveled a long way to get here...'\n He turns around and looks down at you. His face is covered by his helmet so you can't get "
		+ "a read on him at all. \n 'I congratulate you making this far, but there is one slight problem my friend.'\n You stare at him perplexed as to "
		+ "what the hell he's talking about. You glance around the room scanning for any clues as to what will come. It is at this moment you realize "
		+ "the floor has bones scattered everywhere. Your palms get sweaty as you look back at the giant of a man before you. In the moments you looked away he "
		+ "silently moved behind you breathing on your neck. \n 'This is the place of judgement. Fight me and gain entrance to the chamber of worth.' "
		+ "\n He swings his arm around to all the exits, his hand seems to glow as you hear the click of each door locking. His physical form behind you dissolves into a "
		+ "puff of black smoke. Evil laughter fills the room as the lights darken and you feel your body fill with an odd sense of power. \n 'Don't worry my child'\n "
		+ "You turn around and look around but see nothing. \n 'I am known as the spirit Lin, son of the holy god Ux-ix. I can give you the strength to defeat the demon known as "
		+ " Win, son of the demon Dows. Do you accept my guidance? Type 'yes' to accept. Type 'no' for a most timely death");
		hasStarted = false; //sets has started to false so special command events can happen. 
	}
	public void attackBoss(Boss m){		 
				int damage;
					damage = mainGuy.damage(m);
					out.append("Main guy hits " + m.getName()+ " For: " + damage + "\n");
					m.minusHealth(damage);
					int mobDamage = m.getBaseDamage();
					out.append(m.getAttackMessage() + "\n");
					out.append(m.getName() + " attacks you for " + mobDamage + " hp \n");
					mainGuy.minusHealth(mobDamage);
	}
	public void bothOut(String str) {
		if (mainGuy.getLocation().equals(secondaryGuy.getLocation())) {
			out.append(str);
			secondaryOut.append(str);
		}
		else
			out.append(str);
	}
}