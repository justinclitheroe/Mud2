package newMud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserInterface {
	
	private JTextArea textArea = new JTextArea(100, 100);
	private JTextArea consoleOut = new JTextArea(100, 100);
	
	
	public UserInterface(GameCharacter pc,ArrayList<Mob> m) throws InterruptedException{
	
		JFrame window = new JFrame();
		window.setSize(800, 800);
		window.setLayout(new BorderLayout());
		window.setTitle("Nico'a House: An adventure for Glory");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());		
		
		
		//Displays the image at the top
		JLabel pictureLabel = new JLabel("");
		panel.add(pictureLabel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.add(pictureLabel, BorderLayout.WEST);
		window.add(panel, BorderLayout.EAST);

		//holds the enter a command textbox
		textArea = new JTextArea(2,50);
		textArea.setEditable(false);
		JScrollPane display = new JScrollPane(textArea);
		JLabel label = new JLabel("What will you do?");
		display.setAutoscrolls(true);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(display, BorderLayout.CENTER);
		panel.add(label, BorderLayout.WEST);
		window.add(panel, BorderLayout.CENTER);
		
		
		//field to print inventory and other stats to be added later
		JTextArea stats = new JTextArea(1,1);
		stats.setEditable(false);
		stats.setLineWrap(true);
		stats.setLineWrap(true);
		stats.append(pc.getName() + " Stats \n" + "Score: " + pc.getScore() + " \nInventory: \n" + "Health: " + pc.getHealth() +"/" + pc.getMaxHealth() + "\n"+"Stamina: " + pc.getStamina() +"/"+ pc.getMaxStamina() + "\n");
		stats.setBorder(BorderFactory.createMatteBorder( 2, 2, 2, 2, Color.blue));
		JLabel label3 = new JLabel("");
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.add(stats,BorderLayout.NORTH);
		panel3.add(label3, BorderLayout.EAST);
		window.add(panel3, BorderLayout.NORTH);

		
		
		
		//displays the console (output of entering commands)
		JScrollPane display2 = new JScrollPane(textArea);		
		Dimension d = new Dimension(800,500);	
		JLabel label2 = new JLabel("History of actions");
		consoleOut.setEditable(false);
		consoleOut.setLineWrap(true);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setPreferredSize(d);
		panel2.add(display2, BorderLayout.CENTER);
		panel2.add(label2, BorderLayout.WEST);
		window.add(panel2, BorderLayout.CENTER);
		
		JTextField input = new JTextField();
		input.addActionListener(new CommandListener(textArea,pc,pictureLabel,stats,m));
		panel.add(input, BorderLayout.CENTER);
		window.add(panel, BorderLayout.SOUTH);
		
		// Finish preparing the window and display it.
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		ImageIcon bootPic = new ImageIcon("Images/nico.jpg");
		
		Image img = bootPic.getImage();
		Image newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		bootPic = new ImageIcon(newimg);
	
		pictureLabel.setIcon(bootPic);
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textArea.append("You awake from your slumber only to find yourself in a strange house labled 7920. You rub your head and feel a slight bump. \n");
		textArea.append("It's at this time that you start to feel a slight headache. little by little your memory starts to come back. You remeber you stupid friends \n");
		textArea.append("telling you to sign up for some stupid tour of a house. Little did they know it would turn into the adventure of a lifetime");
		
		textArea.append("\n When you are ready to begin, please type 'start' into the command line \n");
		textArea.append("If you have any confusion during the playing of the game, type 'help' into the command line to bring up a list of possible commands");
		
	}	

}


