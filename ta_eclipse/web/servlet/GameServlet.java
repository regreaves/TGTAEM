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
	private Game model;
	private GameController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nGameServlet: doGet");

		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nGameServlet: doPost");

		String errorMessage = null;
		String name         = null;
		String pw           = null;
		boolean validLogin  = false;
		boolean validUsername  = false;

		// Decode form parameters and dispatch to controller
		name = req.getParameter("username");
		pw   = req.getParameter("password");

		System.out.println("   Name: <" + name + "> PW: <" + pw + ">");			

		if (name == null || pw == null || name.equals("") || pw.equals("")) {
			errorMessage = "Please specify both user name and password";
		} else {
			model      = new Game();
			controller = new GameController();

//			if (!validUsername) {
//				errorMessage = "Username invalid";
//			} else if (!validLogin) {
////				errorMessage = "Username and/or password invalid";
//				errorMessage = "Password invalid";
//			}
		}

		// Add parameters as request attributes
//		req.setAttribute("username", req.getParameter("username"));
//		req.setAttribute("password", req.getParameter("password"));

		// Add result objects as request attributes
//		req.setAttribute("errorMessage", errorMessage);
//		req.setAttribute("login",        validLogin);

		// if login is valid, start a session
//		if (validLogin) {
//			System.out.println("   Valid login - starting session, redirecting to /index");
//
//			// store user object in session
//			req.getSession().setAttribute("user", name);
//
//			// redirect to /index page
//			resp.sendRedirect(req.getContextPath() + "/index");
//
//			return;
//		}

//		System.out.println("   Invalid login - returning to /Login");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
}
