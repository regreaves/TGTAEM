package state;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import command.Action;
import command.Parser;
import objects.Item;
import objects.Player;
import objects.Room;
import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;

public class Game {
	User user;
	Player player;
	Parser parser;
	DerbyDatabase db;

	HashMap<String, Room> map = new HashMap<>();
	ArrayList<Item> items = new ArrayList<>();

	boolean done = false;
	boolean newGame = true;

	String command = "";

//	ArrayList<NPC> npcs = new ArrayList<>();
//	ArrayList<Action> actionsTaken = new ArrayList<>();
//	ArrayList<String> roomsVisited = new ArrayList<>();
//	int playerDeaths = 0;
//	int victories = 0;
//	ArrayList<Checkpoint> checkpoints = new ArrayList<>();

	// not done here yet
	public Game() throws SQLException {
		DatabaseProvider.setInstance(new DerbyDatabase());
//		this.user = user;
		this.player = new Player();
		this.db = DatabaseProvider.getInstance();
		this.parser = new Parser(db);
		map = db.getMap();
		items = db.getItems();
		db.placeItems(map, items);
	}

	// for use with jsp

	public boolean isDone() {
		return done;
	}

	public boolean isNewGame() {
		return newGame;
	}

	public String getAction() {
		String s = "";
		Action a = parse(command);
		if (a != null) {
			s = performAction(a);
		} else {
			s = "I don't understand \"" + command + '\"' + " Please try something else.";
		}
		return s;
	}

	public String getRoomOne() {
		newGame = false;
		return loadRoom("1");
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setHere(String id) {
		player.setLocation(id);
	}

	public String here() {
		return player.getLocation();
	}

	// called by proxy through above methods
	// probably could change to private

	public Action parse(String input) {
		Action a = parser.getAction(input);
		return a;
	}

	public ArrayList<Item> itemsHere() {
		return map.get(here()).getItems();
	}

	public String performAction(Action a) {
		String display = "";
		int method = a.getMethod();
		switch (method) {
		case 0:
			display = go(a);
			break;
		case 1:
			display = take(a);
			break;
		case 2:
			display = drop(a);
			break;
		case 3:
			display = examine(a);
			break;
		}

		return display;
	}

	private String go(Action a) {
		String display = "";
		String id = "0";
		String direction = a.noun();
		switch (direction) {
		case "north":
			id = map.get(here()).getNorth();
			break;
		case "northeast":
			id = map.get(here()).getNorthEast();
			break;
		case "east":
			id = map.get(here()).getEast();
			break;
		case "southeast":
			id = map.get(here()).getSouthEast();
			break;
		case "south":
			id = map.get(here()).getSouth();
			break;
		case "southwest":
			id = map.get(here()).getSouthWest();
			break;
		case "west":
			id = map.get(here()).getWest();
			break;
		case "northwest":
			id = map.get(here()).getNorthWest();
			break;
		case "up":
			id = map.get(here()).getUp();
			break;
		case "down":
			id = map.get(here()).getDown();
			break;
		}

		if (id.equals("0")) {
			display = "You can't go that way.";
		} else {
			map.get(here()).setVisited(true);
			db.setVisited(here());
			display = loadRoom(id);
			player.move(id);
		}
		return display;
	}

	private String take(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		for (Item i : roomItems) {
			if (i.getName().equals(obj)) {
				if (i.canTake()) {
//					player.get(i);
//					Room r = map.get(here());
//					r.removeItem(i);
					db.takeItem(i.getID());
					display = "You take the " + obj + ".";
					return display;
				} else {
					display = "You can't take that!";
					return display;
				}
			}
		}
		display = "You can't do that.";
		return display;
	}

	private String drop(Action a) {
		String display = "";
		String obj = a.noun();
//		Item i = player.getInventory().getItemByName(obj);
//		Room r = map.get(here());
//		r.addItem(player.drop(i));
		String id = db.getItemID(obj);
		db.dropItem(id, here());
		display = "You drop the " + obj + ".";
		return display;
	}

	private String examine(Action a) {
		String display = "";
		String obj = a.noun();
		if (obj.equals("room")) {
			display = map.get(here()).look();
			return display;
		} else {
			ArrayList<Item> roomItems = itemsHere();
			for (Item i : roomItems) {
				if (i.getName().equals(obj)) {
					display = i.getInventDscrpt();
					return display;
				}
			}
		}
		display = "There's no " + obj + " for you to examine.";
		return display;
	}

	public String loadRoom(String id) {
		Room r = map.get(id);
		return r.loadRoom();
	}

//	public void makeNPCS(String npcFile) {
//
//	}
//
//	public void addActionTaken(Action action) {
//		actionsTaken.add(action);
//		return;
//	}
//
//	public void addRoomVisited(String roomID) {
//		roomsVisited.add(roomID);
//		return;
//	}
//
//	public void playerDies() {
//		playerDeaths++;
//		return;
//	}
//
//	public void playerWins() {
//		victories++;
//		return;
//	}
//
//	public void saveGame() { // TODO with checkpoint
//		return;
//	}

//	public void loadGame(Checkpoint checkpoint) { // TODO: implement with checkpoint
//		this.user = user;
//		this.player = player;
//		this.parser = parser;
//		this.items = items;
//		this.npcs = npcs;
//		this.map = map;
//		this.actionsTaken = actionsTaken;
//		this.roomsVisited = roomsVisited;
//		this.playerDeaths = playerDeaths;
//		this.victories = victories;
//		this.checkpoints = checkpoints;
//	}
//
//	public void teleport(String roomID) {
//		player.location = roomID; // TODO: I think more is needed here
//		return;
//	}

	public static void main(String[] args) throws SQLException {
//		User u = new User("user", "user");
//		Player p = new Player();
		Game g = new Game();

		Scanner in = new Scanner(System.in);

		System.out.println(g.loadRoom("1"));
		while (!g.done) {
			g.setCommand(in.nextLine());
			System.out.println(g.getAction());
		}
		in.close();
	}
}
