package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import controller.UserController;
import state.User;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("\nUserServlet: doGet");

		String user = (String) req.getSession().getAttribute("user");


		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");

			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		String log = (String) req.getSession().getAttribute("log");
		log += "<br> Welcome " + user + "<br><br>(N)ew Game or (L)oad Game? <br>";
		req.getSession().setAttribute("log", log);
		// now we have the user's User object,
		// proceed to handle request...
		System.out.println("   User: <" + user + "> logged in");

		req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("\nUserServlet: doPost");
		
		UserController controller = new UserController();
		User model = new User();
		controller.setModel(model);
		
		boolean done = false;
		req.getSession().setAttribute("done", done);

		
		String i = req.getParameter("input");
		
		if (i.equalsIgnoreCase("n")) {
			try {
				model.resetGame();
			} catch (SQLException e) {
				System.out.println("SQLException thrown");
				e.printStackTrace();

			}
			String log = (String) req.getSession().getAttribute("log");
			log += "<br>> " + i + "<br> Loading new game... <br>";
			log += "<br>" + model.getGame().loadRoom("1") + "<br>";
			req.getSession().setAttribute("log", log);
			req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
			return;
		} else if (i.equalsIgnoreCase("l")) {
			String log = (String) req.getSession().getAttribute("log");
			log += "<br>> " + i + "<br> Loading saved game... <br>";
			log += model.getGame().getLog() + "<br>";
			req.getSession().setAttribute("log", log);
			req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
			return;
		}
		
		String log = (String) req.getSession().getAttribute("log");
		log += "<br>> " + i + "<br>Input \"n\" for new game and \"l\" for load game.";
		req.getSession().setAttribute("log", log);
		req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);

	}
}