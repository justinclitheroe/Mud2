package newMud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class MobListener implements ActionListener {

	
	private ArrayList<Mob> mobList;
	private JTextArea out;
	
	public MobListener(ArrayList<Mob> m,JTextArea j){
		mobList = m;
		out = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < mobList.size();i++){
			
		}
	}

}
