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
		
		JFrame player1window = new JFrame();
		player1window.setSize(800, 800);
		player1window.setLayout(new BorderLayout());
		player1window.setTitle("The Machine: An adventure for Glory");
			
		//Displays the image at the top
		JPanel player1PicturePanel = new JPanel();
		player1PicturePanel.setLayout(new BorderLayout());
		JLabel pictureLabel = new JLabel("");
		player1PicturePanel.setLayout(new BorderLayout());
		player1PicturePanel.add(pictureLabel, BorderLayout.WEST);
		player1window.add(player1PicturePanel, BorderLayout.WEST);

		//holds the enter a command textbox
		JLabel player1ActionsTitle = new JLabel("What will you do?");
		JPanel player1ActionsPanel = new JPanel();
		player1ActionsPanel.setLayout(new BorderLayout());
		player1ActionsPanel.add(player1ActionsTitle, BorderLayout.NORTH);
		player1window.add(player1ActionsPanel, BorderLayout.CENTER);

		//displays mob name and location for each mob alive
		mobStuff.setEditable(false);
		JLabel player1MobLabel = new JLabel("AYYY LOOK IT'S A MOB");
		JPanel player1MobPanel = new JPanel();
		player1MobPanel.setLayout(new BorderLayout());
		player1MobPanel.add(mobStuff,BorderLayout.CENTER);
		player1MobPanel.add(player1MobLabel, BorderLayout.NORTH);
		player1window.add(player1MobPanel, BorderLayout.EAST);
		mobStuff.setText("MOB STUFF");


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
		player1window.add(panel2, BorderLayout.CENTER);

		//input command textbox
		JTextField input = new JTextField();
		input.addActionListener(new CommandListener(textArea,pc,pictureLabel,player1Stats,m));
		player1ActionsPanel.add(input, BorderLayout.CENTER);
		player1window.add(player1ActionsPanel, BorderLayout.SOUTH);

		// Finish preparing the window and display it.
		player1window.pack();
		player1window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player1window.setVisible(true);

		
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