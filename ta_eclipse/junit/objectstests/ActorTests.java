package objectstests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import objects.Actor;

public class ActorTests {
	private Actor actor = new Actor();

	@Before
	public void setUp() throws Exception {
		actor.setID("0");
		actor.setLocation("1");
		actor.setAlive(true);
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

}
