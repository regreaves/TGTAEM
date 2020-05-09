package objects;

import java.util.ArrayList;

public class Inventory {
	int currentWeight; // weight of current items
	int maxWeight; // maximum weight allowed
	String id; // reference id
	ArrayList<Item> items = new ArrayList<>();

	public Inventory(int currentWeight, int maxTotalWeight, String id) {
		this.currentWeight = currentWeight;
		this.maxWeight = maxTotalWeight;
		this.id = id;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxTotalWeight) {
		this.maxWeight = maxTotalWeight;
		return;
	}

	public boolean hasSpace(Item item) { // checks if there is space for the given item based on weight
		int x = item.getWeight() + getCurrentWeight();
		if (x < this.maxWeight) {
			return true;
		} else {
			return false;
		}
	}

	public int getCurrentWeight() {
		int currentWeight = 0;
		for (Item i : items) {
			currentWeight += i.getWeight();
		}
		return currentWeight;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public Item getItemByName(String name) { // returns item by name, if present
		Item item;
		int i = 0;
		while (i < this.items.size()) {
			item = this.items.get(i);
			if (name.equalsIgnoreCase(item.getName())) {
				return item;
			}
			i++;
		}
		return null;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
		return;
	}

	public void addItem(Item i) { // adds a single item to the inventory
		int x = currentWeight + i.getWeight();
		if (x <= maxWeight) {
			this.items.add(i);
			currentWeight += i.getWeight();
			i.move();
		}

	}

	public void addItems(ArrayList<Item> items) { // adds a list of items to the inventory
		this.items.addAll(items);
		for (Item i : items) {
			currentWeight += i.getWeight();
			i.move();
		}
		return;
	}

	public Item dropItem(Item item) { // removes item from the inventory and returns it
		int x = this.items.indexOf(item);
		currentWeight -= item.getWeight();
		return this.items.remove(x);
	}

	public String displayInventory() { // returns inventory in string
		int i = 0;
		String inventory = null;
		Item item;
		while (i < this.items.size()) {
			item = this.items.get(i);
			inventory += item.getName();
		}
		return inventory;
	}

	public boolean hasItem(String name) // boolean check for item by name
	{
		for (Item i : items) {
			if (i.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public void removeItem(String name) // removes item entirely
	{
		for (Item i : items) {
			if (i.getName().equalsIgnoreCase(name)) {
				items.remove(i);
				return;
			}
		}
	}
}
