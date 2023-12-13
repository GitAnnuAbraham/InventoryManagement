package com.inventrory.dto;

public class Sales {
	private Integer saleId;
	private String saleProduct;
	private Integer salePrice;
	private Integer saleQuantity;
	public String getSaleProduct() {
		return saleProduct;
	}
	public void setSaleProduct(String saleProduct) {
		this.saleProduct = saleProduct;
	}
	public Integer getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(Integer saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public Integer getSaleId() {
		return saleId;
	}
	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}
	
}
