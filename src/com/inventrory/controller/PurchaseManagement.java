package com.inventrory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventrory.dto.Purchase;

@WebServlet("/orders")
public class PurchaseManagement extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Purchase> purchaseList = new ArrayList();
		DBConnectoer connector = new DBConnectoer();
		Connection con = connector.connect();
		try {
			PreparedStatement pst = con.prepareStatement(
					"select OrderId, SupplierId, OrderDate, ExpDeliveryDate,Status from purchaseorder");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Purchase purchase = new Purchase();
				purchase.setOrderId(rs.getInt(1));
				purchase.setSupplierId(rs.getInt(2));
				purchase.setOrderDate(rs.getDate(3));
				purchase.setExpDeliveryDate(rs.getDate(4));
				purchase.setStatus(rs.getString(5));
				purchaseList.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("purchases", purchaseList);
		Map<Integer, String> supplierMap = getSupplierMap();
		req.setAttribute("supplierMap", supplierMap);
		req.getRequestDispatcher("purchaseorder.jsp").forward(req, resp);
	}

	public Map<Integer, String> getSupplierMap() {
		Map<Integer, String> supplierMap = new HashMap<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
			PreparedStatement pst = con.prepareStatement("select SupplierID, SupplierName from supplier");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				supplierMap.put(rs.getInt(1), rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierMap;
	}
}
