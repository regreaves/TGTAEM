package sqlDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import command.Action;
import command.Word;
import objects.Item;
import objects.NPC;
import objects.Player;
import objects.Room;

public class DerbyDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}

	// DO NOT TOUCH: DATABASE INNER WORKINGS
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	public <ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}

	public <ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();

		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;

			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}

			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}

			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");

		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);

		return conn;
	}
	// </DATABASE INNER WORKINGS>

	// Base Database functions
	public void createTables() { // creates all tables
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				PreparedStatement stmt7 = null;
				PreparedStatement stmt8 = null;
				PreparedStatement stmt9 = null;
				PreparedStatement stmt10 = null;
				PreparedStatement stmt11 = null;
				PreparedStatement stmt12 = null;
				PreparedStatement stmt13 = null;

				try {
					stmt1 = conn.prepareStatement( // words table
							"create table words (" + "	prime varchar(42)," + "	word varchar(42)," + " type int" + ")");
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement( // actions table
							"create table actions (" + "	name varchar(42)," + " verb varchar(42),"
									+ " noun varchar(42)," + " method int " + ")");
					stmt2.executeUpdate();

					stmt3 = conn.prepareStatement( // rooms table
							"create table rooms (" + " id varchar(5) primary key," + " name varchar(42),"
									+ " description varchar(200)," + "visited boolean" + ")");
					stmt3.executeUpdate();

					stmt4 = conn.prepareStatement( // items table
							"create table items (" + " id varchar(5) primary key," + " name varchar(42),"
									+ " init_dscrpt varchar(500)," + " invent_dscrpt varchar(500)," + " hidden boolean,"
									+ " moved boolean," + " vowel boolean," + " plural boolean," + " weight int" + ")");
					stmt4.executeUpdate();

					stmt5 = conn.prepareStatement( // itemMap table
							"create table itemMap (" + " id varchar(5) primary key," + " location varchar(5)" + ")");
					stmt5.executeUpdate();

					stmt6 = conn.prepareStatement( // itemAct table
							"create table itemAct (" + " id varchar(5)," + " action varchar(42)" + ")");
					stmt6.executeUpdate();

					stmt7 = conn.prepareStatement( // inventory table
							"create table invent ( id varchar(5))");
					stmt7.executeUpdate();

					stmt8 = conn.prepareStatement( // NPC table
							"create table npcs (" + " id varchar(5) primary key," + " name varchar(42),"
									+ " health varchar(5)," + " attack varchar(5)," + " defense varchar(5),"
									+ " description varchar(200)" + ")");
					stmt8.executeUpdate();

					stmt9 = conn.prepareStatement( // npcMap table
							"create table npcMap (" + " id varchar(5) primary key," + " location varchar(5)" + ")");
					stmt9.executeUpdate();

					stmt10 = conn.prepareStatement( // player table
							"create table player (" + " id varchar(5) primary key," + " location varchar(5),"
									+ " health varchar(5)," + " attack varchar(5)," + " defense varchar(5)" + ")");
					stmt10.executeUpdate();

					stmt11 = conn.prepareStatement( // shortcuts table
							"create table shortcuts (" + " shortcut varchar(5)," + " action varchar(42)" + ")");
					stmt11.executeUpdate();

					stmt12 = conn.prepareStatement( // connections table
							"create table connections (" + " action varchar(42)," + " origin varchar(5),"
									+ " destination varchar(5)" + ")");
					stmt12.executeUpdate();
					
					stmt13 = conn.prepareStatement( // log table
							"create table log (" + " log_row varchar(1000)" + ")");
					stmt13.executeUpdate();

					return true;
				} finally { // close the things
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
					DBUtil.closeQuietly(stmt9);
					DBUtil.closeQuietly(stmt10);
					DBUtil.closeQuietly(stmt11);
					DBUtil.closeQuietly(stmt12);
					DBUtil.closeQuietly(stmt13);
				}
			}
		});

	}

	public void loadInitialData() { // load data into tables
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Word> wordList;
				List<Action> actionList;
				List<Room> roomList;
				List<Item> itemList;
				List<NPC> npcList;
				List<Player> playerList;
				List<Pair<String, String>> itemMap;
				List<Pair<String, String>> npcMap;
				List<Pair<String, String>> itemAction;
				// R
				List<Pair<String, String>> shortcutList;
				List<Pair<String, Pair<String, String>>> connections;

				try { // get info from csvs
					wordList = InitialData.getWords();
					actionList = InitialData.getActions();
					roomList = InitialData.getRooms();
					itemList = InitialData.getItems();
					npcList = InitialData.getNPCs();
					playerList = InitialData.getPlayers();
					itemMap = InitialData.getItemMap();
					npcMap = InitialData.getNPCMap();
					itemAction = InitialData.getItemActions();
					shortcutList = InitialData.getShortcuts();
					connections = InitialData.getConnections();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertWord = null;
				PreparedStatement insertAction = null;
				PreparedStatement insertRoom = null;
				PreparedStatement insertItem = null;
				PreparedStatement insertNPC = null;
				PreparedStatement insertPlayer = null;
				PreparedStatement insertItemMap = null;
				PreparedStatement insertNpcMap = null;
				PreparedStatement insertItemAction = null;
				PreparedStatement insertShortcut = null;
				PreparedStatement insertConnection = null;

				try {
					// populate words table
					insertWord = conn.prepareStatement("insert into words (prime, word, type) values (?, ?, ?)");
					for (Word word : wordList) {
						insertWord.setString(1, word.getPrime());
						insertWord.setString(2, word.getName());
						insertWord.setInt(3, word.getType());
						insertWord.addBatch();
					}
					insertWord.executeBatch();

					// populate actions table
					insertAction = conn
							.prepareStatement("insert into actions (name, verb, noun, method) values (?, ?, ?, ?)");
					for (Action action : actionList) {
						insertAction.setString(1, action.getName());
						insertAction.setString(2, action.getVerb().getName());
						insertAction.setString(3, action.getNoun().getName());
						insertAction.setInt(4, action.getMethod());
						insertAction.addBatch();
					}
					insertAction.executeBatch();

					// populate rooms table
					insertRoom = conn.prepareStatement(
							"insert into rooms (id, name, description, visited)" + " values (?, ?, ?, ?)");
					for (Room room : roomList) {
						insertRoom.setString(1, room.getID());
						insertRoom.setString(2, room.getDisplayName());
						insertRoom.setString(3, room.getDescription());
						insertRoom.setBoolean(4, room.getVisited());
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();

					// populate items table
					insertItem = conn.prepareStatement(
							"insert into items (id, name, init_dscrpt, invent_dscrpt, hidden, moved, vowel, plural, weight)"
									+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Item item : itemList) {
						insertItem.setString(1, item.getID());
						insertItem.setString(2, item.getName());
						insertItem.setString(3, item.getInitDscrpt());
						insertItem.setString(4, item.getInventDscrpt());
						insertItem.setBoolean(5, item.hidden());
						insertItem.setBoolean(6, item.moved());
						insertItem.setBoolean(7, item.vowel());
						insertItem.setBoolean(8, item.plural());
						insertItem.setInt(9, item.getWeight());
						insertItem.addBatch();
					}
					insertItem.executeBatch();

					// populate NPCs table
					insertNPC = conn
							.prepareStatement("insert into npcs (id, name, health, attack, defense, description)"
									+ " values (?, ?, ?, ?, ?, ?)");
					for (NPC npc : npcList) {
						insertNPC.setString(1, npc.getID());
						insertNPC.setString(2, npc.getName());
						insertNPC.setInt(3, npc.getHealth());
						insertNPC.setInt(4, npc.getAttack());
						insertNPC.setInt(5, npc.getDefense());
						insertNPC.setString(6, npc.getDescription());
						insertNPC.addBatch();
					}
					insertNPC.executeBatch();

					// populate players table
					insertPlayer = conn.prepareStatement(
							"insert into player (id, location, health, attack, defense)" + " values (?, ?, ?, ?, ?)");
					for (Player player : playerList) {
						insertPlayer.setString(1, player.getID());
						insertPlayer.setString(2, player.getLocation());
						insertPlayer.setInt(3, player.getHealth());
						insertPlayer.setInt(4, player.getAttack());
						insertPlayer.setInt(5, player.getDefense());
						insertPlayer.addBatch();
					}
					insertPlayer.executeBatch();

					// populate item location table
					insertItemMap = conn.prepareStatement("insert into itemMap (id, location)" + " values (?, ?)");
					for (Pair<String, String> p : itemMap) {
						insertItemMap.setString(1, p.getLeft());
						insertItemMap.setString(2, p.getRight());
						insertItemMap.addBatch();
					}
					insertItemMap.executeBatch();

					// populate NPC location table
					insertNpcMap = conn.prepareStatement("insert into npcMap (id, location)" + " values (?, ?)");
					for (Pair<String, String> p : npcMap) {
						insertNpcMap.setString(1, p.getLeft());
						insertNpcMap.setString(2, p.getRight());
						insertNpcMap.addBatch();
					}
					insertNpcMap.executeBatch();

					// populate item action table
					insertItemAction = conn.prepareStatement("insert into itemAct (id, action)" + " values (?, ?)");
					for (Pair<String, String> p : itemAction) {
						insertItemAction.setString(1, p.getLeft());
						insertItemAction.setString(2, p.getRight());
						insertItemAction.addBatch();
					}
					insertItemAction.executeBatch();

					// populate shortcut table
					insertShortcut = conn
							.prepareStatement("insert into shortcuts (shortcut, action)" + " values (?, ?)");
					for (Pair<String, String> p : shortcutList) {
						insertShortcut.setString(1, p.getLeft());
						insertShortcut.setString(2, p.getRight());
						insertShortcut.addBatch();
					}
					insertShortcut.executeBatch();

					// populate connections table
					insertConnection = conn.prepareStatement(
							"insert into connections (action, origin, destination)" + " values (?, ?, ?)");
					for (Pair<String, Pair<String, String>> p : connections) {
						insertConnection.setString(1, p.getRight().getLeft());
						insertConnection.setString(2, p.getLeft());
						insertConnection.setString(3, p.getRight().getRight());
						insertConnection.addBatch();
					}
					insertConnection.executeBatch();

					return true;
				} finally { // close the things
					DBUtil.closeQuietly(insertAction);
					DBUtil.closeQuietly(insertWord);
					DBUtil.closeQuietly(insertRoom);
					DBUtil.closeQuietly(insertItem);
					DBUtil.closeQuietly(insertNPC);
					DBUtil.closeQuietly(insertPlayer);
					DBUtil.closeQuietly(insertItemMap);
					DBUtil.closeQuietly(insertNpcMap);
					DBUtil.closeQuietly(insertItemAction);
					DBUtil.closeQuietly(insertShortcut);
					DBUtil.closeQuietly(insertConnection);
				}
			}
		});
	}

	public void clearAll() { // remove data from tables
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement tblWord = null;
				PreparedStatement tblAct = null;
				PreparedStatement tblRoom = null;
				PreparedStatement tblItem = null;
				PreparedStatement tblItemMap = null;
				PreparedStatement tblItemAct = null;
				PreparedStatement tblNpcs = null;
				PreparedStatement tblPlayers = null;
				PreparedStatement tblNpcMap = null;
				PreparedStatement tblInvent = null;
				PreparedStatement tblShortcut = null;
				PreparedStatement tblConnections = null;
				PreparedStatement tblLog = null;

				try {
					tblWord = conn.prepareStatement("truncate table words");
					tblWord.executeUpdate();

					tblAct = conn.prepareStatement("truncate table actions");
					tblAct.executeUpdate();

					tblRoom = conn.prepareStatement("truncate table rooms");
					tblRoom.executeUpdate();

					tblItem = conn.prepareStatement("truncate table items");
					tblItem.executeUpdate();

					tblItemMap = conn.prepareStatement("truncate table itemMap");
					tblItemMap.executeUpdate();

					tblItemAct = conn.prepareStatement("truncate table itemAct");
					tblItemAct.executeUpdate();

					tblNpcs = conn.prepareStatement("truncate table npcs");
					tblNpcs.executeUpdate();

					tblPlayers = conn.prepareStatement("truncate table player");
					tblPlayers.executeUpdate();

					tblNpcMap = conn.prepareStatement("truncate table npcMap");
					tblNpcMap.executeUpdate();

					tblInvent = conn.prepareStatement("truncate table invent");
					tblInvent.executeUpdate();

					tblShortcut = conn.prepareStatement("truncate table shortcuts");
					tblShortcut.execute();

					tblConnections = conn.prepareStatement("truncate table connections");
					tblConnections.executeUpdate();
					
					tblLog = conn.prepareStatement("truncate table log");
					tblLog.executeUpdate();

					System.out.println("Tables cleared!"); // messages are good

				} finally { // close the things
					DBUtil.closeQuietly(tblWord);
					DBUtil.closeQuietly(tblAct);
					DBUtil.closeQuietly(tblRoom);
					DBUtil.closeQuietly(tblItem);
					DBUtil.closeQuietly(tblItemMap);
					DBUtil.closeQuietly(tblItemAct);
					DBUtil.closeQuietly(tblNpcs);
					DBUtil.closeQuietly(tblPlayers);
					DBUtil.closeQuietly(tblNpcMap);
					DBUtil.closeQuietly(tblInvent);
					DBUtil.closeQuietly(tblShortcut);
					DBUtil.closeQuietly(tblConnections);
					DBUtil.closeQuietly(tblLog);
				}
				return true;
			}
		});
	}

	public void fillAll() { // refill tables
		loadInitialData(); // don't judge me
		System.out.println("Tables made!"); // messages are good
	}

	public String getLog() { // concatenate each row of strings and return entire log
		String log = null;
		
		
		
		return log;
	}
	
	// Word/Action Functions
	public ArrayList<Action> getActions() { // create all action objects available
		return executeTransaction(new Transaction<ArrayList<Action>>() {
			public ArrayList<Action> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Action> actions = new ArrayList<>();

				try { // get all attributes from the action table
					stmt = conn.prepareStatement("select name, verb, noun, method from actions");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					boolean found = false;
					while (resultSet.next()) { // put the attributes into the action object
						found = true;
						String name = resultSet.getString(1);
						Word verb = Word.makeWord(resultSet.getString(2), 1);
						Word noun = Word.makeWord(resultSet.getString(3), 2);
						int method = resultSet.getInt(4);
						Action action = new Action(name, verb, noun, method);
						actions.add(action); // add the action to the action list
					}

					// check if no results found
					if (!found) {
						System.out.println("error in actions table");
					}
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}

				for (Action action : actions) {
					// get the string back from the word objects
					String verb = action.getVerb().getName();
					String noun = action.getNoun().getName();

					// use the strings to make list of all possible synonyms
					ArrayList<String> verbSyn = getVerbs(verb);
					ArrayList<String> nounSyn = getNouns(noun);

					// combine the synonyms to make all possible acceptable (thought of) commands
					for (String v : verbSyn) {
						for (String n : nounSyn) {
							String alt = v + " " + n;
							action.addAltName(alt);
						}
					}
				}

				return actions; // return all actions
			}
		});
	}

	public ArrayList<String> getVerbs(String prime) { // gets all verbs with same prime
		return executeTransaction(new Transaction<ArrayList<String>>() {
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<String> words = new ArrayList<>();

				try { // selects all words with same prime and type verb
					stmt = conn.prepareStatement("select word " + " from words where prime = ? and type = 1");
					stmt.setString(1, prime); // set blank to prime
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					boolean found = false;
					while (resultSet.next()) {
						found = true;
						words.add(resultSet.getString(1)); // add result to word list
					}

					// check if no verbs found
					if (!found) {
						System.out.println("<" + prime + "> was not found in the words table");
					}
					return words; // return the words
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public ArrayList<String> getNouns(String prime) { // gets all nouns with the same prime
		return executeTransaction(new Transaction<ArrayList<String>>() {
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<String> words = new ArrayList<>();

				try { // selects all words with same prime and type noun
					stmt = conn.prepareStatement("select word " + " from words where prime = ? and type = 2");
					stmt.setString(1, prime); // set blank to prime
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					boolean found = false;
					while (resultSet.next()) {
						found = true;
						words.add(resultSet.getString(1)); // add result to word list
					}

					// check if no nouns found
					if (!found) {
						System.out.println("<" + prime + "> was not found in the words table");
					}
					return words; // return the nouns
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	// Map/Room Functions
	public HashMap<String, Room> getMap() { // get the map (just rooms, no items or npcs, use place methods)
		return executeTransaction(new Transaction<HashMap<String, Room>>() {
			public HashMap<String, Room> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				HashMap<String, Room> map = new HashMap<>();

				try { // get all info about the rooms
					stmt = conn.prepareStatement("select * from rooms");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						String id = resultSet.getString("id");
						String name = resultSet.getString("name");
						String dscrpt = resultSet.getString("description");
						boolean visited = resultSet.getBoolean("visited");
						Room r = new Room();
						r.setID(id);
						r.setDisplayName(name);
						r.setDescription(dscrpt);
						r.setVisited(visited);
						map.put(id, r);
					}

					// check if no rooms were found
					if (!found) {
						System.out.println("map not found");
					}
					return map; // return the map
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public String setVisited(String id) { // change visited value in room given by id
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try { // set visited to true in room given
					stmt = conn.prepareStatement("update rooms set visited = ? where id = ?");
					stmt.setBoolean(1, true); // set visited to true
					stmt.setString(2, id); // set id to given input
					stmt.executeUpdate();
				} finally { // close the things
					DBUtil.closeQuietly(stmt);
				}
				return id; // couldn't figure out how to make a void execute
			}
		});
	}

	// Item Functions
	public ArrayList<Item> getItems() { // get all items
		return executeTransaction(new Transaction<ArrayList<Item>>() {
			public ArrayList<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Item> items = new ArrayList<>();

				try {
					stmt = conn.prepareStatement("select * from items"); // get everything from the item table
					resultSet = stmt.executeQuery();

					Boolean found = false; // for testing that a result was returned
					while (resultSet.next()) {
						found = true;
						Item i = new Item(); // create item

						// set attributes
						i.setID(resultSet.getString("id"));
						i.setName(resultSet.getString("name"));
						i.setInitDscrpt(resultSet.getString("init_dscrpt"));
						i.setInventDscrpt(resultSet.getString("invent_dscrpt"));
						i.setHidden(resultSet.getBoolean("hidden"));
						i.setMoved(resultSet.getBoolean("moved"));
						i.setVowel(resultSet.getBoolean("vowel"));
						i.setPlural(resultSet.getBoolean("plural"));
						i.setWeight(resultSet.getInt("weight"));

						items.add(i); // add item to list
					}

					// check items were found
					if (!found) {
						System.out.println("no items found");
					}
					return items;
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public void placeItems(HashMap<String, Room> map, ArrayList<Item> items) throws SQLException { // place items in map
		Connection conn = connect();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String itemID;

		for (Item i : items) {
			itemID = i.getID(); // get the item id

			try { // get the location for the given id
				stmt = conn.prepareStatement("select location from itemMap where id = ?");
				stmt.setString(1, itemID); // set the blank as the id
				resultSet = stmt.executeQuery();

				while (resultSet.next()) {
					String loc = resultSet.getString("location"); // get the location id from the result
					Room r = map.get(loc); // retrieve the room related to the id
					r.addItem(i); // add the item to the room
				}
			} finally { // close the things
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
		}
		conn.commit();
		DBUtil.closeQuietly(conn);
	}

	public String takeItem(String id) { // let player take item into inventory
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;

				try {
					stmt1 = conn.prepareStatement("insert into invent (id) values (?)"); // move item into inventory
					stmt1.setString(1, id); // set blank to item id
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement("delete from itemMap where id = ?"); // delete item from item map
					stmt2.setString(1, id); // set blank to item id
					stmt2.executeUpdate();

					stmt3 = conn.prepareStatement("update items set moved = ? where id = ?"); // set item.moved to true
					stmt3.setBoolean(1, true); // set blank for moved to true
					stmt3.setString(2, id); // set blank to item id
					stmt3.executeUpdate();

				} finally { // close the things
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}

				return id;
			}
		});
	}

	public String dropItem(String id, String room) { // let player drop item from inventory
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;

				try {
					// put item back into itemMap
					stmt1 = conn.prepareStatement("insert into itemMap (id, location) values (?, ?)");
					stmt1.setString(1, id); // set blank to item id
					stmt1.setString(2, room); // set blank to room id
					stmt1.executeUpdate();

					// delete item from inventory
					stmt2 = conn.prepareStatement("delete from invent where id = ?");
					stmt2.setString(1, id); // set blank to item id
					stmt2.executeUpdate();

				} finally { // close the things
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}

				return id;
			}
		});
	}

	public String getItemID(String itemName) { // get item id from inventory by item name
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				String id = "";

				try {
					// get id from the name
					stmt = conn.prepareStatement(
							"select invent.id from invent, items" + " where invent.id = items.id and items.name = ?");
					stmt.setString(1, itemName); // set blank equal to name
					resultSet = stmt.executeQuery();

					while (resultSet.next()) {
						id = resultSet.getString("id"); // get the id as a string
					}

				} finally { // close the things
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}

				return id; // return id
			}
		});
	}

	public ArrayList<String> listInventory() { // get all items in inventory
		return executeTransaction(new Transaction<ArrayList<String>>() {
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				ArrayList<String> ids = new ArrayList<>();
				ArrayList<String> inventory = new ArrayList<>();

				try {
					stmt1 = conn.prepareStatement("select id from invent");
					resultSet1 = stmt1.executeQuery();

					while (resultSet1.next()) {
						ids.add(resultSet1.getString("id")); // get the id as a string
					}

					for (String id : ids) {
						stmt2 = conn.prepareStatement("select name from items where id = ?");
						stmt2.setString(1, id);
						resultSet2 = stmt2.executeQuery();
						while (resultSet2.next()) {
							inventory.add(resultSet2.getString("name"));
						}
					}

				} finally { // close the things
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet2);
				}

				return inventory; // return id
			}
		});
	}

	public ArrayList<Item> getInventory() { // get all items in inventory
		return executeTransaction(new Transaction<ArrayList<Item>>() {
			public ArrayList<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				ArrayList<String> ids = new ArrayList<>();
				ArrayList<Item> inventory = new ArrayList<>();

				try {
					stmt1 = conn.prepareStatement("select id from invent");
					resultSet1 = stmt1.executeQuery();

					while (resultSet1.next()) {
						ids.add(resultSet1.getString("id")); // get the id as a string
					}

					for (String id : ids) {
						stmt2 = conn.prepareStatement(
								"select name, init_dscrpt, invent_dscrpt, hidden, moved, vowel, plural, weight from items where id = ?");
						stmt2.setString(1, id);
						resultSet2 = stmt2.executeQuery();
						Boolean found = false; // for testing that a result was returned
						while (resultSet2.next()) {
							found = true;
							Item i = new Item(); // create item

							// set attributes
							i.setID(id);
							i.setName(resultSet2.getString("name"));
							i.setInitDscrpt(resultSet2.getString("init_dscrpt"));
							i.setInventDscrpt(resultSet2.getString("invent_dscrpt"));
							i.setHidden(resultSet2.getBoolean("hidden"));
							i.setMoved(resultSet2.getBoolean("moved"));
							i.setVowel(resultSet2.getBoolean("vowel"));
							i.setPlural(resultSet2.getBoolean("plural"));
							i.setWeight(resultSet2.getInt("weight"));

							inventory.add(i); // add item to list
						}

						// check items were found
						if (!found) {
							System.out.println("no items found in inventory");
						}
					}

				} finally { // close the things
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet2);
				}

				return inventory; // return id
			}
		});
	}

	// NPC Functions
	public ArrayList<NPC> getNPCs() { // get all npcs
		return executeTransaction(new Transaction<ArrayList<NPC>>() {
			public ArrayList<NPC> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<NPC> npcs = new ArrayList<>();

				try { // get all the info from NPC table
					stmt = conn.prepareStatement("select * from npcs");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						NPC n = new NPC();
						n.setID(resultSet.getString("id"));
						n.setName(resultSet.getString("name"));
						n.setHealth(Integer.parseInt(resultSet.getString("health")));
						;
						n.setAttack(Integer.parseInt(resultSet.getString("attack")));
						;
						n.setDefense(Integer.parseInt(resultSet.getString("defense")));
						;
						n.setDescription(resultSet.getString("description"));
						;

						npcs.add(n);
					}

					// check if no npcs found
					if (!found) {
						System.out.println("no npcs found");
					}
					return npcs;
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public void placeNPCs(HashMap<String, Room> map, ArrayList<NPC> npcs) throws SQLException { // place NPCs in map
		Connection conn = connect();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String npcID;

		for (NPC n : npcs) {
			npcID = n.getID(); // get the npc id
			try { // get location for the given id
				stmt = conn.prepareStatement("select location from npcMap where id = ?");
				stmt.setString(1, npcID); // set blank to id
				resultSet = stmt.executeQuery();

				while (resultSet.next()) {
					String loc = resultSet.getString("location"); // get the location id from the result
					Room r = map.get(loc); // retrieve the room related to the id
					r.addNPC(n); // add the npc to the room
				}
				conn.commit();
			} finally { // close the things
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
			}
		}
	}

	// Player Functions
	public Player getPlayer() throws SQLException { // get the player character
		Connection conn = connect();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Player p = new Player();

		try { // get all the info from Player table
			stmt = conn.prepareStatement("select * from player");
			resultSet = stmt.executeQuery();

			// for testing that a result was returned
			Boolean found = false;
			while (resultSet.next()) {
				found = true;
				p.setID(resultSet.getString("id"));
				p.setLocation(resultSet.getString("location"));
				p.setHealth(Integer.parseInt(resultSet.getString("health")));
				p.setAttack(Integer.parseInt(resultSet.getString("attack")));
				p.setDefense(Integer.parseInt(resultSet.getString("defense")));

			}

			// check if no player found
			if (!found) {
				System.out.println("no player found");
			}
			conn.commit();
		} finally { // close the things
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
		}
		return p;
	}

	// place player in map
	public void placePlayer(HashMap<String, Room> map, Player p) throws SQLException { // in map
		Connection conn = connect();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String playerID = p.getID(); // get the players id
		try { // get location for the given id
			stmt = conn.prepareStatement("select location from player where id = ?");
			stmt.setString(1, playerID); // set blank to id
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				String loc = resultSet.getString("location"); // get the location id from the result
				Room r = map.get(loc); // retrieve the room related to the id
				r.addPlayer(p); // add the player to the room
			}
			conn.commit();
		} finally { // close the things
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
		}
	}

	// move player to new room id
	public void movePlayer(String id, Player p) throws SQLException { // in map
		Connection conn = connect();
		PreparedStatement stmt = null;
		String playerID = p.getID(); // get the players id
		try { // get location for the given id
			stmt = conn.prepareStatement("update player set location = ? where id = ?");
			stmt.setString(1, id); // set location to roomId
			stmt.setString(2, playerID); // set blank to playerID
			stmt.executeUpdate();
			conn.commit();
		} finally { // close the things
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
		}
	}

	// connections functions
	public void addConnections(HashMap<String, Room> map) throws SQLException { // add connections to room
		Connection conn = connect();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			stmt = conn.prepareStatement("select * from connections");
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				String origin = resultSet.getString("origin"); // get the origin from the result
				String actionName = resultSet.getString("action"); // get the action from the result
				String destination = resultSet.getString("destination"); // get the destination
				Room r = map.get(origin);

				r.addConnection(actionName, destination);
			}
			conn.commit();
		} finally { // close the things
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
		}
	}

	public HashMap<String, String> getShortcuts() {
		return executeTransaction(new Transaction<HashMap<String, String>>() {
			public HashMap<String, String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				HashMap<String, String> shortcuts = new HashMap<>();

				try {
					stmt = conn.prepareStatement("select shortcut, action from shortcuts");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						String shortcut = resultSet.getString("shortcut");
						String action = resultSet.getString("action");
						shortcuts.put(shortcut, action);
					}

					// check if no shortcuts were found
					if (!found) {
						System.out.println("error in shortcuts table");
					}
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return shortcuts;

			}
		});
	}

	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();

		System.out.println("Loading initial data...");
		db.loadInitialData();

		System.out.println("Success!");
	}

}