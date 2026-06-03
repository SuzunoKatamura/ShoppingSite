package jp.co.aforce.update;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Customer;
import jp.co.aforce.dao.CustomerDAO;

/**
 * Servlet implementation class UserEdit
 */
@WebServlet("/update/user-edit")
public class UserEdit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("member_id");

        CustomerDAO dao = new CustomerDAO();

        Customer customer = null;
		try {
			customer = dao.searchById(id);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

        request.setAttribute("customer", customer);

        request.getRequestDispatcher("/views/edit.jsp")
               .forward(request, response);
    }
}
