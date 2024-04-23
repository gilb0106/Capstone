package com.capstone.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.dao.*;
import com.capstone.proxy.userProxy;
import com.capstone.beans.*;

/**
 * Servlet implementation class UserPasswordServlet2
 */
@WebServlet("/UserPasswordServlet2")
public class UserPasswordServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPasswordServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String page =  "";
		String userID=request.getParameter("userID");
		String password=request.getParameter("password");
        System.out.println(userID+password);  
		UserInterface dao = new userProxy();;
		User user = new User();
		user.setPassword(password);
		dao.updatePassword(userID,password);
		ActivityLog log = new ActivityLog(userID, "1", "PasswordChanged");
		dao.logUser(log);
    	page = "PasswordChanged.jsp";
        response.sendRedirect(page);
	}

}
