package com.capstone.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityLog {
    private String userid;
    private String loginStatus;
    private String Activity;
    private String date;
    
    
    
    public ActivityLog(String userid, String loginStatus,String Activity){
    	this.userid = userid;
    	this.loginStatus = loginStatus;
    	this.Activity = Activity;
    	String pattern = "MM-dd-yyyy";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	String date = simpleDateFormat.format(new Date());
    	this.setDate(date);
    	
    }
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String activity) {
		Activity = activity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
