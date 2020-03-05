import java.util.*;

public class Inventory {
	int maxSize;
  ArrayList items;

	public Inventory(int maxSize) {
		this.maxSize = maxSize;
    this.items = new ArrayList();
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
