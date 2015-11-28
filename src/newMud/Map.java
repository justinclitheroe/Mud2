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

		//ITEMS
		
		Item smashBros = new Item("Smash Bros Melee","It's your favorite game!");
		Item screwdriver = new Item("Screwdriver","It's a phillips head screwdriver. You probably have to look up what it looks like");
		Item linux = new Item("linux install disk", "holy moley! You have the a wonderful operating system. You feel like you can take over the world");
		
		Item letterOpener = new Item("letter Opener","it's pretty sharp. Please use caution when inspecting this item");

		
		//Rooms
		Room entryWay = new Room("Foyer", "you're in a foyer", new ArrayList<Item>(Arrays.asList(linux)),
				new ImageIcon("Images/foyer.png"));

		Room den = new Room("Den", "you're in a den", new ArrayList<Item>(Arrays.asList(linux)),
				new ImageIcon("Images/den.jpg"));

		Room diningRoom = new Room("Dining room", "you're in the dining room",
				new ArrayList<Item>(Arrays.asList(letterOpener)), new ImageIcon("Images/diningRoom.jpg"));

		Room kitchen = new Room("Kitchen", "You're in the kitchen", new ArrayList<Item>(Arrays.asList(linux)),
				new ImageIcon("Images/kitchen.jpg"));

		Room livingRoom = new Room("Living room", "You walk into the living room. Theres a couch, two chairs, a TV setup and a computer. You dismiss the computer becuase it's not a linux machine. Your eye notices a glimmer near the tv", new ArrayList<Item>(Arrays.asList(smashBros,screwdriver,linux)),
				new ImageIcon("Images/livingRoom.jpg"));

		Room upDen = new Room("upstairs hallway", "to the ", new ArrayList<Item>(Arrays.asList(linux)),
				new ImageIcon("Images/den.jpg"));

		Room basement = new Room("basement", "a sense of dread looms over you, you see an apple",
				new ArrayList<Item>(Arrays.asList(linux)), new ImageIcon("Images/den.jpg"));

		
		
		
		//EXITS
		entryWay.setExits(den, diningRoom, kitchen, livingRoom, upDen, basement);
		
		den.setExits(diningRoom, entryWay, null, null, null, upDen);
		
		diningRoom.setExits(entryWay, den, null, null, null, null);
		
		kitchen.setExits(null, livingRoom, null, entryWay, null, null);
		
		
		livingRoom.setExits(kitchen, null, entryWay, null, null, null);
		
		upDen.setExits(null, null, null, null, den, entryWay);
		
		basement.setExits(null, null, null, null, entryWay, null);

		this.daMap = new ArrayList<Room>(
				Arrays.asList(entryWay, den, diningRoom, kitchen, livingRoom, upDen, basement));

	}

}
