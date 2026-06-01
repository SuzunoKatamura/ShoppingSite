package jp.co.aforce.beans;

public class Customer implements java.io.Serializable {
	
	private String member_id;
	private String password;
	private String last_name;
	private String first_name;
	private String address;
	private String mail_address;
	
	public String getMember_id() {
		return member_id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getMail_address() {
		return mail_address;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

}
