package commandtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import command.Action;
import command.Word;

public class ActionTests {
	private Action goNorth;
	
	@Before
	public void setUp() {
		Word go = new Word("go", "go", 1);
		Word north = new Word("north", "north", 2);

		goNorth = new Action("go north", go, north, 0);
	}
	
	@Test
	public void testGetAction() throws Exception {
		//TODO this isn't how this method works - needs string arg
		//assertEquals("go north", goNorth.getAction());
	}

}
