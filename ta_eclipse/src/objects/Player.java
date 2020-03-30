package objects;

import java.util.*;
import objects.Actor;

public class Player extends Actor {
	Inventory inventory;
	public String location;

	public Player(){
		this.inventory = new Inventory(0, null, null);
		this.location = "0";
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
