package objectstests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import objects.Connections;
import objects.Room;

public class RoomTests {
	private Room room1;
	private Room room2;
	private Room room3;
	
	private Connections conn1 = new Connections();
	private Connections conn2 = new Connections();
	private Connections conn3 = new Connections();
	
	@Before
	public void setUp() {
		room1 = new Room();
		room2 = new Room();
		room3 = new Room();
		
		room1.setID("r1");
		room2.setID("r2");
		room3.setID("r3");
		
		room1.setDisplayName("room one");
		room2.setDisplayName("room two");
		room3.setDisplayName("room three");
		
		room1.setDescription("A small room.");
		room2.setDescription("A medium room.");
		room3.setDescription("A large room.");
		
		room1.setVisited(false);
		room2.setVisited(false);
		room3.setVisited(true);
		
		room1.setConnections(conn1);
		room2.setConnections(conn2);
		room3.setConnections(conn3);
		
		room1.setDark(false);
		room2.setDark(false);
		room3.setDark(false);
		
		room1.setLocked(false);
		room2.setLocked(false);
		room3.setLocked(false);
		
		room1.setTemp(0);
		room2.setTemp(0);
		room3.setTemp(0);
	}
	
	@Test
	public void testGetID() throws Exception {
		assertEquals("r1", room1.getID());
		assertEquals("r2", room2.getID());
		assertEquals("r3", room3.getID());
	}
	
	@Test
	public void testSetID() throws Exception {
		room1.setID("room1");
		room2.setID("room2");
		room3.setID("room3");
		
		assertEquals("room1", room1.getID());
		assertEquals("room2", room2.getID());
		assertEquals("room3", room3.getID());
	}
	
	@Test
	public void testGetDisplayName() throws Exception {
		assertEquals("room one", room1.getDisplayName());
		assertEquals("room two", room2.getDisplayName());
		assertEquals("room three", room3.getDisplayName());
	}
	
	@Test
	public void testSetDisplayName() throws Exception {
		room1.setDisplayName("small room");
		room2.setDisplayName("medium room");
		room3.setDisplayName("large room");
		
		assertEquals("small room", room1.getDisplayName());
		assertEquals("medium room", room2.getDisplayName());
		assertEquals("large room", room3.getDisplayName());
	}
	
	@Test
	public void testGetDescription() throws Exception {
		assertEquals("A small room.", room1.getDescription());
		assertEquals("A medium room.", room2.getDescription());
		assertEquals("A large room.", room3.getDescription());
	}
	
	@Test
	public void testSetDescription() throws Exception {
		room1.setDescription("small");
		room2.setDescription("medium");
		room3.setDescription("large");
		
		assertEquals("small", room1.getDescription());
		assertEquals("medium", room2.getDescription());
		assertEquals("large", room3.getDescription());
	}
	
	@Test
	public void testGetVisited() throws Exception {
		assertFalse(room1.getVisited());
		assertFalse(room2.getVisited());
		assertTrue(room3.getVisited());
	}
	
	@Test
	public void testSetVisited() throws Exception {
		room1.setVisited(true);
		room2.setVisited(true);
		room3.setVisited(false);
		
		assertTrue(room1.getVisited());
		assertTrue(room2.getVisited());
		assertFalse(room3.getVisited());
	}
	
	@Test
	public void testDark() throws Exception {
		assertFalse(room1.dark());
		assertFalse(room2.dark());
		assertFalse(room3.dark());
	}
	
	@Test
	public void testSetDark() throws Exception {
		room1.setDark(true);
		room2.setDark(true);
		room3.setDark(true);
		
		assertTrue(room1.dark());
		assertTrue(room2.dark());
		assertTrue(room3.dark());
	}
	
	@Test
	public void testLocked() throws Exception {
		assertFalse(room1.locked());
		assertFalse(room2.locked());
		assertFalse(room3.locked());
	}
	
	@Test
	public void testSetLocked() throws Exception {
		room1.setLocked(true);
		room2.setLocked(true);
		room3.setLocked(true);
		
		assertTrue(room1.locked());
		assertTrue(room2.locked());
		assertTrue(room3.locked());
	}
	
	@Test
	public void testTemp() throws Exception {
		assertEquals("", room1.temp());
		assertEquals("", room2.temp());
		assertEquals("", room3.temp());
	}
	
	@Test
	public void testSetTempInt() throws Exception {
		room1.setTemp(1);
		room2.setTemp(0);
		room3.setTemp(-1);
		
		assertEquals("It is rather hot here.", room1.temp());
		assertEquals("", room2.temp());
		assertEquals("It is really cold here.", room3.temp());
	}
	
	@Test
	public void testSetTempString() throws Exception {
		room1.setTemp("hot");
		room2.setTemp("normal");
		room3.setTemp("cold");
		
		assertEquals("hot", room1.temp());
		assertEquals("normal", room2.temp());
		assertEquals("cold", room3.temp());
	}
}
