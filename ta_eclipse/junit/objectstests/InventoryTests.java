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
		sword = new Item("1", "sword", "A steel sword.", "A sword that is light.", false, false, false, false, 10);
		axe = new Item("2", "axe", "An iron axe.", "An iron axe that is heavy.", false, false, true, false, 20);
		flower = new Item("3", "flower", "A red rose.", "The thorns are sharp.", false, false, false, false, 5);
		
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
		assertTrue(inv1.checkWeight(flower));
		assertFalse(inv2.checkWeight(sword));
		assertTrue(inv3.checkWeight(flower));
	}
	
	@Test
	public void testGetMaxTotalWeight() throws Exception {
		assertEquals(40, inv1.getMaxTotalWeight());
		assertEquals(5, inv2.getMaxTotalWeight());
		assertEquals(30, inv3.getMaxTotalWeight());
	}
	
	@Test
	public void testGetNumberItems() throws Exception {
		assertEquals(2, inv1.getNumberItems());
		assertEquals(1, inv2.getNumberItems());
		assertEquals(0, inv3.getNumberItems());
	}
	
	@Test
	public void testSetMaxSize() throws Exception {
		inv1.setMaxTotalWeight(5);
		inv2.setMaxTotalWeight(10);
		inv3.setMaxTotalWeight(15);
		
		assertEquals(5, inv1.getMaxTotalWeight());
		assertEquals(10, inv2.getMaxTotalWeight());
		assertEquals(15, inv3.getMaxTotalWeight());
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
