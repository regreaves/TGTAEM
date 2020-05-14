package statetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import objects.Item;
import state.Status;

public class StatusTests {
	private Status status;
	
	@Before
	public void setUp() {
		status = new Status();
		
		status.setMove(2);
		status.setWaterOn(2);
		status.setHiding(false);
		status.setMonsterCheck1(false);
		status.setSitting(false);
		status.setLaptop(false);
		status.setWindow(false);
		status.setFlashlight(false);
		status.setSink(false);
		status.setShower(false);
		status.setWet(false);
		status.setSearchCouch(false);
		status.setPC(false);
		status.setMailFlag(false);
		status.setSearchGrass1(false);
		status.setSearchGrass2(false);
		status.setFlood(false);
		status.setDialogue(false);
	}
	
	@Test
	public void testGetMove() throws Exception {
		assertEquals(2, status.getMove());
	}
	@Test
	public void testSetMove() throws Exception {
		status.setMove(5);
		assertEquals(5, status.getMove());
	}
	@Test
	public void testGetWaterOn() throws Exception {
		assertEquals(2, status.getWaterOn());
	}
	@Test
	public void testSetWaterOn() throws Exception {
		status.setWaterOn(5);
		assertEquals(5, status.getWaterOn());
	}
	@Test
	public void testIsHiding() throws Exception {
		assertFalse(status.isHiding());
	}
	@Test
	public void testSetHiding() throws Exception {
		status.setHiding(true);
		assertTrue(status.isHiding());
	}
	@Test
	public void testIsMonsterCheck1() throws Exception {
		assertFalse(status.isMonsterCheck1());
	}
	@Test
	public void testSetMonsterCheck1() throws Exception {
		status.setMonsterCheck1(true);
		assertTrue(status.isMonsterCheck1());
	}
	@Test
	public void testIsSitting() throws Exception {
		assertFalse(status.isSitting());
	}
	@Test
	public void testSetSitting() throws Exception {
		status.setSitting(true);
		assertTrue(status.isSitting());
	}
	@Test
	public void testIsLaptop() throws Exception {
		assertFalse(status.isLaptop());
	}
	@Test
	public void testSetLaptop() throws Exception {
		status.setLaptop(true);
		assertTrue(status.isLaptop());
	}
	@Test
	public void testIsWindow() throws Exception {
		assertFalse(status.isWindow());
	}
	@Test
	public void testSetWindow() throws Exception {
		status.setWindow(true);
		assertTrue(status.isWindow());
	}
	@Test
	public void testIsFlashlight() throws Exception {
		assertFalse(status.isFlashlight());
	}
	@Test
	public void testSetFlashlight() throws Exception {
		status.setFlashlight(true);
		assertTrue(status.isFlashlight());
	}
	@Test
	public void testIsSink() throws Exception {
		assertFalse(status.isSink());
	}
	@Test
	public void testSetSink() throws Exception {
		status.setSink(true);
		assertTrue(status.isSink());
	}
	@Test
	public void testIsShower() throws Exception {
		assertFalse(status.isShower());
	}
	@Test
	public void testSetShower() throws Exception {
		status.setShower(true);;
		assertTrue(status.isShower());
	}
	@Test
	public void testIsClothes() throws Exception {
		assertTrue(status.isClothes());
	}
	@Test
	public void testSetClothes() throws Exception {
		status.setClothes(false);
		assertFalse(status.isClothes());
	}
	@Test
	public void testIsWet() throws Exception {
		assertFalse(status.isWet());
	}
	@Test
	public void testSetWet() throws Exception {
		status.setWet(true);
		assertTrue(status.isWet());
	}
	@Test
	public void testIsTV() throws Exception {
		assertTrue(status.isTV());
	}
	@Test
	public void testSetTV() throws Exception {
		status.setTV(false);
		assertFalse(status.isTV());
	}
	@Test
	public void testIsSearchCouch() throws Exception {
		assertFalse(status.isSearchCouch());
	}
	@Test
	public void testSetSearchCouch() throws Exception {
		status.setSearchCouch(true);
		assertTrue(status.isSearchCouch());
	}
	@Test
	public void testIsPC() throws Exception {
		assertFalse(status.isPC());
	}
	@Test
	public void testSetPC() throws Exception {
		status.setPC(true);
		assertTrue(status.isPC());
	}
	@Test
	public void testIsMailFlag() throws Exception {
		assertFalse(status.isMailFlag());
	}
	@Test
	public void testSetMailFlag() throws Exception {
		status.setMailFlag(true);
		assertTrue(status.isMailFlag());
	}
	@Test
	public void testIsSearchGrass1() throws Exception {
		assertFalse(status.isSearchGrass1());
	}
	@Test
	public void testSetSearchGrass1() throws Exception {
		status.setSearchGrass1(true);
		assertTrue(status.isSearchGrass1());
	}
	@Test
	public void testIsSearchGrass2() throws Exception {
		assertFalse(status.isSearchGrass2());
	}
	@Test
	public void testSetSearchGrass2() throws Exception {
		status.setSearchGrass2(true);
		assertTrue(status.isSearchGrass2());
	}
	@Test
	public void testIsFlood() throws Exception {
		assertFalse(status.isFlood());
	}
	@Test
	public void testSetFlood() throws Exception {
		status.setFlood(true);
		assertTrue(status.isFlood());
	}
	@Test
	public void testIsDialogue() throws Exception {
		assertFalse(status.isDialogue());
	}
	@Test
	public void testSetDialogue() throws Exception {
		status.setDialogue(true);
		assertTrue(status.isDialogue());
	}
	@Test
	public void testAdvance() throws Exception {
		status.setWaterOn(20);
		status.setSink(true);
		status.setShower(true);
		assertTrue(status.advance());
		assertTrue(status.isFlood());
	}
	
	@Test
	public void testEquip() throws Exception {
		ArrayList<Item> equipped = status.getEquipped();

		Item item = new Item();
		Item item2 = new Item();

		item.setID("i999");
		item.setName("MY GKDSKGDK");

		item2.setID("i654");
		item2.setName("ghrdshsK");

		status.equip(item);
		status.equip(item2);
		
		System.out.println(status.equippedToString(equipped));
		System.out.println(status.equippedToItemArrayList(status.equippedToString(equipped)));

		equipped.remove(1);
		
		System.out.println(status.equippedToString(equipped));
		System.out.println(status.equippedToItemArrayList(status.equippedToString(equipped)));
	}
}
