package newMud;

import java.util.ArrayList;

public class Boss extends Mob{

	private ArrayList<Item> mobLoot;
	private Item keyItem;
	
	public Boss(String n, String d, Room l, int h, int s, int b, int aC, int tH,Item i) {
		super(null,n, d, l, h, s, b, aC, tH);
		keyItem = i;
	}
	public ArrayList<Item> getMobLoot(){
		return mobLoot;
	}
	public void setMobLoot(ArrayList<Item> m){
		mobLoot = m;
	}
	public Item getItem() {
		return keyItem;
	}
	
	
}