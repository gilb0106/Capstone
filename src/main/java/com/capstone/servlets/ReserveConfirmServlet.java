package com.capstone.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.dao.*;
import com.capstone.proxy.libraryProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ReserveConfirmServlet")
public class ReserveConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        
        
        LibraryInterface  dao = new libraryProxy();
        dao.reserveBook(bookId);
        
        // Redirect back to the library page
        response.sendRedirect("BooksServlet");
        
    /*    Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            // Get the connection from DatabaseUtility
            connection = DatabaseUtility.getConnection();

            // Check stock first
            preparedStatement = connection.prepareStatement("SELECT stock FROM library WHERE bookID = ?");
            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int stock = resultSet.getInt("stock");
                
                if (stock > 0) {
                    // Decrease stock by 1
                    preparedStatement = connection.prepareStatement("UPDATE library SET stock = stock - 1 WHERE bookID = ?");
                    preparedStatement.setInt(1, bookId);
                    preparedStatement.executeUpdate();
                    
                    // Set availability
                    preparedStatement = connection.prepareStatement("UPDATE library SET availability = CASE WHEN stock > 0 THEN 1 ELSE 0 END WHERE bookID = ?");
                    preparedStatement.setInt(1, bookId);
                    preparedStatement.executeUpdate();

                    request.setAttribute("successMessage", "Book reserved successfully!");
                } else {
                    request.setAttribute("errorMessage", "Sorry, this book is out of stock!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again later.");
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      // Redirect back to the library page
        response.sendRedirect("BooksServlet");*/  

    }
}