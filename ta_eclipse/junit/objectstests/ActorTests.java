package objectstests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import objects.Actor;

public class ActorTests {
	private Actor actor = new Actor();
	private ArrayList<String> stats = new ArrayList<>();
	private ArrayList<String> stats2 = new ArrayList<>();
	@Before
	public void setUp() throws Exception {
		actor.setID("0");
		actor.setLocation("1");
		actor.setStats(stats);
	}
	
	@Test
	public void testCheckAlive() throws Exception {
		assertEquals(true, actor.checkAlive());
	}
	
	@Test
	public void testGetLocation() throws Exception {
		assertEquals("1", actor.getLocation());
	}
	
	@Test
	public void testGetID() throws Exception {
		assertEquals("0", actor.getID());
	}
	
	@Test
	public void testGetStats() throws Exception {
		assertEquals(stats, actor.getStats());
	}
	
	@Test
	public void testKill() throws Exception {
		actor.kill();
		assertEquals(false, actor.checkAlive());
	}
	
	@Test
	public void testSetLocation() throws Exception {
		actor.setLocation("10");
		assertEquals("10", actor.getLocation());
	}
	
	@Test
	public void testSetID() throws Exception {
		actor.setID("10");
		assertEquals("10", actor.getID());
	}
	
	@Test
	public void testSetStats() throws Exception {
		actor.setStats(stats2);
		assertEquals(stats2, actor.getStats());
	}

}
