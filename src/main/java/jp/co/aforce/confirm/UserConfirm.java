package jp.co.aforce.confirm;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Customer;

/**
 * Servlet implementation class UserConfirm
 */
@WebServlet("/confirm/user-confirm")
public class UserConfirm extends HttpServlet {

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
        request.setCharacterEncoding("UTF-8");

        Customer customer = new Customer();

        customer.setMember_id(request.getParameter("member_id"));
        customer.setPassword(request.getParameter("password"));
        customer.setLast_name(request.getParameter("last_name"));
        customer.setFirst_name(request.getParameter("first_name"));
        customer.setAddress(request.getParameter("address"));
        customer.setMail_address(request.getParameter("mail_address"));
        
        
        
        String memberId = request.getParameter("member_id");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail_address");

        String error = null;

        if (memberId == null || memberId.isBlank()) {
            error = "メンバーIDを入力してください";
        } else if(password == null || password.length() > 10){
            error = "パスワードは10文字以内で入力してください";
        } else if (mail == null || !mail.contains("@")) {
            error = "メール形式が正しくありません";
        }
        
        if (error != null) {

            request.setAttribute("error", error);
            request.setAttribute("customer", customer);

            request.getRequestDispatcher("/views/register.jsp")
                   .forward(request, response);

            return;
        }

        request.setAttribute("customer", customer);

        request.getRequestDispatcher("/views/user-confirm.jsp")
                .forward(request, response);
		
	}

}
