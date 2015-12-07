package newMud;

import java.util.Observable;

public class MobObserver extends Observable {	
	
	public MobObserver(){
	}
	

	public void updateUI() {
		System.out.println("UpdateUI method runs");
		setChanged();
		System.out.println("hasChanged: " + hasChanged());
		notifyObservers();
	}
	

}