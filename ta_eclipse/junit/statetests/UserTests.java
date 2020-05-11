package statetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import state.User;
import state.Game;

public class UserTests {
	private User user;
	private Game game;
	
	@Before
	public void setUp() {
		user = new User();
		game = new Game();
		user.setName("user");
		user.setGame(game);
	}
	
	@Test
	public void testGetName() throws Exception {
		assertEquals("user", user.getName());
	}
	
	@Test
	public void testGetGame() throws Exception {
		assertEquals(game, user.getGame());
	}
	
}
