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
		Item linux = new Item("linux install disk", "holy moley! You have the a wonderful operating system. You feel like you can take over the world",0,0,true);
		Item broom = new Item("Broom", "It makes you uncomfortable",4,0,true);
		Item frisbee = new Item("Frisbee", "Almost better than Melee",0,0, false);
		Item key = new Item("Key", "A key? Really? How trite",0,0,true);
		Item rawDenim = new Item("Raw Denim", "Stylin on the plebs", 0, 12, false);
		Item salt = new Item("Salt", "Get it?", 0, 0, true);
		Item sword = new Item("Dank Sword", "The dankness",0 , 0, true);
		Item egg = new Item("Egg", "An egg with jewels stapled to it, why would anyone do this?", 0, 0, false);
				
			Room firstBasement = new Room("Deep Dark Basement", "The world around you is dark and confusing, you are filled with determination", 
				new ArrayList<Item>(Arrays.asList(linux)), new ImageIcon("Images/"), null);
			Room closet = new Room("Broom Closet", "You aren't quite sure why, but the gross amount of brooms surrounding you makes you uncomfortable", 
				new ArrayList<Item>(Arrays.asList(broom)), new ImageIcon("Images/"), null);
			Room stairs = new Room("Staircase", "a staircase with a light at the top", 
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), broom);
			Room shed = new Room("Shed", "Various sharp implements surround you, maybe you shouldn't hang out here",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room villaFront = new Room("Front of Villa", "A large spanish villa ",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room outcropping = new Room("Outcropping", "You're on a small ledge to the side of the house, an open window is behind you",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room study = new Room("Study", "The computer is running an inferior os, you shed a tear, there is a slight breeze",
				new ArrayList<Item>(Arrays.asList(key)), new ImageIcon("Images/"), null);
			Room entryWay = new Room("Foyer", "A marvelous staircase spirals upwards",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room nullstairs = new Room("Upstairs", "You're in a very stylish loft",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room sneaks = new Room("Sneaky Room", "Frisbee gear adorns the walls, there's a jewish man snoring in the corner, best leave him be",
				new ArrayList<Item>(Arrays.asList(frisbee)), new ImageIcon("Images/"), null);
			Room dirt = new Room("Dirty Room", "You think you're back in the broom closet because of the size but you're actually just in a gross room",
				new ArrayList<Item>(Arrays.asList(rawDenim)), new ImageIcon("Images/"), null);
			Room hall = new Room("Hall", "Not much here, it seems pretty useless in here",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room nullitchen = new Room("Kitchen", "Various meat products adorn the walls, You feel aroused",
				new ArrayList<Item>(Arrays.asList(sword)), new ImageIcon("Images/"), null);
			Room pantry = new Room("Pantry", "Spices everywhere! your senses feel a-Salt-ed",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room backYard = new Room("Back Yard", "You can hear the sounds of the forest to the south",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), frisbee);
			Room forest = new Room("Forest Clearing", "There's a tall tree here, inconspicuous, a trail leads south",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room tree = new Room("Tall tree", "There's an egg here, you figure you're missing a reference but you don't care. Actually, you might have already picked up the egg, making this description useless. HOLY SHIT DOES THIS MEAN I'm BREAKING THE FOURTH WALL? that's cray, also that lowercase m is on purpose, yeah this is stil lgoing.",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), null);
			Room towerClearing = new Room("Tower Entry", "A mediocre tower stands before you, the door is cold to the touch, maybe if you were passive aggressive towards it",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), salt);
			Room firstFloor = new Room("First Floor", "Not much going on in this room, a knight in a suit of armor sits infront of the ",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), sword);
			Room secondFloor = new Room("Second Floor", "There's a lock on the staircase, for some reason this blocks you from ascending",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), key);
			Room treasureFloor = new Room("Treasure Floor", "",
				new ArrayList<Item>(Arrays.asList()), new ImageIcon("Images/"), linux);

			this.daMap = new ArrayList<Room>(
					Arrays.asList(firstBasement, closet, stairs, shed, villaFront, outcropping, study, entryWay, nullstairs,
						sneaks, dirt, hall, nullitchen, pantry, backYard, forest, tree, towerClearing, firstFloor, secondFloor, treasureFloor));

			firstBasement.setExits(stairs, null, closet, null, null, null);
			firstBasement.setLockedExits(null, null, null, null, null, null);
			
			closet.setExits(null, null, null, firstBasement, null, null);
			closet.setLockedExits(null, null, null, null, null, null);
			
			stairs.setExits(null, firstBasement, null, null, shed, null);
			stairs.setLockedExits(null, null, null, null, shed, null);
			
			shed.setExits(villaFront, null, null, null, null, stairs);
			shed.setLockedExits(null, null, null, null, null, null);
			
			villaFront.setExits(null, shed, outcropping, null, null, null);
			villaFront.setLockedExits(null, null, null, null, null, null);
			
			outcropping.setExits(villaFront, null, null, study, null, null);
			outcropping.setLockedExits(null, null, null, null, null, null);
			
			study.setExits(null, null, outcropping, entryWay, null, null);
			study.setLockedExits(null, null, null, null, null, null);
			
			entryWay.setExits(null, hall, study, null, nullstairs, null);
			entryWay.setLockedExits(null, null, null, null, null, null);
			
			nullstairs.setExits(null, null, sneaks, dirt, null, entryWay);
			nullstairs.setLockedExits(null, null, sneaks, dirt, null, null);
			
			sneaks.setExits(null, null, null, nullstairs, null, null);
			sneaks.setLockedExits(null, null, null, null, null, null);
			
			dirt.setExits(null, null, nullstairs, null, null, null);
			dirt.setLockedExits(null, null, null, null, null, null);
			
			hall.setExits(entryWay, nullitchen, null, null, null, null);
			hall.setLockedExits(null, null, null, null, null, null);
			
			nullitchen.setExits(null, null, backYard, pantry, null, null);
			nullitchen.setLockedExits(null, null, null, null, null, null);
			
			pantry.setExits(null, null, nullitchen, null, null, null);
			pantry.setLockedExits(null, null, null, null, null, null);
			
			backYard.setExits(null, forest, null, nullitchen, null, null);
			backYard.setLockedExits(null, null, null, null, null, null);
			
			forest.setExits(backYard, null, null, null, tree, towerClearing);
			forest.setLockedExits(null, null, null, null, null, null);
			
			tree.setExits(null, null, null, null, null, forest);
			tree.setLockedExits(null, null, null, null, null, null);
			
			towerClearing.setExits(null, null, firstFloor, null, forest, null);
			towerClearing.setLockedExits(null, null, firstFloor, null, null, null);
			
			firstFloor.setExits(null, null, null, towerClearing, secondFloor, null);
			firstFloor.setLockedExits(null, null, null, null, secondFloor, null);
			
			secondFloor.setExits(null, null, null, null, treasureFloor, null);
			secondFloor.setLockedExits(null, null, null, null, treasureFloor, null);
			
			treasureFloor.setExits(null, null, null, null, null, null);
			treasureFloor.setLockedExits(null, null, null, null, null, null);



	}

}
