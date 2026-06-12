package jp.co.aforce.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Customer;
import jp.co.aforce.beans.Order;
import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.CustomerDAO;
import jp.co.aforce.dao.OrderDAO;
import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class AdminMain
 */
@WebServlet("/admin/admin-main")
public class AdminMain extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
        
        //  最初から箱（List）を空っぽで用意しておく
        List<Product> productList = null;
        List<Order> orderList = null;
        List<Customer> customerList = null;

        try {
            ProductDAO productDAO = new ProductDAO();
            productList = productDAO.findAll();

            OrderDAO orderDAO = new OrderDAO();
            orderList = orderDAO.findAllOrders(); 
            
            CustomerDAO customerDAO = new CustomerDAO();
            customerList = customerDAO.findAllCustomers();

        } catch (Exception e) {
            // 万が一DB接続エラーなどが起きたら、Eclipseのコンソールに原因を出力する
            e.printStackTrace();
            request.setAttribute("dbError", "データベースの接続に失敗しました。");
        }

        // ⭕ 例外が起きようが起きまいが、安全にJSPへ荷物をバトンタッチする
        request.setAttribute("productList", productList);
        request.setAttribute("orderList", orderList);
        request.setAttribute("customerList", customerList);

        // 管理者画面へフォワード
        request.getRequestDispatcher("/views/admin/admin-main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

}
