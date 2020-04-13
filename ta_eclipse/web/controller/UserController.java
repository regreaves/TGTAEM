package controller;

import state.User;

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
	
	public void setUsername(String username) {
		this.model.setUsername(username);
	}
	
	public void setPassword(String password) {
		this.model.setPassword(password);
	}
}
