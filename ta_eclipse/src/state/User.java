package state;

import java.sql.SQLException;

public class User {
	String name;
	Game game;
	
	
	public User() {
		this.game = new Game();
	}

	public String getName() {
		return name;
	}

	public void setName(String username) {
		this.name = username;		
	}

 	public Game getGame() {
 		return game;
 	}
 
 	public void setGame(Game game) {
 		this.game = game;	
 	}
 
	public void resetGame() throws SQLException {
		this.game.reset();
	}
}
