package commandtests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import command.Action;
import command.Parser;
import sqlDB.DatabaseProvider;
import sqlDB.DerbyDatabase;

public class ParserTests {
	private Parser parser;
	ArrayList<Action> actionBank = new ArrayList<>();
	DerbyDatabase db;
	
	@Before
	public void setUp() throws Exception {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
		parser = new Parser(db);
		actionBank = db.getActions();
	}

	@Test
	public void testGetAction() {
		String actionName = actionBank.get(0).getName();
		String getAction = parser.getAction(actionBank.get(0).getName()).getName();
		assertEquals(actionName, getAction);
	}

}
