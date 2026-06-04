package jp.co.aforce.update;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.CustomerDAO;

@WebServlet("/update/user-delete")
public class UserDelete extends HttpServlet {

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
            ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 削除する会員のIDを取得
        String memberId = request.getParameter("member_id");

        CustomerDAO dao = new CustomerDAO();

        try {
        	//  DBからデータを完全に削除
            dao.delete(memberId);
            
            // セッションを削除
            jakarta.servlet.http.HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); 
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //  リダイレクトで入力データを引き継がせない
        response.sendRedirect(request.getContextPath() + "/views/delete-out.jsp");
    }
}