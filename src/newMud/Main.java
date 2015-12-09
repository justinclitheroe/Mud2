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
		MobObserver m = new MobObserver();	//creates observer to pass to each mob
		
		//Mob mob_name = new Mob(observer,name,description, room location, health, stamina, base_attack,Armor_class,to_hit);
		Mob t = new Mob(m,"Tom Goblin","a short goblin mumbling about linux servers",world.getDaMap().get(rnGesus(0,6)),1,1,1,1,1); 
			t.setDeathMessage("You hear a loud pitched screeching. It reminds you of dial up tones...");
			t.setAttackMessage("");
		Mob c = new Mob(m,"Cat","A wandering black cat. Possibly a source of bad luck. It sounds like the cat is mumbling about chemical bonds", world.getDaMap().get(rnGesus(0,6)),52,1,1,1,1);	
			c.setDeathMessage("she lets out a high pitched scream before poofing into a cloud of smoke");
			c.setAttackMessage("the cat throws some sort of vial at you. The glass shatters and the liquid burns your sking on contact. You hear it giggle");
		Mob ju = new Mob(m,"Joustain","Another goblin. This one makes terrible puns and seems to have a dislike towards any linux comment tom goblin makes", world.getDaMap().get(rnGesus(0,6)),1,1,1,1,1);
			ju.setDeathMessage("'AUUUUGUGGHHHHHHH' it yells. You then watch it for a good five minutes flopping around being way too overdramatic");
			ju.setAttackMessage("");
		Mob jo = new Mob(m,"Joardenne","A rare goblin to see in the world.Only shows up once every tuesday assuming it's a month divisible by three and there's a full moon",world.getDaMap().get(rnGesus(0,6)),1,1,1,1,1);	
			jo.setDeathMessage("He didn't actually die. He just got a real job");
			jo.setAttackMessage("Error 404: attack not found");
		Mob vd = new Mob(m,"Vim Diesel","ex-WWE(World Wrestling Editor) heavyweight champion. You should probably run...", world.getDaMap().get(rnGesus(0,6)),1,1,1,1,1);
			vd.setDeathMessage("$ exit");
			vd.setAttackMessage("It throws words at you. Apparently they're more effective than sticks and stones");
		Mob a = new Mob(m,"Adulthood","Arguably humankind's greatest enemy",world.getDaMap().get(rnGesus(0,6)),1,1,1,1,1); 
			a.setDeathMessage("There's a metaphor here but you're not quite sure what it is");
			a.setAttackMessage("It throws taxes, mortage, and bills at you. You cower in fear as you can no longer afford to take naps whenever you want");
		mobList.add(t);
		mobList.add(c);
		mobList.add(ju);
		mobList.add(jo);
		mobList.add(vd);
		mobList.add(a);
		
		 
		 UserInterface ui = new UserInterface(pc,tMachine, mobList);
		 //UserInterface ui2 = new UserInterface(tMachine,mobList);
		 m.addObserver(ui);//adds ui as an observer to m (holds the update method)
		
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
	public static int rnGesus(int max,int min ){	 //random number generator to help randomly place mobs
		return (int)(Math.random() * (max - min) + min); 
		}
}
