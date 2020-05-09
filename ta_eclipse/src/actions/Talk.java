package actions;

import java.sql.SQLException;
import java.util.ArrayList;

import command.Action;
import objects.NPC;
import objects.Room;
import state.Game;

public class Talk implements Updater{
	public static String name = "talk";
	@Override
	public void update(Game g, Action a) throws SQLException {
		String obj = a.noun();
		Room r = g.room();
		ArrayList<NPC> npcList = r.getNPCs();
		for(NPC n : npcList) {
			if(obj.equals(n.getName())) {
				if(n.getLocation().equals(r.getID())) {
					if(!n.getDialogue().equals(null)) {
						g.status.setDialogue(true);
						g.setOutput("You walk over and start a conversation with " + n.getName() + "<br>");
						g.addOutput("<em>To exit conversation, WALK AWAY.</em><br>");
						g.addOutput(n.getDialogue().getDialogue());
						return;
					}
				}
			} else {
				g.setOutput("You can't talk to " + a.noun() + " here.");
				return;
			}
		}
	}
}
