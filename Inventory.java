import java.util.*;

public class Inventory {
	String command;
	String description;
	String name;
	List<String> words;
	boolean validity;

	public Inventory(String command, String description, String name) {
		this.command = command;
		this.description = description;
		this.name = name;
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
