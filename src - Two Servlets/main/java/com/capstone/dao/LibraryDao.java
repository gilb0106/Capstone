package com.capstone.dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capstone.beans.Books;

public class LibraryDao implements LibraryInterface{
	@Override
	public List<Books> booklist(){
		List<Books> booksList = new ArrayList<>();
		String query = "SELECT * FROM books";
		try (Connection conn = DatabaseUtility.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				booksList.add(new Books(
						rs.getString("bookId"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("genre"),
						rs.getString("summary"),
						rs.getInt("stock"),  // Retrieve the stock as an integer from the result set
						rs.getInt("rating")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booksList;
	}
	@Override
	public int addBook(Books book) { // first successful mysql tried to use object array but  this worked so i left it	
		String sql = "INSERT INTO capstone.books (title, author, genre, summary) VALUES (?, ?, ?, ?)";
		int rowCount=0; //iterator through rows from id to time, used Int since i dont need filter
		try (Connection conn = DatabaseUtility.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getGenre());
			ps.setString(4, book.getSummary());
			rowCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}return rowCount;
	}
	@Override
	public void updateStock(String bookId, int newStock)  {
		String sql = "UPDATE capstone.books SET stock = ? WHERE bookId = ?";
		try (Connection conn = DatabaseUtility.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, newStock);  // Use setInt for setting integer values.
			ps.setString(2, bookId);
			System.out.println("Executing update query...");
			ps.executeUpdate();
			System.out.println("Update query executed successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}	// TODO Auto-generated method stub	
	}
	@Override
	public int reserveBook(int bookId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection conn = DatabaseUtility.getInstance().getConnection();
			preparedStatement = conn.prepareStatement("SELECT stock FROM books WHERE bookID = ?");
			preparedStatement.setInt(1, bookId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int stock = resultSet.getInt("stock");
				if (stock > 0) {
					// Decrease stock by 1
					preparedStatement = conn.prepareStatement("UPDATE books SET stock = stock - 1 WHERE bookID = ?");
					preparedStatement.setInt(1, bookId);
					preparedStatement.executeUpdate();
				} }}catch (Exception e) {
					e.printStackTrace();
				}
		return 0;
	}
	@Override
	public int returnBook(int bookId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {	
			Connection conn = DatabaseUtility.getInstance().getConnection();
			preparedStatement = conn.prepareStatement("UPDATE books SET stock = stock + 1 WHERE bookID = ?");
			preparedStatement.setInt(1, bookId);
			preparedStatement.executeUpdate();
		}catch (Exception e) {
		}
		return bookId;
	}
	@Override
	public int getBookRating(String bookId) {
	    try (Connection conn = DatabaseUtility.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement("SELECT rating FROM books WHERE bookId = ?")) {
	        ps.setString(1, bookId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("rating");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0; // Default rating if not found
	}
	@Override
	public void updateBookRating(String bookId, int rating) {
	    try (Connection conn = DatabaseUtility.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement("UPDATE books SET rating = ? WHERE bookId = ?")) {
	        ps.setInt(1, rating);
	        ps.setString(2, bookId);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}}

