package controller;

import model.Numbers;

/**
 * Controller for numbers.
 */
public class NumbersController {
	private Numbers model;

	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(Numbers model) {
		this.model = model;
	}

	public void add(double first, double second, double third) {
		model.setResult(first + second + third);
	}
	
	public void multiply(double first, double second, double third) {
		model.setResult(first * second * third);
	}
}
