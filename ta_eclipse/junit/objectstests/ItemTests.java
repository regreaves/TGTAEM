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
		
		sword.setIsCritical(true);
		axe.setIsCritical(true);
		flower.setIsCritical(false);
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
	
	@Test
	public void testIsCritical() throws Exception {
		assertTrue(sword.isCritical());
		assertTrue(axe.isCritical());
		assertFalse(flower.isCritical());
	}
	
	@Test
	public void testSetCritical() throws Exception {
		sword.setIsCritical(true);
		axe.setIsCritical(false);
		flower.setIsCritical(true);
		
		assertTrue(sword.isCritical());
		assertFalse(axe.isCritical());
		assertTrue(flower.isCritical());
	}
	
	@Test
	public void testMoved() throws Exception {
		assertFalse(sword.moved());
		assertFalse(axe.moved());
		assertFalse(flower.moved());
	}
	
	@Test
	public void testMove() throws Exception {
		sword.move();
		axe.move();
		flower.move();
		
		assertTrue(sword.moved());
		assertTrue(axe.moved());
		assertTrue(flower.moved());
	}
	
	@Test
	public void testSetIsHidden() throws Exception {
		sword.setIsHidden(true);
		axe.setIsHidden(true);
		flower.setIsHidden(true);
		
		assertTrue(sword.isHidden());
		assertTrue(axe.isHidden());
		assertTrue(flower.isHidden());
	}
	
	@Test
	public void testSetInitDscrpt() throws Exception {
		sword.setInitDscrpt("A silver sword.");
		axe.setInitDscrpt("An old axe.");
		flower.setInitDscrpt("A bright red rose.");
		
		assertEquals("A silver sword.", sword.getInitDscrpt());
		assertEquals("An old axe.", axe.getInitDscrpt());
		assertEquals("A bright red rose.", flower.getInitDscrpt());
	}
	
	@Test
	public void testSetInventDscrpt() throws Exception {
		sword.setInventDscrpt("A shiny sword.");
		axe.setInventDscrpt("A heavy axe.");
		flower.setInventDscrpt("It has thorns.");
		
		assertEquals("A shiny sword.", sword.getInventDscrpt());
		assertEquals("A heavy axe.", axe.getInventDscrpt());
		assertEquals("It has thorns.", flower.getInventDscrpt());
	}
	
	@Test
	public void testSetCanTake() throws Exception {
		sword.setCanTake(false);
		axe.setCanTake(false);
		flower.setCanTake(false);
		
		assertFalse(sword.canTake());
		assertFalse(axe.canTake());
		assertFalse(flower.canTake());
	}
}
