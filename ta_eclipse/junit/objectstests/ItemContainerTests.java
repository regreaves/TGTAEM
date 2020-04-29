package objectstests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import objects.Item;
import objects.ItemContainer;

public class ItemContainerTests {
	
	private Item sword;
	private Item axe;
	private Item flower;
	
	private ItemContainer box;
	private ItemContainer bag;
	
	private ArrayList<Item> items1 = new ArrayList<>();
	private ArrayList<Item> items2 = new ArrayList<>();
	
	@Before
	public void setUp() {
		sword = new Item();
		axe = new Item();
		flower = new Item();
		box = new ItemContainer();
		bag = new ItemContainer();
		
		sword.setID("1");
		axe.setID("2");
		flower.setID("3");
		box.setID("4");
		bag.setID("5");
		
		sword.setName("sword");
		axe.setName("axe");
		flower.setName("flower");
		box.setName("box");
		bag.setName("bag");
		
		sword.setInitDscrpt("A steel sword.");
		axe.setInitDscrpt("An iron axe.");
		flower.setInitDscrpt("A red rose.");
		box.setInitDscrpt("A small box.");
		bag.setInitDscrpt("A large bag.");
		
		sword.setInventDscrpt("A sword that is light.");
		axe.setInventDscrpt("An iron axe that is heavy.");
		flower.setInventDscrpt("The thorns are sharp.");
		box.setInventDscrpt("A box that can hold small items.");
		bag.setInventDscrpt("A bag that can hold many items.");
		
		sword.setHidden(false);
		axe.setHidden(false);
		flower.setHidden(false);
		box.setHidden(false);
		bag.setHidden(false);
		
		sword.setCritical(true);
		axe.setCritical(true);
		flower.setCritical(false);
		box.setCritical(false);
		bag.setCritical(false);
		
		sword.setMoved(false);
		axe.setMoved(false);
		flower.setMoved(false);
		box.setMoved(false);
		bag.setMoved(false);
		
		sword.setVowel(false);
		axe.setVowel(true);
		flower.setVowel(false);
		box.setVowel(false);
		bag.setVowel(false);
		
		sword.setPlural(false);
		axe.setPlural(false);
		flower.setPlural(false);
		box.setPlural(false);
		bag.setPlural(false);
		
		sword.setWeight(10);
		axe.setWeight(20);
		flower.setWeight(5);
		box.setWeight(15);
		bag.setWeight(20);
		
		box.setMaxWeight(20);
		bag.setMaxWeight(30);
		
		items1.add(sword);
		items1.add(flower);
		
		items2.add(axe);
		
		box.setItems(items1);
		bag.setItems(items2);
	}
	
	@Test
	public void testGetID() throws Exception {
		assertEquals("1", sword.getID());
		assertEquals("2", axe.getID());
		assertEquals("3", flower.getID());
		assertEquals("4", box.getID());
		assertEquals("5", bag.getID());
	}
	
	@Test
	public void testGetName() throws Exception {
		assertEquals("sword", sword.getName());
		assertEquals("axe", axe.getName());
		assertEquals("flower", flower.getName());
		assertEquals("box", box.getName());
		assertEquals("bag", bag.getName());
	}
	
	@Test
	public void testGetInitDscrpt() throws Exception {
		assertEquals("A steel sword.", sword.getInitDscrpt());
		assertEquals("An iron axe.", axe.getInitDscrpt());
		assertEquals("A red rose.", flower.getInitDscrpt());
		assertEquals("A small box.", box.getInitDscrpt());
		assertEquals("A large bag.", bag.getInitDscrpt());
	}
	
	@Test
	public void testGetInventDscrpt() throws Exception {
		assertEquals("A sword that is light.", sword.getInventDscrpt());
		assertEquals("An iron axe that is heavy.", axe.getInventDscrpt());
		assertEquals("The thorns are sharp.", flower.getInventDscrpt());
		assertEquals("A box that can hold small items.", box.getInventDscrpt());
		assertEquals("A bag that can hold many items.", bag.getInventDscrpt());
	}
	
	@Test
	public void testHidden() throws Exception {
		assertFalse(sword.hidden());
		assertFalse(axe.hidden());
		assertFalse(flower.hidden());
		assertFalse(box.hidden());
		assertFalse(bag.hidden());
	}
	
	@Test
	public void testCritical() throws Exception {
		assertTrue(sword.critical());
		assertTrue(axe.critical());
		assertFalse(flower.critical());
		assertFalse(box.critical());
		assertFalse(bag.critical());
	}
	
	@Test
	public void testSetCritical() throws Exception {
		sword.setCritical(true);
		axe.setCritical(false);
		flower.setCritical(true);
		box.setCritical(true);
		bag.setCritical(true);
		
		assertTrue(sword.critical());
		assertFalse(axe.critical());
		assertTrue(flower.critical());
		assertTrue(box.critical());
		assertTrue(bag.critical());
	}
	
	@Test
	public void testMoved() throws Exception {
		assertFalse(sword.moved());
		assertFalse(axe.moved());
		assertFalse(flower.moved());
		assertFalse(box.moved());
		assertFalse(bag.moved());
	}
	
	@Test
	public void testMove() throws Exception {
		sword.move();
		axe.move();
		flower.move();
		box.move();
		bag.move();
		
		assertTrue(sword.moved());
		assertTrue(axe.moved());
		assertTrue(flower.moved());
		assertTrue(box.moved());
		assertTrue(bag.moved());
	}
	
	@Test
	public void testSetHidden() throws Exception {
		sword.setHidden(true);
		axe.setHidden(true);
		flower.setHidden(true);
		box.setHidden(true);
		bag.setHidden(true);
		
		assertTrue(sword.hidden());
		assertTrue(axe.hidden());
		assertTrue(flower.hidden());
		assertTrue(box.hidden());
		assertTrue(bag.hidden());
	}
	
	@Test
	public void testSetInitDscrpt() throws Exception {
		sword.setInitDscrpt("A silver sword.");
		axe.setInitDscrpt("An old axe.");
		flower.setInitDscrpt("A bright red rose.");
		box.setInitDscrpt("A cardboard box.");
		bag.setInitDscrpt("A blue backpack.");
		
		assertEquals("A silver sword.", sword.getInitDscrpt());
		assertEquals("An old axe.", axe.getInitDscrpt());
		assertEquals("A bright red rose.", flower.getInitDscrpt());
		assertEquals("A cardboard box.", box.getInitDscrpt());
		assertEquals("A blue backpack.", bag.getInitDscrpt());
	}
	
	@Test
	public void testSetInventDscrpt() throws Exception {
		sword.setInventDscrpt("A shiny sword.");
		axe.setInventDscrpt("A heavy axe.");
		flower.setInventDscrpt("It has thorns.");
		box.setInventDscrpt("A brown box.");
		bag.setInventDscrpt("A new backpack.");
		
		assertEquals("A shiny sword.", sword.getInventDscrpt());
		assertEquals("A heavy axe.", axe.getInventDscrpt());
		assertEquals("It has thorns.", flower.getInventDscrpt());
		assertEquals("A brown box.", box.getInventDscrpt());
		assertEquals("A new backpack.", bag.getInventDscrpt());
	}
	
	@Test
	public void testVowel() throws Exception {
		assertFalse(sword.vowel());
		assertTrue(axe.vowel());
		assertFalse(flower.vowel());
		assertFalse(box.vowel());
		assertFalse(bag.vowel());
	}
	
	@Test
	public void testSetVowel() throws Exception {
		sword.setVowel(true);
		axe.setVowel(true);
		flower.setVowel(true);
		box.setVowel(true);
		bag.setVowel(true);
		
		assertTrue(sword.vowel());
		assertTrue(axe.vowel());
		assertTrue(flower.vowel());
		assertTrue(box.vowel());
		assertTrue(bag.vowel());
	}
	
	@Test
	public void testPlural() throws Exception {
		assertFalse(sword.plural());
		assertFalse(axe.plural());
		assertFalse(flower.plural());
		assertFalse(box.plural());
		assertFalse(bag.plural());
	}
	
	@Test
	public void testSetPlural() throws Exception {
		sword.setPlural(true);
		axe.setPlural(true);
		flower.setPlural(true);
		box.setPlural(true);
		bag.setPlural(true);
		
		assertTrue(sword.plural());
		assertTrue(axe.plural());
		assertTrue(flower.plural());
		assertTrue(box.plural());
		assertTrue(bag.plural());
	}
	
	@Test
	public void testGetItemWeight() throws Exception {
		assertEquals(10, sword.getWeight());
		assertEquals(20, axe.getWeight());
		assertEquals(5, flower.getWeight());
		assertEquals(15, box.getWeight());
		assertEquals(20, bag.getWeight());
	}
	
	@Test
	public void testSetItemWeight() throws Exception {
		sword.setWeight(5);
		axe.setWeight(10);
		flower.setWeight(15);
		box.setWeight(10);
		bag.setWeight(15);
		
		assertEquals(5, sword.getWeight());
		assertEquals(10, axe.getWeight());
		assertEquals(15, flower.getWeight());
		assertEquals(10, box.getWeight());
		assertEquals(15, bag.getWeight());
	}
	
	@Test
	public void testGetMaxWeight() throws Exception {
		assertEquals(20, box.getMaxWeight());
		assertEquals(30, bag.getMaxWeight());
	}
	
	@Test
	public void testSetMaxWeight() throws Exception {
		box.setMaxWeight(5);
		bag.setMaxWeight(10);
		
		assertEquals(5, box.getMaxWeight());
		assertEquals(10, bag.getMaxWeight());
	}
	
	@Test 
	public void testGetItems() throws Exception {
		assertEquals(items1, box.getItems());
		assertEquals(items2, bag.getItems());
	}
	
	@Test
	public void testSetItems() throws Exception {
		box.setItems(items2);
		bag.setItems(items1);
		
		assertEquals(items2, box.getItems());
		assertEquals(items1, bag.getItems());
	}
	
}
