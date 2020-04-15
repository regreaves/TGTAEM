package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.GameController;
import state.Game;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Game model = new Game();
	private static GameController controller = new GameController();
	private static String log = model.loadRoom(model.here());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nGameServlet: doGet");
		
		req.setAttribute("log", log);
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nGameServlet: doPost");
		
		controller.setModel(model);
		
		String command = req.getParameter("command");
		controller.setCommand(command);
		req.setAttribute("command", req.getParameter("command"));

		log = log.concat("<br>> ").concat(req.getParameter("command")).concat("<br>").concat(model.getAction());
		req.setAttribute("log", log);

		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
