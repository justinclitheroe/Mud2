package newMud;

public class MobThread extends Thread {

	private Mob mob;
	private GameCharacter gc;
	public MobThread(Mob m, GameCharacter g){
		mob = m;
		gc = g;
	}
	
	
	public void run(){
		
		while(true){
			try {
				Thread.sleep(3000); //wait 30 seconds
				this.moveMob();			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.moveMob();
		}
	}
	
	
	public void runAway(){
		if(gc.getLocation() == mob.getLocation()){
			System.out.println("OH SHIT IT'S A MOB");
			this.moveMob();
		}
	}
	
	public int rnGesus(int max,int min ){	 //generates a number between 1 and 6 to randomize the mob movement between rooms
		return (int)(Math.random() * (max - min));
	}
	
	public void moveMob(){	//moves the mob randomly based on rnGesus() method
		int i = rnGesus(6,1);
		if(mob.getLocation().isValid(i)){
		switch(i){	
		case 1:
			mob.goNorth();
			System.out.println(mob.getName() + " has moved north to " + mob.getLocation());
			break;
		case 2:
			mob.goSouth();
			System.out.println(mob.getName() + " has moved south to " + mob.getLocation());
			break;
		case 3:
			mob.goEast();
			System.out.println(mob.getName() + "has moved east to " + mob.getLocation());
			break;
		case 4:
			mob.goWest();
			System.out.println(mob.getName() + " has moved west to " + mob.getLocation());
			break;
		case 5:
			mob.goUp();
			System.out.println(mob.getName() + " has moved up to " + mob.getLocation());
			break;
		case 6:
			mob.goDown();
			System.out.println(mob.getName() + " has moved down to " + mob.getLocation());
			break;
		}
	}
		else{
			System.out.println(mob.getName() + " has not moved.");
		}
	}
}
