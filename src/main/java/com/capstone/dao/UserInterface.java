package com.capstone.dao;

import java.util.List;

import com.capstone.beans.ActivityLog;
import com.capstone.beans.User;
public interface UserInterface {
	
	public boolean checkUserExists(String userID, String email);
	public boolean createUser(String userID, String password,String email, String fName, String lName);
	public ActivityLog logUser(ActivityLog log);
	public List<User> searchUser(String userID);
	public List<User> updatePassword(String userID, String password);
	public List<User> showAll();
	public List<User> forgotPassword(String email);
}
