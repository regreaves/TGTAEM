package command;

import java.util.ArrayList;

import objects.Inventory;
import objects.Item;
import objects.Room;

public class ActionLog {
	ArrayList<Action> history = new ArrayList<>();
	
	public ActionLog() {

	}

	public void setHistory(ArrayList<Action> history) {
		this.history = history;
	}

	public void addAction(Action a) {
		history.add(a);
	}
	
	public Action lastAction() {
		return history.get(history.size()-1);
	}
	
	public boolean prevAction(String name) {
		int i = history.size()-1;
		while(i>=0) {
			Action a = history.get(i);
			if(a.getName().equalsIgnoreCase(name))
			{
				return true;
			}
			i--;
		}
		return false;
	}
}
