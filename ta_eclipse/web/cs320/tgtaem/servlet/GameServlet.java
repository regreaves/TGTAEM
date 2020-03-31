package cs320.tgtaem.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs320.tgtaem.controller.GameController;
import state.Game;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String log = "";
	private int move = 0;
	
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
		
		// create Game model - model does not persist between requests
		// must recreate it each time a Post comes in 
		Game model = null;
		try {
			model = new Game();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(move == 0)
		{
			log = model.getRoomOne();
		}
		// create Game controller - controller does not persist between requests
		// must recreate it each time a Post comes in
		GameController controller = new GameController();
		
		// assign model reference to controller so that controller can access model
		controller.setModel(model);
		
		// decode POSTed form parameters and dispatch to controller

		String command = req.getParameter("command");
			
		// must create the controller each time, since it doesn't persist between POSTs
		// the view does not alter data, only controller methods should be used for that
		// thus, always call a controller method to operate on the data
			
		controller.setCommand(command);
		
		// Add parameters as request attributes
		// this creates attribute named "first" for the response, and grabs the
		// value that was originally assigned to the request attributes, also named "first"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("command", req.getParameter("command"));
		log = log.concat("<br>").concat(req.getParameter("command")).concat("<br>").concat(model.getAction());
		//log = log.concat(("_ ").concat(req.getParameter("first").concat("<br>This is a programmed response.<br>")));
		req.setAttribute("log", log);
		req.setAttribute("surprise", model);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		move++;
	}
}
