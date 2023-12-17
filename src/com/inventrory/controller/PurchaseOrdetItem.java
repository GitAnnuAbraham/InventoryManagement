package com.inventrory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventrory.dto.Product;

@WebServlet("/purchaseorder")
public class PurchaseOrdetItem extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer supplierId = Integer.parseInt(req.getParameter("supplierId"));
		List<Product> productList = getProductDetails();
		req.setAttribute("products", productList);
		req.setAttribute("supplierId", supplierId);

		List<Product> purchaseList = getProductList();
		req.setAttribute("purchase", purchaseList);
		RequestDispatcher rd = req.getRequestDispatcher("createorder.jsp");
		rd.forward(req, resp);
	}

	public List<Product> getProductList() {
		List<Product> purchaseList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
			PreparedStatement pst = con.prepareStatement(
					"select  orderItem.Quantity, product.ProductName from orderitem left join product on orderitem.Productid = product.ProductID");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setStockQuantity(rs.getInt(1));
				product.setProductName(rs.getString(2));
				purchaseList.add(product);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return purchaseList;
	}

	public List<Product> getProductDetails() {
		List<Product> productList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
			PreparedStatement pst = con.prepareStatement("select ProductID, ProductName from product");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer orderId = 1;
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));
		Integer supplierId = Integer.parseInt(req.getParameter("supplierId"));
		Date orderDate = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(orderDate);
		calender.add(Calendar.DATE, 5);
		java.sql.Date ExpDeliveryDate = new java.sql.Date(calender.getTime().getTime());

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
			PreparedStatement maxOrderIdStatement = con
					.prepareStatement("select coalesce(Max(OrderId),0) + 1 from purchaseorder");
			ResultSet rs = maxOrderIdStatement.executeQuery();
			while (rs.next()) {
				orderId = rs.getInt(1);
			}
			PreparedStatement purchaseOrderStatement = con.prepareStatement(
					"insert into purchaseorder(OrderId,SupplierId, OrderDate, ExpDeliveryDate, Status) values(?, ?, ?, ?, ?)");
			PreparedStatement orderItemStatement = con
					.prepareStatement("insert into orderitem(ProductId, PurchaseOrderId, Quantity) values(?, ?, ?)");
			purchaseOrderStatement.setInt(1, orderId);
			purchaseOrderStatement.setInt(2, supplierId);
			purchaseOrderStatement.setDate(3, new java.sql.Date(orderDate.getTime()));
			purchaseOrderStatement.setDate(4, new java.sql.Date(ExpDeliveryDate.getTime()));
			purchaseOrderStatement.setString(5, "In Progress");
			orderItemStatement.setInt(1, Integer.parseInt(req.getParameter("product")));
			orderItemStatement.setInt(2, orderId);
			orderItemStatement.setInt(3, quantity);
			Integer purchaseOrderRecordsAffected = purchaseOrderStatement.executeUpdate();
			Integer orderItemRecordsAffected = orderItemStatement.executeUpdate();
			if (purchaseOrderRecordsAffected > 0) {
				System.out.println("Data inserted to purchaseorder table successfully.");
			}
			if (orderItemRecordsAffected > 0) {
				System.out.println("Data inserted to orderitem table successfully.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(req, resp);
	}

}
