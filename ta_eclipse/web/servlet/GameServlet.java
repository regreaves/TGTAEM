package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.GameController;
import state.Game;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nGameServlet: doGet");
		String user = (String) req.getSession().getAttribute("user");

		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");

			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		Game model = new Game();
		String log = (String) req.getSession().getAttribute("log");
		req.getSession().setAttribute("log", log);

		req.setAttribute("game", model);
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nGameServlet: doPost");
		
		Game model = new Game();
		GameController controller = new GameController();
		controller.setModel(model);
		
		String command = req.getParameter("command");
		controller.setCommand(command);
		req.setAttribute("command", req.getParameter("command"));

		boolean done = (boolean) req.getSession().getAttribute("done");
		String log = (String) req.getSession().getAttribute("log");
		String gameOver = "";
		
		
		if(done) {
			if(model.isDone()) {
				gameOver = "";
				done = false;
				req.getSession().setAttribute("done", done);
				if(command.equalsIgnoreCase("y"))
				{
					try {
						model.reset();
						model.setDone(false);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					log = "Loading new game... <br>";
					log += "<br>" + model.loadRoom("1") + "<br>";
				} else if(command.equalsIgnoreCase("n")) {
					log  = "Goodbye. <br>";
					model.setDone(false);
				} else {
					log += "<br>> " + command + "<br> Please enter Y for yes and N for no. <br>";
				}
			}
			if(model.isQuit()) {
				model.setQuit(false);
				req.getSession().invalidate();
				done = false;
				req.getSession().setAttribute("done", done);
				log = "Welcome to TGTAEM. Please login. <br>";
				req.getSession().setAttribute("log", log);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			}
		} else {
			try {
				  log = log.concat("<br>> ").concat(req.getParameter("command")).concat("<br>").concat(model.getAction() + "<br>");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		if(model.isDone()) {
			gameOver = "YOU DIED <br> Try again? y/n <br>";
			done = true;
			req.getSession().setAttribute("done", done);
		}
		
		if(model.isQuit()) {
			log += "Your game is saved. <br> Come back soon! <br>";
			done = true;
			req.getSession().setAttribute("done", done);
		}
		
		req.getSession().setAttribute("log", log);
		req.setAttribute("game", model);
		req.setAttribute("over", gameOver);

		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
}