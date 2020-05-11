package commandtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import command.Word;
import command.Action;

public class ActionTests {
	private Action move;
	private Word go;
	private Word north;
	
	
	@Before
	public void setUp() {
		go = new Word("go", "go", 1);
		north = new Word("north", "north", 2);
		move = new Action("go north", go, north, "move");
		move.addAltName("move north");
	}
	
	@Test
	public void testGetName() throws Exception {
		assertEquals("go north", move.getName());
	}
	
	@Test
	public void testGetVerb() throws Exception {
		assertEquals(go, move.getVerb());
	}
	
	@Test
	public void testGetNoun() throws Exception {
		assertEquals(north, move.getNoun());
	}
	
	@Test
	public void testVerb() throws Exception {
		assertEquals("go", move.verb());
	}
	
	@Test
	public void testNoun() throws Exception {
		assertEquals("north", move.noun());
	}
	
	@Test
	public void testGetMethod() throws Exception {
		assertEquals("move", move.getMethod());
	}
	
	@Test
	public void testGetAltNames() throws Exception {
		ArrayList<String> a = new ArrayList<>();
		a.add("move north");
		assertEquals(a, move.getAltNames());
	}
}
