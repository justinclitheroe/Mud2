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

	
	
	/*
	 * Same method as before but nothing gets printed to the console
	 * This will be used in the final MUD but is not used to show that mobs are randomly moving from room 
	 * to room
	 * 
	 * public void moveMob(){	//moves the mob randomly based on rnGesus() method
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
	}
	*/
	
	public void moveMob(){	//moves the mob randomly based on rnGesus() method
		int i = rnGesus(6,0);
		if(mob.getLocation().isValid(i)){
			switch(i){	
				case 0:
					System.out.println("the value of i is: "+i+ " " + mob.getName() +" has started in "+ mob.getLocation());
					mob.goNorth();
					System.out.println(mob.getName() +" has moved north to " + mob.getLocation());
					break;
				case 1:
					System.out.println("the value of i is: "+i+ " " + mob.getName() +" has started in " + mob.getLocation());
					mob.goSouth();
					System.out.println(mob.getName() +" has moved south to "+ mob.getLocation());
					break;
				case 2:
					System.out.println("the value of i is: "+i+ " " + mob.getName() +" has started in " + mob.getLocation());
					mob.goEast();
					System.out.println(mob.getName() +" has moved east to " + mob.getLocation());
					break;
				case 3:
					System.out.println("the value of i is: "+i+ " " + mob.getName() +" has started in " +mob.getLocation());
					mob.goWest();
					System.out.println(mob.getName() +" has moved west to" +mob.getLocation());
					break;
				case 4:
					System.out.println("the value of i is: "+i+ " " + mob.getName() +" has started in" +mob.getLocation());
					mob.goUp();
					System.out.println(mob.getName() +" has moved up to" +mob.getLocation());
					break;
				case 5:
					System.out.println("the value of i is: "+i+ " " + mob.getName() +" has started in" +mob.getLocation());
					mob.goDown();
					System.out.println(mob.getName() +" has moved down to" +mob.getLocation());
					break;
				}
			}
		else{
			System.out.println("the value of i is: "+i+ " " +  mob.getName() + " has not moved.");
		}
		
		if(gc.getLocation() == mob.getLocation()){	//if mob has moved into a room with the character, have a 10% chance mob gets scared and tries to run away
			if(rnGesus(0,9)==0){
				this.moveMob();	//tries to run away. has a <number of exits>/6  chance to run away
			}
	}
		
	}
}
