package statetests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import state.User;

public class UserTests {
	private static User user;
	
	@Before
	public void setUp() {
		user = new User();
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("defaultUsername", user.getUsername());
	}
	
	@Test
	public void testSetUsername() {
		user.setUsername("customUsername");
		assertEquals("customUsername", user.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("defaultPassword", user.getPassword());
	}
	
	@Test
	public void testSetPassword() {
		user.setPassword("customPassword");
		assertEquals("customPassword", user.getPassword());
	}
}
