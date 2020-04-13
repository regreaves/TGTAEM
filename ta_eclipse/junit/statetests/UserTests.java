package statetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import state.User;

public class UserTests {
	private User defaultUser;
	//private User customUser;
	
	@Before
	public void setUp() {
		defaultUser = new User();
		//customUser  = new User();
		
		
	}
	
	@Test
	public void testGetUsername() {
		
	}
}