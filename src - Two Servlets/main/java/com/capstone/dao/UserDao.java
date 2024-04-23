package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.capstone.beans.*;

public class UserDao implements UserInterface{
	@Override
	public boolean checkUserExists(String userID, String email) {
		try {
			Connection conn = DatabaseUtility.getInstance().getConnection();
			PreparedStatement ps=conn.prepareStatement("SELECT COUNT(*) FROM capstone.users WHERE userID = ? OR email = ?"); 
			ps.setString(1, userID);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		}catch (Exception e) {
		}
		return false;
	}
	@Override
	public boolean createUser(String userID, String password,String email, String fName, String lName) { // first successful mysql tried to use object array but  this worked so i left it	
		try (Connection conn = DatabaseUtility.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO capstone.users (userID, password, email, fName, lName, loginStatus) VALUES (?, ?, ?, ?, ?, '0')")) {
			ps.setString(1, userID);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, fName);
			ps.setString(5, lName);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}return true;
	}
	@Override
	public ActivityLog logUser(ActivityLog log) { // first successful mysql tried to use object array but  this worked so i left it	
		try (Connection conn = DatabaseUtility.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO capstone.activity_log (userID, loginStatus, activity, timeStamp) VALUES (?,?,?,?)")) {
			ps.setString(1, log.getUserid());
			ps.setString(2, log.getLoginStatus());
			ps.setString(3, log.getActivity());
			ps.setString(4, log.getDate());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}return log;
	}
	@Override
	public List<User> searchUser(String userID) {
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = DatabaseUtility.getInstance().getConnection();
			String searchUser = "SELECT * FROM users WHERE userID =" + "'" + userID + "'";
			PreparedStatement stmt = conn.prepareStatement(searchUser);
			ResultSet set = stmt.executeQuery(searchUser);
			if((set.next())) {
				User user = new User();
				user.setUserID(set.getString(1));
				user.setPassword(set.getString(2));
				user.setEmail(set.getString(3));
				user.setFName(set.getString(4));
				user.setLName(set.getString(5));
				users.add(user);
			}
			conn.close();
		}catch (Exception e) {
		}
		return users;}
	@Override
	public List<User> updatePassword(String userID, String password) {
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = DatabaseUtility.getInstance().getConnection();

			PreparedStatement stmt = conn.prepareStatement("UPDATE USERS set password=?  WHERE userID =?");
			User user = new User();
			user.setUserID(userID);
			user.setPassword(password);
			stmt.setString(1,user.getPassword()); 
			stmt.setString(2,user.getUserID()); 
			users.add(user);
			System.out.println(stmt.executeUpdate()); 
			conn.close();
		}catch (Exception e) {
		}
		return users;}
	@Override
	public List<User> showAll() {
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = DatabaseUtility.getInstance().getConnection();
			String searchUser = "SELECT * FROM users";
			PreparedStatement stmt = conn.prepareStatement(searchUser);
			ResultSet set = stmt.executeQuery(searchUser);
			while(set.next()) {
				User user = new User();
				user.setUserID(set.getString(1));
				user.setPassword(set.getString(2));
				user.setEmail(set.getString(3));
				user.setFName(set.getString(4));
				user.setLName(set.getString(5));
				users.add(user);
			}
			conn.close();
		}catch (Exception e) {
		}
		return users;}
	@Override
	public List<User> forgotPassword(String email) {
		List<User> users = new ArrayList<User>();

		try {
			Connection conn = DatabaseUtility.getInstance().getConnection();
			String searchUser = "SELECT userID,password FROM users where email = " + "'" + email + "'"  ;
			PreparedStatement stmt = conn.prepareStatement(searchUser);
			ResultSet set = stmt.executeQuery(searchUser);
			while(set.next()) {
				User user = new User();
				user.setUserID(set.getString(1));
				user.setPassword(set.getString(2));
				users.add(user);
			}
			conn.close();
		}catch (Exception e) {
		}
		return users;
	}

}


