package state;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;

import command.Action;
import command.ActionLog;
import command.Parser;
import objects.Dialogue;
import objects.Inventory;
import objects.Item;
import objects.NPC;
import objects.Player;
import objects.Room;
import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;
import actions.*;

public class Game {
	Player player; // the player the user controls
	Parser parser; // how user input is made usable
	public DerbyDatabase db; // reference to database

	HashMap<String, String> shortcuts = new HashMap<>(); // Map of <shortcut, action.name>
	public HashMap<String, Room> map = new HashMap<>(); // Map of <Room.id, Room>
	HashMap<String, Updater> updates = new HashMap<>(); // map of <Updater.name, Updater>
	public HashMap<String, Dialogue> dialogue = new HashMap<>();

	ArrayList<Item> items = new ArrayList<>(); // all items in game
	ArrayList<NPC> npcs = new ArrayList<>(); // all NPCs in game
	public ArrayList<String> dialogueTrees = new ArrayList<>();

	public ActionLog al = new ActionLog(); // log of all actions performed by user
	public Status status = new Status(); // the current status of the game

	String command = ""; // user input
	String output = ""; // game output to interface

	public Game() { // constructor
		DatabaseProvider.setInstance(new DerbyDatabase()); // get db instance
		this.db = DatabaseProvider.getInstance(); // set reference

		try {
			this.parser = new Parser(db);
			player = db.getPlayer();
			map = db.getMap();
			items = db.getItems();
			npcs = db.getNPCs();
			dialogue = db.getDialogue();
			dialogueTrees = db.getDialogueTree();
			db.placeDialogue(dialogue, npcs);
			db.placePlayer(map, player); // place player in map
			db.placeItems(map, items); // place items in rooms
			db.placeNPCs(map, npcs); // place npcs in rooms
			shortcuts = db.getShortcuts();
			db.addConnections(map);
			inventory().addItems(db.getInventory()); // add items in inventory Table to player inventory
			al.setHistory(db.getActionLog()); // add performed actions from table to action lof
			status = db.loadStatus(); // load status from db table
			getUpdates();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reset() throws SQLException { // reset database to starting condition
		db.clearAll();
		db.fillAll();
		remake();
		db.addRowToLog(loadRoom("1"));
	}

	public void remake() throws SQLException { // remake game at starting condition
		this.parser = new Parser(db);
		map = db.getMap();
		player = db.getPlayer();
		items = db.getItems();
		npcs = db.getNPCs();
		dialogue = db.getDialogue();
		dialogueTrees = db.getDialogueTree();
		db.placePlayer(map, player);
		db.placeItems(map, items);
		db.placeNPCs(map, npcs);
		db.placeDialogue(dialogue, npcs);
		shortcuts = db.getShortcuts();
		db.addConnections(map);
		getUpdates();
	}

	// put Updaters into map of <Updater.name, Updater>
	public void getUpdates() { // Updaters can be added here as they are made and the game is expanded
		updates.put(Move.name, new Move());
		updates.put(TakeItem.name, new TakeItem());
		updates.put(DropItem.name, new DropItem());
		updates.put(CheckInventory.name, new CheckInventory());
		updates.put(Examine.name, new Examine());
		updates.put(TextOnly.name, new TextOnly());
		updates.put(Switch.name, new Switch());
		updates.put(Talk.name, new Talk());
		updates.put(DialogueHandler.name, new DialogueHandler());
		updates.put(OpenContainer.name, new OpenContainer());
		updates.put(Sitting.name, new Sitting());
		updates.put(MonsterCheck.name, new MonsterCheck());
		updates.put(Search.name, new Search());
		updates.put(Quit.name, new Quit());
	}

	public String getLog() {
		return db.getLog();
	}

	public boolean isDone() {
		return status.isDone();
	}
	
	public void setDone(boolean done) {
		status.setDone(done);
	}
	
	public boolean isQuit() {
		return status.isQuit();
	}

	public void setQuit(boolean quit) {
		status.setIsQuitting(quit);
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

	public void addOutput(String out) { // add addtional output to existing
		this.output += out;
	}

	public String output() { //return output
		return output;
	}

	public Player player() { //get player
		return player;
	}
	
	public String here() { //get player's current room ID
		return player.getLocation();
	}

	public Room room() {  //get player's current room as Room
		return map.get(here());
	}
	
	public ArrayList<Item> itemsHere() {  // get all items in current room
		return map.get(here()).getItems();
	}

	public Inventory inventory() {  // get player's inventory
		return player.getInventory();
	}

	public String loadRoom(String id) { // load the description of the specified room
		Room r = map.get(id);
		return r.loadRoom();
	}

	// parse input into action
	public Action parse(String input) { // parse user input into Action
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
	public String getAction() throws SQLException, JsonProcessingException {
		String s = "";
		Action a = parse(command);
		if(status.isDialogue() == true) {
			Updater u = updates.get("dialogue");
			u.update(this, al.lastAction());
			s = output;
		} else if (a != null) {
			al.addAction(a);
			db.addAction(a);
			s = performAction(a);
		} else {
			s = "I don't understand \"" + command + "." + '\"' + " Please try something else.";
		}
		status.advance();
		db.addRowToLog("<br>" + s);
//		db.saveStatus(status).toJSON());
		db.saveStatus(status);//.toJSON());
		return s;
	}

	// execute Updater.update based on Action
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

	// move player to specified room
	public void movePlayer(String id) throws SQLException {
		db.movePlayer(id, player);
		player.move(id);
	}

	// place specified item in player inventory
	public void takeItem(Item i) {
		String id = i.getID();
		db.takeItem(id);
		inventory().addItem(i);
	}
	
	// remove specified item (by name) from inventory, place in room.
	public void dropItem(String obj) {
		String id = db.getItemID(obj);
		db.dropItem(id, here());
		inventory().dropItem(inventory().getItemByName(obj));
	}
}
