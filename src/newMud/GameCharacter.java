package newMud;
public class GameCharacter extends GameObject {

	
	private int baseDamage;
	private int armourClass;
	
	private int maxHealth;		//max health
	private int health;			//temporary health
	
	private int maxStamina;		//max stamina
	private int stamina;		//temporary stamina
	
	private int score = 0;		
	private int xp = 0;
	private int lvl = 0;
	
	
	public GameCharacter(String n, String d, Room l,int h, int s) {
		this.setName(n);
		this.setDescription(d);
		this.setLocation(l);
		this.setMaxHealth(h);
		this.setMaxStamina(s);
	}
		
	//scoring methods
	public void plusOne(){
		score ++;
	}
	public int getScore(){
		return score;
	}
	public void addScore(int s){
		score = score + s;
	}
		
		//GETTERS
	public int getBaseDamage(){
		return baseDamage;
	}
	public int getArmour(){
		return armourClass;
	}
	
	
	
		//SETTERS
	public void setBaseDamage(int i){
		baseDamage = i;
	}
	public void setArmour(int i){
		armourClass = i;
	}	
	
		//XP SYSTEM
	public int getXP(){
		return xp;
	}
	public void setXP(int i){
		xp = i;
	}
	public void gitGud(){	//if user generates enough xp, user will level up and get stronger	
		if(xp >= 100){
			lvl++;						//add 1 to level
			xp = 0;						//reset xp
			maxHealth = maxHealth + 10*lvl;	//get stat boosts
			stamina = stamina + 10*lvl;
			armourClass = armourClass + 10*lvl;
			baseDamage = baseDamage + 10*lvl;
		}
	}

	
			//HEALTH 
	public void minusHealth(int i){
		health = health - i;
	}
	public void getKneed(int max, int min){
		health = health - (min + (int)(Math.random()*max));
	}
	public void setHealth(int i){
		health = i;
	}
	public void setMaxHealth(int i){
		health = i;
		maxHealth = i;
	}
	public int getHealth(){
		return health;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public void heal(){
		health = maxHealth;
	}

	//Stamina
	public int getStamina(){
		return stamina;
	}
	public int getMaxStamina(){
		return maxStamina;
	}
	public void setStamina(int i){
		stamina = i;
	}
	public void setMaxStamina(int i){
		maxStamina = i;
		stamina = i;
	}
	
	
			//MOVEMENT
	public void goNorth() {
		if (this.getLocation().getExits()[0] != null)
			this.setLocation(this.getLocation().getExits()[0]);
	}

	public void goSouth() {
		if (this.getLocation().getExits()[1] != null)
			this.setLocation(this.getLocation().getExits()[1]);
	}

	public void goEast() {
		if (this.getLocation().getExits()[2] != null)
			this.setLocation(this.getLocation().getExits()[2]);
	}

	public void goWest() {
		if (this.getLocation().getExits()[3] != null)
			this.setLocation(this.getLocation().getExits()[3]);
	}

	public void goUp() {
		if (this.getLocation().getExits()[4] != null)
			this.setLocation(this.getLocation().getExits()[4]);
	}

	public void goDown() {
		if (this.getLocation().getExits()[5] != null)
			this.setLocation(this.getLocation().getExits()[5]);
	}
	
	
}
