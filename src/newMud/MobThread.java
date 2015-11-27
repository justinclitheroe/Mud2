package newMud;

public class MobThread extends Thread {

	private Mob mob;
	private GameCharacter gc;
	private boolean running = true;
	
	public MobThread(Mob m, GameCharacter g){
		mob = m;
		gc = g;
	}
	
	
	public void run(){
		
		while(running){
			try {
				Thread.sleep(10000); //wait <some number > seconds
				this.moveMob();			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public int rnGesus(int max,int min ){	 //generates a number between 1 and 6 to randomize the mob movement between rooms
		return min + (int)(Math.random()*max); 
		}

	
	
	
	 public void moveMob(){	//moves the mob randomly based on rnGesus() method
		int i = rnGesus(6,0);
		if(mob.getLocation().isValid(i)){
			switch(i){	
				case 0:
					mob.goNorth();
					break;
				case 1:
					mob.goSouth();
					break;
				case 2:
					mob.goEast();
					break;
				case 3:
					mob.goWest();
					break;
				case 4:
					mob.goUp();
					break;
				case 5:
					mob.goDown();
					break;
				}
			}
		if(gc.getLocation() == mob.getLocation()){	//if mob has moved into a room with the character, have a 10% chance mob gets scared and tries to run away
			if(rnGesus(0,9)==0){
				this.moveMob();	//tries to run away. has a <number of exits>/6  chance to run away
			}
	}
		
	}
}
