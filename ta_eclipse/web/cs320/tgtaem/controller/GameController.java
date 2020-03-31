package cs320.tgtaem.controller;

import state.Game;

/**
 * Controller for game.
 */
public class GameController {
	private Game model;

	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(Game model) {
		this.model = model;
	}
	
	public void setCommand(String command)
	{
		this.model.setCommand(command);
	}
	//Not sure we need anything else tbh???? - kai

	
}
