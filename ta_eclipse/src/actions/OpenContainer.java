package actions;

import java.sql.SQLException;
import java.util.ArrayList;

import command.Action;
import objects.Item;
import objects.ItemContainer;
import state.Game;

public class OpenContainer implements Updater {
	public static String name = "open";

	@Override
	public void update(Game g, Action a) throws SQLException {
		String obj = a.noun();
		ArrayList<Item> roomItems = g.itemsHere();
		for (Item i : roomItems) {
			if (i.getName().equals(obj)) {
				if (i.isContainer()) {
					ArrayList<Item> items = ((ItemContainer) i).getItems();
					if (obj.equals("mailbox")) {
						g.setOutput("You open the mailbox. You find the following: <br>");
						for (Item j : items) {
							j.setHidden(false);
							g.addOutput("--" + j.getName() + "<br>");
						}
						return;
					}
					else {
						g.setOutput("You open the container. You find the following: <br>");
						for (Item j : items) {
							j.setHidden(false);
							g.addOutput("--" + j.getName() + "<br>");
						}
						return;
					}
				} else {
					g.setOutput("You can't open that.");
					return;
				}
			} else {
				g.setOutput("You can't do that.");
			}
		}
	}
}
