package com.inventrory.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectoer {

	// JDBC URL, username, and password of MySQL server
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/inventory";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	// JDBC variables for opening, closing, and managing a connection
	private static Connection connection;

	// Method to establish a database connection
	public static Connection connect() {
		try {
			// Register the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Open a connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			if (connection != null) {
				System.out.println("Connected to the database!");
			} else {
				System.out.println("Failed to connect to the database.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Method to close the database connection
	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("Connection closed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Example: Connect to the database
		connect();

		// Example: Close the database connection
		closeConnection();
	}
}
