package com.capstone.beans;

public class Books {
    private String bookId; // Added attribute for book's ID
    private String title;
    private String author;
    private String genre;
    private String summary;

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

    // Getters and Setters
    public String getBookId() { return bookId; }  // Getter for book's ID
    public void setBookId(String bookId) { this.bookId = bookId; } // Setter for book's ID

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    
    public int getStock() {return stock; }
    public void setStock(int stock) {this.stock = stock; }
}