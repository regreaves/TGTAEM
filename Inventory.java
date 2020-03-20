import java.util.*;

public class Inventory {
	int maxSize;
  ArrayList items = new ArrayList();

	public Inventory(int maxSize) {
		this.maxSize = maxSize;
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

	public ArrayList getItems() {
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

	public void setItems(ArrayList items) {
		this.items = items;
		return;
	}

	public void addItems(ArrayList items) {
		this.items.addAll(items);
		return;
	}

	public void dropItem(Item item) {
		//TODO
		int x = this.items.indexOf(item);
		Item drop = this.items.get(x);
		this.items.remove(x);
		//need to add the removed item to the room the player is in
		return;
	}

	public void removeItems(Item items) {
		//TODO
		int x = 0;
		while(x < items.size()) {
			Item remove = items.get(x);
			this.items.remove(remove);
			//need to add each removed item to the room the player is in
			x += 1;
		}
		return;
	}

	public int getNumberItems() {
		int number = this.items.size();
		return number;
	}

	public boolean checkValidity() {
		return true;
	}

	public String performInventory() {
		return "empty";
	}

	public String displayInventory(String command){
		return "empty";
	}
}
