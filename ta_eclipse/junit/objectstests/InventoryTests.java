package objectstests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import objects.Inventory;
import objects.Item;

public class InventoryTests {
	private Inventory inv1;
	private Inventory inv2;
	private Inventory inv3;
	
	private ArrayList<Item> items1 = new ArrayList<>();
	private ArrayList<Item> items2 = new ArrayList<>();
	
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
		
		sword.setWeight(10);
		axe.setWeight(20);
		flower.setWeight(5);
		
		items1.add(sword);
		items1.add(axe);
		
		items2.add(flower);
		
		inv1 = new Inventory(0, 40, "inventory one");
		inv2 = new Inventory(0, 5, "inventory two");
		inv3 = new Inventory(0, 30, "inventory three");
		
		inv1.setItems(items1);
		inv2.setItems(items2);
	}
	
	@Test
	public void testGetCurrentWeight() throws Exception {
		assertEquals(30, inv1.getCurrentWeight());
		assertEquals(5, inv2.getCurrentWeight());
		assertEquals(0, inv3.getCurrentWeight());
	}
	
	@Test
	public void testCheckWeight() throws Exception {
		assertTrue(inv1.hasSpace(flower));
		assertFalse(inv2.hasSpace(sword));
		assertTrue(inv3.hasSpace(flower));
	}
	
	@Test
	public void testGetMaxTotalWeight() throws Exception {
		assertEquals(40, inv1.getMaxWeight());
		assertEquals(5, inv2.getMaxWeight());
		assertEquals(30, inv3.getMaxWeight());
	}
	
	@Test
	public void testGetNumberItems() throws Exception {
		assertEquals(2, inv1.getNumberItems());
		assertEquals(1, inv2.getNumberItems());
		assertEquals(0, inv3.getNumberItems());
	}
	
	@Test
	public void testSetMaxSize() throws Exception {
		inv1.setMaxWeight(5);
		inv2.setMaxWeight(10);
		inv3.setMaxWeight(15);
		
		assertEquals(5, inv1.getMaxWeight());
		assertEquals(10, inv2.getMaxWeight());
		assertEquals(15, inv3.getMaxWeight());
	}
	
	@Test
	public void testGetItems() throws Exception {
		assertEquals(items1, inv1.getItems());
		assertEquals(items2, inv2.getItems());
	}
	
	@Test
	public void testGetItemByName() throws Exception {
		assertEquals(sword, inv1.getItemByName("sword"));
		assertEquals(flower, inv2.getItemByName("flower"));
	}
}
