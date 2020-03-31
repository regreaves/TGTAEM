package objectstests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import objects.Item;

public class ItemTests {
	
	private Item sword;
	private Item axe;
	private Item flower;
	
	@Before
	public void setUp() {
		sword = new Item("1", "sword", "A steel sword.", "A sword that is light.", true, false, false);
		axe = new Item("2", "axe", "An iron axe.", "An iron axe that is heavy.", true, false, false);
		flower = new Item("3", "flower", "A red rose.", "The thorns are sharp.", true, false, false);
	}
	
	@Test
	public void testGetID() throws Exception {
		assertEquals("1", sword.getID());
		assertEquals("2", axe.getID());
		assertEquals("3", flower.getID());
	}
	
	@Test
	public void testGetName() throws Exception {
		assertEquals("sword", sword.getName());
		assertEquals("axe", axe.getName());
		assertEquals("flower", flower.getName());
	}
	
	@Test
	public void testGetInitDscrpt() throws Exception {
		assertEquals("A steel sword.", sword.getInitDscrpt());
		assertEquals("An iron axe.", axe.getInitDscrpt());
		assertEquals("A red rose.", flower.getInitDscrpt());
	}
	
	@Test
	public void testGetInventDscrpt() throws Exception {
		assertEquals("A sword that is light.", sword.getInventDscrpt());
		assertEquals("An iron axe that is heavy.", axe.getInventDscrpt());
		assertEquals("The thorns are sharp.", flower.getInventDscrpt());
	}
	
	@Test
	public void testCanTake() throws Exception {
		assertTrue(sword.canTake());
		assertTrue(axe.canTake());
		assertTrue(flower.canTake());
	}
	
	@Test
	public void testIsHidden() throws Exception {
		assertFalse(sword.isHidden());
		assertFalse(axe.isHidden());
		assertFalse(flower.isHidden());
	}
}
