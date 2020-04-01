package objectstests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import objects.Inventory;
import objects.Item;
import objects.Room;

public class RoomTests {
	private Room room1;
	private Room room2;
	private Room room3;
	
	private ArrayList<String> conn1 = new ArrayList<>();
	private ArrayList<String> conn2 = new ArrayList<>();
	private ArrayList<String> conn3 = new ArrayList<>();
	
	@Before
	public void setUp() {
		room1 = new Room("r1", "room one", "A small room.", false, conn1);
		room2 = new Room("r2", "room two", "A medium room.", false, conn2);
		room3 = new Room("r3", "room three", "A large room.", true, conn3);
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
}
