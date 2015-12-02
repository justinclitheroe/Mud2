package newMud;
public class Mob extends GameCharacter {

	private boolean inCombat = false;
	private boolean isDead = false;
	
	public Mob(String n, String d, Room l,int h,int s) {
		super(n, d, l,h,s);
	}
	
	public void engage(){	//engages the mob in combat
		inCombat = true;
	}
	
	public boolean isEngaged(){			//if mob is in combat, mob thread checks the value of inCombat, if it is true, mob will not move
		return inCombat;
	}

	public void makeDead(){
		isDead = true;
	}
	public boolean isDead(){
		return isDead;
	}
}