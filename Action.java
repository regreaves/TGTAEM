import java.util.*;

public class Action {
	String command;
	String description;
	String name;
	List<String> words;
	boolean validity;

	public Action(String command, String description, String name) {
		this.command = command;
		this.description = description;
		this.name = name;
	}

	public boolean checkValidity() {
		return true;
	}

	public String performAction() {
		return "empty";
	}

	public String displayAction(String command){
		return "empty";
	}
}
