package newMud;

import java.util.Observable;

public class MobObserver extends Observable {	
	
	public MobObserver(){
	}
	

	public void updateUI() {
		setChanged();
		notifyObservers();
	}
	

}