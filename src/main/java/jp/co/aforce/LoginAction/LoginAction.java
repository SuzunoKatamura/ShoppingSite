package jp.co.aforce.LoginAction;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
			if (customer != null) {
			    session.setAttribute("customer", customer);

			    // セッションから戻り先（cart.jsp）のメモを取り出す
			    String returnTarget = (String) session.getAttribute("returnTarget");

			    if (returnTarget != null) {
			        // メモがあった場合（カート画面から飛ばされてきた場合）
			        session.removeAttribute("returnTarget"); // 使い終わったメモは消す
			        
			        // カート画面（cart.jsp）へリダイレクトする
			        response.sendRedirect(request.getContextPath() + "/views/" + returnTarget);
			    } else {
			        // メモがない場合（普通にログインボタンから来た場合）
			        // 今まで通りマイページへ
			        request.getRequestDispatcher("/views/user-menu.jsp").forward(request, response);
			    }

			} else {
			    // ログイン失敗時（ここはそのまま）
			    request.getRequestDispatcher("/views/login-error.jsp").forward(request, response);
			    }

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/views/login-error.jsp")
					.forward(request, response);

		}
	}
}
