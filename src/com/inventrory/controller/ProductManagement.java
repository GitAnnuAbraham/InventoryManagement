package com.inventrory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventrory.dto.Product;

@WebServlet("/products")
public class ProductManagement extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> productList = new ArrayList<>();
		DBConnectoer connector = new DBConnectoer();
		Connection con = connector.connect();
		try {
			PreparedStatement pst = con
					.prepareStatement("select ProductName, Description, Category, Price, StockQuantity from product");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductName(rs.getString(1));
				product.setDescription(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getFloat(4));
				product.setStockQuantity(rs.getInt(5));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("products", productList);
		req.getRequestDispatcher("product.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Handle product operations (add, edit, delete) based on request
		// parameters
		String action = req.getParameter("action");

		if ("add".equals(action)) {
			// Perform add operation
			String productName = req.getParameter("productName");
			String description = req.getParameter("Description");
			String category = req.getParameter("Category");
			Double price = Double.parseDouble(req.getParameter("Price"));
			Integer stockQuantity = Integer.parseInt(req.getParameter("stockQuantity"));
			// Get other product details similarly
			// Create a Product object and add it to the list

			DBConnectoer connector = new DBConnectoer();
			Connection con = connector.connect();
			try {
				PreparedStatement pst = con.prepareStatement(
						"insert into product(ProductName, Description, Category, Price, StockQuantity) values(?, ?, ?, ?, ?)");
				pst.setString(1, productName);
				pst.setString(2, description);
				pst.setString(3, category);
				pst.setDouble(4, price);
				pst.setInt(5, stockQuantity);
				pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("edit".equals(action)) {
			// Perform edit operation
			// Get product ID and other details from request parameters
			// Update the corresponding Product in the list
		} else if ("delete".equals(action)) {
			// Perform delete operation
			// Get product ID from request parameters and remove the
			// corresponding Product from the list
		}
		// Redirect to the doGet method to refresh the product list on the page
		resp.sendRedirect("products");
	}

}
