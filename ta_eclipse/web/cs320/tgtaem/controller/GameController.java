package cs320.tgtaem.controller;

import cs320.tgtaem.model.GameTempModel;

/**
 * Controller for game.
 */
public class GameController {
	private GameTempModel model;

	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(GameTempModel model) {
		this.model = model;
	}

	public void myMethod(String first) {
		model.setResult(first);
	}
}
