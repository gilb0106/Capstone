package com.capstone.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.beans.Books;
import com.capstone.dao.*;
import com.capstone.proxy.libraryProxy;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	LibraryInterface dao = new libraryProxy();
     List<Books> booksList = dao.booklist();
     request.setAttribute("booksList", booksList);
     request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
 }

        
     /*     String query = "SELECT books.*, capstone.library.availability, capstone.library.stock " + 
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
                    rs.getInt("stock")  // Retrieve the stock as an integer from the result set
                ));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error fetching books: " + e.getMessage()); */


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Received action: " + action);

        switch (action) {
            case "add":
                addBook(request, response);
                break;
            case "updateStock":
                updateStock(request, response);
                break;
            default:
                doGet(request, response);
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the form
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        String summary = request.getParameter("summary");
        PrintWriter writer=response.getWriter(); 
        LibraryInterface dao = new libraryProxy(); // using interface with proxy pattern
  	    Books book = new Books();  // create object and set  values for storage
	      book.setTitle(title);
	      book.setAuthor(author);
	      book.setGenre(genre);
	      book.setSummary(summary);
	      int rowCount = dao.addBook(book);
	        if(rowCount>0){  
	        	writer.print("<p>Record saved successfully!</p>"); 
	            request.setAttribute("successMessage", "New book added successfully!");
	            doGet(request, response);      
	        }else{  
	        	writer.println("<p>Sorry! unable to save record</p>");  
	        	request.getRequestDispatcher("/AdminDashboard.jsp").include(request, response);  
	        }       
	        writer.close();  
	    }  

    /*    String sql = "INSERT INTO capstone.books (title, author, genre, summary) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, genre);
            ps.setString(4, summary);

            ps.executeUpdate();
            
            request.setAttribute("successMessage", "New book added successfully!");
            doGet(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding book: " + e.getMessage());
            request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response); 
        }*/
    

    private void updateStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        System.out.println("Book ID: " + bookId);  // Debugging 
        int newStock;   
        try {
            newStock = Integer.parseInt(request.getParameter("stock"));
            System.out.println("New Stock: " + newStock);  // Debugging
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid stock value provided.");
            doGet(request, response);  // Fetch the list of books before forwarding
            return;
        }
        System.out.println("Updating stock for bookId: " + bookId + " to " + newStock);
        LibraryInterface dao = new libraryProxy(); // using interface with proxy pattern
        dao.updateStock(bookId,newStock);
        doGet(request, response);
    }
}
        /*String sql = "UPDATE capstone.library SET stock = ? WHERE bookId = ?";
        
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newStock);  // Use setInt for setting integer values.
            ps.setString(2, bookId);

            System.out.println("Executing update query...");
            ps.executeUpdate();
            System.out.println("Update query executed successfully.");
            doGet(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating stock: " + e.getMessage());
            doGet(request, response);  // Fetch the list of books before forwarding */
