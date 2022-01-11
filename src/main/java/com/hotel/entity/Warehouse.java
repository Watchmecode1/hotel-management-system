package com.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Warehouse {

	@Id
	private String productName;
	private int quantity;
	
//	@OneToOne
//    @MapsId
//    @JoinColumn(name = "nomeProdotto")
	private Product product;
	
	public Warehouse(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Warehouse() {}

	public String getProductName() { return productName; }

	public void setProductName(String productName) { this.productName = productName; }

	public int getQuantity() { return quantity; }

	public void setQuantity(int quantity) { this.quantity = quantity; }

	public Product getProduct() { return product; }

	public void setProduct(Product product) { this.product = product; }
}