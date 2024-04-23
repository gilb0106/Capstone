package com.capstone.LoginSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.capstone.beans.User;
import com.capstone.dao.DatabaseUtility;

public class Login {
   
    private static Login instance;
   
    private Login() {

    }
    public static synchronized Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }
 
    public boolean login(String userid, String password) {
        boolean status = false;
        try {
        	Connection conn = DatabaseUtility.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from users where userid=? and password=?");
            ps.setString(1, userid);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
	public boolean checkUserAdmin(String userID) {
		 boolean status = false;
		 List<User> users  = new ArrayList<User>();
		try{  
			Connection conn = DatabaseUtility.getInstance().getConnection(); 
			PreparedStatement ps=conn.prepareStatement("select userID,isAdmin from users where userid=?");  
			ps.setString(1,userID);
			ResultSet rs=ps.executeQuery();  
			while(rs.next()) {
				User user = new User();
				user.setUserID(rs.getString(1));
				user.setIsAdmin(rs.getInt(2));
				users.add(user);
			if(user.getIsAdmin() == 1) {
				status  = true;
			}else {	
			}
			}
		}catch(Exception e){System.out.println(e);}  
		return status;
	}  
}