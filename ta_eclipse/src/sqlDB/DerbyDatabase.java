package sqlDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import command.Action;
import command.Word;

public class DerbyDatabase implements IDatabase {
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

	@Override
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
					Boolean found = false;
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

	@Override
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
					Boolean found = false;
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
	
	@Override
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
					Boolean found = false;
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
				
				for (Action action: actions)
				{
					String verb = action.getVerb().getName();
					String noun = action.getNoun().getName();
					ArrayList<String> verbSyn = getVerbs(verb);
					ArrayList<String> nounSyn = getNouns(noun);
					for(String v : verbSyn)
					{
						for(String n : nounSyn)
						{
							String alt = v + " " + n;
							action.addAltName(alt);
						}
					}
				}
				
				return actions;
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

				try {
					stmt1 = conn.prepareStatement("create table words ("
							+ "	prime varchar(42)," + "	word varchar(42)," + " type int" + ")");
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement("create table actions (" 
							+ "	name varchar(42)," + " verb varchar(42)," + " noun varchar(42),"   + " method int " + ")");
					stmt2.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
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

				try {
					wordList = InitialData.getWords();
					actionList = InitialData.getActions();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertWord = null;
				PreparedStatement insertAction = null;

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
					insertAction = conn.prepareStatement(
							"insert into actions (name, verb, noun, method) values (?, ?, ?, ?)");
					for (Action action : actionList) {
						insertAction.setString(1, action.getName());
						insertAction.setString(2, action.getVerb().getName());
						insertAction.setString(3, action.getNoun().getName());
						insertAction.setInt(4, action.getMethod());
						insertAction.addBatch();
					}
					insertAction.executeBatch();

					return true;
				} finally {
					DBUtil.closeQuietly(insertAction);
					DBUtil.closeQuietly(insertWord);
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
