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
		sword = new Item("1", "sword", "A steel sword.", "A sword that is light.", true, false);
		axe = new Item("2", "axe", "An iron axe.", "An iron axe that is heavy.", true, false);
		flower = new Item("3", "flower", "A red rose.", "The thorns are sharp.", true, false);
		
		items1.add(sword);
		items1.add(axe);
		
		items2.add(flower);
		
		inv1 = new Inventory(10, "inventory one");
		inv2 = new Inventory(1, "inventory two");
		inv3 = new Inventory(5, "inventory three");
		
		inv1.setItems(items1);
		inv2.setItems(items2);
	}
	
	@Test
	public void testGetSize() throws Exception {
		assertEquals(10, inv1.getSize());
		assertEquals(1, inv2.getSize());
		assertEquals(5, inv3.getSize());
	}
	
	@Test
	public void testCheckSize() throws Exception {
		assertTrue(inv1.checkSize());
		assertFalse(inv2.checkSize());
		assertTrue(inv3.checkSize());
	}
	
	@Test
	public void testGetCurrentSize() throws Exception {
		assertEquals(8, inv1.getCurrentSize());
		assertEquals(0, inv2.getCurrentSize());
		assertEquals(5, inv3.getCurrentSize());
	}
	
	@Test
	public void testGetNumberItems() throws Exception {
		assertEquals(2, inv1.getNumberItems());
		assertEquals(1, inv2.getNumberItems());
		assertEquals(0, inv3.getNumberItems());
	}
}
