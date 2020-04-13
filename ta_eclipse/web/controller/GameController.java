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
	public void setModel(Game model) {
		this.model = model;
	}
	
	public void setCommand(String command)
	{
		this.model.setCommand(command);
	}
	
	public void setHere(String id)
	{
		this.model.setHere(id);
	}
	//Not sure we need anything else tbh???? - kai
	
/*
 *	[!]	10 April 2020
 *
 *		Include User controls here, or have a UserController class?
 *
 *		I favor the latter: We can have GameController set which
 *		Game is currently played, and which Games are saved, for
 *		separate User objects this way. -R
 */
}
