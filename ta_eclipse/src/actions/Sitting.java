package actions;

import java.sql.SQLException;

import command.Action;
import state.Game;

public class Sitting implements Updater {
	public static String name = "sit";
	@Override
	public void update(Game g, Action a) throws SQLException {
		String v = a.verb();
		String n = a.noun();
		switch (v) {
		case "sit":
			if(n.equalsIgnoreCase("desk") && g.status.isSitting() == false) {
				g.status.setSitting(true);
				g.setOutput("You sit down at the desk.");
			} else if(n.equalsIgnoreCase("bench") && g.status.isSitting() == false) {
				g.status.setSitting(true);
				g.setOutput("You sit down on the bench.");
			} else if(n.equalsIgnoreCase("swings") && g.status.isSitting() == false) {
				g.status.setSitting(true);
				g.setOutput("You sit down on the swings.");
			} else if(g.status.isSitting()) {
				g.setOutput("You're already sitting.");
			} else {
				g.setOutput("You can't do that.");
			}
			break;
		case "stand":
			if(g.status.isSitting() == false) {
				g.setOutput("You're already standing.");
			} else if(g.status.isSitting()) {
				g.status.setSitting(false);
				g.setOutput("You stand up.");
			} else {
				g.setOutput("You can't do that.");
			}
			break;
		}
	}
}
