package jp.co.aforce.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class RegisterProduct
 */
@WebServlet("/admin/register-product")
public class RegisterProduct extends HttpServlet {
	
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
        
        // 1. 画面のフォームから送られてきたデータ（name属性）をキャッチ
        String pName = request.getParameter("pName");
        String pDesc = request.getParameter("pDesc");
        String pPriceStr = request.getParameter("pPrice");
        int pPrice = (pPriceStr != null) ? Integer.parseInt(pPriceStr) : 0;

        // 2. Beansにデータを詰め込む
        Product product = new Product();
        product.setProductName(pName);
        product.setDescription(pDesc);
        product.setPrice(pPrice);
        // ※ もしIDや3D天球画像名(sphere)の初期値が必要ならここでセットします
        // 例: product.setSphere("default.jpg");

        // 3. DAOを召喚してDBへ一括インサート（注入）
        ProductDAO productDAO = new ProductDAO();
        boolean isSuccess = productDAO.insertProduct(product);

        if (isSuccess) {
            // ⭕ 成功したら、管理者画面を表示する「大本のサーブレット」にリダイレクトして画面を更新する
            // ※このあと作る、データを再取得してくれるサーブレットへ戻します
            response.sendRedirect("AdminMainServlet"); 
        } else {
            // 失敗時
            request.setAttribute("errorMessage", "アーカイブへの注入に失敗しました。");
            request.getRequestDispatcher("admin-main.jsp").forward(request, response);
        }
    }

}
