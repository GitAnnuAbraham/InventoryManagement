package com.inventrory.controller;

import java.io.IOException;
import java.sql.Connection;
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

@WebServlet("/reports")
public class Reports extends HttpServlet {

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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("products", productList);
		req.getRequestDispatcher("product.jsp").forward(req, resp);

	}

}