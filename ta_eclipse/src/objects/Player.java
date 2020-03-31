package objects;

import java.util.ArrayList;

public class Player extends Actor {
	Inventory inventory;

	public Player() {
		this.inventory = new Inventory(0, "in1");
		this.location = "1";
	}

	public void move(String roomID) {
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

	// TODO: here or in inventory: max size check
	public void get(Item i) {
		inventory.addItem(i);
	}

	public void getAll(ArrayList<Item> i) {
		inventory.addItems(i);
	}

	public Item drop(Item i) {
		return inventory.dropItem(i);
	}

	public ArrayList<Item> dropAll() {
		ArrayList<Item> i = inventory.getItems();
		ArrayList<Item> x = new ArrayList<>();
		inventory.setItems(x);
		return i;
	}

	public Inventory getInventory() {
		return inventory;
	}
}
