package command;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import sqlDB.DBUtil;

public class Parser
{
  ArrayList<Action> actionBank = new ArrayList<>();

  public Parser(String wordFile, String actionFile) throws Exception{
    //TODO read files in to create action and words tables -- will figure out later
    // load Derby JDBC driver
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			System.err.println("Could not load Derby JDBC driver");
			System.err.println(e.getMessage());
			System.exit(1);
		}

    Connection conn = null;
		PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
		ResultSet resultSet1 = null;
    ResultSet resultSet2 = null;

		// connect to the database
		conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);

    //TODO for each action in action table
		try {
			conn.setAutoCommit(true);

			// a canned query to find verb synonyms
			stmt1 = conn.prepareStatement(
					"select word "
          + " from words inner join actions where verb = prime "
			);

			// execute the query
			resultSet1 = stmt1.executeQuery();

			// get the precise schema of the tuples returned as the result of the query
			ResultSetMetaData resultSchema1 = stmt1.getMetaData();
      ArrayList<String> verbs = new ArrayList<>();
			// iterate through the returned tuples, printing each one
			// count # of rows returned
			int rowsReturned = 0;

			while (resultSet1.next()) {
				for (int i = 1; i <= resultSchema1.getColumnCount(); i++) {
					Object obj = resultSet1.getObject(i);
					verbs.add(obj.toString());
				}
				rowsReturned++;
			}
			// indicate if the query returned nothing
			if (rowsReturned == 0) {
				System.out.println("No rows returned that matched the query");
			}

      // a canned query to find verb synonyms
			stmt2 = conn.prepareStatement(
					"select word "
          + " from words inner join actions where noun = prime "
			);

			// execute the query
			resultSet2 = stmt2.executeQuery();

			// get the precise schema of the tuples returned as the result of the query
			ResultSetMetaData resultSchema2 = stmt2.getMetaData();
			ArrayList<String> nouns = new ArrayList<>();
			// iterate through the returned tuples, printing each one
			// count # of rows returned
			int rowsReturned2 = 0;

			while (resultSet2.next()) {
				for (int i = 1; i <= resultSchema2.getColumnCount(); i++) {
					Object obj = resultSet2.getObject(i);
					nouns.add(obj.toString());
				}
				rowsReturned2++;
			}
			// indicate if the query returned nothing
			if (rowsReturned2 == 0) {
				System.out.println("No rows returned that matched the query");
			}
    //TODO create arraylist of possible strings
    //TODO create action object
    //TODO add each action object to actionBank

		} finally {
			// close result set, statement, connection
			DBUtil.closeQuietly(resultSet1);
			DBUtil.closeQuietly(resultSet2);
			DBUtil.closeQuietly(stmt1);
			DBUtil.closeQuietly(stmt2);
			DBUtil.closeQuietly(conn);
		}

  	}
}
