package actions;

import java.sql.SQLException;

import command.Action;
import state.Game;

public class Quit implements Updater {
	public static String name = "quit";

	@Override
	public void update(Game g, Action a) throws SQLException {
		g.setQuit(true);
	}

}
