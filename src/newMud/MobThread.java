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
	while(!mob.isDead()&& running){		//while the mob is not dead...
			try { 
				if(!mob.isEngaged()){	//if mob is not engaged in combat...else do nothing
					Thread.sleep(10000); 	//wait <some number > seconds
					if(mob.isEngaged()){
						break;
					}
					this.moveMob();	
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
	
	}
	running = false; 
	mob.upd();
	//perform any clean up here
	//ie... drop any items in the mobs inventory
	}
	
	public int rnGesus(int max,int min ){	 //generates a number between 1 and 6 to randomize the mob movement between rooms
		return min + (int)(Math.random()*max); 
	}

	public void stopRunning(){
		running = false;
	}
	
	 public void moveMob(){	//moves the mob randomly based on rnGesus() method
		int i = rnGesus(6,0);
		if(mob.getLocation().isValid(i)){
			switch(i){	
				case 0:
					mob.goNorth();
					System.out.println(mob.getName() +  " has moved to the:" + mob.getLocation().getName());
					mob.upd();
					break;
				case 1:
					mob.goSouth();
					System.out.println(mob.getName() + " has moved to the:" + mob.getLocation().getName());
					mob.upd();
					break;
				case 2:
					mob.goEast();
					System.out.println(mob.getName() + " has moved to the:" + mob.getLocation().getName());
					mob.upd();
					break;
				case 3:
					mob.goWest();
					System.out.println(mob.getName() + " has moved to the:" + mob.getLocation().getName());
					mob.upd();
					break;
				case 4:
					mob.goUp();
					System.out.println(mob.getName() +  " has moved to the:" + mob.getLocation().getName());
					mob.upd();
					break;
				case 5:
					mob.goDown();
					System.out.println(mob.getName() + " has moved to the:" + mob.getLocation().getName());
					mob.upd();
					break;
				}
		if(gc.getLocation() == mob.getLocation()){	//if mob has moved into a room with the character, have a 10% chance mob gets scared and tries to run away
			if(rnGesus(0,9)==0){
				this.moveMob();	//tries to run away. has a <number of exits>/6  chance to run away
			}
		}
		}
		
	}
}
