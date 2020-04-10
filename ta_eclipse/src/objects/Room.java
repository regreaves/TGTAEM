package objects;

import java.util.ArrayList;

import command.Action;

public class Room {
	String ID;
	String displayName;
	String description;
	ArrayList<String> connections = new ArrayList<>();
	ArrayList<Item> items = new ArrayList<>();
	ArrayList<NPC> npcs = new ArrayList<>();
	ArrayList<Action> actions = new ArrayList<>();
	boolean visited;

	public Room(String ID, String displayName, String description, boolean visited, ArrayList<String> connections) {
		this.ID = ID;
		this.displayName = displayName;
		this.description = description;
		this.visited = visited;
		this.connections = connections;
	}

	public String loadRoom() {
		String dscrpt = displayName;
		if (!visited) {
			dscrpt += ":" + '\n' + this.description;
			for (Item i : items) {
				if (!(i.init_dscrpt.contentEquals("null")) && !(i.moved())) {
					dscrpt += " " + i.init_dscrpt;
				} else if (i.moved() && !(i.vowel()) && !(i.plural())) {
					dscrpt += " There is a " + i.getName() + " here.";
				} else if (i.moved() && i.vowel() && !(i.plural())) {
					dscrpt += " There is an " + i.getName() + " here.";
				} else if (i.moved() && i.plural()) {
					dscrpt += " There are " + i.getName() + " here.";
				}
			}
			for (NPC n : npcs) {
				if (!(n.description.contentEquals("null"))) {
					dscrpt += " " + n.description;
			}
			}
		}
		return dscrpt;
	}

	public String look() {
		String dscrpt = this.description;
		for (Item i : items) {
			if (!(i.init_dscrpt.contentEquals("null")) && !(i.moved())) {
				dscrpt += " " + i.init_dscrpt;
			} else if (i.moved() && !(i.vowel()) && !(i.plural())) {
				dscrpt += " There is a " + i.getName() + " here.";
			} else if (i.moved() && i.vowel() && !(i.plural())) {
				dscrpt += " There is an " + i.getName() + " here.";
			} else if (i.moved() && i.plural()) {
				dscrpt += " There are " + i.getName() + " here.";
			}
		}
		for (NPC n : npcs) {
			dscrpt += " There is a " + n.getName() + " here.";
		}
		return dscrpt;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
		return;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		return;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		return;
	}

	public void addDescription(String description) {
		this.description += description;
		return;
	}

	public ArrayList<String> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<String> connections) {
		this.connections = connections;
		return;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void addItems(ArrayList<Item> items) {
		items.forEach(this::addItem);
		// #swag
	}

	public void addItem(Item item) {
		this.items.add(item);
		this.actions.addAll(item.getActions());
	}

	public Item removeItem(Item item) {
		int x = this.items.indexOf(item);
		actions.removeAll(item.getActions());
		return this.items.remove(x);
	}

	public ArrayList<NPC> getNPCs() {
		return npcs;
	}

	public void addNPC(NPC npc) {
		this.npcs.add(npc);
	}

	public void removeNPC(NPC npc) {
		int x = this.npcs.indexOf(npc);
		this.npcs.remove(x);
		return;
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	public void addActions(ArrayList<Action> newActions) {
		actions.addAll(newActions);
	}

	public void removeActions(ArrayList<Action> newActions) {
		actions.removeAll(newActions);

	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
		return;
	}

	public String getNorth() {
		return connections.get(0);
	}

	public String getNorthEast() {
		return connections.get(1);
	}

	public String getEast() {
		return connections.get(2);
	}

	public String getSouthEast() {
		return connections.get(3);
	}

	public String getSouth() {
		return connections.get(4);
	}

	public String getSouthWest() {
		return connections.get(5);
	}

	public String getWest() {
		return connections.get(6);
	}

	public String getNorthWest() {
		return connections.get(7);
	}

	public String getUp() {
		return connections.get(8);
	}

	public String getDown() {
		return connections.get(9);
	}

	// TODO add NPC based methods
}
