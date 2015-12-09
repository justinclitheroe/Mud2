package newMud;

import java.util.ArrayList;

public class GameObject {
	private ArrayList<Item> inventory = new ArrayList<Item>(); //We'll see if I can get away with this. Easy fix if I can't
	private Room location;
	private String name;
	private String description;
	
	//Methods
	
	public ArrayList<Item> getItemObject(){
		return inventory;
	}
	
	public Boolean checkItem(String item_name){              //Checks to see if an item is in the array of Items in current room
		for(int i = 0; i < this.getInventory().size() ; i++){
			if (this.getInventory().get(i).getName().equalsIgnoreCase(item_name)){
				return true;
			}
		}
		return false;
	}

	public Item returnItem(String item_name){     			//returns an item based on the string item_name
		for(int i = 0; i < this.getInventory().size() ; i++){
			if (this.getInventory().get(i).getName().equalsIgnoreCase(item_name)){
				Item tempItem =this.getInventory().get(i);
				this.getInventory().remove(i);
				return tempItem;
			}
		}
		return null;
	}
	
	//Getters n' Setters
	
	
	public Room getLocation() {
		return location;
	}

	public void setLocation(Room location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}


	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public String toString() {
		return name + ", " + description;
	}
	
}
