package cs320.tgtaem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.tgtaem.controller.NumbersController;
import cs320.tgtaem.model.Numbers;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("AddNumbers Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("AddNumbers Servlet: doPost");
		
		// create GuessingGame model - model does not persist between requests
		// must recreate it each time a Post comes in 
		Numbers model = new Numbers();

		// create GuessingGame controller - controller does not persist between requests
		// must recreate it each time a Post comes in
		NumbersController controller = new NumbersController();
		
		// assign model reference to controller so that controller can access model
		controller.setModel(model);
		
		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		Double result = null;
		
		// decode POSTed form parameters and dispatch to controller
		try {
			Double first = getDoubleFromParameter(req.getParameter("first"));
			Double second = getDoubleFromParameter(req.getParameter("second"));
			Double third = getDoubleFromParameter(req.getParameter("third"));

			// check for errors in the form data before using is in a calculation
			if (first == null || second == null || third == null) {
				errorMessage = "<i>More!</i> Give me <i>more!</i>";
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				//NumbersController controller = new NumbersController();
				model.setFirst(first);
				model.setSecond(second);
				model.setThird(third);
				controller.add(first, second, third);
			}
		} catch (NumberFormatException e) {
			errorMessage = "Oh, now you've really done it.";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("first", req.getParameter("first"));
		req.setAttribute("second", req.getParameter("second"));
		req.setAttribute("third", req.getParameter("third"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("result", result);
		req.setAttribute("surprise", model);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s
	private Double getDoubleFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Double.parseDouble(s);
		}
	}
}
