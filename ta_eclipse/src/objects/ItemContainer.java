package objects;

import java.util.ArrayList;

public class ItemContainer extends Item {
	int maxWeight;
	ArrayList<Item> items = new ArrayList<>();

	public ItemContainer() {
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
	
	public void addItem(Item i) {
		int x = weight + i.getWeight();
		if(x <= maxWeight) {
			this.items.add(i);
			weight += i.getWeight();
			i.move();
		}
	}
	
	public Item removeItem(Item item) {
		int x = this.items.indexOf(item);
		weight -= item.getWeight();
		return this.items.remove(x);
	}
	
	public boolean hasSpace(Item item) {
		int x = item.getWeight() + getWeight();
		if (x < this.maxWeight) {
			return true;
		} else {
			return false;
		}
	}
	
}
