package objects;

public class Player extends Actor {
	Inventory inventory;

	public Player(){
		this.inventory = new Inventory(0, null, null);
		this.location = "1";
	}

	public void move(String roomID)
	{
		location = roomID;
	}
	
	public void changeStats() {
		modifyStats(stats);
		return;
	}

	public void checkInventory() {
		System.out.println(inventory.displayInventory());
		return;
	}

	public Inventory getInventory() {
		return inventory;
	}
}
