package objects;

import java.util.ArrayList;

public class Inventory {
	int maxSize;
	String id;
  ArrayList<Item> items = new ArrayList<>();

	public Inventory(int maxSize, String id, ArrayList<Item> items) {
		this.maxSize = maxSize;
		this.id = id;
    this.items = items;
	}

	public int getSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
		return;
	}

	public boolean checkSize() {
		int x = items.size();
		if (x < this.maxSize) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getCurrentSize() {
		int currentSize = this.maxSize - this.items.size();
		return currentSize;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public Item getItemName(String name) {
		Item item;
		int i = 0;
		while(i < this.items.size()) {
			item = this.items.get(i);
			if (name == item.getName()) {
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

	public void addItems(ArrayList<Item> items) {
		this.items.addAll(items);
		return;
	}

	public Item dropItem(Item item) {
		//TODO
		int x = this.items.indexOf(item);
		//item.setLocation(?);
		//need to get player location
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
		while(i < this.items.size()) {
			item = this.items.get(i);
			inventory += item.getName();
		}
		return inventory;
	}
}
