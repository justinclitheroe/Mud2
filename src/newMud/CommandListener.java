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
	private Pattern commandRegex = Pattern.compile("(\\S+)(\\s+)(.+)");				//creates a pattern that command entered will be checked against
	private String commandType = null;		//command entered
	private String commandValue = null;		//variable following command
	private ArrayList<Mob> mobList;
	
	public CommandListener(JTextArea out, GameCharacter pc, JLabel label, JTextArea sList,ArrayList<Mob>m){
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
		Matcher commandMatcher = commandRegex.matcher(s);		//checks the command entered and matches it with pattern given above
		if(commandMatcher.matches()){			//if command type gets matched with pattern...
			commandType = commandMatcher.group(1);
			commandValue = commandMatcher.group(3);
		}
		else commandType = s;	//otherwise set commandType to the whole command entered
		
		switch(commandType){
		case("start"):
			out.append("Enjoy your stay.");
			break;
		case("exit"):
			out.append("Goodbye \n");
			System.exit(0);		//close the all java stuff
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
		case("get"):	//command for getting only the item you want to pick up
			if((mainGuy.getLocation()).checkItem(commandValue)){
				mainGuy.pickUp((mainGuy.getLocation()).returnItem(commandValue));
				out.append("Got it!" + "\n");
			}
			else out.append("That item is not in the room." + "\n");
			break;
		case("getall"):	//command for getting every item in the room
			for(int i =0;i < mainGuy.getLocation().getItemObject().size(); i++){
				mainGuy.pickUp(mainGuy.getLocation().getItemObject().get(i));
				}
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
			 * 
			 */
			if(sameRoom()){
				
			}
			else{
				
			}
			break;
		default:
			out.append("That is not a valid command." + "\n");
			break;
		}
		
			//creates a string of Stats for the player )
		String list = mainGuy.getName() + " Stats \n";
		list = list +  "Score: " + mainGuy.getScore() + " \n";
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
		out.append("You are holding the following items: ");	  		  	 
		out.append(mainGuy.getInventory().toString() + "\n");
		
	}
	
	
	public boolean sameRoom(){
		for(int i =0 ; i< mobList.size();i++){
			if(mainGuy.getLocation()==mobList.get(i).getLocation()){
				return true;
			}
		}
		return false;
	}
		
}
