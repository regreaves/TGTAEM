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
		//TODO
		return true;
	}

	public ArrayList getItems() {
		return items;
	}

	public void setItems(ArrayList items) {
		this.items = items;
		return;
	}

	public void addItems(ArrayList items) {
		//TODO
		return;
	}

	public void dropItem(Item item) {
		//TODO
		return;
	}

	public void removeItems(Item items) {
		//TODO
		return;
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
