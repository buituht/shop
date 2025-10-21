package vn.iotstar.services;

import java.util.List;

import org.apache.catalina.User;

import vn.iotstar.daos.UserDao;
import vn.iotstar.daos.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.impl.UserServiceImpl;

public interface UserService {
	
	UserModel login(String username, String password);
	UserModel findByUserName(String username);
	void insert(UserDao user);
	//boolean register(String username);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);

	boolean insert(UserDaoImpl newUser);

	boolean register(String username);

	boolean register(UserServiceImpl user);

	boolean register(UserDao user);

	//boolean register(String username, String password, String email, String fullname, String phone);
	boolean register(String email, String username,String fullname, String password,String avatar,int roleid, String phone);
	

 // --- Quản lý User (CRUD) ---
    
    /** Lấy tất cả người dùng (Dùng cho Admin List) */
    List<UserModel> findAll();
    
    /** Tìm người dùng theo ID */
    UserModel findById(int id);
    
    /** Thêm người dùng mới */
    void insert(UserModel user); 
    
    /** Cập nhật thông tin người dùng */
    void update(UserModel user);

    /** Xóa người dùng */
    void delete(int id);

	


	
}


