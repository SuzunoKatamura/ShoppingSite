package jp.co.aforce.confirm;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Customer;

/**
 * Servlet implementation class RegisterBack
 */
@WebServlet("/confirm/register-back")
public class RegisterBack extends HttpServlet {

    protected void doPost(
    		HttpServletRequest req, HttpServletResponse res
    		) throws ServletException, IOException {

        Customer customer = new Customer();

        customer.setMember_id(req.getParameter("member_id"));
        customer.setPassword(req.getParameter("password"));
        customer.setLast_name(req.getParameter("last_name"));
        customer.setFirst_name(req.getParameter("first_name"));
        customer.setAddress(req.getParameter("address"));
        customer.setMail_address(req.getParameter("mail_address"));

        req.setAttribute("customer", customer);

        req.getRequestDispatcher("/views/register.jsp")
           .forward(req, res);
    }
}