package newMud;

import java.util.ArrayList;

public class GameCharacter extends GameObject {

	
	private int baseDamage;
	private int ArmorClass;
	private int toHit;
	
	private int maxHealth;		//max health
	private int health;			//temporary health
	
	private int maxStamina;		//max stamina
	private int stamina;		//temporary stamina
	
	private int score = 0;		
	private int xp = 0;
	private int lvl = 0;
	
	private ArrayList<Item> inventory = new ArrayList<Item>();
	
	public GameCharacter(String n, String d, Room l,int h, int s,int b ,int aC, int tH) {
		this.setName(n);
		this.setDescription(d);
		this.setLocation(l);
		this.setMaxHealth(h);
		this.setMaxStamina(s);
		this.setBaseDamage(b);
		this.setArmor(aC);
		this.setToHit(tH);
	}
	
	
				//INVENTORY\\
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	public void pickUp(Item i) {
		this.addArmor(i.getArmor());
		this.addBaseDamage(i.getDamage());
		this.getInventory().add(i);
	}
	public void drop(Item i){
		inventory.remove(i);
		this.addBaseDamage(-i.getDamage());
		this.addArmor(-i.getDamage());
		this.getInventory().add(i);
	}

			//COMBAT\\
	public int damage(Mob m){
		if ((int) Math.random()*20 + this.getToHit() > m.getArmor()){
			return this.getBaseDamage(); //TODO add equip damage
	}
		else
			return 0;
	}
	public int getArmor(){
		return ArmorClass;
	}
	public void setArmor(int i){
		ArmorClass = i;
	}
	public void addArmor(int i){
		ArmorClass = ArmorClass + i;
	}
	public int getBaseDamage(){
		return baseDamage;
	}
	public void setBaseDamage(int i){
		baseDamage = i;
	}
	public void addBaseDamage(int i){
		baseDamage = baseDamage + i;
	}
	public int getToHit() {
		return toHit;
	}
	public void setToHit(int toHit) {
		this.toHit = toHit;
	}
	
	
		//SCORE\\
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
	
		//XP SYSTEM\\
	public int getLevel(){
		return lvl;
	}
	public void setLevel(int l){
		lvl = l;
	}
	public int getXP(){
		return xp;
	}
	public void setXP(int i){
		xp = i;
	}
	public void addXP(int x){
		xp = xp + x;
	}
	public void gitGud(){	//if user generates enough xp, user will level up and get stronger	
		if(xp >= 100){
			int remainder = xp-100;				//sets remainder to any left over xp
			lvl++;								//add 1 to level
			xp = remainder;						//xp gets set to remainder
			maxHealth = maxHealth + 10*lvl;		//get stat boosts and heal the player to max health
			maxStamina = stamina + 10*lvl;
			ArmorClass = ArmorClass + 10*lvl;
			baseDamage = baseDamage + 10*lvl;
			this.addScore(42);
			this.heal();	
		}
	}


			//HEALTH\\
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
	public void heal(){			//heals the player back to max health
		health = maxHealth;
	}

		//STAMINA\\
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
	
			//MOVEMENT\\
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
