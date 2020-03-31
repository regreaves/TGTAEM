package cs320.tgtaem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.tgtaem.controller.GameController;
import cs320.tgtaem.model.GameTempModel;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Game Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game Servlet: doPost");
		
		// create GuessingGame model - model does not persist between requests
		// must recreate it each time a Post comes in 
		GameTempModel model = new GameTempModel();

		// create GuessingGame controller - controller does not persist between requests
		// must recreate it each time a Post comes in
		GameController controller = new GameController();
		
		// assign model reference to controller so that controller can access model
		controller.setModel(model);
		
		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		Double result = null;
		
		// decode POSTed form parameters and dispatch to controller
		try {
			String first = req.getParameter("first");

			// check for errors in the form data before using is in a calculation

				// Do we really need to check if the input is empty?
			
			/*if (first == null) {
				errorMessage = "Empty input";
			}*/
			
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			
			/*else {*/
				model.setFirst(first);
				controller.myMethod(first);
			/*}*/
		} catch (Exception e) {
			errorMessage = "<i>This is some sort of exception.</i>";
		}
		
		// Add parameters as request attributes
		// this creates attribute named "first" for the response, and grabs the
		// value that was originally assigned to the request attributes, also named "first"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("first", req.getParameter("first"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("result", result);
		req.setAttribute("surprise", model);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
}
