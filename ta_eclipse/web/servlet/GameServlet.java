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

		String log = (String) req.getSession().getAttribute("log");
		try {
			log = log.concat(">").concat(req.getParameter("command")).concat("<br>").concat(model.getAction());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getSession().setAttribute("log", log);

		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
}