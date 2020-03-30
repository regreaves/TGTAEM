package objects;

import java.util.*;
import command.*;

public class Room {
	String ID;
	String displayName;
	String description;
	ArrayList<String> connections = new ArrayList<>();
	ArrayList<Item> items = new ArrayList<>();
	ArrayList<NPC> npcs = new ArrayList<>();
	ArrayList<Action> actions = new ArrayList<>();
	boolean visited = false;

	public Room(String ID, String displayName, String description, ArrayList<String> connections) {
		this.ID = ID;
		this.displayName = displayName;
		this.description = description;
		this.connections = connections;
		HashMap<String, Action> map = directions();
		if (!(getNorth().equals("0"))) {
			actions.add(map.get("north"));
		}
		if (!(getNorthEast().equals("0"))) {
			actions.add(map.get("northeast"));
		}
		if (!(getEast().equals("0"))) {
			actions.add(map.get("east"));
		}
		if (!(getSouthEast().equals("0"))) {
			actions.add(map.get("southeast"));
		}
		if (!(getSouth().equals("0"))) {
			actions.add(map.get("south"));
		}
		if (!(getSouthWest().equals("0"))) {
			actions.add(map.get("southwest"));
		}
		if (!(getWest().equals("0"))) {
			actions.add(map.get("west"));
		}
		if (!(getNorthWest().equals("0"))) {
			actions.add(map.get("northwest"));
		}
		if (!(getUp().equals("0"))) {
			actions.add(map.get("up"));
		}
		if (!(getDown().equals("0"))) {
			actions.add(map.get("down"));
		}
	}

	private HashMap<String, Action> directions() {
		Word go = Word.makeWord("go", 1);
		Word n = Word.makeWord("north", 2);
		Word ne = Word.makeWord("northeast", 2);
		Word e = Word.makeWord("east", 2);
		Word se = Word.makeWord("southeast", 2);
		Word s = Word.makeWord("south", 2);
		Word sw = Word.makeWord("southwest", 2);
		Word w = Word.makeWord("west", 2);
		Word nw = Word.makeWord("northwest", 2);
		Word u = Word.makeWord("up", 2);
		Word d = Word.makeWord("down", 2);

		Action north = new Action("go north", go, n, 0);
		Action northeast = new Action("go northeast", go, ne, 0);
		Action east = new Action("go east", go, e, 0);
		Action southeast = new Action("go southeast", go, se, 0);
		Action south = new Action("go south", go, s, 0);
		Action southwest = new Action("go southwest", go, sw, 0);
		Action west = new Action("go west", go, w, 0);
		Action northwest = new Action("go northwest", go, nw, 0);
		Action up = new Action("go up", go, u, 0);
		Action down = new Action("go down", go, d, 0);

		HashMap<String, Action> map = new HashMap<>();
		map.put("north", north);
		map.put("northeast", northeast);
		map.put("east", east);
		map.put("southeast", southeast);
		map.put("south", south);
		map.put("southwest", southwest);
		map.put("west", west);
		map.put("northwest", northwest);
		map.put("up", up);
		map.put("down", down);

		return map;
	}

	public String loadRoom() {
		String dscrpt = this.description;
		for (Item i : items) {
			dscrpt += " " + i.init_dscrpt;
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

	public Item removeItem(Item item, Inventory inventory) {
		int x = this.items.indexOf(item);
		actions.removeAll(item.getActions());
		return this.items.remove(x);
	}

	public ArrayList<NPC> getNPCs() {
		return npcs;
	}

	public void addNPC(NPC npc) {
		this.npcs.add(npc);
		return;
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
