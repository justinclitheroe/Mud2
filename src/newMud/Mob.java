package newMud;
@SuppressWarnings("unused")
public class Mob extends GameCharacter {

	private boolean inComabat = false;
	
	public Mob(String n, String d, Room l,int h,int s) {
		super(n, d, l,h,s);
	}
	
	public void engage(){
		inComabat = true;
	}
	
	
	
	
}