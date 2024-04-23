package com.capstone.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.beans.ActivityLog;
import com.capstone.dao.*;
import com.capstone.proxy.userProxy;

@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

  /*  private boolean userExists(String userID, String email, Connection connection) throws SQLException {
        String checkUserQuery = "SELECT COUNT(*) FROM capstone.users WHERE userID = ? OR email = ?";
        try (PreparedStatement ps = connection.prepareStatement(checkUserQuery)) {
            ps.setString(1, userID);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page  = "";
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        
        UserInterface dao = new userProxy(); // proxy
        if(dao.checkUserExists(userID, email)) {
            request.setAttribute("errorMessage", "userID or email already registered");
            request.getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
            return;
        }  
        if(dao.createUser(userID, password, email, fName, lName)) {
            ActivityLog log = new ActivityLog(userID, "1", "UserCreated");
            dao.logUser(log);
        	page = "RegistrationSuccess.jsp";
            response.sendRedirect(page);
        }else {
        page = "RegistrationFailed.jsp";
        response.sendRedirect(page);
        }
    } }
    /*    try (Connection connection = DatabaseUtility.getConnection()) {
            if (userExists(userID, email, connection)) {
                request.setAttribute("errorMessage", "userID or email already registered");
                request.getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
                return;
            }
            
            String insertUserQuery = "INSERT INTO capstone.users (userID, password, email, fName, lName, loginStatus) VALUES (?, ?, ?, ?, ?, '0')";
            try (PreparedStatement ps = connection.prepareStatement(insertUserQuery)) {
                ps.setString(1, userID);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setString(4, fName);
                ps.setString(5, lName);
                
                ps.executeUpdate();
                
                response.sendRedirect("RegistrationSuccess.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error Message: " + e.getMessage());
            response.sendRedirect("RegistrationFailed.jsp");
        } */
