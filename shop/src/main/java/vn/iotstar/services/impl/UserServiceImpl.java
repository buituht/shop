package vn.iotstar.services.impl;

import vn.iotstar.daos.UserDao;
import vn.iotstar.daos.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;



public class UserServiceImpl implements UserService{
	
	
	//private static final UserDaoImpl UserDao = null;
	//private UserDao userDao;
	UserDao userDao = new UserDaoImpl();// Gọi tất cả các phương thức của tầng DAO
	
	@Override
	public UserModel findByUserName(String username) {
		
		return userDao.findByUserName(username);
	}

	public UserModel login(String username, String password) {
		
		UserModel user = (UserModel) this.findByUserName(username);
		if (user != null && password.equals(user.getPassWord())) {
		return user;
	}
		return null;
	}

	public boolean register(String email, String username,String fullname, String password,String avatar,int roleid, String phone ) {

			userDao.Insert(email,username,fullname,password,avatar,roleid,phone);
			
			return true;
	        
		}

	@Override
	public void insert(UserDao user) {
		// TODO Auto-generated method stub
		
		
	}

	

	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		
		return false;
		
		
	}

	@Override
	public boolean checkExistUsername(String username) {
		// TODO Auto-generated method stub
		
		return false;

	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean insert(UserDaoImpl newUser) {
		// TODO Auto-generated method stub
		return false;
	}


	
	@Override
	public boolean register(UserServiceImpl user) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub

		return false;
	}





	@Override
	public boolean register(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(UserDao user) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
