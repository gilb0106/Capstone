package com.capstone.proxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.capstone.beans.ActivityLog;
import com.capstone.beans.User;
import com.capstone.dao.DatabaseUtility;
import com.capstone.dao.UserDao;
import com.capstone.dao.UserInterface;

public class userProxy implements UserInterface {

	private UserDao user;

	public userProxy() {
		this.user = new UserDao();
	} 
	@Override
	public boolean checkUserExists(String userID, String email) {
		return user.checkUserExists(userID, email);
	}
	@Override
	public boolean createUser(String userID, String password,String email, String fName, String lName) { // first successful mysql tried to use object array but  this worked so i left it	
		return user.createUser(userID, password, email, fName, lName); 
	}
	@Override
	public ActivityLog logUser(ActivityLog log) { // first successful mysql tried to use object array but  this worked so i left it	
		return user.logUser(log);
	}
	@Override
	public List<User> searchUser(String userID) {
		return user.searchUser(userID);}
	@Override
	public List<User> updatePassword(String userID, String password) {
		return user.updatePassword(userID, password);
	}
	@Override
	public List<User> showAll() {
		// TODO Auto-generated method stub
		return user.showAll();
	}
	@Override
	public List<User> forgotPassword(String email) {
		return user.forgotPassword(email);
	}
}


