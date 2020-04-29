package actions;

import java.sql.SQLException;
import java.util.ArrayList;

import command.Action;
import state.Game;

public class CheckInventory implements Updater {
	public static String name = "check inventory";
	@Override
	public void update(Game g, Action a) throws SQLException {
		boolean empty = true;
		if (a.noun().equals("inventory")) {
			g.setOutput("<br>>Inventory: <br>");
			ArrayList<String> items = g.db.listInventory();
			for (String i : items) {
				empty = false;
				g.addOutput("--" + i + "<br>");
			}
			if (empty) {
				g.addOutput("Your inventory is empty. <br>");
			}
		}
	}
}
