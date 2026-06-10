package jp.co.aforce.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CartAdd
 */
@WebServlet("/cart/cart-add")
public class CartAdd extends HttpServlet {

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		// 画面から送られてきた商品IDを受け取る
        String productId = request.getParameter("productId");
        
        HttpSession session = request.getSession();
        
     // セッションから既存のカートリストを取得
        @SuppressWarnings("unchecked")
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        
        //  カートリストに商品IDを追加
        if (productId != null && !productId.isEmpty()) {
            cart.add(productId);
        }
        
        //  アップデートしたカートを再びセッションに保存
        session.setAttribute("cart", cart);
        
        //  cart.jspへリダイレクト
        response.sendRedirect("cart.jsp");

	}
	
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		doGet(request, response);

	}

}
