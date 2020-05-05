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
		sword = new Item();
		axe = new Item();
		flower = new Item();
		
		sword.setID("1");
		axe.setID("2");
		flower.setID("3");
		
		sword.setName("sword");
		axe.setName("axe");
		flower.setName("flower");
		
		sword.setInitDscrpt("A steel sword.");
		axe.setInitDscrpt("An iron axe.");
		flower.setInitDscrpt("A red rose.");
		
		sword.setInventDscrpt("A sword that is light.");
		axe.setInventDscrpt("An iron axe that is heavy.");
		flower.setInventDscrpt("The thorns are sharp.");
		
		sword.setHidden(false);
		axe.setHidden(false);
		flower.setHidden(false);
		
		sword.setCritical(true);
		axe.setCritical(true);
		flower.setCritical(false);
		
		sword.setMoved(false);
		axe.setMoved(false);
		flower.setMoved(false);
		
		sword.setVowel(false);
		axe.setVowel(true);
		flower.setVowel(false);
		
		sword.setPlural(false);
		axe.setPlural(false);
		flower.setPlural(false);
		
		sword.setIsContainer(false);
		axe.setIsContainer(false);
		flower.setIsContainer(false);
		
		sword.setWeight(10);
		axe.setWeight(20);
		flower.setWeight(5);
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
	public void testHidden() throws Exception {
		assertFalse(sword.hidden());
		assertFalse(axe.hidden());
		assertFalse(flower.hidden());
	}
	
	@Test
	public void testCritical() throws Exception {
		assertTrue(sword.critical());
		assertTrue(axe.critical());
		assertFalse(flower.critical());
	}
	
	@Test
	public void testSetCritical() throws Exception {
		sword.setCritical(true);
		axe.setCritical(false);
		flower.setCritical(true);
		
		assertTrue(sword.critical());
		assertFalse(axe.critical());
		assertTrue(flower.critical());
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
	public void testSetHidden() throws Exception {
		sword.setHidden(true);
		axe.setHidden(true);
		flower.setHidden(true);
		
		assertTrue(sword.hidden());
		assertTrue(axe.hidden());
		assertTrue(flower.hidden());
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
	public void testVowel() throws Exception {
		assertFalse(sword.vowel());
		assertTrue(axe.vowel());
		assertFalse(flower.vowel());
	}
	
	@Test
	public void testSetVowel() throws Exception {
		sword.setVowel(true);
		axe.setVowel(true);
		flower.setVowel(true);
		
		assertTrue(sword.vowel());
		assertTrue(axe.vowel());
		assertTrue(flower.vowel());
	}
	
	@Test
	public void testPlural() throws Exception {
		assertFalse(sword.plural());
		assertFalse(axe.plural());
		assertFalse(flower.plural());
	}
	
	@Test
	public void testSetPlural() throws Exception {
		sword.setPlural(true);
		axe.setPlural(true);
		flower.setPlural(true);
		
		assertTrue(sword.plural());
		assertTrue(axe.plural());
		assertTrue(flower.plural());
	}
	
	@Test
	public void testGetIsContainer() throws Exception {
		assertFalse(sword.isContainer());
		assertFalse(axe.isContainer());
		assertFalse(flower.isContainer());
	}
	
	@Test
	public void testSetIsContainer() throws Exception {
		sword.setIsContainer(true);
		axe.setIsContainer(true);
		flower.setIsContainer(true);
		
		assertTrue(sword.isContainer());
		assertTrue(axe.isContainer());
		assertTrue(flower.isContainer());
	}
	
	@Test
	public void testGetItemWeight() throws Exception {
		assertEquals(10, sword.getWeight());
		assertEquals(20, axe.getWeight());
		assertEquals(5, flower.getWeight());
	}
	
	@Test
	public void testSetItemWeight() throws Exception {
		sword.setWeight(5);
		axe.setWeight(10);
		flower.setWeight(15);
		
		assertEquals(5, sword.getWeight());
		assertEquals(10, axe.getWeight());
		assertEquals(15, flower.getWeight());
	}
	
}
