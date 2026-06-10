package jp.co.aforce.cart;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// ⭕ 作成したBeansをインポート
import jp.co.aforce.beans.Cart;
import jp.co.aforce.beans.CartItem;

@WebServlet("/cart/cart-add")
public class CartAdd extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //  画面から送られてきた情報をキャッチ
        String productId = request.getParameter("productId");
        String name = request.getParameter("name"); // もし画面から商品名や価格も送れるなら受け取る
        String priceStr = request.getParameter("price");
        int price = (priceStr != null) ? Integer.parseInt(priceStr) : 0; // 数値に変換
        
        HttpSession session = request.getSession();
        
        //  セッションから「Cartクラス」のインスタンスを取得する形に変更！
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart(); // 初めてなら新しくカートの器を作る
        }
        
        //  カートに商品を追加
        if (productId != null && !productId.isEmpty()) {
            // CartItemを作って、カートに放り込む（数量は1固定のコンストラクタ）
            CartItem item = new CartItem(productId, name, price, 1);
            cart.addItem(item); // ⭕ 重複ガードが入ったaddItemメソッドが動く！
        }
        
        //  アップデートしたカートを再びセッションに保存
        session.setAttribute("cart", cart);
        
        //  画面はそのまま（トップページなど）に突き返す
        response.sendRedirect("/ShoppingSite/product/product-list");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}