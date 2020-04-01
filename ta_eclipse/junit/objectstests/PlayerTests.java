package objectstests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import objects.Player;

public class PlayerTests {
	private Player player = new Player();
	private ArrayList<String> stats = new ArrayList<>();
	private ArrayList<String> stats2 = new ArrayList<>();
	@Before
	public void setUp() throws Exception {
		player.setID("0");
		player.setLocation("1");
		player.setStats(stats);
	}
	
	@Test
	public void testCheckAlive() throws Exception {
		assertEquals(true, player.checkAlive());
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
		assertEquals(false, player.checkAlive());
	}
	
	@Test
	public void testGetStats() throws Exception {
		assertEquals(stats, player.getStats());
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
	
	@Test
	public void testSetStats() throws Exception {
		player.setStats(stats2);
		assertEquals(stats2, player.getStats());
	}
	
}
