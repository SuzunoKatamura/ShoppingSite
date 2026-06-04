package jp.co.aforce.confirm;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.CustomerDAO;

/**
 * Servlet implementation class IdCheck
 * メンバーIDの重複チェックをリアルタイムで行うためのサーブレット
 * 「Ajax（エイジャックス）」という技術（fetch APIなど）
 */
@WebServlet("/confirm/id-check")
public class IdCheck extends HttpServlet {

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String memberId = request.getParameter("member_id");
        CustomerDAO dao = new CustomerDAO();
        
        try {
            // DBにIDが存在するかチェック
            if (dao.isMemberIdExists(memberId)) {
                out.print("NG"); // すでに使われている
            } else {
                out.print("OK"); // 使える
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("ERROR");
        }
	}

}
