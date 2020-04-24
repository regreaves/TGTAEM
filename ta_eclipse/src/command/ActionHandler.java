package command;

import java.util.ArrayList;

import objects.Inventory;
import objects.Item;
import objects.Room;

public class ActionHandler {
	ArrayList<Action> history;
	boolean freeze = false;
	String freezeOut = "";
	
	public ActionHandler() {

	}

	public void setHistory(ArrayList<Action> history) {
		this.history = history;
	}

	public void freeze(boolean freeze) {
		this.freeze = freeze;
	}
	
	public boolean freeze()
	{
		return freeze;
	}
	
	public void setFreeze(String s) {
		freezeOut = s;
	}
	
	public String getFreeze() {
		return freezeOut;
	}

	public void addAction(Action a) {
		history.add(a);
	}
	
	public Action getLastAction()
	{
		return history.get(history.size()-1);
	}

	public boolean hasConnection(Action a, Room r) {
		String id = r.getDestination(a.getName());
		if (id.equals("0")) {
			return false;
		}
		return true;
	}
	
//	public boolean isLocked(Action a, Room r) {
//
//	}
	
	public boolean itemInRoom(Room r, String obj) {
		ArrayList<Item> roomItems = r.getItems();
		for (Item i : roomItems) {
			if (i.getName().equals(obj)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasItem(Inventory in, String obj) {
		Item i = in.getItemByName(obj);
		if(i == null)
		{
			return false;
		}
		return true;
	}
	
	public boolean canTake(Action a, Inventory in) {
		String obj = a.noun();
		Item i = in.getItemByName(obj);
		if(in.hasSpace(i) && i.getWeight()<30)
		{
			return true;
		}
		return false;
	}
}
