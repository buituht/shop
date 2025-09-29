package vn.iotstar.models;

import java.io.Serializable;

public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Khai bao các trường
	
	private int id;
	private String email;
	private String userName;
	private String fullName;
	private String passWord;
	private String avatar;
	private int roleid;
	private String phone;

	
	
	//Tạo contructor
	public UserModel() {
		super();
		
	}

	public UserModel(int id, String email, String userName, String fullName, String passWord, String avatar,int roleid,
			String phone) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.fullName = fullName;
		this.passWord = passWord;
		this.avatar = avatar;
		this.roleid = roleid;
		this.phone = phone;
		
	}

	
	// Tạo getters và Setter
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}
	

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRoleid() {
		return roleid;
	}
	
	public void setRoleid(int roleid) {
		// TODO Auto-generated method stub
		this.roleid = roleid;
	}
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	

	//Overide một số phương thức của java
	
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", email=" + email + ", userName=" + userName + ", fullName=" + fullName
				+ ", passWord=" + passWord + ", avatar=" + avatar + ", roleid=" + roleid + ", phone=" + phone
				+ "]";
	}


	
	
	
	
}
