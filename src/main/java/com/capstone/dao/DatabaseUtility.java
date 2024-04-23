package com.capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/capstone";
    private static final String USER = "root";
    private static final String PASS = "";
    private static DatabaseUtility instance;
    // Private constructor to prevent instantiation
    private DatabaseUtility() {
    }
    public static synchronized DatabaseUtility getInstance() {
        if (instance == null) {
            instance = new DatabaseUtility();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    public boolean isConnectionSuccessful() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}