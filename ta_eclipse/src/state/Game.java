package state;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import command.Action;
import command.ActionLog;
import command.Parser;
import objects.Inventory;
import objects.Item;
import objects.NPC;
import objects.Player;
import objects.Room;
import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;
import actions.*;

public class Game {
	Player player;
	Parser parser;
	public DerbyDatabase db;

	HashMap<String, String> shortcuts = new HashMap<>();
	public HashMap<String, Room> map = new HashMap<>();
	HashMap<String, Updater> updates = new HashMap<>();

	ArrayList<Item> items = new ArrayList<>();
	ArrayList<NPC> npcs = new ArrayList<>();

	public ActionLog al = new ActionLog();
	public Status s = new Status();

	boolean done = false;

	String command = "";
	String output = "";

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
			al.setHistory(db.getActionLog());
			getUpdates();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reset() throws SQLException {
		db.clearAll();
		db.fillAll();
		remake();
		db.addRowToLog(loadRoom("1"));
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
		al.setHistory(db.getActionLog());
		getUpdates();
	}

	public void getUpdates() {
		updates.put(Move.name, new Move());
		updates.put(TakeItem.name, new TakeItem());
		updates.put(DropItem.name, new DropItem());
		updates.put(CheckInventory.name, new CheckInventory());
		updates.put(Examine.name, new Examine());
		updates.put(TextOnly.name, new TextOnly());
		updates.put(Switch.name, new Switch());
	}
	
	public String getLog() {
		return db.getLog();
	}

	public boolean isDone() {
		return done;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
		db.addRowToLog("<br><br>>" + command);

	}

	public void setOutput(String out) {
		this.output = out;
	}

	public void addOutput(String out) {
		this.output += out;
	}

	public String output() {
		return output;
	}

	public String here() {
		return player.getLocation();
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

	// parse input into action
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

	// get output from action
	public String getAction() throws SQLException {
		String s = "";
		Action a = parse(command);
		if (a != null) {
			al.addAction(a);
			db.addAction(a);
			s = performAction(a);
		} else {
			s = "I don't understand \"" + command + "." + '\"' + " Please try something else.";
		}
		db.addRowToLog("<br>" + s);
		return s;
	}

	public String performAction(Action a) throws SQLException {
		Updater u = updates.get(a.getMethod());
		if (u == null) {
			setOutput("You can't do that.... yet.");
		} else {
			u.update(this, a);
		}
		return output;
	}

	// Updater methods
	public void setVisited() {
		room().setVisited(true);
		db.setVisited(here());
	}

	public void movePlayer(String id) throws SQLException {
		db.movePlayer(id, player);
		player.move(id);
	}

	public void takeItem(Item i) {
		String id = i.getID();
		db.takeItem(id);
		inventory().addItem(i);
	}

	public void dropItem(String obj) {
		String id = db.getItemID(obj);
		db.dropItem(id, here());
		inventory().dropItem(inventory().getItemByName(obj));
	}
}