package com.capstone.beans;

public class Books {
    private String bookId; // Added attribute for book's ID
    private String title;
    private String author;
    private String genre;
    private String summary;
    private int rating;

	private int stock;
	
	public  Books() { //empty constructor
		
	}
    // Constructor
    public Books(String bookId, String title, String author, String genre, String summary, int stock) {
        this.bookId = bookId; // Initialize book's ID
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.summary = summary;
        this.stock = stock;
        
    }
    public Books(String bookId, String title, String author, String genre, String summary, int stock,int rating) {
        this.bookId = bookId; // Initialize book's ID
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.summary = summary;
        this.stock = stock;
        this.rating  = rating;
    }
    public String getBookId() {
    	return bookId; 
    	}  
    public void setBookId(String bookId) {
    	this.bookId = bookId; 
    	} 
    public String getTitle() { 
    	return title; 
    	}
    public void setTitle(String title) { 
    	this.title = title; 
    	}
    public String getAuthor() { 
    	return author; 
    	}
    public void setAuthor(String author) {
    	this.author = author;
    	}
    public String getGenre() { 
    	return genre; 
    	}
    public void setGenre(String genre) { 
    	this.genre = genre; 
    	}
    public String getSummary() { 
    	return summary; 
    	}
    public void setSummary(String summary) {
    	this.summary = summary;
    	}
    public int getStock() {
    	return stock;
    	}
    public void setStock(int stock) {
    	this.stock = stock; 
    	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}