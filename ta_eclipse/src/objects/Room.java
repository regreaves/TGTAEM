package objects;

import java.util.ArrayList;

import command.Action;

public class Room {
	String ID; //the room's id
	String displayName; //the room's display name
	String description; //the room's description
	Connections connections = new Connections(); //the room's connections to other rooms
	ArrayList<Item> items = new ArrayList<>(); //an arraylist of items in the room
	ArrayList<NPC> npcs = new ArrayList<>(); //an arraylist of npcs in the room
	ArrayList<Player> players = new ArrayList<>(); //an arraylist of players in the room
	boolean visited; //whether the room has been visited by the player or not
	boolean dark; //whether the room is dark or not
	boolean locked; //whether the room is locked or not
	String temp; //the room's temperature description

	public Room() {

	}

	public String loadRoom() { //returns the description the player sees when first visiting a room
		String dscrpt = displayName;
		if (!visited) { //if the room hasn't been visited

			if(dark) { //if the room is dark, add related text
				dscrpt += ":<br>" +  "It is dark here. You can see nothing. You are likely to be eaten by a grue.";
				if(!temp.equals("")){ //if temperature doesn't equal nothing, output related text
					dscrpt += " " + temp;
				}	
				return dscrpt; //return the description
			}
			dscrpt += ":<br>" + this.description; //add the room description after the display name
			for (Item i : items) { //for each item within items
				if (!(i.init_dscrpt.contentEquals("null")) && !(i.moved())) { //if there is an initial description and the item hasn't been moved
					dscrpt += " " + i.init_dscrpt; //add the item's initial description to the room's description
				} else if (i.moved() && !(i.vowel()) && !(i.plural())) { //if the item has been moved and isn't a vowel or plural
					dscrpt += " There is a " + i.getName() + " here."; //add the appropriate text
				} else if (i.moved() && i.vowel() && !(i.plural())) { //if the item has been moved and is a vowel but not plural
					dscrpt += " There is an " + i.getName() + " here."; //add the appropriate text
				} else if (i.moved() && i.plural()) { //if the item has been moved and is plural
					dscrpt += " There are " + i.getName() + " here."; //add the appropriate text 
				}
			}
			for (NPC n : npcs) { //for each npc with npcs
				if (!(n.description.contentEquals("null"))) { //if npc has a description
					dscrpt += " " + n.description; //add npc description
				}
			}
			if(!temp.equals("")){ //if temp does not equal nothing
				dscrpt += " " + temp; //add temp description
			}	
		}
		return dscrpt; //return the description
	}

	public String look() { //returns the description of the room + items + npcs seen by the player
		String dscrpt = this.description; //get the room's description
		if(!temp.equals("")){ //if temp does not equal nothing
			dscrpt += " " + temp; //add temp description
		}	
		if(dark) { //if the room is dark, add related text
			dscrpt += " It is dark here. You can see nothing. You are likely to be eaten by a grue.";
			return dscrpt; //return the description
		}
		for (Item i : items) { //for each item within items
			if (!(i.init_dscrpt.contentEquals("null")) && !(i.moved())) { //if there is an initial description and the item hasn't been moved
				dscrpt += " " + i.init_dscrpt; //add the item's initial description to the room's description
			} else if (i.moved() && !(i.vowel()) && !(i.plural())) { //if the item has been moved and isn't a vowel or plural
				dscrpt += " There is a " + i.getName() + " here."; //add the appropriate text
			} else if (i.moved() && i.vowel() && !(i.plural())) { //if the item has been moved and is a vowel but not plural
				dscrpt += " There is an " + i.getName() + " here."; //add the appropriate text
			} else if (i.moved() && i.plural()) { //if the item has been moved and is plural
				dscrpt += " There are " + i.getName() + " here."; //add the appropriate text
			}
		}
		for (NPC n : npcs) { //for each npc in npcs
			dscrpt += " There is a " + n.getName() + " here."; //add the appropriate text
		}
		return dscrpt; //return the description
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

	public void addDescription(String description) { //adds description to the room's current description
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

	public void addItems(ArrayList<Item> items) { //adds items to the room
		items.forEach(this::addItem); // #swag
		return;
	}

	public void addItem(Item item) { //adds an item to the room
		this.items.add(item);
		return;
	}

	public Item removeItem(Item item) { //removes an item from the room
		int x = this.items.indexOf(item);
		return this.items.remove(x);
	}

	public ArrayList<NPC> getNPCs() {
		return npcs;
	}

	public void addNPC(NPC npc) { //adds an npc to the room
		this.npcs.add(npc);
		return;
	}

	public void removeNPC(NPC npc) { //removes an npc from the room
		int x = this.npcs.indexOf(npc);
		this.npcs.remove(x);
		return;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) { //adds a player to the room
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

	public void addConnection(String action, String destination) { //adds a connection to the room
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
