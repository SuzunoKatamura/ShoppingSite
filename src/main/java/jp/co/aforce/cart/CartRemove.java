package jp.co.aforce.cart;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Cart;

@WebServlet("/cart/cart-remove")
public class CartRemove extends HttpServlet {

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		//  画面から送られてきた消したい商品のIDをキャッチ
		String productId = request.getParameter("productId");
		
		//  セッションから現在のカートを取り出す
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		//  カートが存在して、IDもちゃんと届いていたら削除を実行
		if (cart != null && productId != null && !productId.isEmpty()) {
			
			//  Cartクラスにある、商品を削除するメソッドを呼び出し
			cart.removeItem(productId); 
			
			// アップデートされたカートを再びセッションに上書き保存
			session.setAttribute("cart", cart);
		}
		
		// JavaScript（fetch）に対して「無事に削除できた」という合図（ステータス200）だけを送る
		response.setStatus(HttpServletResponse.SC_OK);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}