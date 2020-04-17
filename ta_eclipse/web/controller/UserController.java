package controller;

import state.*;

/**
 * Controller for User.
 */
public class UserController {
	private User model;

	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(User model) {
		this.model = model;
	}
	
	public void setName(String username) {
		this.model.setName(username);
	}
	
	public void setGame(Game game) {
		this.model.setGame(game);
	}
}
