package actions;

import java.sql.SQLException;
import java.util.ArrayList;

import command.Action;
import objects.Item;
import objects.ItemContainer;
import state.Game;

public class OpenContainer implements Updater {
	public static String name = "open";

	@Override
	public void update(Game g, Action a) throws SQLException {
		String obj = a.noun(); //the object referenced by the action
		ArrayList<Item> roomItems = g.itemsHere(); //the items within the player's current room
		for (Item i : roomItems) { //for each item within the room
			if (i.getName().equals(obj)) { //if the item's name equals the object referenced by the action
				if (i.isContainer()) { //if that item is a container
					ArrayList<Item> items = ((ItemContainer) i).getItems(); //get the items out of the item container
					if (obj.equals("mailbox")) { //if the object is a mailbox
						g.setOutput("You open the mailbox. You find the following: <br>"); //output the appropriate text
						for (Item j : items) { //for each item in the item container
							j.setHidden(false); //set the item to not hidden 
							g.addOutput("--" + j.getName() + "<br>"); //add the item's name to the output
						}
						return; 
					}
					else { //if the item is not a mailbox
						g.setOutput("You open the container. You find the following: <br>"); //output the appropriate text
						for (Item j : items) { //for each item in the item container
							j.setHidden(false); //set the item to not hidden
							g.addOutput("--" + j.getName() + "<br>"); //add the item's name to the output
						}
						return;
					}
				} else { //if the item name doesn't match the object referenced by the action
					g.setOutput("You can't open that."); //output related text
					return;
				}
			} else { //if the item isn't in the room or something else
				g.setOutput("You can't do that."); //output related text
			}
		}
	}
}
