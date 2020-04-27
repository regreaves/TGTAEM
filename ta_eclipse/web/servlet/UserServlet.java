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
		
		req.getSession().setAttribute("log", "<br>Welcome " + user + "<br>(N)ew Game or (L)oad Game?<br>");
		req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("\nUserServlet: doPost");
		
		UserController controller = new UserController();
		User model = new User();
		controller.setModel(model);
		
		if (req.getParameter("input").equalsIgnoreCase("N")) {
			try {
				model.resetGame();
			} catch (SQLException e) {
				System.out.println("SQLException thrown");
				e.printStackTrace();
			}
			
			req.getSession().setAttribute("log", model.getGame().getLogFromDatabase());
			req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
			
			return;
		} else if (req.getParameter("input").equalsIgnoreCase("L")) {
			req.getSession().setAttribute("log", ("Loading saved game... <br>" + model.getGame().getLogFromDatabase()));
			req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
			
			return;
		}
		
		req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);

	}
}
