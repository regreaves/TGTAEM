package actions;

import java.sql.SQLException;

import command.Action;
import state.Game;

public class Move implements Updater {
	public static String name = "move";

	@Override
	public void update(Game g, Action a) throws SQLException {
		String id = g.room().getDestination(a.getName());
		if (!g.s.clothes) {
			g.setOutput("You aren't going anywhere until you put your clothes back on.");
		} else if (g.s.sitting) {
			g.setOutput("You can't move while you're sitting. Why don't you try to STAND UP?");
		} else if (id.equals("0")) {
			g.setOutput("You can't go that way.");
		} else if (g.map.get(id).locked()){
			g.setOutput(g.loadRoom(id));
			g.addOutput("You can't go in a locked room. Try something else." + "<br><br>");
			g.addOutput(g.room().getDisplayName());
		} else {
			g.setVisited();
			g.movePlayer(id);
			g.setOutput(g.loadRoom(id));
		}
	}

}
