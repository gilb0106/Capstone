package com.capstone.beans;

public class User {
    private String userID;
    private String password;
    private String email;
    private String fName;
    private String lName;
    private int isAdmin =  0;
    
    public User() {
    	this.setUserID(userID);
    	this.setPassword(password);
    	this.setEmail(email);
    	this.setFName(fName);
    	this.setLName(lName);
    	this.setIsAdmin(isAdmin);
    }
    public User(String userID,String password,String email,String fName,String lName) {
    	this.setUserID(userID);
    	this.setPassword(password);
    	this.setEmail(email);
    	this.setFName(fName);
    	this.setLName(lName);
    	this.setIsAdmin(isAdmin);
    }

    public void reserveBook() {}
    public void searchBook() {}
    public void receiveNotification() {}

    // Getters and Setters
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFName() { return fName; }
    public void setFName(String fName) { this.fName = fName; }

    public String getLName() { return lName; }
    public void setLName(String lName) { this.lName = lName; }

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}


    
    
}