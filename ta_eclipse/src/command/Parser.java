package command;

import java.util.ArrayList;
import java.util.HashMap;

import sqlDB.DerbyDatabase;

public class Parser {
	ArrayList<Action> actionBank = new ArrayList<>();
	HashMap<String, Action> actionBankMap = new HashMap<>();
	DerbyDatabase db;

	public Parser(DerbyDatabase db) {
		this.db = db;
		this.actionBank = db.getActions();

		// wjen map make sure all keys are lowercase
	}

	public void printActions() {
		for (Action a : actionBank) {
			System.out.println(a.toString());
		}
	}

	// this is a terrible way to search
	// if you have a better idea lemme know.
	public Action getAction(String input) {
		// make input lowercase
//		then only map.get is needed to lookup action

		for (Action a : actionBank) {
			for (String alt : a.getAltNames()) {
				if (input.equalsIgnoreCase(alt)) {
					return a;
				}
			}
		}
		return null;
	}
	

	public static void main(String[] args) {
		//Parser p = new Parser();
		//Action a = p.getAction("Jump on bed");
		//System.out.println(a);
	}

}