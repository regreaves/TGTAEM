package commandtests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import command.Action;
import command.Word;

public class ActionTests {
	private Action goNorth;
	
	@Before
	public void setUp() {
		Word go    = new Word("go", "go", 1);
		Word north = new Word("north", "north", 2);

		goNorth = new Action("go north", go, north, 0);
	}
	
	@Test
	public void testGetAction() throws Exception {
		assertEquals(goNorth, goNorth.getAction("go north"));
	}

}
