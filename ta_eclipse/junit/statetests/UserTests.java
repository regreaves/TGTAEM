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
		user.setName("defaultUsername");
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("defaultUsername", user.getName());
	}
	
	@Test
	public void testSetUsername() {
		user.setName("customUsername");
		assertEquals("customUsername", user.getName());
	}
	
//	@Test
//	public void testGetPassword() {
//		assertEquals("defaultPassword", user.getPassword());
//	}
//	
//	@Test
//	public void testSetPassword() {
//		user.setPassword("customPassword");
//		assertEquals("customPassword", user.getPassword());
//	}
}
