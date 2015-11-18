package newMud;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Map {

	private ArrayList<Room> daMap = new ArrayList<Room>(7);

	public ArrayList<Room> getDaMap() {
		return daMap;
	}

	public int getLength() {
		return daMap.size();
	}

	public void setDaMap(ArrayList<Room> daMAp) {
		this.daMap = daMAp;
	}

	public void generateTestMap() {

		Item apple = new Item("apple", "Tastes sweet probably");
		Room entryWay = new Room("Foyer", "you're in a foyer", new ArrayList<Item>(Arrays.asList(apple)),
				new ImageIcon("Images/foyer.png"));

		Room den = new Room("Den", "you're in a den", new ArrayList<Item>(Arrays.asList(apple)),
				new ImageIcon("Images/den.jpg"));

		Room diningRoom = new Room("Dining room", "you're in the dining room",
				new ArrayList<Item>(Arrays.asList(apple)), new ImageIcon("Images/diningRoom.jpg"));

		Room kitchen = new Room("Kitchen", "You're in the kitchen", new ArrayList<Item>(Arrays.asList(apple)),
				new ImageIcon("Images/kitchen.jpg"));

		Room livingRoom = new Room("Living room", "You're in a living room", new ArrayList<Item>(Arrays.asList(apple)),
				new ImageIcon("Images/livingRoom.jpg"));

		Room upDen = new Room("upstairs den", "the upstairs den", new ArrayList<Item>(Arrays.asList(apple)),
				new ImageIcon("Images/den.jpg"));

		Room basement = new Room("basement", "a sense of dread looms over you, you see an apple",
				new ArrayList<Item>(Arrays.asList(apple)), new ImageIcon("Images/den.jpg"));

		entryWay.setExits(den, diningRoom, kitchen, livingRoom, upDen, basement);
		den.setExits(null, entryWay, null, null, null, null);
		diningRoom.setExits(entryWay, null, null, null, null, null);
		kitchen.setExits(null, null, null, entryWay, null, null);
		livingRoom.setExits(null, null, entryWay, null, null, null);
		upDen.setExits(null, null, null, null, null, entryWay);
		basement.setExits(null, null, null, null, entryWay, null);

		this.daMap = new ArrayList<Room>(
				Arrays.asList(entryWay, den, diningRoom, kitchen, livingRoom, upDen, basement));

	}

}
