package objects;

public class Player extends Actor {
	Inventory inventory;

	public Player() {
		//Initializes inventory and location
		this.inventory = new Inventory(0, 30, "in1");
		this.location = "1";
	}
	
	//Changes player location to given roomID while moving
	public void move(String roomID) {
		location = roomID;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		return;
	}

	public Inventory getInventory() {
		return inventory;
	}

	//Returns player id and location in a string
	public String toString()
	{
		String out = "Player: id - " + id + " location - " + location;
		return out;
	}
}
