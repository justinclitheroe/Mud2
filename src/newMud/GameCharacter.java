package newMud;
public class GameCharacter extends GameObject {

	
	private int baseDamage;
	private int armourClass;
	private int toHit;
	
	private int maxHealth;		//max health
	private int health;			//temporary health
	
	private int maxStamina;		//max stamina
	private int stamina;		//temporary stamina
	
	private int score = 0;		
	private int xp = 0;
	private int lvl = 0;
	
	
	public GameCharacter(String n, String d, Room l,int h, int s,int b ,int aC, int tH) {
		this.setName(n);
		this.setDescription(d);
		this.setLocation(l);
		this.setMaxHealth(h);
		this.setMaxStamina(s);
		this.setBaseDamage(b);
		this.setArmour(aC);
		this.setToHit(tH);
	}
	
	// Combat Methods \\
	public int damage(Mob m){
		if ((int) Math.random()*20 + this.getToHit() > m.getArmour()){
			return this.getBaseDamage(); //TODO add equip damage
	}
		else
			return 0;
	}
	
	public int getArmour(){
		return armourClass;
	}
	public void setArmour(int i){
		armourClass = i;
	}
	public int getBaseDamage(){
		return baseDamage;
	}
	public void setBaseDamage(int i){
		baseDamage = i;
	}
	public int getToHit() {
		return toHit;
	}
	public void setToHit(int toHit) {
		this.toHit = toHit;
	}
	
	
	//scoring methods
	public void plusOne(){
		score ++;
	}
	public int getScore(){
		return score;
	}
	public void setScore(int s){
		score = s;
	}
	public void addScore(int s){
		score = score + s;
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
			int remainder = xp-100;				//sets remainder to any left over xp
			lvl++;								//add 1 to level
			xp = remainder;						//xp gets set to remainder
			maxHealth = maxHealth + 10*lvl;		//get stat boosts and heal the player to max health
			stamina = stamina + 10*lvl;
			armourClass = armourClass + 10*lvl;
			baseDamage = baseDamage + 10*lvl;
			this.addScore(42);
			this.heal();	
		}
	}


			//HEALTH
	
	
	public void minusHealth(int i){			//subtracts the players health by the given int
		health = health - i;
	}
	public int intGetKneed(int max, int min){			//subtracts health from the player for a random amount of value between a given min and max value
		int i = - (min + (int)(Math.random()*max));		//and also returns the value of health lost
		health = health - i;
		return i;
	}
	public void getKneed(int max, int min){						//same method as above however does not return an int
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
	public void heal(){			//heals the player completely
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
