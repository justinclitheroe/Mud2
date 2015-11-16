package newMud;

import java.util.Scanner;

public class Main {
	
	private static Map world = new Map();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		world.generateTestMap();
		GameCharacter pc = new GameCharacter("Frisk", "The PC", world.getDaMap().get(0));
		
		UserInterface ui = new UserInterface(pc);
	}

}
