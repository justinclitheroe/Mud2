package newMud;
@SuppressWarnings("unused")
public class GameCharacter extends GameObject {

	
	private int baseDamage;
	private int armourClass;
	private int health;
	private int stamina;
	private int score = 0;
	private int xp = 0;
	
	
	public GameCharacter(String n, String d, Room l,int h, int s) {
		this.setName(n);
		this.setDescription(d);
		this.setLocation(l);
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
	
	
	public void getAll(){
		for(int i = 0; i < this.getLocation().getItemObject().size();i++){
			this.pickUp(getLocation().getItemObject().get(i));
		}
	}
	
	
	//getters
	
	public int getBaseDamage(){
		return baseDamage;
	}
	public int getArmour(){
		return armourClass;
	}
	public int getStamina(){
		return stamina;
	}
	public int getHealth(){
		return health;
	}
	

	
	//Setters
	public void setBaseDamage(int i){
		baseDamage = i;
	}
	public void setArmour(int i){
		armourClass = i;
	}
	public void setStamina(int i){
		stamina = i;
	}
	public void setHealth(int i){
		health = i;
	}
	
	
	//movement
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
