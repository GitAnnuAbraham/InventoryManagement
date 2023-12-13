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
import com.inventrory.dto.Supplier;

@WebServlet("/supplier")
public class SupplierManagement extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Supplier> supplierList = new ArrayList<>(); 
		DBConnectoer connector = new DBConnectoer();
		Connection con = connector.connect();
		try {
			PreparedStatement pst = con.prepareStatement("select supplierId, supplierName, place from supplier");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getInt(1));
				supplier.setSupplierName(rs.getString(2));
				supplier.setPlace(rs.getString(3));
				supplierList.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("supplier", supplierList);
		req.getRequestDispatcher("supplier.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("add".equals(action)) {

		Integer supplierId = Integer.parseInt(req.getParameter("supplierId"));
		String supplierName = req.getParameter("supplierName");
		String place = req.getParameter("place");

		Supplier supplier = new Supplier();
		supplier.setSupplierId(supplierId);
		supplier.setSupplierName(supplierName);
		supplier.setPlace(place);
		DBConnectoer connector = new DBConnectoer();
		Connection con = connector.connect();
		try {
			PreparedStatement pst = con.prepareStatement(
					"insert into supplier(SupplierID, SupplierName, Place) values(?, ?, ?)");

			pst.setInt(1, supplier.getSupplierId());
			pst.setString(2, supplier.getSupplierName());
			pst.setString(3,supplier.getPlace());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		connector.closeConnection();
		}

	}
}
