package com.hotel.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	private String name;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@OneToMany(mappedBy = "product")
	private Set<Consumption> consumptions;
	
//	@OneToOne(mappedBy = "prodotto", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//	private Magazzino magazzino;
	
	public Product(String name, BigDecimal price) {
		this.name = Objects.requireNonNull(name);
		this.price = Objects.requireNonNull(price);
	}
	
	public Product() {}

	public String getName() { return name; }

	public void setName(String name) { this.name = Objects.requireNonNull(name); }

	public BigDecimal getPrice() { return price; }

	public void setPrice(BigDecimal price) { this.price = Objects.requireNonNull(price); }

	public Set<Consumption> getConsumptions() { return consumptions; }

	public void setConsumptions(Set<Consumption> consumptions) {
		this.consumptions = Objects.requireNonNull(consumptions);
	}

//	public Magazzino getMagazzino() { return magazzino; }
//
//	public void setMagazzino(Magazzino magazzino) { this.magazzino = Objects.requireNonNull(magazzino); }
	
	@Override
	public String toString() {
		return "Product: " + name + ", price: " + price;
	}
}