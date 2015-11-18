package newMud;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Map {

	private ArrayList<Room> daMap = new ArrayList<Room>(7);

	public ArrayList<Room> getDaMap() {
		return daMap;
	}

	public int getLength(){
		return daMap.size();
	}
	
	public void setDaMap(ArrayList<Room> daMAp) {
		this.daMap = daMAp;
	}
	
	
	public void generateTestMap() {
		
		Item apple = new Item("apple", "Tastes sweet probably");
		Room entryWay = new Room("Foyer", "you're in a foyer",
									new ArrayList<Item> (Arrays.asList(apple)), new ImageIcon("Images/foyer.png"));
		
	    Room northRoom = new Room("Den", "you're in a den",
									new ArrayList<Item> (Arrays.asList(apple)), new ImageIcon("Images/den.jpg"));
		
		Room southRoom = new Room("Dining room", "you're in the dining room",
									new ArrayList<Item> (Arrays.asList(apple)), new ImageIcon("Images/diningRoom.jpg"));
		
		Room eastRoom = new Room("Kitchen", "You're in the kitchen",
									new ArrayList<Item> (Arrays.asList(apple)), new ImageIcon("Images/kitchen.jpg"));
		
		Room westRoom = new Room("Living room", "You're in a living room",
									new ArrayList<Item> (Arrays.asList(apple)), new ImageIcon("Images/livingRoom.jpg"));
		
		Room upstairs = new Room("Upstairs", "the upstairs den",
									new ArrayList<Item> (Arrays.asList(apple)), new ImageIcon("Images/den.jpg"));
		
		Room downstairs = new Room("Downstairs", "a sense of dread looms over you, you see an apple",
									new ArrayList<Item> (Arrays.asList(apple)), new ImageIcon("Images/den.jpg"));
		
		entryWay.setExits(northRoom, southRoom, eastRoom, westRoom, upstairs, downstairs);
		northRoom.setExits(null, entryWay, null, null, null, null);
		southRoom.setExits(entryWay, null, null, null, null, null);
		eastRoom.setExits(null, null, null, entryWay, null, null);
		westRoom.setExits(null, null, entryWay, null, null, null);
		upstairs.setExits(null, null, null, null, null, entryWay);
		downstairs.setExits(null, null, null, null, entryWay, null);
		
		
		this.daMap = new ArrayList<Room> (
				Arrays.asList(entryWay, northRoom, southRoom, eastRoom, westRoom, upstairs, downstairs));
		
		
		
	}
	
	
	
	
	
	
}
