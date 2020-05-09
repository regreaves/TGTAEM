package command;

import java.util.ArrayList;

public class ActionLog { // stores all actions performed
	ArrayList<Action> history = new ArrayList<>();

	public ActionLog() { // POJO

	}

	public void setHistory(ArrayList<Action> history) { // set past history, used when seting from table
		this.history = history;
	}

	public void addAction(Action a) { // add an action to table
		history.add(a);
	}

	public Action lastAction() { // return very last action performed
		return history.get(history.size() - 1);
	}

	public boolean prevAction(String name) { // check if action matching name specified was previously performed.
		int i = history.size() - 1;
		while (i >= 0) {
			Action a = history.get(i);
			if (a.getName().equalsIgnoreCase(name)) {
				return true;
			}
			i--;
		}
		return false;
	}
}
