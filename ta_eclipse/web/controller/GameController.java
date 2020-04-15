package controller;

import state.Game;

/**
 * Controller for Game.
 */
public class GameController {
	private Game model;

	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	
	/*
	 * Set the Game model from pass of Login or Index
	 */
	public void setModel(Game model) {
		this.model = model;
	}

	public void setCommand(String command) {
		this.model.setCommand(command);
	}

	public void setHere(String id) {
		this.model.setHere(id);
	}
}
