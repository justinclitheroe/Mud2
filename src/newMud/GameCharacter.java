package newMud;

public class GameCharacter extends GameObject {

	@SuppressWarnings("unused")
	private int baseDamage;
	@SuppressWarnings("unused")
	private int armourClass;
	@SuppressWarnings("unused")
	private int health;
	@SuppressWarnings("unused")
	private int stamina;
	private int score = 0;
	@SuppressWarnings("unused")
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
