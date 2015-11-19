package newMud;

import java.util.ArrayList;
public class Main {

	/**
	 * 
	 * program prints to the console if mobs move and when they move. This is only here to show that 
	 * they are moving. This will be taken out of the final version. 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	private static Map world = new Map();


	public static void main(String[] args) throws InterruptedException {
		
		world.generateTestMap();
		GameCharacter pc = new GameCharacter("Frisk", "The PC", world.getDaMap().get(0));
		
		
		//creates a list of mobs
		ArrayList<Mob> mobList = new ArrayList<Mob>();
		//creates the mobs
		Mob t = new Mob("Tom Goblin","a short goblin mumbling about linux servers", world.getDaMap().get(0)); 	//world.getDaMap().get((int)(Math.random() * (world.getLength()-0)) ));
		Mob c = new Mob("Cat","A wandering black cat. Possibly a source of bad luck",world.getDaMap().get(0));	//world.getDaMap().get((int)(Math.random() * (world.getLength()-0)) ));
		Mob ju = new Mob("Joustain","Another goblin. This one makes terrible puns and seems to have a dislike towards any linux comment tom goblin makes",world.getDaMap().get(0));	  	//world.getDaMap().get((int)(Math.random() * (world.getLength()-0)) ));
		Mob jo = new Mob("Joardenne","A rare goblin to see in the world.Only shows up once every tuesday assuming it's a month divisible by three and there's a full moon",world.getDaMap().get(0));			//world.getDaMap().get((int)(Math.random() * (world.getLength()-0)) ));
		//adds the mobs to the arrayList
		mobList.add(t);
		mobList.add(c);
		mobList.add(ju);
		mobList.add(jo);
		
		
		/*
		 *		TESTING TO SEE WHERE MOBS GET PUT @START UP 
		 *System.out.println(t.getName() + "	" + t.getLocation());
		 *System.out.println(c.getName() + "	" +c.getLocation());
		 *System.out.println(ju.getName() + " " +ju.getLocation());
		 *System.out.println(jo.getName() + "	" +jo.getLocation());
		 */
		
		
		ArrayList<MobThread> threadList = new ArrayList<MobThread>();
		UserInterface ui = new UserInterface(pc,threadList);
		
		
		for (int i = 0 ; i < mobList.size() ; i++){	//for loop creating threads for each mob in the array list
			threadList.add(new MobThread(mobList.get(i),pc));
		}
		for (int i = 0 ; i < threadList.size() ; i++){	//start each thread in the list
			threadList.get(i).start();
		}
		for (int i = 0 ; i < threadList.size() ; i++){	//join each thread in the list
			threadList.get(i).join();
		}
		
		
	}

}
