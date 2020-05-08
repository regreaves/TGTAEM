package databasetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import command.Action;
import objects.Item;
import objects.Room;
import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;

public class DerbyDatabaseTests {

	private static DerbyDatabase db = new DerbyDatabase();

	private ArrayList<Action> actionList = null;
	private ArrayList<Item> itemList = null;

	private HashMap<String, Room> map = null;
	private HashMap<String, String> shortcutList = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		db.createTables();
		db.fillAll();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		db.dropTables();
	}

	@Before
	public void setUp() throws Exception {
		DatabaseProvider.setInstance(db);
		db = DatabaseProvider.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		db.clearAll();
		db.fillAll();
	}

	@Test
	public void testGetVerbs() {
		System.out.println("\n> TESTING String sqlDB.DerbyDatabase.getVerbs():\n");

		System.out.println("  Trying db.getVerbs(\"tenderize\")...");

		System.out.println("  " + db.getVerbs("tenderize"));
		System.out.println();

		System.out.println("  Trying db.getVerbs(\"wear\")...");

		System.out.println("  " + db.getVerbs("wear"));

		System.out.println();
	}

	@Test
	public void testGetLog() {
		System.out.println("\n> TESTING String sqlDB.DerbyDatabase.getLog():\n");

		db.addRowToLog("  " + "ha!");
		System.out.println(db.getLog());

		System.out.println();
	}

	@Test
	public void testGetMap() {
		System.out.println("\n> TESTING HashMap<String, Room> sqlDB.DerbyDatabase.getMap():\n");

		map = db.getMap();

		for (int i = 1; i <= map.size(); i++) {
			if ((i == 1) || (i == 2) || (i == 3) || (i == (map.size() - 2)) || (i == (map.size() - 1))
					|| (i == map.size())) {
				System.out.format(
						"  " + "%2s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + "%n",
						map.get(Integer.toString(i)).getID(), map.get(Integer.toString(i)).getDisplayName(),
						map.get(Integer.toString(i)).getDescription(), map.get(Integer.toString(i)).getVisited(),
						map.get(Integer.toString(i)).dark(), map.get(Integer.toString(i)).locked(),
						map.get(Integer.toString(i)).temp());
			} else if (i == (map.size() / 2)) {
				System.out.println("\t...");
			}
		}

		System.out.println();
	}

	@Test
	public void testSetVisited() {
		System.out.println("\n> TESTING String sqlDB.DerbyDatabase.setVisited(String id):\n");
		
		System.out.println("  Trying db.setVisited(\"1\")...\n");
		
		db.setVisited("1");
		
		System.out.println("  db.getMap().get(\"1\").getVisited() = " + "<" + db.getMap().get("1").getVisited() + ">");
		
		if (db.getMap().get("1").getVisited()) {
			System.out.println("      Success!");
		} else {
			System.out.println("      Failure.");
		}

		System.out.println();

		assertEquals(db.getMap().get("1").getVisited(), true);
	}

	@Test
	public void testGetActions() {
		System.out.println("\n> TESTING ArrayList<Action> sqlDB.DerbyDatabase.getActions():\n");

		actionList = db.getActions();

		for (int i = 0; i < actionList.size(); i++) {
			if ((i == 0) || (i == 1) || (i == 2) || (i == (actionList.size() - 3)) || (i == (actionList.size() - 2))
					|| (i == (actionList.size() - 1))) {
				System.out.format("  " + "%03d" + ". " + "%s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + "%n",
						i, actionList.get(i).getName(), actionList.get(i).getVerb().getPrime(),
						actionList.get(i).getNoun().getPrime(), actionList.get(i).getMethod());
			} else if (i == (actionList.size() / 2)) {
				System.out.println("         ...");
			}
		}

		System.out.println();
	}

	@Test
	public void testGetShortcuts() {
		System.out.println("\n> TESTING HashMap<String, String> sqlDB.DerbyDatabase.getShortcuts():\n");

		shortcutList = db.getShortcuts();

		for (Entry<String, String> entry : shortcutList.entrySet()) {
			System.out.format("  " + "%2s" + ", " + "%s" + "%n", entry.getKey(), entry.getValue());
		}

		System.out.println();
	}

	@Test
	public void testGetItems() {
		System.out.println("\n> TESTING ArrayList<Item> sqlDB.DerbyDatabase.getItems():\n");

		itemList = db.getItems();

		for (int i = 0; i < itemList.size(); i++) {
			if ((i == 0) || (i == 1) || (i == 2) || (i == (itemList.size() - 3)) || (i == (itemList.size() - 2))
					|| (i == (itemList.size() - 1))) {
			System.out.format(
					"  " + "%s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + ", " + "%s" + ", "
							+ "%s" + ", " + "%s" + ", " + "%s" + "%n",
							itemList.get(i).getID(), itemList.get(i).getName(), itemList.get(i).getInitDscrpt(), itemList.get(i).getInventDscrpt(), itemList.get(i).hidden(),
					itemList.get(i).moved(), itemList.get(i).vowel(), itemList.get(i).plural(), itemList.get(i).isContainer(), itemList.get(i).getWeight());
			} else if (i == (itemList.size() / 2)) {
				System.out.println("         ...");
			}
		}

		System.out.println();
	}

	/*
	 * Is this really acceptable...?
	 */
	@Test
	public void testPlaceItems() throws SQLException {
		System.out.println("\n> TESTING boolean sqlDB.DerbyDatabase.placeItems(HashMap<String, Room> map, ArrayList<Item> items) throws SQLException:\n");

		map = db.getMap();
		itemList = db.getItems();

		System.out.println("  ** See DerbyDatabaseTests.testPlaceItems() comment. **");
		
		System.out.println();

		assertTrue(db.placeItems(map, itemList));
	}
}
