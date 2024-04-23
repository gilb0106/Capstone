package com.capstone.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.beans.Books;
import com.capstone.dao.*;
import com.capstone.proxy.*;

@WebServlet("/BooksServlet")
public class BooksServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	LibraryInterface dao = new libraryProxy(); // using interface with proxy pattern
        System.out.println("BooksServlet Called");
        
        List<Books> booksList = dao.booklist();
        request.setAttribute("buk", booksList);
        request.getRequestDispatcher("/library.jsp").forward(request, response);
    }
}
      /*
        String query = "SELECT books.*, capstone.library.availability, capstone.library.stock " + 
                "FROM books LEFT JOIN capstone.library ON books.bookId = capstone.library.bookId";

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

        	while(rs.next()) {
        	    booksList.add(new Books(
        	        rs.getString("bookId"),
        	        rs.getString("title"),
        	        rs.getString("author"),
        	        rs.getString("genre"),
        	        rs.getString("summary"),
        	        rs.getString("availability"),
        	        rs.getInt("stock")  // Use the integer stock value here
        	    ));
        	}
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error fetching books: " + e.getMessage());
        }*/
