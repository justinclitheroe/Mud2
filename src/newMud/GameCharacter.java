package newMud;

public class GameCharacter extends GameObject {

	private int health;
	private int stamina;
	
	public GameCharacter(String n, String d, Room l) {
		this.setName(n);
		this.setDescription(d);
		this.setLocation(l);
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
