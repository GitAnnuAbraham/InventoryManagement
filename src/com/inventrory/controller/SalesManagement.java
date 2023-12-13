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
import com.inventrory.dto.Sales;

@WebServlet("/sales")
public class SalesManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Sales> saleList = new ArrayList();
		DBConnectoer connector = new DBConnectoer();
		Connection con = connector.connect();
		try {
			PreparedStatement pst = con
					.prepareStatement("select saleId, saleProduct, salePrice, saleQuantity from sales");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Sales sales = new Sales();
				sales.setSaleId(rs.getInt(1));
				sales.setSaleProduct(rs.getString(2));
				sales.setSalePrice(rs.getInt(3));
				sales.setSaleQuantity(rs.getInt(4));
				saleList.add(sales);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("sales", saleList);
		req.getRequestDispatcher("sales.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("add".equals(action)) {
			String saleProduct = req.getParameter("saleProduct");
			Integer salePrice = Integer.parseInt(req.getParameter("salePrice"));
			Integer saleQuantity = Integer.parseInt(req.getParameter("saleQuantity"));
			Integer saleId = Integer.parseInt(req.getParameter("saleId"));

			Sales sales = new Sales();
			sales.setSaleProduct(saleProduct);
			sales.setSalePrice(salePrice);
			sales.setSaleQuantity(saleQuantity);
			sales.setSaleId(saleId);
			DBConnectoer connector = new DBConnectoer();
			Connection con = connector.connect();
			try {
				PreparedStatement pst = con.prepareStatement(
						"insert into sales(saleId, saleProduct, salePrice, saleQuantity) values(?, ?, ?, ?)");

				pst.setInt(1, sales.getSaleId());
				pst.setString(2, sales.getSaleProduct());
				pst.setInt(3, sales.getSalePrice());
				pst.setInt(4, sales.getSaleQuantity());
				pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			connector.closeConnection();
		}

		

	}

}
