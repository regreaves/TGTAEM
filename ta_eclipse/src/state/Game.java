package state;

import java.util.*;
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

	String location = "1";

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

	private String go(Action a)
	{
		String display = "";
		String id = "0";
		String direction = a.getNoun().getPrime();
		switch (direction) {
		case "north":
			id = map.get(location).getNorth();
			break;
		case "northeast":
			id = map.get(location).getNorthEast();
			break;
		case "east":
			id = map.get(location).getEast();
			break;
		case "southeast":
			id = map.get(location).getSouthEast();
			break;
		case "south":
			id = map.get(location).getSouth();
			break;
		case "southwest":
			id = map.get(location).getSouthWest();
			break;
		case "west":
			id = map.get(location).getWest();
			break;
		case "northwest":
			id = map.get(location).getNorthWest();
			break;
		case "up":
			id = map.get(location).getUp();
			break;
		case "down":
			id = map.get(location).getDown();
			break;
		}

		if (id.equals("0")) {
			display = "You can't go that way.";
		} else {
			map.get(location).setVisited(true);
			display = loadRoom(id);
			location = id;
		}
		return display;
	}
	
	private String take(Action a)
	{
		String display = "";
		return display;
	}
	
	private String drop(Action a)
	{
		String display = "";
		return display;
	}
	
	private String examine(Action a)
	{
		String display = "";
		String obj = a.getNoun().getPrime();
		if(obj.equals("room"))
		{
			display = map.get(location).look();
			return display;
		}
		else
		{
			ArrayList<Item> roomItems = map.get(location).getItems();
			for(Item i : roomItems)
			{
				if(i.getName().equals(obj))
				{
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
				System.out.println(g.performAction(a));
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
