package state;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import command.Action;
import command.Parser;
import objects.Item;
import objects.NPC;
import objects.Player;
import objects.Room;
import sqlDB.DBUtil;
import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;

public class Game {
	Player player;
	Parser parser;
	DerbyDatabase db;

	//R
	HashMap<String, String> shortcuts = new HashMap<>();
	HashMap<String, Room> map = new HashMap<>();
	ArrayList<Item> items = new ArrayList<>();
	ArrayList<NPC> npcs = new ArrayList<>();

	boolean done = false;
	boolean newGame = true;

	String command = "";

	// not done here yet
	public Game() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		this.db = DatabaseProvider.getInstance();

		try {
			this.parser = new Parser(db);
			player = db.getPlayer();
			map = db.getMap();
			items = db.getItems();
			npcs = db.getNPCs();
			db.placePlayer(map, player);
			db.placeItems(map, items);
			db.placeNPCs(map, npcs);
			shortcuts = db.getShortcuts();
			db.addConnections(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// for use with jsp

	public boolean isDone() {
		return done;
	}

	public boolean isNewGame() {
		return newGame;
	}

	public String getAction() throws SQLException {
		String s = "";
		Action a = parse(command);
		if (a != null) {
			s = performAction(a);
		} else {
			s = "I don't understand \"" + command + '\"' + " Please try something else.";
		}
		return s;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String here() {
		return player.getLocation();
	}

	// called by proxy through above methods
	// probably could change to private

	public void reset() throws SQLException {
		db.clearAll();
		db.fillAll();
		remake();
	}

	public void remake() throws SQLException {
		this.parser = new Parser(db);
		map = db.getMap();
		player = db.getPlayer();
		items = db.getItems();
		npcs = db.getNPCs();
		db.placePlayer(map, player);
		db.placeItems(map, items);
		db.placeNPCs(map, npcs);
		//R
		shortcuts = db.getShortcuts();
		db.addConnections(map);
	}

	public Action parse(String input) {
		String s = shortcuts.get(input);
		Action a;
		
		if (s == null) {
			a = parser.getAction(input);
		} else {
			a = parser.getAction(s);
		}
		
		return a;
	}

	public ArrayList<Item> itemsHere() {
		return map.get(here()).getItems();
	}

	public Room room() {
		return map.get(here());
	}

	public String performAction(Action a) throws SQLException {
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
		case 6:
			display = hideUnder(a);
			break;
		case 7:
			display = flip(a);
			break;
		case 8:
			display = reset(a);
			break;
		case 9:
			display = dump(a);
			break;
		case 10:
			display = throwItem(a);
			break;
		case 11:
			display = breakItem(a);
			break;
		case 12:
			display = drink(a);
			break;
		case 13:
			display = smell(a);
			break;
		case 14:
			display = eat(a);
			break;
		case 15:
			display = wear(a);
			break;
		case 23:
			display = jumpOn(a);
			break;
		case 24:
			display = sleep(a);
			break;
		case 26:
			display = quit(a);
			break;
		}

		return display;
	}

	private String go(Action a) throws SQLException {
		String display = "";
		String id = room().getDestination(a.getName());
		if (id.equals("0")) {
			display = "You can't go that way.";
		} else {
			room().setVisited(true);
			db.setVisited(here());
			display = loadRoom(id);
			db.movePlayer(id, player);
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
				if (i.getWeight() < 30 && player.getInventory().checkWeight(i)) { // TODO - hardcoded maxSize but this
																					// would be found in the player
																					// table in the future
//					player.get(i);
//					Room r = map.get(here());
//					r.removeItem(i);
					db.takeItem(i.getID());
					display = "You take the " + obj + ".";
					return display;
				} else {
					display = "You can't take that, it's too heavy!";
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

	private String hideUnder(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("table")))) {
			display = "You hide under the table. Nothing in here can hurt you, though, so you get back up.";
			return display;
		} else if (roomItems.contains(roomItems.get(obj.indexOf("bed")))) {
			display = "You hide under the bed. Once you realize the coast is clear, you crawl back out.";
			return display;
		}
		display = "There is nothing to hide under in here.";
		return display;
	}

	private String flip(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("table")))) {
			display = "You flip the table in a fit of rage. Everything on the table goes flying.";
			return display;
		}
		display = "There is nothing to flip in here.";
		return display;
	}

	private String reset(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("table")))) {
			display = "You calm down and put the table back up.";
			return display;
		}
		display = "There is nothing to reset in here.";
		return display;
	}

	private String dump(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("vase")))) {
			display = "You dump the contents of the vase out onto the floor. It\'s a mess.";
			return display;
		}
		display = "There is nothing to dump in here.";
		return display;
	}

	private String throwItem(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("vase")))) {
			display = "You throw the vase against a wall and it shatters into a million pieces. The water stains the wall and the flowers fall to the floor.";
			return display;
		}
		display = "There is nothing to throw in here.";
		return display;
	}

	private String breakItem(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("vase")))) {
			display = "You smash the vase, spilling water everywhere and while leaving the flowers unharmed.";
			return display;
		}
		display = "There is nothing to break in here.";
		return display;
	}

	private String wear(Action a) {
		String display = "";
		String obj = a.noun();
		take(a);
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("coat")))) {
			display = "You take the coat and put it on. You feel warm and look very fashionable!";
			return display;
		}
		display = "There is nothing to wear in here.";
		return display;
	}

	private String drink(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("water")))) {
			display = "You drink the water in the vase. You feel sick to your stomach and a little dizzy.";
			return display;
		}
		display = "There is nothing to drink in here.";
		return display;
	}

	private String smell(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("flowers")))) {
			display = "You smell the flowers in the vase. They have a delightful scent!";
			return display;
		}
		display = "There is nothing to smell in here.";
		return display;
	}

	private String eat(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("flowers")))) {
			display = "You eat the flowers in the vase. They taste horrible and make you feel woozy.";
			return display;
		}
		display = "There is nothing to eat in here.";
		return display;
	}

	private String jumpOn(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("bed")))) {
			display = "Wheeeeeee!";
			return display;
		}
		display = "There is no bed to jump on in here.";
		return display;
	}

	private String sleep(Action a) {
		String display = "";
		String obj = a.noun();
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("bed")))) {
			display = "You take a quick nap in the " + obj + ". You feel refreshed!";
			return display;
		}
		display = "You cannot sleep in the " + obj + ", no matter how hard you try.";
		return display;
	}

	private String quit(Action a) {
		String display = "";
		display = "Quitting game...";
		done = true;
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

		System.out.println("New Game? Y/N");
		String response = in.nextLine();
		if (response.equalsIgnoreCase("y")) {
			g.reset();
		}

		System.out.println(g.loadRoom("1"));
		while (!g.done) {
			g.setCommand(in.nextLine());
			System.out.println(g.getAction());
			g.remake();
		}
		in.close();
	}
}