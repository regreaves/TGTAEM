package actions;

import java.sql.SQLException;

import command.Action;
import state.Game;

public class Sitting implements Updater {
	public static String name = "sit";
	@Override
	public void update(Game g, Action a) throws SQLException {
		String v = a.verb();
		switch (v) {
		case "sit":
			g.status.setSitting(true);
			break;
		case "stand":
			g.status.setSitting(false);
			break;
		}
	}
}
