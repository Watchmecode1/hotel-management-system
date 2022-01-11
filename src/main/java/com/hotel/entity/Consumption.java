package com.hotel.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ConsumptionId.class)
public class Consumption {
	
	@Id
	private Long reservationId;
	
	@Id
	private String productName;
	
	@ManyToOne
	@JoinColumn(name = "reservationId", insertable = false, updatable = false)
	private Reservation reservation;
	
	@ManyToOne
	@JoinColumn(name = "productName", insertable = false, updatable = false)
	private Product product;
	
	@Column(nullable = false)
	private int quantity;
	
	public Consumption(Reservation reservation, Product product, int quantity) {
		this.reservationId = Objects.requireNonNull(reservation.getId());
		this.productName = Objects.requireNonNull(product.getName());
		this.reservation = Objects.requireNonNull(reservation);
		this.product = product;
		this.quantity = quantity;
	}
	
	public Consumption() {}
	
	public Long getReservationId() { return reservationId; }

	public void setReservationId(Long reservationId) { this.reservationId = Objects.requireNonNull(reservationId); }

	public String getProductName() { return productName; }

	public void setProductName(String productName) { this.productName = Objects.requireNonNull(productName); }

	public Reservation getReservation() { return reservation; }

	public void setReservation(Reservation reservation) { this.reservation = Objects.requireNonNull(reservation); }

	public Product getProduct() { return product; }

	public void setProduct(Product product) { this.product = Objects.requireNonNull(product); }

	public int getQuantity() { return quantity; }

	public void setQuantity(int quantity) { this.quantity = quantity; }
}