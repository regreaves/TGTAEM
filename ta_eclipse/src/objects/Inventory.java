package objects;

import java.util.ArrayList;

public class Inventory {
	int currentWeight;
	int maxTotalWeight;
	String id;
	ArrayList<Item> items = new ArrayList<>();

	public Inventory(int currentWeight, int maxTotalWeight, String id) {
		this.currentWeight = currentWeight;
		this.maxTotalWeight = maxTotalWeight;
		this.id = id;
	}

	public int getMaxTotalWeight() {
		return maxTotalWeight;
	}

	public void setMaxTotalWeight(int maxTotalWeight) {
		this.maxTotalWeight = maxTotalWeight;
		return;
	}

	public boolean checkWeight(Item item) {
		int x = getCurrentWeight();
		if (x + item.getWeight() < this.maxTotalWeight) {
			return true;
		} else {
			return false;
		}
	}

	public int getCurrentWeight() {
		int currentWeight = 0;
		for(Item i : items) {
			currentWeight += i.getWeight();
		}
		return currentWeight;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public Item getItemByName(String name) {
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

	public void addItem(Item i) {
		int x = currentWeight + i.getWeight();
		if(x <= maxTotalWeight) {
			this.items.add(i);
			currentWeight += i.getWeight();
			i.move();
		}
		
	}

	public void addItems(ArrayList<Item> items) {
		this.items.addAll(items);
		for (Item i : items) {
			currentWeight += i.getWeight();
			i.move();
		}
		return;
	}

	public Item dropItem(Item item) {
		int x = this.items.indexOf(item);
		currentWeight -= item.getWeight();
		return this.items.remove(x);
	}

	public int getNumberItems() {
		int number = this.items.size();
		return number;
	}

	public String displayInventory() {
		int i = 0;
		String inventory = null;
		Item item;
		while (i < this.items.size()) {
			item = this.items.get(i);
			inventory += item.getName();
		}
		return inventory;
	}
}
