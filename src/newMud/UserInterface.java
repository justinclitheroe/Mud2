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
	
	private JTextArea player1TextArea = new JTextArea(100, 100);
	private JTextArea player1ConsoleOut = new JTextArea(100, 100);
	private JTextArea player2TextArea = new JTextArea(100, 100);
	private JTextArea player2ConsoleOut = new JTextArea(100, 100);
	private JTextArea player1MobStuff = new JTextArea();
	private JTextArea player2MobStuff = new JTextArea();
	private ArrayList<Mob> mobList;
	
	public UserInterface(GameCharacter pc, GameCharacter pc2, ArrayList<Mob> m, Map map) throws InterruptedException{
		mobList = m;
		
		//alternate character observing
		
		
		
		
		
		JFrame player1window = new JFrame();
		player1window.setSize(800, 800);
		player1window.setLayout(new BorderLayout());
		player1window.setTitle("The Machine: An adventure for Glory");
			
		//Displays the image at the top
		JPanel player1PicturePanel = new JPanel();
		player1PicturePanel.setLayout(new BorderLayout());
		JLabel player1PictureLabel = new JLabel("");
		player1PicturePanel.setLayout(new BorderLayout());
		player1PicturePanel.add(player1PictureLabel, BorderLayout.WEST);
		player1window.add(player1PicturePanel, BorderLayout.WEST);
		
		//displays player's inventory, health,stamina, etc...
		JTextArea player1Stats = new JTextArea(1,1);
		player1Stats.setEditable(false);
		player1Stats.setLineWrap(true);
		player1Stats.setLineWrap(true);
		player1Stats.append("WELCOME");
		player1Stats.setBorder(BorderFactory.createMatteBorder( 2, 2, 2, 2, Color.blue));
		JLabel player1StatsLabel = new JLabel("");
		JPanel player1StatsPanel = new JPanel();
		player1StatsPanel.setLayout(new BorderLayout());
		player1StatsPanel.add(player1Stats,BorderLayout.NORTH);
		player1StatsPanel.add(player1StatsLabel, BorderLayout.CENTER);
		player1window.add(player1StatsPanel, BorderLayout.NORTH);

		//holds the enter a command text box
		JLabel player1InputTitle = new JLabel("What will you do?");
		JPanel player1InputPanel = new JPanel();
		player1InputPanel.setLayout(new BorderLayout());
		player1InputPanel.add(player1InputTitle, BorderLayout.NORTH);
		JTextField player1Input = new JTextField();
		player1Input.addActionListener(new CommandListener(player1TextArea, player2TextArea,pc, pc2,player1PictureLabel,player1Stats,m, map));
		player1InputPanel.add(player1Input, BorderLayout.CENTER);
		player1window.add(player1InputPanel, BorderLayout.SOUTH);

		//displays mob name and location for each mob alive
		player1MobStuff.setEditable(false);
		JLabel player1MobLabel = new JLabel("AYYY LOOK IT'S A MOB");
		JPanel player1MobPanel = new JPanel();
		player1MobPanel.setLayout(new BorderLayout());
		player1MobPanel.add(player1MobStuff,BorderLayout.CENTER);
		player1MobPanel.add(player1MobLabel, BorderLayout.NORTH);
		player1window.add(player1MobPanel, BorderLayout.EAST);
		player1MobStuff.setText("MOB STUFF");

		//displays the console (output of entering commands)
		JScrollPane player1ActionDisplay = new JScrollPane(player1TextArea);		
		Dimension d = new Dimension(800,500);	
		JLabel player1ActionTitle = new JLabel("History of actions");
		player1ConsoleOut.setEditable(false);
		player1ConsoleOut.setLineWrap(true);
		JPanel player1ActionDisplayPanel = new JPanel();
		player1ActionDisplayPanel.setLayout(new BorderLayout());
		player1ActionDisplayPanel.setPreferredSize(d);
		player1ActionDisplayPanel.add(player1ActionDisplay, BorderLayout.CENTER);
		player1ActionDisplayPanel.add(player1ActionTitle, BorderLayout.NORTH);
		player1window.add(player1ActionDisplayPanel, BorderLayout.CENTER);		

		// Finish preparing the window and display it.
		player1window.pack();
		player1window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player1window.setVisible(true);

		
		ImageIcon bootPic = new ImageIcon("Images/nico.jpg");
		Image img = bootPic.getImage();
		Image newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		bootPic = new ImageIcon(newimg);
		player1PictureLabel.setIcon(bootPic);
		
		player1TextArea.setLineWrap(true);
		player1TextArea.setWrapStyleWord(true);
		player1TextArea.append("You awake from your slumber only to find yourself in a strange house labled 7920. You rub your head and feel a slight bump. \n");
		player1TextArea.append("It's at this time that you start to feel a slight headache. little by little your memory starts to come back. You remeber you stupid friends \n");
		player1TextArea.append("telling you to sign up for some stupid tour of a house. Little did they know it would turn into the adventure of a lifetime");
		player1TextArea.append("\n When you are ready to begin, please type 'start' into the command line \n");
		player1TextArea.append("If you have any confusion during the playing of the game, type 'help' into the command line to bring up a list of possible commands");
		
		JFrame player2window = new JFrame();
		player2window.setSize(800, 800);
		player2window.setLayout(new BorderLayout());
		player2window.setTitle("The Machine: An adventure for Glory");
			
		//Displays the image at the top
		JPanel player2PicturePanel = new JPanel();
		player2PicturePanel.setLayout(new BorderLayout());
		JLabel player2PictureLabel = new JLabel("");
		player2PicturePanel.setLayout(new BorderLayout());
		player2PicturePanel.add(player2PictureLabel, BorderLayout.WEST);
		player2window.add(player2PicturePanel, BorderLayout.WEST);
		
		//displays player's inventory, health,stamina, etc...
		JTextArea player2Stats = new JTextArea(1,1);
		player2Stats.setEditable(false);
		player2Stats.setLineWrap(true);
		player2Stats.setLineWrap(true);
		player2Stats.append("WELCOME");
		player2Stats.setBorder(BorderFactory.createMatteBorder( 2, 2, 2, 2, Color.blue));
		JLabel player2StatsLabel = new JLabel("");
		JPanel player2StatsPanel = new JPanel();
		player2StatsPanel.setLayout(new BorderLayout());
		player2StatsPanel.add(player2Stats,BorderLayout.NORTH);
		player2StatsPanel.add(player2StatsLabel, BorderLayout.CENTER);
		player2window.add(player2StatsPanel, BorderLayout.NORTH);

		//holds the enter a command text box
		JLabel player2InputTitle = new JLabel("What will you do?");
		JPanel player2InputPanel = new JPanel();
		player2InputPanel.setLayout(new BorderLayout());
		player2InputPanel.add(player2InputTitle, BorderLayout.NORTH);
		JTextField player2Input = new JTextField();
		player2Input.addActionListener(new CommandListener(player2TextArea,player1TextArea,pc2,pc,player2PictureLabel,player2Stats,m, map));
		player2InputPanel.add(player2Input, BorderLayout.CENTER);
		player2window.add(player2InputPanel, BorderLayout.SOUTH);

		//displays mob name and location for each mob alive
		player2MobStuff.setEditable(false);
		JLabel player2MobLabel = new JLabel("AYYY LOOK IT'S A MOB");
		JPanel player2MobPanel = new JPanel();
		player2MobPanel.setLayout(new BorderLayout());
		player2MobPanel.add(player2MobStuff,BorderLayout.CENTER);
		player2MobPanel.add(player2MobLabel, BorderLayout.NORTH);
		player2window.add(player2MobPanel, BorderLayout.EAST);
		player2MobStuff.setText("MOB STUFF");

		//displays the console (output of entering commands)
		JScrollPane player2ActionDisplay = new JScrollPane(player2TextArea);		
		d = new Dimension(800,500);	
		JLabel player2ActionTitle = new JLabel("History of actions");
		player2ConsoleOut.setEditable(false);
		player2ConsoleOut.setLineWrap(true);
		JPanel player2ActionDisplayPanel = new JPanel();
		player2ActionDisplayPanel.setLayout(new BorderLayout());
		player2ActionDisplayPanel.setPreferredSize(d);
		player2ActionDisplayPanel.add(player2ActionDisplay, BorderLayout.CENTER);
		player2ActionDisplayPanel.add(player2ActionTitle, BorderLayout.NORTH);
		player2window.add(player2ActionDisplayPanel, BorderLayout.CENTER);		

		// Finish preparing the window and display it.
		player2window.pack();
		player2window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player2window.setVisible(true);

		
		bootPic = new ImageIcon("Images/nico.jpg");
		img = bootPic.getImage();
		newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		bootPic = new ImageIcon(newimg);
		player2PictureLabel.setIcon(bootPic);
		
		player2TextArea.setLineWrap(true);
		player2TextArea.setWrapStyleWord(true);
		player2TextArea.append("You awake from your slumber only to find yourself in a strange house labled 7920. You rub your head and feel a slight bump. \n");
		player2TextArea.append("It's at this time that you start to feel a slight headache. little by little your memory starts to come back. You remeber you stupid friends \n");
		player2TextArea.append("telling you to sign up for some stupid tour of a house. Little did they know it would turn into the adventure of a lifetime");
		player2TextArea.append("\n When you are ready to begin, please type 'start' into the command line \n");
		player2TextArea.append("If you have any confusion during the playing of the game, type 'help' into the command line to bring up a list of possible commands");
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		String mobInfo = "";
		for(int i = 0; i<mobList.size();i++){
			mobInfo = mobInfo + mobList.get(i).getName() + "\n---------\n Health: " + mobList.get(i).getHealth() + "\n Location: " + mobList.get(i).getLocation().getName() + "\n\n";		
		}
		player1MobStuff.setText("");
		player1MobStuff.setText("Mobs left: " + mobList.size() + "\n\n" + mobInfo);
		player2MobStuff.setText("");
		player2MobStuff.setText("Mobs left: " + mobList.size() + "\n\n" + mobInfo);
	}	
}