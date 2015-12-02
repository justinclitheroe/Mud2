package newMud;

import java.util.Observable;
import java.util.Observer;

public class MobObserver implements Observer {

	private Mob mob;
	private MobThread thread;
	
	public MobObserver(Mob m, MobThread t){
		mob = m;
		thread = t;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(mob.getHealth()<=0){
			
		}

	}

}
