package com.inventrory.dto;

public class Supplier {
	Integer SupplierId;
	String SupplierName;
	String Place;
	public Supplier() {
	
	}
	public Integer getSupplierId() {
		return SupplierId;
	}
	public void setSupplierId(Integer supplierId) {
		SupplierId = supplierId;
	}
	public String getSupplierName() {
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}
	public String getPlace() {
		return Place;
	}
	public void setPlace(String place) {
		Place = place;
	}
}
