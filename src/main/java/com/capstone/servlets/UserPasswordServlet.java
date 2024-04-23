package com.capstone.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.beans.User;
import com.capstone.dao.UserDao;
import com.capstone.proxy.userProxy;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserPasswordServlet")
public class UserPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userID = request.getParameter("userID");
        System.out.println("UserPasswordServlet Called");
     
        userProxy dao = new userProxy();
        List<User> list = dao.searchUser(userID); // loading values into list from SQL
       final String  userID2 = userID; //final for second update servlet
       for(User user: list) {
    	   request.setAttribute("user", user.getUserID());
    	   request.setAttribute("pass", user.getPassword());
    	   request.getRequestDispatcher("change_password.jsp").forward(request, response);
       }
	}

}