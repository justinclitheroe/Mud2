package newMud;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Room extends GameObject {

	private Room[] exits = new Room[6];
	private Room[] lockedExits = new Room[6];
	private ImageIcon image;
	
	public Room(String n, String d, ArrayList<Item> i, ImageIcon pic) {
		this.setName(n);
		this.setDescription(d);
		this.setInventory(i);
		this.setImage(pic);
	}

	public boolean isValid(int i){
		return exits[i] != null;
	}
	
	public void setExits(Room n, Room s, Room e, Room w, Room u, Room d) {
		exits[0] = n;
		exits[1] = s;
		exits[2] = e;
		exits[3] = w;
		exits[4] = u;
		exits[5] = d;
	}
	public void setLockedExits(Room n, Room s, Room e, Room w, Room u, Room d) {
		exits[0] = n;
		exits[1] = s;
		exits[2] = e;
		exits[3] = w;
		exits[4] = u;
		exits[5] = d;
	}

	public Room[] getExits(){
		return this.exits;
	}

	public ImageIcon getImage() {
		return image;
	}
	
	public String listExits() {
		String exitList = "";
		for(int i = 0 ; i<exits.length;i++){
			if(exits[i] != null){
				if(i==0){
					exitList = exitList + "North:" + exits[i].getName()+ "   ";
					}
				else if(i==1){
					exitList = exitList + "South:" + exits[i].getName()+ "   ";
					}
				else if(i==2){
					exitList = exitList + "East:" + exits[i].getName()+ "   ";
					}
				else if(i==3){
					exitList = exitList + "West:" + exits[i].getName()+ "   ";
					}
				else if(i==4){
					exitList = exitList + "Up:" + exits[i].getName()+ "   ";
					}
				else if(i==5){
					exitList = exitList + "Down:" + exits[i].getName() + "   ";
					}
				else{
					exitList = exitList + "";
				}
			}
		}
		return exitList;
	}
	public String listLockedExits() {
		String exitList = "";
		for(int i = 0 ; i<exits.length;i++){
			if(lockedExits[i] != null){
				if(i==0){
					exitList = exitList + "North:" + lockedExits[i].getName()+ "   ";
					}
				else if(i==1){
					exitList = exitList + "South:" + lockedExits[i].getName()+ "   ";
					}
				else if(i==2){
					exitList = exitList + "East:" + lockedExits[i].getName()+ "   ";
					}
				else if(i==3){
					exitList = exitList + "West:" + lockedExits[i].getName()+ "   ";
					}
				else if(i==4){
					exitList = exitList + "Up:" + lockedExits[i].getName()+ "   ";
					}
				else if(i==5){
					exitList = exitList + "Down:" + lockedExits[i].getName() + "   ";
					}
				else{
					exitList = exitList + "";
				}
			}
		}
		return exitList;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public void unlockExit(int d){
		switch(d){
		case 0:
			exits[0]= lockedExits[0];
			lockedExits[0] = null;
			break;
		case 1:
			exits[1]= lockedExits[1];
			lockedExits[1] = null;
			break;
		case 2:
			exits[2]= lockedExits[2];
			lockedExits[2] = null;
			break;
		case 3:
			exits[3]= lockedExits[3];
			lockedExits[3] = null;
			break;
		case 4:
			exits[3]= lockedExits[3];
			lockedExits[0] = null;
			break;
		case 5:
			exits[3]= lockedExits[3];
			lockedExits[3] = null;
			break;
		}
	}
}
