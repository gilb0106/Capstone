package com.capstone.beans;

import java.util.Date;

public class UserRegistration {
    private String userID;
    private String password;
    private String email;
    private String fName;
    private String lName;
    private int isAdmin =  0;
    private Date date = new Date();
    
    
    public UserRegistration(User user) {
        this.userID = user.getUserID();
        this.password  = user.getPassword();
        this.email = user.getEmail();
        this.fName = user.getFName();
        this.lName = user.getLName();
        sendEmailVerification();
    }

    public void createUser() {}
    public void sendEmailVerification() {
    	System.out.println("Verify your email: "  + email  + " for user " +  userID +  " just registered   on " +  date);

}

    // Getters and Setters
    public String getUserID()  { 
    	return userID;
    }
    public void setUserID(String userID) { 
    	this.userID = userID; 
    }
    public String getPassword() { 
    	return password; }
    
    public void setPassword(String password) {
    	this.password = password; 
    }
    public String getEmail() { 
    	return email; 
    }
    public void setEmail(String email) {
    	this.email = email; 
    }
    public String getFName() { 
    	return fName; }
    public void setFName(String fName) {
    	this.fName = fName; 
    }
    public String getLName() { 
    	return lName; }
    public void setLName(String lName) {
    	this.lName = lName; 
    }
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
}