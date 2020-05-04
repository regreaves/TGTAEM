package objectstests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import objects.Player;

public class PlayerTests {
	private Player player = new Player();
	
	@Before
	public void setUp() throws Exception {
		player.setID("0");
		player.setLocation("1");
		player.setAlive(true);
	}
	
	@Test
	public void testCheckAlive() throws Exception {
		assertEquals(true, player.alive());
	}
	
	@Test
	public void testGetLocation() throws Exception {
		assertEquals("1", player.getLocation());
	}
	
	@Test
	public void testGetID() throws Exception {
		assertEquals("0", player.getID());
	}
	
	@Test
	public void testKill() throws Exception {
		player.kill();
		assertEquals(false, player.alive());
	}
	
	@Test
	public void testSetLocation() throws Exception {
		player.setLocation("10");
		assertEquals("10", player.getLocation());
	}
	
	@Test
	public void testSetID() throws Exception {
		player.setID("10");
		assertEquals("10", player.getID());
	}
	
}
