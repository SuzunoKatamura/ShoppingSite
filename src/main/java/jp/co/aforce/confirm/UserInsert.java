package jp.co.aforce.confirm;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Customer;
import jp.co.aforce.dao.CustomerDAO;

/**
 * Servlet implementation class UserInsert
 */
@WebServlet("/confirm/user-insert")
public class UserInsert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Customer customer = new Customer();

        customer.setMember_id(request.getParameter("member_id"));
        customer.setPassword(request.getParameter("password"));
        customer.setLast_name(request.getParameter("last_name"));
        customer.setFirst_name(request.getParameter("first_name"));
        customer.setAddress(request.getParameter("address"));
        customer.setMail_address(request.getParameter("mail_address"));

        CustomerDAO dao = new CustomerDAO();
        try {
			dao.insert(customer);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

        request.getRequestDispatcher("user-out.jsp")
                .forward(request, response);
	}

}
