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
		actor.setAttack(10);
		actor.setDefense(10);
	}
	
	@Test
	public void testCheckAlive() throws Exception {
		assertEquals(true, actor.alive());
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
		assertEquals(false, actor.alive());
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
	public void testGetAttack() throws Exception {
		assertEquals(10, actor.getAttack());
	}
	
	@Test
	public void testSetAttack() throws Exception {
		actor.setAttack(100);
		assertEquals(100, actor.getAttack());
	}
	
	@Test
	public void testGetDefense() throws Exception {
		assertEquals(10, actor.getDefense());
	}
	
	@Test
	public void testSetDefense() throws Exception {
		actor.setDefense(100);
		assertEquals(100, actor.getDefense());
	}

}
