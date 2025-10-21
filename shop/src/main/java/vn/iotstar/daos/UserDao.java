package vn.iotstar.daos;

import org.apache.catalina.User;

import vn.iotstar.models.UserModel;

import java.util.List;
import vn.iotstar.models.UserModel;

public interface UserDao {
	UserModel findByUserName(String username);

	int getRoleid();

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);
	
	UserModel Insert(String email,String username,String fullname, String password,String avatar,int roleid,String phone );

	//user on admin
	 List<UserModel> findAll();
	 UserModel findById(int id);
	 void insert(UserModel user);
	 void update(UserModel user);
	 void delete(int id);

	




	
}
