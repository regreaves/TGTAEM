package commandtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import command.Word;

public class WordTests {
	private Word north;
	
	@Before
	public void setUp() {
		north = new Word("north", "north", 2);
	}
	
	@Test
	public void testGetName() throws Exception {
		assertEquals("north", north.getName());
	}
	
	@Test
	public void testGetPrime() throws Exception {
		assertEquals("north", north.getPrime());
	}
	
	@Test
	public void testGetType() throws Exception {
		assertEquals(2, north.getType());
	}
	
	@Test
	public void testIsNoun() throws Exception {
		assertTrue(north.isNoun());
	}
	
	@Test
	public void testIsVerb() throws Exception {
		assertFalse(north.isVerb());
	}
	
	@Test
	public void testIsPrime() throws Exception {
		assertTrue(north.isPrime());
	}
}
