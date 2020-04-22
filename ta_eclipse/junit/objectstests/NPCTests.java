package objectstests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import objects.NPC;

public class NPCTests {
	private NPC npc = new NPC();

	@Before
	public void setUp() throws Exception {
		npc.setID("1");
		npc.setName("test");
		npc.setHealth(100);
		npc.setAttack(10);
		npc.setDefense(10);
		npc.setDescription("description");
	}
	
	@Test
	public void testGetID() throws Exception {
		assertEquals("1", npc.getID());
	}
	
	@Test
	public void testGetName() throws Exception {
		assertEquals("test", npc.getName());
	}
	
	@Test
	public void testGetHealth() throws Exception {
		assertEquals(100, npc.getHealth());
	}
	
	@Test
	public void testGetAttack() throws Exception {
		assertEquals(10, npc.getAttack());
	}
	
	@Test
	public void testSetAttack() throws Exception {
		npc.setAttack(100);
		assertEquals(100, npc.getAttack());
	}
	
	@Test
	public void testGetDefense() throws Exception {
		assertEquals(10, npc.getDefense());
	}
	
	@Test
	public void testSetDefense() throws Exception {
		npc.setDefense(100);
		assertEquals(100, npc.getDefense());
	}
	
	@Test
	public void testGetDescription() throws Exception {
		assertEquals("description", npc.getDescription());
	}

}
