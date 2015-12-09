package newMud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserInterface implements Observer{
	
	private JTextArea textArea = new JTextArea(100, 100);
	private JTextArea consoleOut = new JTextArea(100, 100);
	private JTextArea mobStuff = new JTextArea();
	private ArrayList<Mob> mobList;
	
	public UserInterface(GameCharacter pc,ArrayList<Mob> m) throws InterruptedException{
		mobList = m;
		
		JFrame window = new JFrame();
		window.setSize(800, 800);
		window.setLayout(new BorderLayout());
		window.setTitle("The Machine: An adventure for Glory");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());	

		//Displays the image at the top
		JLabel pictureLabel = new JLabel("");
		panel.add(pictureLabel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.add(pictureLabel, BorderLayout.WEST);
		window.add(panel, BorderLayout.WEST);

		//holds the enter a command textbox
		textArea = new JTextArea(2,50);
		textArea.setEditable(false);
		JScrollPane display = new JScrollPane(textArea);
		JLabel label = new JLabel("What will you do?");
		display.setAutoscrolls(true);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(display, BorderLayout.CENTER);
		panel.add(label, BorderLayout.NORTH);
		window.add(panel, BorderLayout.CENTER);

		//displays mob name and location for each mob alive
		mobStuff.setEditable(false);
		JLabel mobLabel = new JLabel("AYYY LOOK IT'S A MOB");
		JPanel mobPanel = new JPanel();
		mobPanel.setLayout(new BorderLayout());
		mobPanel.add(mobStuff,BorderLayout.CENTER);
		mobPanel.add(mobLabel, BorderLayout.NORTH);
		window.add(mobPanel, BorderLayout.EAST);
		mobStuff.setText("MOB STUFF");


		//displays player's inventory, health,stamina, etc...
		JTextArea stats = new JTextArea(1,1);
		stats.setEditable(false);
		stats.setLineWrap(true);
		stats.setLineWrap(true);
		stats.append("WELCOME");
		stats.setBorder(BorderFactory.createMatteBorder( 2, 2, 2, 2, Color.blue));
		JLabel label3 = new JLabel("");
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.add(stats,BorderLayout.NORTH);
		panel3.add(label3, BorderLayout.CENTER);
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
		panel2.add(label2, BorderLayout.NORTH);
		window.add(panel2, BorderLayout.CENTER);

		//input command textbox
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

	@Override
	public void update(Observable o, Object arg) {
		mobStuff.setText("");
		String mobInfo = "";
		for(int i = 0; i<mobList.size();i++){
			mobInfo = mobInfo + mobList.get(i).getName() + "\n---------\n Health: " + mobList.get(i).getHealth() + "\n Location: " + mobList.get(i).getLocation().getName() + "\n\n";		
		}
		mobStuff.setText("Mobs left: " + mobList.size() + "\n\n" + mobInfo);
	}	
}


