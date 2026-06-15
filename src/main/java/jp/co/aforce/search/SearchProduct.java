package jp.co.aforce.search;

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
 * Servlet implementation class SearchProduct
 */
@WebServlet("/search/search-product")
public class SearchProduct extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		//  画面の検索窓から送られてきたキーワード（inputのname属性）を受け取る
		String keyword = request.getParameter("keyword");

		// 万が一、キーワードが空（何も入力せずに検索された）の場合は空文字にしておく
		if (keyword == null) {
			keyword = "";
		}

		List<Product> productList = null;

		try {
			//  DAOの検索メソッドを召喚
			ProductDAO dao = new ProductDAO();
			productList = dao.searchProducts(keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//  検索結果のリストと、ユーザーが何で検索したかのキーワードをJSPに送る
		request.setAttribute("productList", productList);
		request.setAttribute("keyword", keyword); // 「〇〇の検索結果」と画面に出す用

		//  商品一覧画面にフォワードして表示させる
		request.getRequestDispatcher("/product/product-list").
			forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
