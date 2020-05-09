package command;

import java.util.ArrayList;
import java.util.HashMap;

import sqlDB.DerbyDatabase;

public class Parser {
	ArrayList<Action> actionBank = new ArrayList<>(); // list of all actions
	HashMap<String, Action> actionBankMap = new HashMap<>(); // map of <action.name, actions>
	DerbyDatabase db; // database reference

	public Parser(DerbyDatabase db) { // constructor
		this.db = db;
		this.actionBank = db.getActions(); // get actions from database
	}

	public void printActions() { // print all actions in the actionBank
		for (Action a : actionBank) {
			System.out.println(a.toString());
		}
	}

	public Action getAction(String input) { // match input to action or alt name, return matching action
		for (Action a : actionBank) {
			for (String alt : a.getAltNames()) {
				if (input.equalsIgnoreCase(alt)) {
					return a;
				}
			}
		}
		return null;
	}
}