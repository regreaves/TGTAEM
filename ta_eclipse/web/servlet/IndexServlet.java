package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserController;
import state.User;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final User model = new User();
	private static final UserController controller = new UserController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");

		req.setAttribute("username", model.getUsername());
		req.setAttribute("password", model.getPassword());
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Index Servlet: doPost");
				
		//UserController controller = new UserController();
		controller.setModel(model);
		
		//String myUsername = req.getParameter("terminalInput");
		controller.setUsername(req.getParameter("terminalInput"));
		req.setAttribute("username", model.getUsername());

		//String myPassword = req.getParameter("terminalInput");
		//controller.setPassword(req.getParameter("terminalInputTwo"));
		req.setAttribute("password", model.getPassword());
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
