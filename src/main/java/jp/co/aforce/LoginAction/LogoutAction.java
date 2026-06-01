package jp.co.aforce.LoginAction;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutAction
 */
@WebServlet("/LoginAction/logout")
public class LogoutAction extends HttpServlet {

	public void doGet(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate(); // ログアウト処理
		}

		request.getRequestDispatcher("/views/login-in.jsp")
			   .forward(request, response);
	}
}