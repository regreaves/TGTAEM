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
import objects.Dialogue;
import objects.Item;
import objects.ItemContainer;
import objects.NPC;
import objects.Player;
import objects.Room;
import state.Status;

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
				PreparedStatement stmt14 = null;
				PreparedStatement stmt15 = null;
				PreparedStatement stmt16 = null;
				PreparedStatement stmt17 = null;
				PreparedStatement stmt18 = null;

				try {
					stmt1 = conn.prepareStatement( // words table
							"create table words (" + "	prime varchar(42)," + "	word varchar(42)," + " type int" + ")");
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement( // actions table
							"create table actions (" + "	name varchar(42)," + " verb varchar(42),"
									+ " noun varchar(42)," + " method varchar(42) " + ")");
					stmt2.executeUpdate();

					stmt3 = conn.prepareStatement( // rooms table
							"create table rooms (" + " id varchar(5) primary key," + " name varchar(42),"
									+ " description varchar(200)," + " visited boolean," + " dark boolean,"
									+ " locked boolean," + " temp varchar(42)" + ")");
					stmt3.executeUpdate();

					stmt4 = conn.prepareStatement( // items table
							"create table items (" + " id varchar(5) primary key," + " name varchar(42),"
									+ " init_dscrpt varchar(500)," + " invent_dscrpt varchar(500)," + " hidden boolean,"
									+ " moved boolean," + " vowel boolean," + " plural boolean," + " container boolean,"
									+ " weight int" + ")");
					stmt4.executeUpdate();

					stmt5 = conn.prepareStatement( // itemMap table
							"create table itemMap (" + " id varchar(5) primary key," + " location varchar(5)" + ")");
					stmt5.executeUpdate();

					stmt6 = conn.prepareStatement( // dialogue table
							"create table dialogue (" + " id varchar(5)," + " dialogue varchar(500)" + ")");
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
							"create table log (" + "log_row varchar(10000)" + ")");
					stmt13.executeUpdate();

					stmt14 = conn.prepareStatement( // action log table
							"create table actionLog (" + "	name varchar(42)," + " verb varchar(42),"
									+ " noun varchar(42)," + " method varchar(42) " + ")");
					stmt14.executeUpdate();

					stmt15 = conn.prepareStatement( // item containers table
							"create table itemContainers (" + " id varchar(5)," + " maxWeight int" + ")");
					stmt15.executeUpdate();

					stmt16 = conn.prepareStatement("create table status ( "
							+ "hiding boolean, monsterCheck1 boolean, sitting boolean, laptop boolean, win boolean, flashlight boolean, sink boolean, shower boolean, "
							+ "clothes boolean, wet boolean, TV boolean, searchCouch boolean, "
							+ "PC boolean, mailFlag boolean, searchGrass1 boolean, "
							+ "searchGrass2 boolean, flood boolean, dialogue boolean, done boolean, "
							+ "quit boolean, move varchar(5), waterOn varchar(5), equipped varchar(500) " + ")");
					stmt16.executeUpdate();

					stmt17 = conn.prepareStatement( // dialogueTree table
							"create table dialogueTrees (" + " newickString varchar(100)" + ")");
					stmt17.executeUpdate();

					stmt18 = conn.prepareStatement( // npcDialogueMap table
							"create table npcDialogueMap (" + " npcID varchar(5)," + " dialogueID varchar(5)" + ")");
					stmt18.executeUpdate();
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
					DBUtil.closeQuietly(stmt14);
					DBUtil.closeQuietly(stmt15);
					DBUtil.closeQuietly(stmt16);
					DBUtil.closeQuietly(stmt17);
					DBUtil.closeQuietly(stmt18);
				}
				return true;
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
				List<Pair<String, String>> shortcutList;
				List<Pair<String, Pair<String, String>>> connections;
				List<Pair<String, String>> dialogueList;
				List<String> dialogueTreeList;
				List<ItemContainer> containers;
				List<Pair<String, String>> npcDialogueMap;

				try { // get info from csvs
					wordList = InitialData.getWords();
					actionList = InitialData.getActions();
					roomList = InitialData.getRooms();
					itemList = InitialData.getItems();
					npcList = InitialData.getNPCs();
					playerList = InitialData.getPlayers();
					itemMap = InitialData.getItemMap();
					npcMap = InitialData.getNPCMap();
					shortcutList = InitialData.getShortcuts();
					connections = InitialData.getConnections();
					dialogueList = InitialData.getDialogue();
					dialogueTreeList = InitialData.getDialogueTree();
					containers = InitialData.getItemContainers();
					npcDialogueMap = InitialData.getNPCDialogueMap();
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
				PreparedStatement insertShortcut = null;
				PreparedStatement insertConnection = null;
				PreparedStatement insertDialogue = null;
				PreparedStatement insertDialogueTree = null;
				PreparedStatement insertContainer = null;
				PreparedStatement insertNpcDialogueMap = null;

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
						insertAction.setString(4, action.getMethod());
						insertAction.addBatch();
					}
					insertAction.executeBatch();

					// populate rooms table
					insertRoom = conn
							.prepareStatement("insert into rooms (id, name, description, visited, dark, locked, temp)"
									+ " values (?, ?, ?, ?, ?, ?, ?)");
					for (Room room : roomList) {
						insertRoom.setString(1, room.getID());
						insertRoom.setString(2, room.getDisplayName());
						insertRoom.setString(3, room.getDescription());
						insertRoom.setBoolean(4, room.getVisited());
						insertRoom.setBoolean(5, room.dark());
						insertRoom.setBoolean(6, room.locked());
						insertRoom.setString(7, room.temp());
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();

					// populate items table
					insertItem = conn.prepareStatement(
							"insert into items (id, name, init_dscrpt, invent_dscrpt, hidden, moved, vowel, plural, container, weight)"
									+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Item item : itemList) {
						insertItem.setString(1, item.getID());
						insertItem.setString(2, item.getName());
						insertItem.setString(3, item.getInitDscrpt());
						insertItem.setString(4, item.getInventDscrpt());
						insertItem.setBoolean(5, item.hidden());
						insertItem.setBoolean(6, item.moved());
						insertItem.setBoolean(7, item.vowel());
						insertItem.setBoolean(8, item.plural());
						insertItem.setBoolean(9, item.isContainer());
						insertItem.setInt(10, item.getWeight());
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

					// populate dialogue table
					insertDialogue = conn.prepareStatement("insert into dialogue (id, dialogue)" + " values (?, ?)");
					for (Pair<String, String> p : dialogueList) {
						insertDialogue.setString(1, p.getLeft());
						insertDialogue.setString(2, p.getRight());
						insertDialogue.addBatch();
					}
					insertDialogue.executeBatch();

					// populate dialogueTrees table
					insertDialogueTree = conn
							.prepareStatement("insert into dialogueTrees (newickString)" + " values (?)");
					for (String s : dialogueTreeList) {
						insertDialogueTree.setString(1, s);
						insertDialogueTree.addBatch();
					}
					insertDialogueTree.executeBatch();
					// populate itemContainers table
					insertContainer = conn
							.prepareStatement("insert into itemContainers (id, maxWeight)" + " values (?, ?)");
					for (ItemContainer i : containers) {
						insertContainer.setString(1, i.getID());
						insertContainer.setInt(2, i.getMaxWeight());
						insertContainer.addBatch();
					}
					insertContainer.executeBatch();

					// populate NPC dialogue map table
					insertNpcDialogueMap = conn
							.prepareStatement("insert into npcDialogueMap (npcID, dialogueID)" + " values (?, ?)");
					for (Pair<String, String> p : npcDialogueMap) {
						insertNpcDialogueMap.setString(1, p.getLeft());
						insertNpcDialogueMap.setString(2, p.getRight());
						insertNpcDialogueMap.addBatch();
					}
					insertNpcDialogueMap.executeBatch();

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
					DBUtil.closeQuietly(insertShortcut);
					DBUtil.closeQuietly(insertConnection);
					DBUtil.closeQuietly(insertDialogue);
					DBUtil.closeQuietly(insertDialogueTree);
					DBUtil.closeQuietly(insertContainer);
					DBUtil.closeQuietly(insertNpcDialogueMap);
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
				PreparedStatement tblNpcs = null;
				PreparedStatement tblPlayers = null;
				PreparedStatement tblNpcMap = null;
				PreparedStatement tblInvent = null;
				PreparedStatement tblShortcut = null;
				PreparedStatement tblConnections = null;
				PreparedStatement tblLog = null;
				PreparedStatement tblActionLog = null;
				PreparedStatement tblDialogue = null;
				PreparedStatement tblDialogueTrees = null;
				PreparedStatement tblItemContainers = null;
				PreparedStatement tblNpcDialogueMap = null;
				PreparedStatement tblStatus = null;

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

					tblActionLog = conn.prepareStatement("truncate table actionLog");
					tblActionLog.executeUpdate();

					tblItemContainers = conn.prepareStatement("truncate table itemContainers");
					tblItemContainers.executeUpdate();

					tblStatus = conn.prepareStatement("truncate table status");
					tblStatus.executeUpdate();

					tblDialogue = conn.prepareStatement("truncate table dialogue");
					tblDialogue.executeUpdate();

					tblDialogueTrees = conn.prepareStatement("truncate table dialogueTrees");
					tblDialogueTrees.executeUpdate();

					tblNpcDialogueMap = conn.prepareStatement("truncate table npcDialogueMap");
					tblNpcDialogueMap.executeUpdate();

					System.out.println("Tables cleared!"); // messages are good

				} finally { // close the things
					DBUtil.closeQuietly(tblWord);
					DBUtil.closeQuietly(tblAct);
					DBUtil.closeQuietly(tblRoom);
					DBUtil.closeQuietly(tblItem);
					DBUtil.closeQuietly(tblItemMap);
					DBUtil.closeQuietly(tblNpcs);
					DBUtil.closeQuietly(tblPlayers);
					DBUtil.closeQuietly(tblNpcMap);
					DBUtil.closeQuietly(tblInvent);
					DBUtil.closeQuietly(tblShortcut);
					DBUtil.closeQuietly(tblConnections);
					DBUtil.closeQuietly(tblLog);
					DBUtil.closeQuietly(tblActionLog);
					DBUtil.closeQuietly(tblDialogue);
					DBUtil.closeQuietly(tblDialogueTrees);
					DBUtil.closeQuietly(tblItemContainers);
					DBUtil.closeQuietly(tblNpcDialogueMap);
					DBUtil.closeQuietly(tblStatus);
				}
				return true;
			}
		});
	}

	public void fillAll() { // refill tables
		loadInitialData(); // don't judge me
		System.out.println("Tables filled!"); // messages are good
	}

	public void dropTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement tblWord = null;
				PreparedStatement tblAct = null;
				PreparedStatement tblRoom = null;
				PreparedStatement tblItem = null;
				PreparedStatement tblItemMap = null;
				PreparedStatement tblNpcs = null;
				PreparedStatement tblPlayers = null;
				PreparedStatement tblNpcMap = null;
				PreparedStatement tblInvent = null;
				PreparedStatement tblShortcut = null;
				PreparedStatement tblConnections = null;
				PreparedStatement tblLog = null;
				PreparedStatement tblActionLog = null;
				PreparedStatement tblDialogue = null;
				PreparedStatement tblDialogueTrees = null;
				PreparedStatement tblItemContainers = null;
				PreparedStatement tblNpcDialogueMap = null;
				PreparedStatement tblStatus = null;

				try {
					tblWord = conn.prepareStatement("drop table words");
					tblWord.executeUpdate();

					tblAct = conn.prepareStatement("drop table actions");
					tblAct.executeUpdate();

					tblRoom = conn.prepareStatement("drop table rooms");
					tblRoom.executeUpdate();

					tblItem = conn.prepareStatement("drop table items");
					tblItem.executeUpdate();

					tblItemMap = conn.prepareStatement("drop table itemMap");
					tblItemMap.executeUpdate();

					tblNpcs = conn.prepareStatement("drop table npcs");
					tblNpcs.executeUpdate();

					tblPlayers = conn.prepareStatement("drop table player");
					tblPlayers.executeUpdate();

					tblNpcMap = conn.prepareStatement("drop table npcMap");
					tblNpcMap.executeUpdate();

					tblInvent = conn.prepareStatement("drop table invent");
					tblInvent.executeUpdate();

					tblShortcut = conn.prepareStatement("drop table shortcuts");
					tblShortcut.execute();

					tblConnections = conn.prepareStatement("drop table connections");
					tblConnections.executeUpdate();

					tblLog = conn.prepareStatement("drop table log");
					tblLog.executeUpdate();

					tblActionLog = conn.prepareStatement("drop table actionLog");
					tblActionLog.executeUpdate();

					tblDialogue = conn.prepareStatement("drop table dialogue");
					tblDialogue.executeUpdate();

					tblDialogueTrees = conn.prepareStatement("drop table dialogueTrees");
					tblDialogueTrees.executeUpdate();

					tblItemContainers = conn.prepareStatement("drop table itemContainers");
					tblItemContainers.executeUpdate();

					tblNpcDialogueMap = conn.prepareStatement("drop table npcDialogueMap");
					tblNpcDialogueMap.executeUpdate();

					tblStatus = conn.prepareStatement("drop table status");
					tblStatus.executeUpdate();

					System.out.println("Tables dropped!"); // messages are good

				} finally { // close the things
					DBUtil.closeQuietly(tblWord);
					DBUtil.closeQuietly(tblAct);
					DBUtil.closeQuietly(tblRoom);
					DBUtil.closeQuietly(tblItem);
					DBUtil.closeQuietly(tblItemMap);
					DBUtil.closeQuietly(tblNpcs);
					DBUtil.closeQuietly(tblPlayers);
					DBUtil.closeQuietly(tblNpcMap);
					DBUtil.closeQuietly(tblInvent);
					DBUtil.closeQuietly(tblShortcut);
					DBUtil.closeQuietly(tblConnections);
					DBUtil.closeQuietly(tblLog);
					DBUtil.closeQuietly(tblActionLog);
					DBUtil.closeQuietly(tblDialogue);
					DBUtil.closeQuietly(tblDialogueTrees);
					DBUtil.closeQuietly(tblItemContainers);
					DBUtil.closeQuietly(tblNpcDialogueMap);
					DBUtil.closeQuietly(tblStatus);
				}
				return true;
			}
		});
	}

	public void addRowToLog(String row) {
		executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = conn.prepareStatement("insert into log (log_row) values (?)");
				stmt.setString(1, row);
				stmt.executeUpdate();

				return null;
			}
		});
	}

	public String getLog() {
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				String log = "";

				try {
					stmt = conn.prepareStatement("select * from log");
					resultSet = stmt.executeQuery();

					while (resultSet.next()) {
						log += (resultSet.getString("log_row"));

					}
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}

				return log;
			}
		});
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
						String name = resultSet.getString("name");
						Word verb = Word.makeWord(resultSet.getString("verb"), 1);
						Word noun = Word.makeWord(resultSet.getString("noun"), 2);
						String method = resultSet.getString("method");
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
						boolean dark = resultSet.getBoolean("dark");
						boolean locked = resultSet.getBoolean("locked");
						String temp = resultSet.getString("temp");
						Room r = new Room();
						r.setID(id);
						r.setDisplayName(name);
						r.setDescription(dscrpt);
						r.setVisited(visited);
						r.setDark(dark);
						r.setLocked(locked);
						r.setTemp(temp);
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
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				ArrayList<Item> items = new ArrayList<>();

				try {
					stmt = conn.prepareStatement("select * from items"); // get everything from the item table
					resultSet = stmt.executeQuery();

					Boolean found = false; // for testing that a result was returned
					Boolean found2 = false;
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
						i.setIsContainer(resultSet.getBoolean("container"));
						i.setWeight(resultSet.getInt("weight"));

						if (i.isContainer()) {
							stmt2 = conn.prepareStatement("select * from itemContainers");
							resultSet2 = stmt2.executeQuery();

							while (resultSet2.next()) {
								found2 = true;
								ItemContainer ic = new ItemContainer();
								ic.setID(resultSet.getString("id"));
								ic.setName(resultSet.getString("name"));
								ic.setInitDscrpt(resultSet.getString("init_dscrpt"));
								ic.setInventDscrpt(resultSet.getString("invent_dscrpt"));
								ic.setHidden(resultSet.getBoolean("hidden"));
								ic.setMoved(resultSet.getBoolean("moved"));
								ic.setVowel(resultSet.getBoolean("vowel"));
								ic.setPlural(resultSet.getBoolean("plural"));
								ic.setIsContainer(resultSet.getBoolean("container"));
								ic.setWeight(resultSet.getInt("weight"));
								ic.setMaxWeight(resultSet2.getInt("maxWeight"));

								items.add(ic);
							}
						} else {
							items.add(i); // add item to list
						}
					}

					// check items were found
					if (!found) {
						System.out.println("no items found");
					}
					if (!found2) {
						System.out.println("no item containers found");
					}
					return items;
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}

	public Integer placeItems(HashMap<String, Room> map, ArrayList<Item> items) { // place items in map
		return executeTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				String itemID;
				ArrayList<Pair<String, Item>> al = new ArrayList<>();

				for (Item i : items) {
					itemID = i.getID(); // get the item id

					try { // get the location for the given id
						stmt = conn.prepareStatement("select location from itemMap where id = ?");
						stmt.setString(1, itemID); // set the blank as the id
						resultSet = stmt.executeQuery();

						while (resultSet.next()) {
							String loc = resultSet.getString("location"); // get the location id from the result
							if (loc.startsWith("i")) {
								Pair<String, Item> e = new Pair<>(loc, i); // create a new pair of IC ids and items
								al.add(e); // add pair to arraylist
							} else {
								Room r = map.get(loc); // retrieve the room related to the id
								r.addItem(i); // add the item to the room
							}
						}
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
				for (Pair<String, Item> p : al) {
					try {
						stmt2 = conn.prepareStatement("select location from itemMap where id = ?");
						stmt2.setString(1, p.getLeft()); // set the blank as the IC id
						resultSet2 = stmt2.executeQuery();

						while (resultSet2.next()) {
							String loc2 = resultSet2.getString("location"); // get the location id from the result
							Room r2 = map.get(loc2);
							ArrayList<Item> al2 = r2.getItems(); // get items from room
							for (Item i2 : al2) {
								if (i2.getID().equals(p.getLeft())) {
									((ItemContainer) i2).addItem(p.getRight()); // add item to itemContainer
								}
							}
						}
					} finally { // close the things
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(resultSet2);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
				return 1;
			}
		});
	}

	public String takeItem(String id) { // let player take item into inventory
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;

				try {
					stmt1 = conn.prepareStatement("insert into invent (id) values (?)"); // move item into
																							// inventory
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
								"select name, init_dscrpt, invent_dscrpt, hidden, moved, vowel, plural, container, weight from items where id = ?");
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
							i.setIsContainer(resultSet2.getBoolean("container"));
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
						n.setAttack(Integer.parseInt(resultSet.getString("attack")));
						n.setDefense(Integer.parseInt(resultSet.getString("defense")));
						n.setDescription(resultSet.getString("description"));

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
					n.setLocation(loc);
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
		return executeTransaction(new Transaction<Player>() {
			public Player execute(Connection conn) throws SQLException {
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
				}
				return p;
			}
		});
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

	public void addAction(Action a) throws SQLException {
		Connection conn = connect();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement("insert into actionLog (name, verb, noun, method) values (?, ?, ?, ?)");
			stmt.setString(1, a.getName());
			stmt.setString(2, a.verb());
			stmt.setString(3, a.noun());
			stmt.setString(4, a.getMethod());
			stmt.executeUpdate();
			conn.commit();

		} finally {
			DBUtil.closeQuietly(conn);
			DBUtil.closeQuietly(stmt);
		}
	}

	public ArrayList<Action> getActionLog() { // create all action objects available
		return executeTransaction(new Transaction<ArrayList<Action>>() {
			public ArrayList<Action> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Action> actions = new ArrayList<>();

				try { // get all attributes from the action table
					stmt = conn.prepareStatement("select name, verb, noun, method from actionLog");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					boolean found = false;
					while (resultSet.next()) { // put the attributes into the action object
						found = true;
						String name = resultSet.getString("name");
						Word verb = Word.makeWord(resultSet.getString("verb"), 1);
						Word noun = Word.makeWord(resultSet.getString("noun"), 2);
						String method = resultSet.getString("method");
						Action action = new Action(name, verb, noun, method);
						actions.add(action); // add the action to the action list
					}

					// check if no results found
					if (!found) {
						System.out.println("error in action log table");
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

//	public Integer saveStatus(String s) {
//		return executeTransaction(new Transaction<Integer>() {
//			public Integer execute(Connection conn) throws SQLException {
//				PreparedStatement stmt0 = null;
//				PreparedStatement stmt = null;
//				try {
//					stmt0 = conn.prepareStatement("truncate table status");
//					stmt0.executeUpdate();
//					stmt = conn.prepareStatement("insert into status (json) values (?)");
//					stmt.setString(1, s);
//					stmt.executeUpdate();
//
//				} finally {
//					DBUtil.closeQuietly(stmt0);
//					DBUtil.closeQuietly(stmt);
//				}
//				return 1;
//			}
//		});
//	}

	public Integer saveStatus(Status status) {
		return executeTransaction(new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt0 = null;
				PreparedStatement stmt = null;

				try {
					stmt0 = conn.prepareStatement("truncate table status");
					stmt0.executeUpdate();

					stmt = conn.prepareStatement("insert into status (hiding, monsterCheck1, sitting,"
							+ "laptop, win, flashlight, sink, shower," + "clothes, wet, TV, searchCouch, PC,"
							+ "mailFlag, searchGrass1, searchGrass2, flood, dialogue,"
							+ "done, quit, move, waterOn, equipped"
							+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					stmt.setBoolean(1, status.isHiding());
					stmt.setBoolean(2, status.isMonsterCheck1());
					stmt.setBoolean(3, status.isSitting());
					stmt.setBoolean(4, status.isLaptop());
					stmt.setBoolean(5, status.isWindow());
					stmt.setBoolean(6, status.isFlashlight());
					stmt.setBoolean(7, status.isSink());
					stmt.setBoolean(8, status.isShower());
					stmt.setBoolean(9, status.isClothes());
					stmt.setBoolean(10, status.isWet());
					stmt.setBoolean(11, status.isTV());
					stmt.setBoolean(12, status.isSearchCouch());
					stmt.setBoolean(13, status.isPC());
					stmt.setBoolean(14, status.isMailFlag());
					stmt.setBoolean(15, status.isSearchGrass1());
					stmt.setBoolean(16, status.isSearchGrass2());
					stmt.setBoolean(17, status.isFlood());
					stmt.setBoolean(18, status.isDialogue());
					stmt.setBoolean(19, status.isDone());
					stmt.setBoolean(20, status.isQuit());

					stmt.setInt(21, status.getMove());
					stmt.setInt(22, status.getWaterOn());

					stmt.setString(23, status.equippedToString(status.getEquipped()));

					stmt.executeUpdate();
					conn.commit();
				} finally {
					DBUtil.closeQuietly(stmt0);
					DBUtil.closeQuietly(stmt);
				}

				return 1;
			}
		});
	}

	public Status loadStatus() {
		return executeTransaction(new Transaction<Status>() {
			public Status execute(Connection conn) throws SQLException {
				PreparedStatement statement = null;
				ResultSet resultSet = null;

				Status status = new Status();

				try {
					statement = conn.prepareStatement("select * from status");
					resultSet = statement.executeQuery();

					while (resultSet.next()) {
						boolean isHiding = resultSet.getBoolean(1);
						boolean isCheckingForMonsters = resultSet.getBoolean(2);
						boolean isSitting = resultSet.getBoolean(3);
						boolean isLaptopOn = resultSet.getBoolean(4);
						boolean isWindow = resultSet.getBoolean(5);
						boolean isFlashlightOn = resultSet.getBoolean(6);
						boolean isSinkOn = resultSet.getBoolean(7);
						boolean isShowerOn = resultSet.getBoolean(8);
						boolean isDressed = resultSet.getBoolean(9);
						boolean isWet = resultSet.getBoolean(10);
						boolean isTelevisionOn = resultSet.getBoolean(11);
						boolean isSearchingCouch = resultSet.getBoolean(12);
						boolean isPersonalComputerOn = resultSet.getBoolean(13);
						boolean isMailFlagUp = resultSet.getBoolean(14);
						boolean isSearchingGrass01 = resultSet.getBoolean(15);
						boolean isSearchingGrass02 = resultSet.getBoolean(16);
						boolean isFlooded = resultSet.getBoolean(17);
						boolean dialogue = resultSet.getBoolean(18);
						boolean isDone = resultSet.getBoolean(19);
						boolean isQuitting = resultSet.getBoolean(20);

						int moveCount = resultSet.getInt(21);
						int moveCountWaterOn = resultSet.getInt(22);

						String equipped = resultSet.getString(23);

						status.setHiding(isHiding);
						status.setMonsterCheck1(isCheckingForMonsters);
						status.setSitting(isSitting);
						status.setLaptop(isLaptopOn);
						status.setWindow(isWindow);
						status.setFlashlight(isFlashlightOn);
						status.setSink(isSinkOn);
						status.setShower(isShowerOn);
						status.setClothes(isDressed);
						status.setWet(isWet);
						status.setTV(isTelevisionOn);
						status.setSearchCouch(isSearchingCouch);
						status.setPC(isPersonalComputerOn);
						status.setMailFlag(isMailFlagUp);
						status.setSearchGrass1(isSearchingGrass01);
						status.setSearchGrass2(isSearchingGrass02);
						status.setFlood(isFlooded);
						status.setDialogue(dialogue);
						status.setDone(isDone);
						status.setIsQuitting(isQuitting);

						status.setMove(moveCount);
						status.setWaterOn(moveCountWaterOn);

						status.setEquipped(status.equippedToItemArrayList(equipped));
					}
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(statement);
				}

				return status;
			}
		});
	}

	// Dialogue functions
	public HashMap<String, Dialogue> getDialogue() throws SQLException { // get all dialogue
		return executeTransaction(new Transaction<HashMap<String, Dialogue>>() {
			public HashMap<String, Dialogue> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				HashMap<String, Dialogue> dialogue = new HashMap<>();

				try { // get all the info from dialogue table
					stmt = conn.prepareStatement("select * from dialogue");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						Dialogue d = new Dialogue();
						d.setID(resultSet.getString("id"));
						d.setDialogue(resultSet.getString("dialogue"));
						dialogue.put(d.getID(), d);
					}

					// check if no dialogue found
					if (!found) {
						System.out.println("no dialogue found");
					}
				} finally { // close the things
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				return dialogue;
			}
		});
	}

	public ArrayList<String> getDialogueTree() throws SQLException { // get all dialogueTrees
		return executeTransaction(new Transaction<ArrayList<String>>() {
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<String> dialogueTree = new ArrayList<String>();

				try { // get all the info from dialogueTrees table
					stmt = conn.prepareStatement("select * from dialogueTrees");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						Dialogue d = new Dialogue();
						d.setNewickTree(resultSet.getString("newickString"));
						dialogueTree.add(d.getNewickTree());
					}

					// check if no dialogueTrees found
					if (!found) {
						System.out.println("no dialogueTrees found");
					}
				} finally { // close the things
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return dialogueTree;
			}
		});
	}

	public void placeDialogue(HashMap<String, Dialogue> dialogue, ArrayList<NPC> npcs) throws SQLException { // set
																												// dialogue
																												// to
																												// npcs
		Connection conn = connect();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String npcID;

		for (NPC n : npcs) {
			npcID = n.getID(); // get the npc id
			try { // get location for the given id
				stmt = conn.prepareStatement("select dialogueID from npcDialogueMap where npcID = ?");
				stmt.setString(1, npcID); // set blank to id
				resultSet = stmt.executeQuery();

				while (resultSet.next()) {
					String id = resultSet.getString("dialogueID"); // get the dialogue id from the result
					Dialogue d = dialogue.get(id);
					n.setDialogue(d);
				}
				conn.commit();
			} finally { // close the things
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
			}
		}
	}

	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();

		System.out.println("Loading initial data...");
		db.loadInitialData();

		System.out.println("Success!");

//		ArrayList<Item> items = db.getItems();
//		for (Item i : items) {
//			System.out.println("isContainer: " + i.isContainer() + "");
//			if(i.isContainer()) {
//				System.out.println("maxWeight: " + ((ItemContainer) i).getMaxWeight() + "");
//			}
//		}
	}
}
