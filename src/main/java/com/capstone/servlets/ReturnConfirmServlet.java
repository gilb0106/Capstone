package com.capstone.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.dao.LibraryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ReturnConfirmServlet")
public class ReturnConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        LibraryDao dao  = new LibraryDao();
        dao.returnBook(bookId);
        
        // Redirect back to the library page
        request.setAttribute("successMessage", "Book returned successfully!");
        response.sendRedirect("BooksServlet");
        
  /*      Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            // Get the connection from DatabaseUtility
            connection = DatabaseUtility.getConnection();
            
            // Increase stock by 1 for returned book
            preparedStatement = connection.prepareStatement("UPDATE library SET stock = stock + 1 WHERE bookID = ?");
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
            
            // Update availability based on the new stock value
            preparedStatement = connection.prepareStatement("UPDATE library SET availability = CASE WHEN stock > 0 THEN 1 ELSE 0 END WHERE bookID = ?");
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();

            request.setAttribute("successMessage", "Book returned successfully!");

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
        response.sendRedirect("BooksServlet");
    } */
}}