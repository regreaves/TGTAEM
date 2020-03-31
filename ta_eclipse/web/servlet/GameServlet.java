package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.GameController;
import state.Game;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String log = "";
	private int move = 0;
	private String here = "1";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Game Servlet: doGet");	
		Game model;
		try {
		model = new Game();
		log = model.getRoomOne();
		req.setAttribute("log", log);

		} catch (SQLException e) {
		e.printStackTrace();
		}
		// call JSP to generate empty form
		Game model;
		try {
			model = new Game();
			log = model.getRoomOne();
			req.setAttribute("log", log);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// create Game controller - controller does not persist between requests
		// must recreate it each time a Post comes in
		GameController controller = new GameController();
		
		// assign model reference to controller so that controller can access model
		controller.setModel(model);
		controller.setHere(here);
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
		req.setAttribute("command", req.getParameter("command"));
		req.setAttribute("log", log);
		req.setAttribute("surprise", model);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		here = model.here();
		move++;
	}
}
