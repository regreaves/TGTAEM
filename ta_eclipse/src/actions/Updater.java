package actions;

import java.sql.SQLException;

import command.Action;
import state.Game;

public interface Updater {
	public void update(Game g, Action a) throws SQLException;
}
