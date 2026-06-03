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
            dao.delete(memberId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/views/delete-out.jsp")
               .forward(request, response);
    }
}