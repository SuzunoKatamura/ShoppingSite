package jp.co.aforce.tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.action")
public class FrontController extends HttpServlet {

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException {

		try {
			String path = request.getServletPath();
			// /UserConfirm.action

			String className = path.substring(1, path.lastIndexOf(".action"));
			// UserConfirm

			className = "jp.co.aforce.action." + className;
			// jp.co.aforce.action.UserConfirm

			Action action = (Action) Class.forName(className)
					.getDeclaredConstructor()
					.newInstance();

			String url = action.execute(request, response);

			request.getRequestDispatcher(url)
					.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}