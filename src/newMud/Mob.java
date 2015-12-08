package newMud;
public class Mob extends GameCharacter {

	private boolean inCombat = false;
	private boolean isDead = false;
	private MobObserver mobObserver;
	private String deathMessage = "";
	private String attackMessage = "";
	
	
	public Mob(MobObserver m,String n, String d, Room l,int h,int s,int b,int aC,int tH) {
		super(n, d, l ,h ,s ,b ,aC ,tH);
		mobObserver = m;
	}
	
	
	public void setDeathMessage(String s){
		deathMessage = s;
	}
	public String getDeathMessage(){
		return deathMessage;
	}
	public void setAttackMessage(String s){
		attackMessage = s;
	}
	public String getAttackMessage(){
		return attackMessage;
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

	//MOVEMENT
		public void goNorth() {
			if (this.getLocation().getExits()[0] != null)
				this.setLocation(this.getLocation().getExits()[0]);
				mobObserver.updateUI();
		}

		public void goSouth() {
			if (this.getLocation().getExits()[1] != null)
				this.setLocation(this.getLocation().getExits()[1]);
				mobObserver.updateUI();
		}

		public void goEast() {
			if (this.getLocation().getExits()[2] != null)
				this.setLocation(this.getLocation().getExits()[2]);
				mobObserver.updateUI();
		}

		public void goWest() {
			if (this.getLocation().getExits()[3] != null)
				this.setLocation(this.getLocation().getExits()[3]);
				mobObserver.updateUI();
		}

		public void goUp() {
			if (this.getLocation().getExits()[4] != null)
				this.setLocation(this.getLocation().getExits()[4]);
				mobObserver.updateUI();
		}

		public void goDown() {
			if (this.getLocation().getExits()[5] != null)
				this.setLocation(this.getLocation().getExits()[5]);
				mobObserver.updateUI();
		}

		public void upd(){
			mobObserver.updateUI();
		}

}