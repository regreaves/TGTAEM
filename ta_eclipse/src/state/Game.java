package state;

import java.util.*;
import java.io.*;
import java.sql.SQLException;

import command.*;
import objects.*;
import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;

public class Game {
	User user;
	Player player;
	Parser parser;
	DerbyDatabase db;

	HashMap<String, Room> map = new HashMap<>();
	ArrayList<Item> items = new ArrayList<>();

	boolean notDone = true;

//	ArrayList<NPC> npcs = new ArrayList<>();
//	ArrayList<Action> actionsTaken = new ArrayList<>();
//	ArrayList<String> roomsVisited = new ArrayList<>();
//	int playerDeaths = 0;
//	int victories = 0;
//	ArrayList<Checkpoint> checkpoints = new ArrayList<>();

	// not done here yet
	public Game(User user, Player player) throws SQLException {
		DatabaseProvider.setInstance(new DerbyDatabase());
		this.user = user;
		this.player = player;
		this.db = DatabaseProvider.getInstance();
		this.parser = new Parser(db);
		map = db.getMap();
		items = db.getItems();
		db.placeItems(map, items);
	}

	public void makeActions(String file) {

	}

	public String performAction(Action a) {
		String display;
		String currentRoom = player.location;
		if(a.getMethod() == 0)
		{
			String id = "0";
			String direction = a.getNoun().getPrime();
			if(direction.equals("north"))
			{
				id = 
			}
		}
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
		User u = new User("user", "user");
		Player p = new Player();
		Game g = new Game(u, p);

		Scanner in = new Scanner(System.in);

		System.out.println(g.loadRoom("1"));
		while (g.notDone) {

			String input = in.nextLine();
			Action a = g.parser.getAction(input);

			if (a != null) {
//				...this should be g.performAction(a)
//				game::runAction(Action a, String roomId)
//				its within this function that there will be probably 
//				a massive switch case that modifies the game/player state
//				for each combination of location, player state and action
				g.performAction(a);
			} else {
				System.out.println("I don't understand \"" + input + '\"' + " Please try something else.");
			}
		}
		in.close();
//		the best thing you can do to help yourself with this
//		is provide good feedback in console for where you are etc
//		as well as good error messages that describe the problem 
//		and show relevant information alongside
	}
}
