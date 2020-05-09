package objects;

import java.util.ArrayList;

public class ItemContainer extends Item {
	int maxWeight; // maximum allowed weight
	ArrayList<Item> items = new ArrayList<>(); // items in container

	public ItemContainer() { // POJO
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
		return;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
		return;
	}

	public void addItem(Item i) { // add item to container
		// if(i.getWeight() <= maxWeight) { // maxWeight check currently still buggy,
		// but low priority
		this.items.add(i);
		// weight += i.getWeight();
		i.move();
		// }
	}

	public Item removeItem(Item item) { // remove and return specified Item
		int x = this.items.indexOf(item);
		return this.items.remove(x);
	}

	public boolean hasSpace(Item item) { // check if there is space for the specified item by weight
		int x = 0;
		for (Item i : items) {
			x += i.getWeight();
		}
		x += item.getWeight();
		if (x < this.maxWeight) {
			return true;
		} else {
			return false;
		}
	}

}
