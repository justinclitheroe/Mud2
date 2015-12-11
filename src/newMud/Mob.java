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
	
			//COMBAT SYSTEM FOR MOBS\\
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
		public void upd(){
			mobObserver.updateUI();
		}
}