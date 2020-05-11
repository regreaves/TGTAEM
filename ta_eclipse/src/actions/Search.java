package actions;

import java.sql.SQLException;

import command.Action;
import objects.Item;
import objects.Room;
import state.Game;

public class Search implements Updater{
	public static String name = "search";
	@Override
	public void update(Game g, Action a) throws SQLException {
		Room r = g.room();
		String obj = a.noun();
		for(Item i : r.getItems()) {
			if(obj.equalsIgnoreCase("couch")) {
				if(i.hidden() && g.status.isSearchCouch() == false) {
					i.setHidden(false);
					g.setOutput("You finally found that remote you lost years ago! You decide to hold onto it for now.");
					g.takeItem(i);
					g.status.setSearchCouch(true);
					return;
				} else if (g.status.isSearchCouch()) {
					g.setOutput("Nothing to see here.");
					return;
				}
			} else if(obj.equalsIgnoreCase("grass")) {
				if(r.getID().equalsIgnoreCase("11")) {
					if(i.hidden() && g.status.isSearchGrass1() == false) {
						i.setHidden(false);
						g.setOutput("A wild Pikachu doll appears! You take it with you.");
						g.takeItem(i);
						g.status.setSearchGrass1(true);
						return;
					} else if (g.status.isSearchGrass1()) {
						g.setOutput("Nothing to see here.");
						return;
					}	
				} else if(r.getID().equalsIgnoreCase("13")) {
					if(i.hidden() && g.status.isSearchGrass2() == false) {
						i.setHidden(false);
						g.setOutput("You find a heavy sword in the tall grass. You take it with you before your dad mows the lawn.");
						g.takeItem(i);
						g.status.setSearchGrass2(true);
						return;
					} else if (g.status.isSearchGrass2()) {
						g.setOutput("Nothing to see here.");
						return;
					}	
				}
				
			} else {
				g.setOutput("Nothing to see here.");
				return;
			}
		}
	}

}
