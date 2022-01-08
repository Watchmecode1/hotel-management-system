package com.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 *
 * @author Matthew Mazzotta
 */
@Entity
public class Magazzino {

	@Id
	private String nomeProdotto;
	private int quantita;
	
//	@OneToOne
//    @MapsId
//    @JoinColumn(name = "nomeProdotto")
	private Prodotto prodotto;
	
	public Magazzino(Prodotto prodotto, int quantita) {
		this.prodotto = prodotto;
		this.quantita = quantita;
	}
	
	public Magazzino() {}

	public String getNomeProdotto() { return nomeProdotto; }

	public void setNomeProdotto(String nomeProdotto) { this.nomeProdotto = nomeProdotto; }

	public int getQuantita() { return quantita; }

	public void setQuantita(int quantita) { this.quantita = quantita; }

	public Prodotto getProdotto() { return prodotto; }

	public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }
}