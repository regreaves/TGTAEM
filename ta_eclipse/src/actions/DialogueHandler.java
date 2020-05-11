package actions;

import java.sql.SQLException;

import command.Action;
import objects.NPC;
import state.Game;

public class DialogueHandler implements Updater{
	public static String name = "dialogue";
	@Override
	public void update(Game g, Action a) throws SQLException {
		// TODO Auto-generated method stub
		String s = g.getCommand();
		NPC n = g.room().getNPCs().get(0);
		n.getDialogue().parseNewickTree(g.dialogueTrees.get(0));
		switch (s) {
			case "1":
				g.setOutput(g.dialogue.get(n.getDialogue().getTreeList().get(0).getLeftData()).getDialogue() + "<br>" + n.getName() + " walks away.");
				g.status.setDialogue(false);
				return;
			case "2":
				g.setOutput(g.dialogue.get(n.getDialogue().getTreeList().get(0).getRightData()).getDialogue() + "<br>" + n.getName() + " walks away.");
				g.status.setDialogue(false);
				return;
			case "walk away":
				g.al.addAction(g.parse(s));
				g.setOutput("You walk away from the conversation.");
				g.status.setDialogue(false);
				return;
			default:
				g.setOutput("<em>Please enter a valid option.</em>");
				return;
		}
	}

}
