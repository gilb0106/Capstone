package com.capstone.dao;

import java.util.List;

import com.capstone.beans.Books;

public interface LibraryInterface {
	public  List<Books> booklist();
	public int addBook(Books book);
	public void updateStock(String bookId, int newStock);
	public int reserveBook(int bookId);
	public int returnBook(int bookId);
    int getBookRating(String bookId);
    void updateBookRating(String bookId, int rating);
}

