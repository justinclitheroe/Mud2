package newMud;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

public class MobObserver implements Observer {

	ArrayList<Mob> mobList;
	JTextArea out;
	
	public MobObserver(ArrayList<Mob> m,JTextArea j){
		mobList = m;
		out = j;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		String mobLocation = "number of mobs left: " + mobList.size();
		for(int i = 0; i < mobList.size() ;i++){
			mobLocation = mobLocation + mobList.get(i).getName() + ": " + mobList.get(i).getLocation().getName() + "\n";
		}
		out.setText(mobLocation);	//prints the location of each mob to the the given JTextArea
	}

}
