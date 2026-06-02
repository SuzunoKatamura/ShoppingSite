package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Customer;
import jp.co.aforce.tool.Action;

public class UserConfirmAction extends Action {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

        // ① フォームの値を受け取る
        String member_id = request.getParameter("member_id");
        String password = request.getParameter("password");
        String last_name = request.getParameter("last_name");
        String first_name = request.getParameter("first_name");
        String address = request.getParameter("address");
        String mail_address = request.getParameter("mail_address");

        // ② Beanに詰める
        Customer customer = new Customer();
        customer.setMember_id(member_id);
        customer.setPassword(password);
        customer.setLast_name(last_name);
        customer.setFirst_name(first_name);
        customer.setAddress(address);
        customer.setMail_address(mail_address);

        // ③ JSPに渡す
        request.setAttribute("customer", customer);

        // ④ 遷移先を返す（FrontControllerがforwardする）
        return "user-confirm.jsp";
	}
}