package newMud;

import java.util.ArrayList;

public class Boss extends Mob{

	private ArrayList<Item> mobLoot;
	
	public Boss(String n, String d, Room l, int h, int s, int b, int aC, int tH,Item i) {
		super(null,n, d, l, h, s, b, aC, tH);
	}
	public ArrayList<Item> getMobLoot(){
		return mobLoot;
	}
	public void setMobLoot(ArrayList<Item> m){
		mobLoot = m;
	}
}