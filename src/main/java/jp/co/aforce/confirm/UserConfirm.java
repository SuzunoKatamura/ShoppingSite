package jp.co.aforce.confirm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Customer;

/**
 * Servlet implementation class UserConfirm
 */
@WebServlet("/confirm/user-confirm")
public class UserConfirm extends HttpServlet {

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
        request.setCharacterEncoding("UTF-8");

        Customer customer = new Customer();

        customer.setMember_id(request.getParameter("member_id"));
        customer.setPassword(request.getParameter("password"));
        customer.setLast_name(request.getParameter("last_name"));
        customer.setFirst_name(request.getParameter("first_name"));
        customer.setAddress(request.getParameter("address"));
        customer.setMail_address(request.getParameter("mail_address"));
        
        
        
        String memberId = request.getParameter("member_id");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail_address");

        // エラーメッセージを複数詰め込めるリスト
        List<String> errors = new ArrayList<>();

        // 1. メンバーIDのチェック
        if (memberId == null || memberId.isBlank()) {
            errors.add("メンバーIDを入力してください");
        } else if (!memberId.matches("^[a-zA-Z0-9]+$")) {
            errors.add("メンバーIDは半角英数字のみで入力してください");
        }

        // 2. パスワードのチェック
        if (password == null || password.isBlank()) {
            errors.add("パスワードを入力してください");
        } else if (password.length() > 10) {
            errors.add("パスワードは10文字以内で入力してください");
        } else if (!password.matches("^[a-zA-Z0-9]+$")) {
            errors.add("パスワードは半角英数字のみで入力してください");
        }

        // 3. メールのチェック
        if (mail == null || mail.isBlank()) {
            errors.add("メールアドレスを入力してください");
        } else if (!mail.contains("@")) {
            errors.add("メール形式が正しくありません");
        }

        // リストの中にエラーが1個でも入っていたら画面を戻す
        if (!errors.isEmpty()) {

            // "errors" という名前でリストをJSPに渡す
            request.setAttribute("errors", errors);
            request.setAttribute("customer", customer);

            request.getRequestDispatcher("/views/register.jsp")
                   .forward(request, response);

            return;
        }

        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/views/user-confirm.jsp")
                .forward(request, response);
		
	}

}
