package state;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import command.Action;
import command.ActionHandler;
import command.Parser;
import objects.Inventory;
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

	HashMap<String, String> shortcuts = new HashMap<>();
	HashMap<String, Room> map = new HashMap<>();
	ArrayList<Item> items = new ArrayList<>();
	ArrayList<NPC> npcs = new ArrayList<>();

	ActionHandler ah;

	boolean done = false;

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
			inventory().addItems(db.getInventory());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// for use with jsp

	public boolean isDone() {
		return done;
	}

	public String getAction() throws SQLException {
		String s = "";
		Action a = parse(command);
		if (a != null) {
			ah.addAction(a);
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
		shortcuts = db.getShortcuts();
		db.addConnections(map);
		inventory().addItems(db.getInventory());
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

	public Inventory inventory() {
		return player.getInventory();
	}

	public String loadRoom(String id) {
		Room r = map.get(id);
		return r.loadRoom();
	}
	
	//Updater methods
	private void setVisited() {
		room().setVisited(true);
		db.setVisited(here());
	}

	private void movePlayer(String id) throws SQLException {
		db.movePlayer(id, player);
		player.move(id);
	}

	private void takeItem(String obj) {
		ArrayList<Item> roomItems = itemsHere();
		boolean taken = false;
		for (Item i : roomItems) {
			if (i.getName().equals(obj)) {
				String id = i.getID();
				db.takeItem(id);
				inventory().addItem(i);
				taken = true;
			}
		}
		if (!taken) {
			System.out.println("Error: " + obj + " not taken.");
		}
	}

	private void dropItem(String obj) {
		String id = db.getItemID(obj);
		db.dropItem(id, here());
		inventory().dropItem(inventory().getItemByName(obj));
	}

	//Switch Case for all actions
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
		case 4:
			display = checkInventory(a);
			break;
		case 5:
			display = quit(a);
			break;
		case 6:
			display = wear(a);
			break;
		case 7:
			display = jumpOn(a);
			break;
		case 14:
			display = eat(a);
			break;
		case 15:
			
			break;
		case 23:
			break;
		case 24:
			display = sleep(a);
			break;
		case 26:
			break;
		}

		return display;
	}

	//(Mostly) Fixed Action Methods
	private String go(Action a) throws SQLException {
		String display = "";
		String id = room().getDestination(a.getName());
		if (!ah.hasConnection(a, room())) {
			display = "You can't go that way.";
		} else {
			setVisited();
			movePlayer(id);
			display = loadRoom(id);
		}
		return display;
	}

	private String take(Action a) {
		String display = "";
		String obj = a.noun();
		if (ah.itemInRoom(room(), obj)) {
			if (ah.canTake(a, inventory())) {
				takeItem(obj);
				display = "You take the " + obj + ".";
				return display;
			} else {
				if (obj.equalsIgnoreCase("window")) {
					display = "You can't take the window. That's sorta attached to the house.";
				} else if (obj.equalsIgnoreCase("painting")) {
					display = "As much as you'd like to take and destroy it, you really shouldn't.";
				} else {
					display = "That's too heavy to carry. You can't take that.";
				}
				return display;
			}
		}
		display = "You can't do that.";
		return display;
	}

	private String drop(Action a) {
		String display = "";
		String obj = a.noun();
		if (!ah.hasItem(inventory(), obj)) {
			display = "You don't have that in your inventory to drop.";
			return display;
		}
		dropItem(obj);
		display = "You drop the " + obj + ".";
		return display;
	}

	private String examine(Action a) {
		String display = "<br>>";
		String obj = a.noun();
		if (obj.equals("room")) {
			display += map.get(here()).look();
			return display;
		} else {
			ArrayList<Item> roomItems = itemsHere();
			for (Item i : roomItems) {
				if (i.getName().equals(obj)) {
					display += i.getInventDscrpt();
					return display;
				}
			}
		}
		display += "There's no " + obj + " for you to examine.";
		return display;
	}

	private String checkInventory(Action a) {
		String display = "";
		boolean empty = true;
		if (a.noun().equals("inventory")) {
			display = "<br>>Inventory: <br>";
			ArrayList<String> items = db.listInventory();
			for (String i : items) {
				empty = false;
				display += "--" + i + "<br>";
			}
			if(empty) {
				display += "Your inventory is empty. <br>";
			}
		}
		return display;
	}

	private String quit(Action a) {
		String display = "";
		display = "Quitting game...";
		done = true;
		return display;
	}
	
	//Flexible Action Methods
	


	private String wear(Action a) {
		String display = "";
		String obj = a.noun();
		take(a);
		ArrayList<Item> roomItems = itemsHere();
		if (roomItems.contains(roomItems.get(obj.indexOf("jacket")))) {
			display = "You take the jacket and put it on. You feel warm and look very fashionable!";
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
		if (roomItems.contains(roomItems.get(obj.indexOf("bed")))
				|| roomItems.contains(roomItems.get(obj.indexOf("couch")))) {
			display = "You take a quick nap in the " + obj + ". You feel refreshed!";
			return display;
		}
		display = "You cannot sleep in the " + obj + ", no matter how hard you try.";
		return display;
	}

	


	public static void main(String[] args) throws SQLException {
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