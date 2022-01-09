package com.hotel.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ConsumptionId implements Serializable {

	@Serial
	private static final long serialVersionUID = 4188650510728064111L;

	private Long reservationId;
	
	private String productName;
	
	public ConsumptionId(Long reservationId, String productName) {
		this.reservationId = Objects.requireNonNull(reservationId);
		this.productName = Objects.requireNonNull(productName);
	}
	
	public ConsumptionId() {}

	public Long getReservationId() { return reservationId; }

	public void setReservationId(Long reservationId) { this.reservationId = Objects.requireNonNull(reservationId); }

	public String getProductName() { return productName; }

	public void setProductName(String productName) { this.productName = Objects.requireNonNull(productName); }

	@Override
	public int hashCode() {
		int result = Long.hashCode(reservationId);
		result = 31 * result + productName.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof ConsumptionId consumptionId)) return false;
		return consumptionId.getProductName().equals(productName) &&
				consumptionId.getReservationId().equals(reservationId);
	}
}