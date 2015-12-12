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


/**
 * 
 * Dear Tom,
 * 	  if you are looking through our command listener to find the secret codes to win you the game
 * then shame on you. Please don't cheat, it ruins the fun of the game. Any grammatical errors still
 * left in the game are purely accidental as we cannot fix every poor grammatical mistake in the game
 * in the short time we have. Also we both question Justin's ability to use proper english.
 * sincerely,
 * Justin and Nico
 */

@SuppressWarnings("unused")
public class CommandListener implements ActionListener {
		//CHARACTER AND MAP\\
	private GameCharacter mainGuy;
	private Map map = new Map();
		//JTEXT STUFF\\
	private JLabel imLabel;
	private ImageIcon roomPic;
	private JTextArea statsList;
	private JTextArea out;
		//CHECKING THE COMMAND ENTERED\\
	private Pattern commandRegex = Pattern.compile("(\\S+)(\\s+)(.+)"); 
	private String commandType = null;
	private String commandValue = null;
		//MOB STUFF\\
	private ArrayList<Mob> mobList;
	private ArrayList<Mob> engagedMob = new ArrayList<Mob>();
		//GAME BOOLEANS(not including  booleans)\\
	private boolean hasStarted = false;	//game will not start unless this boolean is set to true
		//FINAL BOSS VARIABLES\\
	Item masterKey = new Item("Master Key","Unlocks the final door",0,0,true);
	private int secretNumberCount = 0;
	private boolean inBossFight = false;
	private boolean hasAttacked = false;
	private int bossRound = -1;
	private int temBossRound;
	private int questionNum = 1;
	private int right = 0;
	private int wrong = 0;
	private boolean endGame = false; //if endGame is set to true, boss fight is over and final "cutscene" is played
		//CONSTURCTOR\\
	public CommandListener(JTextArea out, JTextArea out2, GameCharacter pc, GameCharacter pc2, JLabel label, JTextArea sList,ArrayList<Mob> m, Map ma){
		this.out = out;
		mainGuy = pc;
		imLabel = label;
		statsList = sList;
		mobList = m;
		map = ma;
	}
	@Override //WHEN COMMAND IS ENTERED...\\
	public void actionPerformed(ActionEvent e) {
		if(mainGuy.getHealth()>0){		//if statment that runs the game. If player dies then you cannot enter any more commands
		Boss win = new Boss("Win","A large monster wearing black armor with gold trim",map.getDaMap().get(20),160,100,25,50,50,masterKey);	
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
		if(!hasStarted && !endGame){
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
					statsList();
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
			 */
		else if(inBossFight && hasStarted && !endGame){
			if(bossRound ==-3){//bossround gets set to -3 upon beating the boss in one of the couple ways
				out.append("congratulations " + mainGuy.getName() + "You have defeated the evil monster. \n");
				out.append("Take what is rightfully yours and move to victory");
				mainGuy.getLocation().unlockExit();
				mainGuy.pickUp(win.getItem());
				inBossFight = false;
				hasStarted = true;
				endGame = true;
			}
			if(bossRound == -1){	//bossRound is set to -1 on first round of boss fight 
				switch(commandType){
				case("yes"):
					out.append("good job young warrior. I will give you the strength to defeat this monster\n");
					bossRound++;
					mainGuy.setMaxHealth(4200);
					mainGuy.setMaxStamina(4200);
					mainGuy.setBaseDamage(142);
					mainGuy.setArmor(70);
					break;
				case("no"):
					out.append("so be it young warrior. May the spirits guide you to victory \n");
					bossRound = bossRound++;
					break;
				default:
					out.append("I am sorry, I do not know what that means. Please accept my help by typing 'yes' or reject my help by typing 'no' \n");
					break;
			}
				statsList();
				out.append("What do you want to do on your turn? \n");
		}//end of if bossround = -1 (fighting has not started)
			else if(bossRound == -2){
						//BELOW ARE THE QUESTIONS TO NEGOTIATION ROUND\\
				switch(commandType){
					case("yes"):
						out.append("very well. I will ask you a series of questions that you must answer. To pass this trial you "
								+ "must answer 6/10 of these questions \n");
						break;
					case("no"):
						break;
					//BELOW ARE THE ANSWERS TO THE NEGOTIATION QUESTIONS\\
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
					case("gandalf the grey"):
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
					case("Tom Kelliher"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n ");
						out.append("On to question " + questionNum + "\n");
						break;
					case("Kelliher"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n ");
						out.append("On to question " + questionNum + "\n");
						break;
					case("Tom"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n ");
						out.append("On to question " + questionNum + "\n");
						break;
					case("42"):
						questionNum++;
						right++;
						out.append("You have answered " + right + "questions right. \n ");
						out.append("On to question " + questionNum + "\n");
						break;
					default:
						wrong ++;
						questionNum++;
						out.append("I'm sorry that is not the right answer. You have " + wrong + "missed questions. Do not let it get to 4 \n");
						out.append("you have " + (10-questionNum) + "questions left \n");
						break;				
				}
				if(questionNum ==0) out.append("");
				else if(questionNum == 1 && wrong != 4) out.append("Mortal, for your first question answer me this. How many mobs were alive at the beggining of the game?");
				else if(questionNum ==2 && wrong != 4 && right<6) out.append("What was my father's name?");
				else if(questionNum ==3 && wrong != 4 && right<6) out.append("True or False? Emacs is the best");
				else if(questionNum ==4 && wrong != 4 && right<6) out.append("What is the Linux kernel’s official mascot?");
				else if(questionNum ==5 && wrong != 4 && right<6) out.append("What is the name of the linux mascot?");
				else if(questionNum ==6 && wrong != 4 && right<6) out.append("IBM's super computer Watson appears on what major TV gameshow?");
				else if(questionNum ==7 && wrong != 4 && right<6) out.append("In the popular series Lord of the Rings, who is the fearless wizard who leads the party to destroy the one ring?");
				else if(questionNum ==8 && wrong != 4 && right<6) out.append("When did the robot known as Tom Kelliher recieve his PHD in computer science from Penn State?");
				else if(questionNum ==9 && wrong != 4 && right<6) out.append("What is the greatest number in the universe?");
				else if(questionNum ==10 && wrong != 4){
					roomPic = new ImageIcon("Images/den.jpg");	
					Image img = roomPic.getImage();
					Image newimg = img.getScaledInstance(230, 310, java.awt.Image.SCALE_SMOOTH);
					roomPic = new ImageIcon(newimg);
					imLabel.setIcon(roomPic);	
					out.append("Who is the subject in the following photo");
				}
				else{
					if(right >= 6){
						out.append("'I have granted you mercy. You have a kind and gentle heart to be able to answer my questions' \n");
						out.append("'Take this key and leave me' \n");
						out.append("Win waves his hand around the room as each door unlocks. after unlocking the doors he reaches into his pocket"
								+ "and pulls out an ornamental looking key. You give him a silent nod as he begins to vanish in front of you. "
								+ "you swear he gives you a wink and a smirk before dissapearing completely \n");
						mainGuy.pickUp(win.getItem());
						inBossFight = false;
						hasStarted = true;
						endGame = true;
					}
					else {
						out.append("You have failed my test...Prepare to die D:< \n");
						bossRound = temBossRound;	//upon failing the question/answer you go back to the normal combat
					}
				}
				statsList();
			}
			
			else{
				if(win.getHealth()>0){	//as long as win's health is greater than 0...
				hasAttacked = false;	//reset hasAttacked
				statsList();
			switch(commandType){
				case("attack"):			//if you attack and win attacks then he will not attack at the end of the turn
					//attackBoss(win);
					int d = mainGuy.getBaseDamage();
					if((d-win.getHealth()) < 0 ){
						d=win.getHealth();
					}
					else {
						d = mainGuy.getBaseDamage();
					}
					System.out.println("base Damage: " + mainGuy.getBaseDamage());
					out.append("You attack Win for " + d + "HP");
					win.minusHealth(d);
					if(win.getHealth() <=0 ){		//if win is dead
						System.out.println("Win has no health and has died");
						bossRound = -3;		//go to special event mentioned above
					}
					else{
						mainGuy.minusHealth(win.getBaseDamage());	//mainGuy gets hit
						out.append(mainGuy.getName() + " gets hit for " + win.getBaseDamage() + "HP");
						hasAttacked = true;
						bossRound++;
					}
					break;
				case("delete"):			//TOTALLY NOT THE SECRET COMMAND TOM. DON'T CHEAT
					if(commandValue.equalsIgnoreCase("system32")){
					out.append("MORTAL HOW DARE YOU....WHO HAS TOLD YOU MY WEAKNESS... \n");
					out.append("The demon falls to his knees and a blackish-red smoke begins to pour off his body. You can hear him mumbling about some sort"
							+ "of lost power. you watch as he looks straight at you and takes his helmet off.\n");
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
					out.append("Type 'no' to continue violent fighting \n");
					temBossRound = bossRound;
					bossRound = -2;
					out.append("Mortal, for your first question answer me this. How many mobs were alive at the beggining of the game?");
					break;
				case("exit"):
					System.exit(0);
					break;
				default:
					out.append("that is not a valid command, please try again \n");
					break;
				}
			if(!hasAttacked)	
				switch(rnGesus(0,30)){
					case 0://heals boss
						win.heal();
						break;
					case 1://insults players mom
						out.append("YOUR MOM IS UGLY \n");
						out.append("you are hurt by his comment. You take one point of damage becuase you're sad /n");
						mainGuy.minusHealth(1);
						break;
					default:
						out.append("win attacks you for" + win.getBaseDamage() + "HP");
						mainGuy.minusHealth(win.getBaseDamage());
						break;
				}
				out.append("What do you want to do on your turn?");
				statsList();
			}
			statsList();
			out.append("Win's Health: " + win.getHealth());
		}
	}			
		else if(endGame){
			/*
			 * 
			 * 
			 * JUSTIN 
			 * 
			 * PUT YOUR CODE HERE
			 * 
			 * DO IT
			 * 
			 * 
			 * OH MY GOD PLEASE CODE ME
			 * 
			 * 
			 * SENPAI PLS NOTICE ME
			 * 
			 * 
			 * THIS IS WHERE YOU WOULD PUT THE FINAL CUTSCENE
			 * 
			 * 
			 * 
			 * YOU CAN MAKE ANOTHER SWITCH STATEMENT FOR COMMANDS THAT ARE NOT THE SAME AS THE NORMAL GAME
			 * 
			 * 
			 * ANY QUESTIONS YOU HAVE PLEASE FEEL FREE TO CONTACT ME
			 * 
			 * 
			 * ALSO IF SOMETHING IS HELLA BROKEN ALSO CONTACT ME AND I'LL DO SOME DEBUGGING
			 * 
			 * 
			 * also make boss much harder because he doesn't have enough health so whatever nethack would use as a boss is what you should change it to
			 * other things you should change
			 * 			-boss location(when he's declared in the variables at the top of the code)
			 * 			-the checking of if player has moved into the boss room (code is in switch statement for 'go' <Direction>)
			 * 			-any other style thing you notice and want to change
			 * 
			 * 
			 * 
			 */
		}
else if(hasStarted && !inBossFight && !endGame){	
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
				if(mainGuy.getLocation().getExits()[0] != null && mainGuy.getLocation().getLockedExits()[0] == null){
					mainGuy.goNorth();
					if(mainGuy.getLocation().equals(map.getDaMap().get(20))){
						bossFight(win);
						inBossFight = true;
					}
				}
				else if(mainGuy.getLocation().getExits()[0] == null && mainGuy.getLocation().getLockedExits()[0] != null) out.append("that door is locked, you cannot pass.");
				else out.append("\n You can not go in that direction." + "\n");			
			
			break;
			case("south"):
				if(mainGuy.getLocation().getExits()[1] != null && mainGuy.getLocation().getLockedExits()[1] == null){
					mainGuy.goSouth();
					if(mainGuy.getLocation().equals(map.getDaMap().get(20))){
						bossFight(win);
						inBossFight = true;
					}
				}
				else if(mainGuy.getLocation().getExits()[1] == null && mainGuy.getLocation().getLockedExits()[1] != null) out.append("that door is locked, you cannot pass.");
				else out.append("\n You can not go in that direction." + "\n");
				break;
			case("east"):
				if(mainGuy.getLocation().getExits()[2] != null && mainGuy.getLocation().getLockedExits()[2] == null){
					mainGuy.goEast();
					if(mainGuy.getLocation().equals(map.getDaMap().get(20))){
						bossFight(win);
						inBossFight = true;
					}
				}
				else if(mainGuy.getLocation().getExits()[2] == null && mainGuy.getLocation().getLockedExits()[2] != null) out.append("that door is locked, you cannot pass.");
				else out.append("\n You can not go in that direction." + "\n");
				break;
			case("west"):
				if(mainGuy.getLocation().getExits()[3] != null && mainGuy.getLocation().getLockedExits()[3] == null) {
					mainGuy.goWest();
					if(mainGuy.getLocation().equals(map.getDaMap().get(20))){
						bossFight(win);
						inBossFight = true;
					}
				}
				else if(mainGuy.getLocation().getExits()[3] == null && mainGuy.getLocation().getLockedExits()[3] != null) out.append("that door is locked, you cannot pass.");
				else out.append("\n You can not go in that direction." + "\n ");
				break;
			case("up"):
				if(mainGuy.getLocation().getExits()[4] != null && mainGuy.getLocation().getLockedExits()[4] == null){
					mainGuy.goUp();
					if(mainGuy.getLocation().equals(map.getDaMap().get(20))){
						bossFight(win);
						inBossFight = true;
					}
				}
				else if(mainGuy.getLocation().getExits()[4] == null && mainGuy.getLocation().getLockedExits()[4] != null) out.append("that door is locked, you cannot pass.");
				else out.append("\n You can not go in that direction." + "\n");
				break;
			case("down"):
				if(mainGuy.getLocation().getExits()[5] != null && mainGuy.getLocation().getLockedExits()[5] == null) {
					mainGuy.goDown();
					if(mainGuy.getLocation().equals(map.getDaMap().get(20))){
						bossFight(win);
						inBossFight = true;
					}
				}
				else if(mainGuy.getLocation().getExits()[5] == null && mainGuy.getLocation().getLockedExits()[5] != null) out.append("that door is locked, you cannot pass.");
				else out.append("\n You can not go in that direction." + "\n");
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
		case("use"):
			if(mainGuy.checkItem(commandValue)){
				if(mainGuy.getLocation().getKeyItem().equals(mainGuy.returnItem(commandValue))){
						if(mainGuy.getLocation().getLockedExits()[0] != null){
							out.append("You have unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[0].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[1] != null){
							out.append("You have unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[1].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[2] != null){
							out.append("You have unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[2].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[3] != null){
							out.append("You have unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[3].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[4] != null){
							out.append("You have unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[4].getName() );
						}
						else if(mainGuy.getLocation().getLockedExits()[5] != null){
							out.append("You have unlocked the exit to the " + mainGuy.getLocation().getLockedExits()[5].getName() );
						}	
					mainGuy.plusOne();
					mainGuy.addXP(20);
					mainGuy.getLocation().unlockExit();
					if (commandValue.toLowerCase().equals("frisbee") && mainGuy.getLocation().getName().toLowerCase().equals("outside")) {
						out.append("Gnarly! no one catches it though, it flies into the sunset");
						mainGuy.setScore(420);
					}
				}
			}
			else{
				out.append("You don't have that item dummy");
			}
			break;
		case("exit"):
			System.exit(0);
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
		
		statsList();
		out.selectAll();//makes console auto scroll to the bottom when entering a new command
	
				//scales the room photo to the same size
		roomPic = mainGuy.getLocation().getImage();	
		Image img = roomPic.getImage();
		Image newimg = img.getScaledInstance(230, 310, java.awt.Image.SCALE_SMOOTH);
		roomPic = new ImageIcon(newimg);
		imLabel.setIcon(roomPic);	
		if(!inBossFight){
		out.append("\n \n"+(mainGuy.getLocation()).getDescription() +"\n");
		out.append("The room contains the following items: ");
		out.append((mainGuy.getLocation()).getInventory().toString() + "\n");
		out.append("Exits \n ---------------\n" + mainGuy.getLocation().listExits() + "\n\n\n");
		out.append("Locked Exits \n ---------------\n" + mainGuy.getLocation().listLockedExits() + "\n\n\n");
		}
	}//end of else if			
	}//if player has no health do the code below vvv
		checkHealth();
}//end of action performed
	
	
	
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
					out.append("Main guy hits " + mobList.get(i).getName()+ " For: " + damage + "\n");
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
		+ "puff of black smoke. Evil laughter fills the room as the lights darken and you feel your body fill with an odd sense of power. \n "
		+ "'Don't worry my child' you hear from behind you.\n "
		+ "You quickly turn around and look around but see nothing. "
		+ "\n 'I am known as the spirit Lin, son of the holy god Ux-ix. I can give you the strength to defeat the demon known as Win, son of the demon Dows. Do you accept my guidance?' "
		+ "\n\n Type 'yes' to accept. Type 'no' for a most timely death\n");
	}
	public void statsList(){
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
	}
	public void checkHealth(){ //method that exits the program upon dying
		if(mainGuy.getHealth() <= 0){
			int score = mainGuy.getScore();
			out.append("GAME OVER \n");
			out.append("your score was " + score);
					//DISPLAYS HOW YOUR SCORE RATES\\
			if(score >= 0 && score<10) out.append("You could have gotten more points just picking items up...good job \n");
			else if(score >= 10 && score < 20) out.append("you rate slightly above a pet rock \n");
			else if(score >= 20 && score < 30) out.append("you're smarter than the average high schooler \n");
			else if(score >= 30 && score < 40) out.append("You probably have a college degree \n");
			else if(score >= 40 && score < 50) out.append("How many PHDs do you have? 1 or 7? \n");
			else out.append("You are the best of the best");
			}	
	}
	
	public static int rnGesus(int max,int min ){	 //random number generator to help randomly place mobs
		return (int)(Math.random() * (max - min) + min); 
		}
}