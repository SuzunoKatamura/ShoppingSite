package jp.co.aforce.product;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/product/product-list")
public class ProductList extends HttpServlet {

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		try {
			// 1. 商品DAOのインスタンスを生成
			ProductDAO dao = new ProductDAO();

			// 2. DAOの findAll() を呼び出して、DBから全商品データを取得
			List<Product> productList = dao.findAll();

			// 3. 取得したリストを、JSPに引き渡すためにリクエストスコープに保存
			//    名前（鍵）を "productList" にして、中身のデータをセット
			request.setAttribute("productList", productList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 4. 商品一覧を表示するJSPへフォワード
		request.getRequestDispatcher("/views/index.jsp").
		forward(request, response);
	}

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException {
		doGet(request, response);
	}
	
}
