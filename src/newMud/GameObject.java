package newMud;

import java.util.ArrayList;


public class GameObject {
	private ArrayList<Item> inventory = new ArrayList<Item>(0);
	private int xCoord;
	private int yCoord;
	private int id;
	
	//Methods
	
	public void pickUp(Item i) {
		inventory.add(i);
	}
	
	public void drop(Item i){
		int indexOf = inventory.indexOf(i);
		inventory.remove(indexOf);
	}
	
	//Getters n' Setters
	
	public int getyCoord() {
		return yCoord;
	}
	
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public int getxCoord() {
		return xCoord;
	}
	
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
