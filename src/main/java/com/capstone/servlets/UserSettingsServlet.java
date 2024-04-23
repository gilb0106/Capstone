package com.capstone.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.LoginSingleton.Login;
import com.capstone.beans.ActivityLog;
import com.capstone.beans.User;
import com.capstone.dao.*;
import com.capstone.proxy.userProxy;

/**
 * Servlet implementation class UserSettingsServlet
 */
@WebServlet("/UserSettingsServlet")
public class UserSettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserSettingsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		boolean isAdmin  = false;
		boolean isAuthenticated = false;
		String dbMessage = "";
		UserInterface user = new userProxy();// using interface with proxy pattern
		Login login =  Login.getInstance();  // calling singleton class
		if (login.login(userID, password)) {
			isAuthenticated = true;
		} else {
			dbMessage = "Invalid userID or password.";
		}
        if(isAuthenticated) {
            ActivityLog log = new ActivityLog(userID, "1", "login");
            user.logUser(log);
        	if(login.checkUserAdmin(userID)) {
      		  isAdmin = true;	
      	}
      }	
        if(isAuthenticated) {
            if(isAdmin) {
				List<User> users = user.showAll();
				request.setAttribute("userInfo", users);
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			} else {
				List<User> users = user.searchUser(userID);
				request.setAttribute("userInfo", users);
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			}
        } else {
            request.setAttribute("dbMessage", dbMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard_login.jsp");
            dispatcher.forward(request, response);
        }
        }}
			
		
		
	

				/*         PrintWriter writer = response.getWriter(); // make writer object to deliver html code
                UserDao dao = new UserDao();
                List<User> list= dao.searchUser(userID); //show all easy to use cause staticc 
                request.setAttribute("user", list);
                writer.print("<html><body><table border='1' width='100%'");  
                writer.print("<tr><th>UserID</th><th>Password</th><th>Email</th><th>First Name</th>"  
                        +"<th>Last Name</th><th>Edit</th></tr>");  
                for(User user:list){  // doesnt sort
                	writer.print("<tr><td>"+ user.getUserID()+"</td><td>"+user.getPassword()+"</td><td>"+user.getEmail()+"</td> " 
                      +   "<td>"+user.getFName()+"</td><td>"+user.getLName()+"</td>"
                      		+ "<td><a href='editBookServlet?title="+user.getUserID()+"'>edit</a></td>"  
                         +"/tr></body></html>");  
                }  
                writer.print("</table>");  

				 */
			


		
	



