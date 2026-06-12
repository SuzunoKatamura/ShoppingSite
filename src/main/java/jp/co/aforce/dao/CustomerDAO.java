package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import jp.co.aforce.beans.Customer;

public class CustomerDAO extends DAO {
	public Customer search(String member_id, String password)
			throws Exception {
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

	// 会員登録処理
	public int insert(Customer c) throws Exception {

	    Connection con = getConnection();

	    // ★重要：入れる対象のカラム名（6個）をハッキリ指定する書き方に変更！
	    PreparedStatement st = con.prepareStatement(
	    		"INSERT INTO users (member_id, password, last_name, first_name, address, mail_address, username) VALUES (?, ?, ?, ?, ?, ?, ?)");

	    st.setString(1, c.getMember_id());
	    st.setString(2, c.getPassword());
	    st.setString(3, c.getLast_name());
	    st.setString(4, c.getFirst_name());
	    st.setString(5, c.getAddress());
	    st.setString(6, c.getMail_address());
	    
	 // ② 7番目のハテナに、仮のデータ（"temporary" など）を強制的にセットする
	    st.setString(7, "temporary");

	    // ★重要：実行は1回だけに修正して、結果を直接受け取る
	    int result = st.executeUpdate();

	    st.close();
	    con.close();
	    
	    return result;
	}

	//	会員情報修正処理
	public int update(Customer c) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"UPDATE users SET password=?, last_name=?, first_name=?, address=?, mail_address=? WHERE member_id=?");

		st.setString(1, c.getPassword());
		st.setString(2, c.getLast_name());
		st.setString(3, c.getFirst_name());
		st.setString(4, c.getAddress());
		st.setString(5, c.getMail_address());
		st.setString(6, c.getMember_id());

		int result = st.executeUpdate();

		st.close();
		con.close();

		return result;
	}

	public Customer searchById(String member_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"SELECT * FROM users WHERE member_id = ?");

		st.setString(1, member_id);

		ResultSet rs = st.executeQuery();

		Customer customer = null;

		if (rs.next()) {
			customer = new Customer();
			customer.setMember_id(rs.getString("member_id"));
			customer.setPassword(rs.getString("password"));
			customer.setLast_name(rs.getString("last_name"));
			customer.setFirst_name(rs.getString("first_name"));
			customer.setAddress(rs.getString("address"));
			customer.setMail_address(rs.getString("mail_address"));
		}

		rs.close();
		st.close();
		con.close();

		return customer;
	}

	//  会員情報削除処理
	public int delete(String memberId) throws Exception {

		Connection con = getConnection();

		// member_id を条件に1件削除するSQL
		PreparedStatement st = con.prepareStatement(
				"DELETE FROM users WHERE MEMBER_ID = ?");

		st.setString(1, memberId);

		int result = st.executeUpdate();

		st.close();
		con.close();

		return result;
	}

	//  メールアドレスの重複チェック（存在すればtrue、なければfalse）
	public boolean isEmailExists(String mailAddress) throws Exception {

		Connection con = getConnection();

		// 入力されたメールアドレスの件数を数えるSQL
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM users WHERE mail_address = ?");
		st.setString(1, mailAddress);

		ResultSet rs = st.executeQuery();

		boolean exists = false;
		if (rs.next()) {
			// カウントが0より大きければ「すでに存在する」
			if (rs.getInt(1) > 0) {
				exists = true;
			}
		}

		rs.close();
		st.close();
		con.close();

		return exists;
	}

	//  会員IDの重複チェック
	public boolean isMemberIdExists(String memberId) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM users WHERE member_id = ?");

		st.setString(1, memberId);
		ResultSet rs = st.executeQuery();
		boolean exists = false;
		if (rs.next() && rs.getInt(1) > 0)
			exists = true;

		rs.close();
		st.close();
		con.close();

		return exists;
	}

	//  ユーザーネームの重複チェック（DBにカラムを追加した後に動きます）
	public boolean isUsernameExists(String username) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");

		st.setString(1, username);
		ResultSet rs = st.executeQuery();
		boolean exists = false;
		if (rs.next() && rs.getInt(1) > 0)
			exists = true;

		rs.close();
		st.close();
		con.close();

		return exists;
	}
	
		// 管理者画面用：会員情報を全件取得する処理
		public List<Customer> findAllCustomers() throws Exception {
			List<Customer> customerList = new java.util.ArrayList<>();

			Connection con = getConnection();

			// usersテーブルから会員情報を全員分、ID順で取得するSQL
			PreparedStatement st = con.prepareStatement(
					"SELECT * FROM users ORDER BY member_id ASC");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer();
				
				customer.setMember_id(rs.getString("member_id"));
				customer.setPassword(rs.getString("password"));
				customer.setLast_name(rs.getString("last_name"));
				customer.setFirst_name(rs.getString("first_name"));
				customer.setAddress(rs.getString("address"));
				customer.setMail_address(rs.getString("mail_address"));
				
				// リストに1人ずつ追加
				customerList.add(customer);
			}

			rs.close();
			st.close();
			con.close();

			return customerList;
		}

}
