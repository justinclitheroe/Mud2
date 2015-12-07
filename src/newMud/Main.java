package newMud;

import java.util.ArrayList;
public class Main {

	/**
	 * 
	 * program prints to the console if mobs move and when they move. This is only here to show that 
	 * they are moving. This will be taken out of the final version. 
	 * 
	 * Score currently increases every time you pick an item up. There will be more use for score for the next 
	 * section of the project
	 * 
	 * Map will be updated for the next project with more distinct rooms with better descriptions
	 * 
	 * Nico's House: An adventure for glory
	 * By Justin, Nico
	 * 
	 * 
	 * Rest in peace Jordan and Alex....
	 * May their souls be forever in peace
	 * 
	 */

	
	
	private static Map world = new Map();
	public static void main(String[] args) throws InterruptedException {
		
		world.generateTestMap();
		
		ArrayList<GameCharacter> playerList= new ArrayList<GameCharacter>();
		GameCharacter pc = new GameCharacter("Frisk", "The PC", world.getDaMap().get(0),42,42,42,42,42);
		GameCharacter tMachine = new GameCharacter("Tom","Is probably a robot",world.getDaMap().get(0),42,42,42,42,42);
		playerList.add(pc);
		playerList.add(tMachine);
		//creates a list of mobs
		ArrayList<Mob> mobList = new ArrayList<Mob>();
		//Mob mobName(Name,Description,Starting Room,Health,Stamina)
		

		MobObserver m = new MobObserver();
		
		Mob t = new Mob(m,"Tom Goblin","a short goblin mumbling about linux servers", world.getDaMap().get(0),1,1,1,1,1);//world.getDaMap().get(rnGesus(0,6)),1,1); 
			t.setDeathMessage("");
		Mob c = new Mob(m,"Cat","A wandering black cat. Possibly a source of bad luck. It sounds like the cat is mumbling about chemical bonds", world.getDaMap().get(0),52,1,1,1,1);//,world.getDaMap().get(rnGesus(0,6)),1,1);	
			t.setDeathMessage("");
		Mob ju = new Mob(m,"Joustain","Another goblin. This one makes terrible puns and seems to have a dislike towards any linux comment tom goblin makes", world.getDaMap().get(0),1,1,1,1,1);//,world.getDaMap().get(rnGesus(0,6)),1,1);
			t.setDeathMessage("");
		Mob jo = new Mob(m,"Joardenne","A rare goblin to see in the world.Only shows up once every tuesday assuming it's a month divisible by three and there's a full moon", world.getDaMap().get(0),1,1,1,1,1);//,world.getDaMap().get(rnGesus(0,6)),1,1);	
			t.setDeathMessage("");
		Mob vd = new Mob(m,"Vim Diesel","ex-WWE(World Wrestling Editor) heavyweight champion. You should probably run...", world.getDaMap().get(0),1,1,1,1,1);//,world.getDaMap().get(rnGesus(0,6)),1,1);
			t.setDeathMessage("");
		mobList.add(t);
		mobList.add(c);
		mobList.add(ju);
		mobList.add(jo);
		mobList.add(vd);
		 
		
		 
		 UserInterface ui = new UserInterface(pc,mobList);
		 //UserInterface ui2 = new UserInterface(tMachine,mobList);
		 m.addObserver(ui);
		 
		 
		
		/*
		 *		TESTING TO SEE WHERE MOBS GET PUT @START UP
		 *				PLEASE IGNORE THIS TOM
		 *System.out.println(t.getName() + "	" + t.getLocation());
		 *System.out.println(c.getName() + "	" + c.getLocation());
		 *System.out.println(ju.getName() + " " + ju.getLocation());
		 *System.out.println(jo.getName() + " " + jo.getLocation());
		 *System.out.println(vd.getName() + " " + vd.getLocation());
		 *
		 */
		
		 ArrayList<MobThread> threadList = new ArrayList<MobThread>();		
		 	for (int i = 0 ; i < mobList.size() ; i++){	//for loop creating threads for each mob in the array list
		 		threadList.add(new MobThread(mobList.get(i),pc));
		 		//threadList.add(new MobThread(mobList.get(i),tMachine));
		 	}
		 	for (int i = 0 ; i < threadList.size() ; i++){	//start each thread in the list
		 		threadList.get(i).start();
		 	}
		 	for (int i = 0 ; i < threadList.size() ; i++){	//join each thread in the list
		 		threadList.get(i).join();
		 	}
		
		
		
	}
	public static int rnGesus(int max,int min ){	 //generates a number between 1 and 6 to randomize the mob movement between rooms
		return min + (int)(Math.random()*max); 
		}
}
