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
import objects.Room;

public class DerbyDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}

	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	public ArrayList<String> getVerbs(String prime) {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<String> words = new ArrayList<>();

				try {
					stmt = conn.prepareStatement("select word " + " from words where prime = ? and type = 1");
					stmt.setString(1, prime);
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					boolean found = false;
					while (resultSet.next()) {
						found = true;
						words.add(resultSet.getString(1));
					}

					// check if the title was found
					if (!found) {
						System.out.println("<" + prime + "> was not found in the words table");
					}
					return words;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public ArrayList<String> getNouns(String prime) {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<String> words = new ArrayList<>();

				try {
					stmt = conn.prepareStatement("select word " + " from words where prime = ? and type = 2");
					stmt.setString(1, prime);
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					boolean found = false;
					while (resultSet.next()) {
						found = true;
						words.add(resultSet.getString(1));
					}

					// check if the title was found
					if (!found) {
						System.out.println("<" + prime + "> was not found in the words table");
					}
					return words;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public ArrayList<Action> getActions() {
		return executeTransaction(new Transaction<ArrayList<Action>>() {
			public ArrayList<Action> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Action> actions = new ArrayList<>();

				try {
					stmt = conn.prepareStatement("select name, verb, noun, method from actions");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					boolean found = false;
					while (resultSet.next()) {
						found = true;
						String name = resultSet.getString(1);
						Word verb = Word.makeWord(resultSet.getString(2), 1);
						Word noun = Word.makeWord(resultSet.getString(3), 2);
						int method = resultSet.getInt(4);
						Action action = new Action(name, verb, noun, method);
						actions.add(action);
					}

					// check if the title was found
					if (!found) {
						System.out.println("error in actions table");
					}
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}

				for (Action action : actions) {
					String verb = action.getVerb().getName();
					String noun = action.getNoun().getName();
					ArrayList<String> verbSyn = getVerbs(verb);
					ArrayList<String> nounSyn = getNouns(noun);
					for (String v : verbSyn) {
						for (String n : nounSyn) {
							String alt = v + " " + n;
							action.addAltName(alt);
						}
					}
				}

				return actions;
			}
		});
	}

	public void placeItems(HashMap<String, Room> map, ArrayList<Item> items) throws SQLException {
		Connection conn = connect();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String itemID;

		for (Item i : items) {
			itemID = i.getID();
			try {
				stmt = conn.prepareStatement("select location from itemLoc where id = ?");
				stmt.setString(1, itemID);
				resultSet = stmt.executeQuery();

				while (resultSet.next()) {
					String loc = resultSet.getString(1);
					Room r = map.get(loc);
					r.addItem(i);
				}
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
		}
	}

	public ArrayList<Item> getItems() {
		return executeTransaction(new Transaction<ArrayList<Item>>() {
			public ArrayList<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Item> items = new ArrayList<>();

				try {
					stmt = conn.prepareStatement("select * from items");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						String id = resultSet.getString(1);
						String name = resultSet.getString(2);
						String init_dscrpt = resultSet.getString(3);
						String invent_dscrpt = resultSet.getString(4);
						boolean canTake = resultSet.getBoolean(5);
						boolean isHidden = resultSet.getBoolean(6);
						boolean moved = resultSet.getBoolean(7);
						Item i = new Item(id, name, init_dscrpt, invent_dscrpt, canTake, isHidden, moved);
						items.add(i);
					}

					// check if the title was found
					if (!found) {
						System.out.println("no items found");
					}
					return items;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public HashMap<String, Room> getMap() {
		return executeTransaction(new Transaction<HashMap<String, Room>>() {
			public HashMap<String, Room> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				HashMap<String, Room> map = new HashMap<>();

				try {
					stmt = conn.prepareStatement("select * from rooms");
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						String id = resultSet.getString(1);
						String name = resultSet.getString(2);
						String dscrpt = resultSet.getString(3);
						boolean visited = resultSet.getBoolean(4);
						ArrayList<String> c = new ArrayList<>();
						c.add(resultSet.getString(5)); // north
						c.add(resultSet.getString(6)); // northeast
						c.add(resultSet.getString(7)); // east
						c.add(resultSet.getString(8)); // southeast
						c.add(resultSet.getString(9)); // south
						c.add(resultSet.getString(10)); // southwest
						c.add(resultSet.getString(11)); // west
						c.add(resultSet.getString(12)); // northwest
						c.add(resultSet.getString(13)); // up
						c.add(resultSet.getString(14)); // down
						Room r = new Room(id, name, dscrpt, visited, c);
						map.put(id, r);
					}

					// check if the title was found
					if (!found) {
						System.out.println("map not found");
					}
					return map;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public String takeItem(String id) {
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;

				try {
					stmt1 = conn.prepareStatement("insert into invent (id) values (?)");
					stmt1.setString(1, id);
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement("delete from itemLoc where id = ?");
					stmt2.setString(1, id);
					stmt2.executeUpdate();

					stmt3 = conn.prepareStatement("update items set moved = ? where id = ?");
					stmt3.setBoolean(1, true);
					stmt3.setString(2, id);
					stmt3.executeUpdate();
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}

				return id;
			}
		});
	}

	public String dropItem(String id, String room) {
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;

				try {
					stmt1 = conn.prepareStatement("insert into itemLoc (id, location) values (?, ?)");
					stmt1.setString(1, id);
					stmt1.setString(2, room);
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement("delete from invent where id = ?");
					stmt2.setString(1, id);
					stmt2.executeUpdate();
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}

				return id;
			}
		});
	}

	public String setVisited(String id) {
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement("update rooms set visited = ? where id = ?");
					stmt.setBoolean(1, true);
					stmt.setString(2, id);
					stmt.executeUpdate();
				} finally {
					DBUtil.closeQuietly(stmt);
				}
				return id;
			}
		});
	}

	public String getItemID(String itemName) {
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				String id = "";

				try {
					stmt = conn.prepareStatement("select id from items where name = ?");
					stmt.setString(1, itemName);
					resultSet = stmt.executeQuery();

					while (resultSet.next()) {
						id = resultSet.getString(1);
					}

				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}

				return id;
			}
		});
	}

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

	public void createTables() {
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

				try {
					stmt1 = conn.prepareStatement(
							"create table words (" + "	prime varchar(42)," + "	word varchar(42)," + " type int" + ")");
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement("create table actions (" + "	name varchar(42),"
							+ " verb varchar(42)," + " noun varchar(42)," + " method int " + ")");
					stmt2.executeUpdate();

					stmt3 = conn.prepareStatement(
							"create table rooms (" + " id varchar(5) primary key," + " name varchar(42),"
									+ " description varchar(200)," + "visited boolean," + " north varchar(5), "
									+ " northeast varchar(5), " + " east varchar(5), " + " southeast varchar(5), "
									+ " south varchar(5), " + " southwest varchar(5), " + " west varchar(5), "
									+ " northwest varchar(5), " + " up varchar(5), " + " down varchar(5)" + ")");
					stmt3.executeUpdate();

					stmt4 = conn.prepareStatement("create table items (" + " id varchar(5) primary key,"
							+ " name varchar(42)," + " init_dscrpt varchar(200)," + " invent_dscrpt varchar(200),"
							+ " canTake boolean," + " isHidden boolean," + "moved boolean" + ")");
					stmt4.executeUpdate();

					stmt5 = conn.prepareStatement(
							"create table itemLoc (" + " id varchar(5) primary key," + " location varchar(5)" + ")");
					stmt5.executeUpdate();

					stmt6 = conn.prepareStatement(
							"create table itemAct (" + " id varchar(5)," + " action varchar(42)" + ")");
					stmt6.executeUpdate();

					stmt7 = conn.prepareStatement("create table invent ( id varchar(5))");
					stmt7.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
				}
			}
		});

	}

	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Word> wordList;
				List<Action> actionList;
				List<Room> roomList;
				List<Item> itemList;
				List<Pair<String, String>> itemLoc;
				List<Pair<String, String>> itemAction;

				try {
					wordList = InitialData.getWords();
					actionList = InitialData.getActions();
					roomList = InitialData.getRooms();
					itemList = InitialData.getItems();
					itemLoc = InitialData.getItemLoc();
					itemAction = InitialData.getItemActions();

				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertWord = null;
				PreparedStatement insertAction = null;
				PreparedStatement insertRoom = null;
				PreparedStatement insertItem = null;
				PreparedStatement insertItemLoc = null;
				PreparedStatement insertItemAction = null;

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
					insertRoom = conn
							.prepareStatement("insert into rooms (id, name, description, visited, north, northeast,"
									+ " east, southeast, south, southwest, west, northwest, up, down)"
									+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Room room : roomList) {
						insertRoom.setString(1, room.getID());
						insertRoom.setString(2, room.getDisplayName());
						insertRoom.setString(3, room.getDescription());
						insertRoom.setBoolean(4, room.getVisited());
						insertRoom.setString(5, room.getNorth());
						insertRoom.setString(6, room.getNorthEast());
						insertRoom.setString(7, room.getEast());
						insertRoom.setString(8, room.getSouthEast());
						insertRoom.setString(9, room.getSouth());
						insertRoom.setString(10, room.getSouthWest());
						insertRoom.setString(11, room.getWest());
						insertRoom.setString(12, room.getNorthWest());
						insertRoom.setString(13, room.getUp());
						insertRoom.setString(14, room.getDown());
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();

					// populate items table
					insertItem = conn.prepareStatement(
							"insert into items (id, name, init_dscrpt, invent_dscrpt, canTake, isHidden, moved)"
									+ " values (?, ?, ?, ?, ?, ?, ?)");
					for (Item item : itemList) {
						insertItem.setString(1, item.getID());
						insertItem.setString(2, item.getName());
						insertItem.setString(3, item.getInitDscrpt());
						insertItem.setString(4, item.getInventDscrpt());
						insertItem.setBoolean(5, item.canTake());
						insertItem.setBoolean(6, item.isHidden());
						insertItem.setBoolean(7, item.moved());
						insertItem.addBatch();
					}
					insertItem.executeBatch();

					// populate item location table
					insertItemLoc = conn.prepareStatement("insert into itemLoc (id, location)" + " values (?, ?)");
					for (Pair<String, String> p : itemLoc) {
						insertItemLoc.setString(1, p.getLeft());
						insertItemLoc.setString(2, p.getRight());
						insertItemLoc.addBatch();
					}
					insertItemLoc.executeBatch();

					// populate item action table
					insertItemAction = conn.prepareStatement("insert into itemAct (id, action)" + " values (?, ?)");
					for (Pair<String, String> p : itemAction) {
						insertItemAction.setString(1, p.getLeft());
						insertItemAction.setString(2, p.getRight());
						insertItemAction.addBatch();
					}
					insertItemAction.executeBatch();

					return true;
				} finally {
					DBUtil.closeQuietly(insertAction);
					DBUtil.closeQuietly(insertWord);
					DBUtil.closeQuietly(insertRoom);
					DBUtil.closeQuietly(insertItem);
					DBUtil.closeQuietly(insertItemLoc);
					DBUtil.closeQuietly(insertItemAction);
				}
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
