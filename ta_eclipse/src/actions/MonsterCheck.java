package actions;

import java.sql.SQLException;

import command.Action;
import objects.Player;
import state.Game;

public class MonsterCheck implements Updater {
	public static String name = "monster check";
	@Override
	public void update(Game g, Action a) throws SQLException {
		// TODO Auto-generated method stub
		String v = a.verb();
		String n = a.noun();
		Player p = g.player();
		switch (v) {
			case "check for":
				if(g.status.isMonsterCheck1() == false) {
					if(n.equalsIgnoreCase("monsters")) {
						g.status.setMonsterCheck1(true);
						g.setOutput("You check for monsters under the bed. You may have seen something scurry away, but the coast is clear now.");
					}
				} else if(g.status.isMonsterCheck1()) {
					g.setOutput("You already checked for monsters and found none. What are you waiting for?");
				} else {
					g.setOutput("You can't do that.");
				}
				break;
			case "hide under":
				if(g.status.isMonsterCheck1() == false) {
					if(n.equalsIgnoreCase("bed")) {
						p.kill();
						g.status.setDone(true);
						g.setOutput("You hide under the bed, but find you are not alone. A horde of hungry monsters is hiding with you. Didn't your parents check for monsters every night?");
					}
				} else if(g.status.isMonsterCheck1() == true && g.status.isSitting() == false) {
					if(n.equalsIgnoreCase("bed")) {
						g.status.setSitting(true);
						g.setOutput("You hide under the bed. Nobody will ever find you here! It is a bit cramped, though.");
					}
				} else if(g.status.isMonsterCheck1() == true && g.status.isSitting() == true) {
					if(n.equalsIgnoreCase("bed")) {
						g.status.setSitting(false);
						g.setOutput("You get out from under the bed.");
					}
				}
				break;
		}
	}

}
