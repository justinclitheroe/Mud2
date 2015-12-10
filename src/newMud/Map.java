package newMud;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Map {

	private ArrayList<Room> daMap = new ArrayList<Room>();	
	
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

		
		
								//ITEMS\\
		//Item item_name = new Item(Name, description, damage, armor)
		Item smashBros = new Item("Smash Bros Melee","It's your favorite game!",0,0,false);
		Item screwdriver = new Item("Screwdriver","It's a phillips head screwdriver. You probably have to look up what it looks like",0,0,false);
		Item linux = new Item("linux install disk", "holy moley! You have the a wonderful operating system. You feel like you can take over the world",0,0,true);
		Item letterOpener = new Item("letter Opener","it's pretty sharp. Please use caution when inspecting this item",0,0,false);
								//WEAPONS\\
		Item sword = new Item("Dank sword","real shapr wow",10,1,false);
								//ARMOR\\
		Item goldArmor = new Item("Golden Armor", "such gold, very armor", 1 ,10,false);
		
		
		
								//ROOMS\\
		Room entryWay = new Room("Foyer", "you're in a foyer. This is the entryway to a bunch of stuff", new ArrayList<Item>(Arrays.asList(linux,sword,goldArmor)),
				new ImageIcon("Images/foyer.png"),sword);

		Room den = new Room("Den", "The only important features in the room are a large ornamental fireplace and an odd looking torch to the left of the fireplace", new ArrayList<Item>(Arrays.asList(linux)),
				new ImageIcon("Images/den.jpg"),null);

		Room diningRoom = new Room("Dining room", "you're in the dining room. There's a bunch of unopened mail",
				new ArrayList<Item>(Arrays.asList(letterOpener)), new ImageIcon("Images/diningRoom.jpg"),null);

		Room kitchen = new Room("Kitchen", "You're in the kitchen. You see a bowl of fruit, and a broom ", new ArrayList<Item>(Arrays.asList(linux)),
				new ImageIcon("Images/kitchen.jpg"),null);

		Room livingRoom = new Room("Living room", "You walk into the living room. Theres a couch, two chairs, a TV setup and a computer. You dismiss the computer becuase it's not a linux machine. Your eye notices a glimmer near the tv", new ArrayList<Item>(Arrays.asList(smashBros,screwdriver,linux)),
				new ImageIcon("Images/livingRoom.jpg"),null);

		Room upDen = new Room("upstairs hallway", "up, up, and away", new ArrayList<Item>(Arrays.asList(linux)),
				new ImageIcon("Images/den.jpg"),null);

		Room basement = new Room("basement", "a sense of dread looms over you, you see an apple",
				new ArrayList<Item>(Arrays.asList(linux)), new ImageIcon("Images/den.jpg"),null);

		
		
		
							//EXITS\\
		entryWay.setExits(null,diningRoom, kitchen, livingRoom, upDen, basement);
		entryWay.setLockedExits(den, null, null, null, null, null);
		
		den.setExits(diningRoom, entryWay, null, null, null, upDen);
		//den.setLockedExits(null,null,null,null,null,null);
		
		diningRoom.setExits(entryWay, den, null, null, null, null);
		//diningRoom.setLockedExits(null,null,null,null,null,null);
		
		kitchen.setExits(null, livingRoom, null, entryWay, null, null);
		//kitchen.setLockedExits(null,null,null,null,null,null);
		
		livingRoom.setExits(kitchen, null, entryWay, null, null, null);
		//livingRoom.setLockedExits(null,null,null,null,null,null);
		
		upDen.setExits(null, null, null, null, den, entryWay);
		//upDen.setLockedExits(null,null,null,null,null,null);
		
		basement.setExits(null, null, null, null, entryWay, null);
		//basement.setLockedExits(null,null,null,null,null,null);
		
		this.daMap = new ArrayList<Room>(
				Arrays.asList(entryWay, den, diningRoom, kitchen, livingRoom, upDen, basement));

	}

}
