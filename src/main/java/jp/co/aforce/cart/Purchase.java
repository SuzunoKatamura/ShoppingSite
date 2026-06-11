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

import jp.co.aforce.beans.Cart;
import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Customer;
import jp.co.aforce.beans.Order;
import jp.co.aforce.beans.OrderItem;
import jp.co.aforce.dao.OrderDAO;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/cart/purchase")
public class Purchase extends HttpServlet {

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        
        //  セッションからユーザー情報とカート情報を引っこ抜く
        Customer customer = (Customer) session.getAttribute("customer"); 
        Cart cart = (Cart) session.getAttribute("cart");

        //  安全弁：そもそもログインしていない、またはカートが空っぽなら手続き画面などに強制送還
        if (customer == null) {
            response.sendRedirect("login-in.jsp"); // ログイン画面へ
            return;
        }
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp"); // カート画面へ
            return;
        }

        //  カートのデータを、DB保存用の「Order」構造に詰め替える
        Order order = new Order();
        order.setUserId(customer.getMember_id());
        order.setTotalPrice(cart.getTotalPrice()); //  カートクラスの合計金額を取得するメソッド名に合わせてね

        // カートの中身を、注文明細（OrderItem）のリストに変換する
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) { //  カートの中身をループする処理
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setPrice(cartItem.getPrice()); // 購入時の価格を固定
            
            orderItems.add(orderItem);
        }
        // 親のOrderに子の明細リストをガチッと合体
        order.setOrderItems(orderItems);

        //  DAOを召喚してDBへの一括インサート（購入処理）を実行
        OrderDAO orderDAO = new OrderDAO();
        boolean isSuccess = orderDAO.executePurchase(order);

        //  処理結果に応じた画面遷移
        if (isSuccess) {
            //  購入成功
            
            // お金を払ったので、セッションのカートの中身を消去する
            session.removeAttribute("cart"); 
            // または cart.clear(); など、カートを空にする処理でもOK

            // 4つ目の画面「購入完了画面（JSP）」へフォワード
            request.getRequestDispatcher("/views/cart-out.jsp").forward(request, response);
            
        } else {
            //  万が一DBエラーなどで失敗した場合
            request.setAttribute("errorMessage", "購入処理中にエラーが発生しました。もう一度お試しください。");
            // 3つ目の画面「注文内容確認画面（JSP）」に戻してあげる
            request.getRequestDispatcher("purchase.jsp").forward(request, response);
        }
	}
	
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		response.sendRedirect("cart.jsp");
	}


}
