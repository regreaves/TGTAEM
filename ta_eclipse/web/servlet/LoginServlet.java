package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LoginController;
import state.Library;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Library model;
	private LoginController controller;
	private String log = "Welcome to TGTAEM. Please login.";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("\nLoginServlet: doGet");
		req.getSession().setAttribute("log", log);
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("\nLoginServlet: doPost");

		String errorMessage = null;
		String name = null;
		String pw = null;
		boolean validLogin = false;
		boolean validUsername = false;

		// Decode form parameters and dispatch to controller
		name = req.getParameter("username");
		pw = req.getParameter("password");

		System.out.println("   Name: <" + name + "> PW: <" + pw + ">");

		if (name == null || pw == null || name.equals("") || pw.equals("")) {
			errorMessage = "Please specify both user name and password";
		} else {
			model = new Library();
			controller = new LoginController(model);
			validUsername = controller.checkUserName(name);
			validLogin = controller.validateCredentials(name, pw);

			if (!validUsername || !validLogin) {
				errorMessage = "Username and/or password invalid";
			}
		}

		// Add parameters as request attributes
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("login", validLogin);

		// if login is valid, start a session
		if (validLogin) {
			System.out.println("   Valid login - starting session, redirecting to /user");

			// store user object in session
			req.getSession().setAttribute("user", name);

			// redirect to /user page
			resp.sendRedirect(req.getContextPath() + "/user");

			return;
		}

		System.out.println("   Invalid login - returning to /Login");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
}
