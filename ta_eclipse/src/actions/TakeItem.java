package actions;

import java.util.ArrayList;

import command.Action;
import objects.Item;
import state.Game;

public class TakeItem implements Updater {
	public static String name = "take item";
	
	@Override
	public void update(Game g, Action a) {
		String obj = a.noun();
		ArrayList<Item> roomItems = g.itemsHere();
		for (Item i : roomItems) {
			if (i.getName().equals(obj)) {
				if (i.getWeight()<30 && g.inventory().hasSpace(i)) {
					g.takeItem(i);
					g.setOutput("You take the " + obj + ".");
					return;
				} else {
					if (obj.equalsIgnoreCase("window")) {
						g.setOutput("You can't take the window. That's sorta attached to the house.");
						return;
					} else if (obj.equalsIgnoreCase("painting")) {
						g.setOutput("As much as you'd like to take and destroy it, you really shouldn't.");
						return;
					} else {
						g.setOutput("That's too heavy to carry. You can't take that.");
						return;
					}
				}
			} else {
				g.setOutput("You can't do that.");
			}
		}
	}
}
