package com.hotel.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.hotel.util.DateUtils;

@Entity
public class Order {

	@Id
	private String batchNumber;
	@Column(nullable = false)
	private String supplier;
	@Column(nullable = false)
	private LocalDate deliveryDate;
	
	private LocalDate expirationDate;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private BigDecimal price;
	@Column(nullable = false)
	private int quantity;
	
	public Order(String batchNumber, String supplier, LocalDate deliveryDate, LocalDate expirationDate,
				 String productName, int quantity, BigDecimal price) {
		this.batchNumber = Objects.requireNonNull(batchNumber);
		this.supplier = Objects.requireNonNull(supplier);
		this.deliveryDate = Objects.requireNonNull(deliveryDate);
		this.expirationDate = expirationDate;
		this.productName = Objects.requireNonNull(productName);
		this.quantity = quantity;
		this.price = Objects.requireNonNull(price);
	}
	
	public Order() {}

	public String getBatchNumber() { return batchNumber; }

	public void setBatchNumber(String batchNumber) { this.batchNumber = Objects.requireNonNull(batchNumber); }

	public String getSupplier() { return supplier; }

	public void setSupplier(String supplier) { this.supplier = Objects.requireNonNull(supplier); }

	public LocalDate getDeliveryDate() { return deliveryDate; }

	public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = Objects.requireNonNull(deliveryDate); }

	public LocalDate getExpirationDate() { return expirationDate; }

	public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = Objects.requireNonNull(expirationDate); }

	public String getProductName() { return productName; }

	public void setProductName(String productName) { this.productName = Objects.requireNonNull(productName); }

	public BigDecimal getPrice() { return price; }

	public void setPrice(BigDecimal price) { this.price = Objects.requireNonNull(price); }
	
	public int getQuantity() { return quantity; }
	
	public void setQuantity(int quantity) { this.quantity = quantity; }
	
	@Override
	public String toString() {
		return "ID: " + batchNumber + " PRODUCT: " + productName + " EXPIRATION DATE: " + DateUtils.prettyLocalDate(expirationDate);
	}
}