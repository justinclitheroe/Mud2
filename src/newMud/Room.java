package newMud;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Room extends GameObject {

	private Room[] exits = new Room[6];
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

	public void setImage(ImageIcon image) {
		this.image = image;
	}

}
