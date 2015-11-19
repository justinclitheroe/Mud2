package newMud;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CommandListener implements ActionListener {

	private JTextArea out;
	private GameCharacter mainGuy;
	private JLabel imLabel;
	private ImageIcon roomPic;
	
	public CommandListener(JTextArea out, GameCharacter pc, JLabel label){
		this.out = out;
		mainGuy = pc;
		imLabel = label;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean running = true;
		
		
		while(running){
		JTextField source = (JTextField) e.getSource();
		String s = source.getText();
		out.append(s + "\n");
		source.setText("");
		
		
		String command = null;
		String command2 = null;
		
		
		if (s.contains(" ")){ 
			command = s.substring(0,s.indexOf(' '));						
			command2 = s.substring(s.indexOf(' ')+1,s.length());	
			}
		else{ 
			command = s;
		}
		
			
		if (command.toLowerCase().equals("go")) {
			if (command2.toLowerCase().equals("north") && (mainGuy.getLocation().getExits()[0] != null)) 
				mainGuy.goNorth();
			else if (command2.toLowerCase().equals("south") && (mainGuy.getLocation().getExits()[1] != null)) 
				mainGuy.goSouth();
			
			else if (command2.toLowerCase().equals("east") && (mainGuy.getLocation().getExits()[2] != null)) 
				mainGuy.goEast();
			
			else if (command2.toLowerCase().equals("west") && (mainGuy.getLocation().getExits()[3] != null)) 
				mainGuy.goWest();
			
			else if (command2.toLowerCase().equals("up") && (mainGuy.getLocation().getExits()[4] != null)) 
				mainGuy.goUp();

			else if (command2.toLowerCase().equals("down") && (mainGuy.getLocation().getExits()[5] != null)) 
				mainGuy.goDown();
			else
				out.append("Can't go that way, sorry" + "\n");
		}
		
		
		else if (command.toLowerCase().equals("get")) {
			if ((mainGuy.getLocation()).checkItem(command2)  ){					    //checks if item is in the room item arrayList
				mainGuy.pickUp((mainGuy.getLocation()).returnItem(command2));
				out.append("got it" + "\n");										//adds item to player's inventory
		  			}
			else 
				out.append("not an item in the room" + "\n");
			}
		
		else if (command.equals("drop")){	
			if (mainGuy.checkItem(command2)){						
				mainGuy.drop(mainGuy.returnItem(command2));
				}
			}
		else if(command.equals("exit")){
			out.append("Goodbye \n");
			running = false;
		}
		else {out.append("SPEAK UP SONNY I CANT HEAR YOU" + "\n");}
		
		
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
	}
}
