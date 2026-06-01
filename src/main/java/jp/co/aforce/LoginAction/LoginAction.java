package jp.co.aforce.LoginAction;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Customer;
import jp.co.aforce.dao.CustomerDAO;

@WebServlet("/LoginAction/login")
public class LoginAction extends HttpServlet {

	public void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			HttpSession session = request.getSession();

			String member_id = request.getParameter("member_id");
			String password = request.getParameter("password");

			CustomerDAO dao = new CustomerDAO();
			Customer customer = dao.search(member_id, password);

			//			クッキー
			String remember = request.getParameter("remember");
			if ("on".equals(remember)) {
				Cookie cookie = new Cookie("member_id", member_id);
				
				cookie.setMaxAge(60*60*24*30);
				
				response.addCookie(cookie);
			}

				if (customer != null) {
					session.setAttribute("customer", customer);

					request.getRequestDispatcher("/views/user-menu.jsp")
							.forward(request, response);

				} else {

					request.getRequestDispatcher("/views/login-error.jsp")
							.forward(request, response);
				}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/views/login-error.jsp")
					.forward(request, response);

		}
	}
}
