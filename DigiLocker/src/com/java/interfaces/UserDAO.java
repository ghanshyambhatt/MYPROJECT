package com.java.interfaces;

import com.java.model.User;

public interface UserDAO {
	String username="";
	String password="";
	String name="";
	String email="";
	String mobile="";
	
	public boolean createNewUser(User u);
	public boolean deleteUser(User u);
	public User getUserbyUsername(String username);
	public User getUserbyEmail(String email);
	public boolean UpdateUserInfo(User user);
	public boolean changePassword(String username, String password);
}
