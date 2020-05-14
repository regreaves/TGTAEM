package databasetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import command.Action;
import objects.Item;
import objects.Room;
import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;

public class DerbyDatabaseTests {

	private static DerbyDatabase db = null;

	@BeforeAll
	private static void setUpBeforeClass() throws Exception {
		db = new DerbyDatabase();

		db.createTables();
		db.fillAll();
	}

	@AfterAll
	private static void tearDownAfterClass() throws Exception {
		db.dropTables();
	}

	@BeforeEach
	private void setUp() throws Exception {
		DatabaseProvider.setInstance(db);
		db = DatabaseProvider.getInstance();
	}

	@AfterEach
	private void tearDown() throws Exception {

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/csvFiles/shortcuts.csv", numLinesToSkip = 1, delimiter = '|')
	void testGetShortcuts(String shortcut, String action) {
		assertEquals(action, db.getShortcuts().getOrDefault(shortcut, action));
	}

	@ParameterizedTest(name = "[{index}] {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}")
	@MethodSource("provideItemList")
	void testGetItems(Item item, String id, String name, String init, String invent, boolean hidden, boolean moved,
			boolean vowel, boolean plural, boolean container, int weight) {
		assertEquals(item.getID(), id);
		assertEquals(item.getName(), name);
		assertEquals(item.getInitDscrpt(), init);
		assertEquals(item.getInventDscrpt(), invent);
		assertEquals(item.hidden(), hidden);
		assertEquals(item.moved(), moved);
		assertEquals(item.vowel(), vowel);
		assertEquals(item.plural(), plural);
		assertEquals(item.isContainer(), container);
		assertEquals(item.getWeight(), weight);
	}

	private static Stream<Arguments> provideItemList() {
		ArrayList<Item> itemList = db.getItems();

		return Stream.of(
				Arguments.of(itemList.get(0), "i01", "bed", "Your bed is in the corner. It's very comfy.",
						"It's calling you. You feel your eyes getting heavy.", false, false, false, false, false, 1000),
				Arguments.of(itemList.get(13), "i14", "remote", "Where is the remote?",
						"The remote for your living room TV. Generally useless without the TV.", true, false, false,
						false, false, 1));
	}

	@ParameterizedTest(name = "[{index}] {1}, {2}, {3}, {4}")
	@MethodSource("provideActionList")
	void testGetActions(Action action, String name, String verb, String noun, String method) {
		assertEquals(action.getName(), name);
		assertEquals(action.getVerb().getPrime(), verb);
		assertEquals(action.getNoun().getPrime(), noun);
		assertEquals(action.getMethod(), method);
	}

	private static Stream<Arguments> provideActionList() {
		ArrayList<Action> actionList = db.getActions();

		return Stream.of(Arguments.of(actionList.get(0), "go north", "go", "north", "move"),
				Arguments.of(actionList.get(32), "take pikachu", "take", "pikachu", "take item"));
	}

	@ParameterizedTest(name = "[{index}] {1}, {2}, {3}, {4}, {5}, {6}, {7}")
	@MethodSource("provideMap")
	void testGetMap(Room room, String id, String name, String description, boolean visited, boolean dark,
			boolean locked, String temp) {
		assertEquals(room.getID(), id);
		assertEquals(room.getDisplayName(), name);
		assertEquals(room.getDescription(), description);
		assertEquals(room.getVisited(), visited);
		assertEquals(room.dark(), dark);
		assertEquals(room.locked(), locked);
		assertEquals(room.temp(), temp);
	}

	private static Stream<Arguments> provideMap() {
		HashMap<String, Room> map = db.getMap();

		return Stream.of(
				Arguments.of(map.get("2"), "2", "Closet", "It's your closet. It's a mess.", false, false, false, ""),
				Arguments.of(map.get("15"), "15", "E/W Road", "It's a long and empty road.", false, false, false, ""));
	}

	@ParameterizedTest(name = "[{index}] {0}, {1}")
	@MethodSource("provideVisited")
	void testSetVisited(Room room, boolean wasVisited) {
		assertEquals(wasVisited, room.getVisited());
	}

	private static Stream<Arguments> provideVisited() {
		HashMap<String, Room> map = db.getMap();

		map.get("1").setVisited(false);
		map.get("3").setVisited(true);

		return Stream.of(Arguments.of(map.get("1"), false), Arguments.of(map.get("3"), true));
	}

	@Disabled
	@TestFactory
	DynamicTest[] dynamicTestsFromArray() {
		db.addRowToLog("This is the added row to log.");

		return new DynamicTest[] {
				dynamicTest("testGetLog", () -> assertEquals("This is the added row to log.", db.getLog())) };
	}
}
