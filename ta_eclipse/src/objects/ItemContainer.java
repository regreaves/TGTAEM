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
		//if(i.getWeight() <= maxWeight) {
			this.items.add(i);
			//weight += i.getWeight();
			i.move();
		//}
	}
	
	public Item removeItem(Item item) {
		int x = this.items.indexOf(item);
		return this.items.remove(x);
	}
	
	public boolean hasSpace(Item item) {
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
