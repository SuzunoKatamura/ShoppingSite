package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.Customer;

public class CustomerDAO extends DAO {
	public Customer search(String member_id, String password)
			throws Exception{
		Customer customer = null;
		
		Connection con = getConnection();
		
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from users where member_id=? and password=?");
		st.setString(1, member_id);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			customer = new Customer();
			customer.setMember_id(rs.getString("member_id"));
			customer.setPassword(rs.getString("password"));
			customer.setLast_name(rs.getString("last_name"));
		}
		
		st.close();
		con.close();
		return customer;
		
	}

}
