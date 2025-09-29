package vn.iotstar.daos;

import org.apache.catalina.User;

import vn.iotstar.models.UserModel;

public interface UserDao {
	UserModel findByUserName(String username);

	int getRoleid();

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);

	//boolean insert(User user);
	
	UserModel Insert(String email,String username,String fullname, String password,String avatar,int roleid,String phone );


	

	




	
}
