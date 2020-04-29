package actions;

import java.sql.SQLException;

import command.Action;
import state.Game;

public class DropItem implements Updater {
	public static String name = "drop item";
	@Override
	public void update(Game g, Action a) throws SQLException {
		String obj = a.noun();
		if (g.inventory().getItemByName(obj)== null) {
			g.setOutput("You don't have that in your inventory to drop.");
		}
		g.dropItem(obj);
		g.setOutput("You drop the " + obj + ".");
	}

}
