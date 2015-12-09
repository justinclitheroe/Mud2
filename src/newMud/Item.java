package newMud;

public class Item extends GameObject {
	
	private int damage;
	private int armor;
	
	
	public Item(String n, String d, int da, int a) {
		this.setName(n);
		this.setDescription(d);
		this.setDamage(da);
		this.setArmor(a);
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
