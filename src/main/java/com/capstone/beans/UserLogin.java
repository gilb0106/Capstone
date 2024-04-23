package com.capstone.beans;

public class UserLogin {
    private String userID;
    private String password;
    private boolean loginStatus;

    public void login() {}
    public void loginStatus() {}
    public void passwordRecovery() {}

    // Getters and Setters
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isLoginStatus() { return loginStatus; }
    public void setLoginStatus(boolean loginStatus) { this.loginStatus = loginStatus; }
}
