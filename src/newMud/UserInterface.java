package newMud;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserInterface {
	
	private JTextArea textArea = new JTextArea(10, 20);
	private JTextArea consoleOut = new JTextArea(10, 20);
	
	public UserInterface(GameCharacter pc,ArrayList<Mob> m) throws InterruptedException{
		
		
		
		JFrame window = new JFrame();
		window.setSize(800, 800);
		window.setLayout(new BorderLayout());
		window.setTitle("Howdy Bitch");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());		
		
		//Displays the image at the top
		JLabel pictureLabel = new JLabel("");
		panel.add(pictureLabel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.add(pictureLabel, BorderLayout.WEST);
		window.add(panel, BorderLayout.NORTH);

		//holds the enter a command textbox
		textArea = new JTextArea(10, 20);
		JScrollPane display = new JScrollPane(textArea);
		JLabel label = new JLabel("What will you do?");
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(display, BorderLayout.CENTER);
		panel.add(label, BorderLayout.WEST);
		window.add(panel, BorderLayout.CENTER);
		
		
		
		//displays the console (output of entering commands)
		JScrollPane display2 = new JScrollPane(textArea);
		JLabel label2 = new JLabel("History of actions");
		consoleOut.setEditable(false);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(display2, BorderLayout.CENTER);
		panel2.add(label2, BorderLayout.WEST);
		window.add(panel2, BorderLayout.CENTER);
		
		JTextField input = new JTextField(15);
		input.addActionListener(new CommandListener(textArea,pc,pictureLabel));
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
		pictureLabel.setIcon(bootPic);
		textArea.setText("Welcome\n");
		textArea.append("When you are ready to begin, please type 'start' into the command line \n");
	}
	


}
