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
import jp.co.aforce.dao.CustomerDAO;

/**
 * Servlet implementation class UserInsert
 */
@WebServlet("/confirm/user-insert")
public class UserInsert extends HttpServlet {

    protected void doPost(
    		HttpServletRequest request, HttpServletResponse response
    		) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
     // 画面（JSP）からパラメータを取得
        String memberId = request.getParameter("member_id");
        String password = request.getParameter("password");
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");
        String address = request.getParameter("address");
        String mailAddress = request.getParameter("mail_address");
        String username = request.getParameter("username");

        Customer customer = new Customer();

        customer.setMember_id(request.getParameter("member_id"));
        customer.setPassword(request.getParameter("password"));
        customer.setLast_name(request.getParameter("last_name"));
        customer.setFirst_name(request.getParameter("first_name"));
        customer.setAddress(request.getParameter("address"));
        customer.setMail_address(request.getParameter("mail_address"));

        CustomerDAO dao = new CustomerDAO();
        
        try {
            // エラーを詰めるリスト
            List<String> errors = new ArrayList<>();

            // 1. 会員IDの重複チェック
            if (dao.isMemberIdExists(memberId)) {
                errors.add("この会員IDは既に登録されています。");
            }
            
            // 2. メールアドレスの重複チェック
            if (dao.isEmailExists(mailAddress)) {
                errors.add("このメールアドレスは既に登録されています。");
            }
            
            // 3. ユーザーネームの重複チェック
            // if (dao.isUsernameExists(username)) {
            //     errors.add("このユーザーネームは既に使われています。");
            // }

            // もし重複エラーが1つでも入っていたら、登録画面に戻す
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors); // JSPが待っている "errors" という名前で送る
                request.setAttribute("customer", customer); // 入力データを保持して戻す

                request.getRequestDispatcher("/views/register.jsp").forward(request, response);
                return; 
            }

            // すべてのチェックを通過したら、DBへ挿入
            dao.insert(customer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/views/register-out.jsp")
                .forward(request, response);
    }
}
