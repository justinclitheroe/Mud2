package newMud;

public class Item extends GameObject {
	
	private int damage;
	private int armor;
	private boolean isKey;
	
	public Item(String n, String d, int da, int a, boolean k) {
		this.setName(n);
		this.setDescription(d);
		this.setDamage(da);
		this.setArmor(a);
		isKey = k;
	}
	
	
	
public Item getItem(){
	return this;
}
public int getDamage(){
	return damage;
}
public void setDamage(int d){
	damage = d;
}
public int getArmor(){
	return armor;
}
public void setArmor(int a){
	armor = a;
}


}
