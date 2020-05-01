package objects;

import java.util.ArrayList;

import command.Action;

public class Room {
	String ID;
	String displayName;
	String description;
	Connections connections = new Connections();
	ArrayList<Item> items = new ArrayList<>();
	ArrayList<NPC> npcs = new ArrayList<>();
	ArrayList<Player> players = new ArrayList<>();
	boolean visited;
	boolean dark;
	boolean locked;
	String temp;

	public Room() {

	}

	public String loadRoom() {
		String dscrpt = displayName;
		if (!visited) {

			if(dark) {
				dscrpt += ":<br>" +  "It is dark here. You can see nothing. You are likely to be eaten by a grue.";
				if(!temp.equals("")){
					dscrpt += " " + temp;
				}	
				return dscrpt;
			}
			dscrpt += ":<br>" + this.description;
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
			if(!temp.equals("")){
				dscrpt += " " + temp;
			}	
		}
		return dscrpt;
	}

	public String look() {
		String dscrpt = this.description;
		if(!temp.equals("")){
			dscrpt += " " + temp;
		}	
		if(dark) {
			dscrpt += " It is dark here. You can see nothing. You are likely to be eaten by a grue.";
			return dscrpt;
		}
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

	public Connections getConnections() {
		return connections;
	}

	public void setConnections(Connections connections) {
		this.connections = connections;
		return;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void addItems(ArrayList<Item> items) {
		items.forEach(this::addItem); // #swag
		return;
	}

	public void addItem(Item item) {
		this.items.add(item);
		return;
	}

	public Item removeItem(Item item) {
		int x = this.items.indexOf(item);
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

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		this.players.add(player);
		return;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
		return;
	}

	public void addConnection(String action, String destination) {
		this.connections.put(action, destination);
		return;
	}

	public String getDestination(String action) {
		return this.connections.getDestination(action);
	}

	public boolean dark() {
		return dark;
	}

	public void setDark(boolean d) {
		this.dark = d;
	}

	public boolean locked() {
		return locked;
	}

	public void setLocked(boolean l) {
		this.locked = l;
	}

	public String temp() {
		return temp;
	}

	public void setTemp(int t) {
		if (t == 1) {
			this.temp = "It is rather hot here.";
		} else if (t == 0) {
			this.temp = "";
		} else if (t == -1) {
			this.temp = "It is really cold here.";
		} else {
			System.out.println("temp error room: " + this.ID);
		}
	}
	
	public void setTemp(String t) {
		this.temp = t;
	}

	public String toString() {
		String out = "ID: " + ID + "Name: " + displayName;
		return out;

	}
	// TODO add NPC based methods
}
