package com.capstone.beans;
import java.util.List;

public class Library {
    private List<Books> BOOKS;
    private boolean availability;
    private int stock;

    // Constructor
    public Library(List<Books> BOOKS) {
        this.BOOKS = BOOKS;
    }

    // Methods
    public void getBookStock() {
        // Logic to get stock of a book
    }

    public void setBookAvailability() {
        // Logic to set availability of a book
    }

    public void checkBookAvailability() {
        // Logic to check if a book is available or not
    }

    public void sendNotification() {
        // Logic to send notification
    }

    // Getters and Setters
    public List<Books> getBOOKS() {
        return BOOKS;
    }

    public void setBOOKS(List<Books> BOOKS) {
        this.BOOKS = BOOKS;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}