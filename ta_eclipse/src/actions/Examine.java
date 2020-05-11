package actions;

import java.sql.SQLException;
import java.util.ArrayList;

import command.Action;
import objects.Item;
import state.Game;

public class Examine implements Updater {
	public static String name = "examine";

	@Override
	public void update(Game g, Action a) throws SQLException {
		g.setOutput("");
		String obj = a.noun();
		if (obj.equals("room")) {
			g.setOutput(g.map.get(g.here()).look());
		} else {
			ArrayList<Item> roomItems = g.itemsHere();
			for (Item i : roomItems) {
				if (i.getName().equalsIgnoreCase(obj)) {
					g.setOutput(i.getInventDscrpt());
					return;
				} else {
					g.setOutput("There's no " + obj + " for you to examine.");

				}
			}
		}
	}
}
