package com.capstone.proxy;
import java.util.List;
import com.capstone.beans.Books;
import com.capstone.dao.LibraryDao;
import com.capstone.dao.LibraryInterface;

public class libraryProxy implements LibraryInterface{

	private LibraryDao lib;

	public libraryProxy() {
		this.lib = new LibraryDao();
	}
	@Override
	public  List<Books> booklist(){
		return lib.booklist();
	}
	@Override
	public int addBook(Books book) { // updated name  to addbook
		return lib.addBook(book);
	}
	@Override
	public void updateStock(String bookId, int newStock)  {// updated name  to updateStock
		lib.updateStock(bookId, newStock);
	}
	@Override
	public int reserveBook(int bookId) {
		return lib.reserveBook(bookId);
	}
	@Override
	public int returnBook(int bookId) {
		return lib.returnBook(bookId);
	}
	@Override
	public int getBookRating(String bookId) {
		return lib.getBookRating(bookId);
	}
	@Override
	public void updateBookRating(String bookId, int rating) {
		lib.updateBookRating(bookId, rating);
		
	}
}

