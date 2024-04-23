package com.capstone.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capstone.LoginSingleton.Login;
import com.capstone.beans.ActivityLog;
import com.capstone.dao.*;
import com.capstone.proxy.userProxy;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        Login login =  Login.getInstance();  // calling singleton class
        boolean isAuthenticated = false;
        UserInterface user  =  new  userProxy();
        boolean isAdmin = false;
        String dbMessage = "";
       
        if (login.login(userID, password)) { // using method from singleton
                isAuthenticated = true;
            } else {
                dbMessage = "Invalid userID or password.";
            }
        if(isAuthenticated) {
            ActivityLog log = new ActivityLog(userID, "1", "login");
            user.logUser(log);
        	if(login.checkUserAdmin(userID)) {// using method from singleton
      		  isAdmin = true;	
      	}
      }	
        if(isAuthenticated) {
            if(isAdmin) {
                response.sendRedirect("AdminDashboardServlet");
            } else {
                response.sendRedirect("BooksServlet");
            }
        } else {
            request.setAttribute("dbMessage", dbMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        }
        /*try {
            Connection conn = DatabaseUtility.getConnection();
            
            // Query to check if user exists with the given credentials.
            String sql = "SELECT COUNT(*) FROM users WHERE userID = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                if(count == 1) {
                    isAuthenticated = true;
                } else {
                    dbMessage = "Invalid userID or password.";
                }
            }
            
            if(isAuthenticated) {
                // Check if user is an admin
                String adminCheckSQL = "SELECT COUNT(*) FROM capstone.admin WHERE userID = ?";
                PreparedStatement adminPstmt = conn.prepareStatement(adminCheckSQL);
                adminPstmt.setString(1, userID);
                ResultSet adminRS = adminPstmt.executeQuery();
                
                if (adminRS.next()) {
                    int count = adminRS.getInt(1);
                    if(count == 1) {
                        isAdmin = true;
                    }
                }
                
                adminRS.close();
                adminPstmt.close();
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            dbMessage = "Error: " + e.getMessage();
        }
        
        if(isAuthenticated) {
            if(isAdmin) {
                response.sendRedirect("AdminDashboardServlet");
            } else {
                response.sendRedirect("BooksServlet");
            }
        } else {
            request.setAttribute("dbMessage", dbMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }*/
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    /*    try {
            Connection conn = DatabaseUtility.getConnection();
            if (conn != null) {
                request.setAttribute("dbMessage", "Database connection is successful.");
            } else {
                request.setAttribute("dbMessage", "Failed to connect to the database.");
            }
        } catch (Exception e) {
            request.setAttribute("dbMessage", "Error: " + e.getMessage());
        }
        
        // forward to the login.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
} */}}