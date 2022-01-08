package com.hotel.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ConsumazioneId.class)
public class Consumazione {
	
	@Id
	private Long prenotazioneId;
	
	@Id
	private String nomeProdotto;
	
	@ManyToOne
	@JoinColumn(name = "prenotazioneId", insertable = false, updatable = false)
	private Prenotazione prenotazione;
	
	@ManyToOne
	@JoinColumn(name = "nomeProdotto", insertable = false, updatable = false)
	private Prodotto prodotto;
	
	@Column(nullable = false)
	private int quantita;
	
	public Consumazione(Prenotazione prenotazione, Prodotto prodotto, int quantita) {
		this.prenotazioneId = Objects.requireNonNull(prenotazione.getId());
		this.nomeProdotto = Objects.requireNonNull(prodotto.getNome());
		this.prenotazione = Objects.requireNonNull(prenotazione);
		this.prodotto = prodotto;
		this.quantita = quantita;
	}
	
	public Consumazione() {}
	
	public Long getPrenotazioneId() { return prenotazioneId; }

	public void setPrenotazioneId(Long prenotazioneId) { this.prenotazioneId = Objects.requireNonNull(prenotazioneId); }

	public String getNomeProdotto() { return nomeProdotto; }

	public void setNomeProdotto(String nomeProdotto) { this.nomeProdotto = Objects.requireNonNull(nomeProdotto); }

	public Prenotazione getPrenotazione() { return prenotazione; }

	public void setPrenotazione(Prenotazione prenotazione) { this.prenotazione = Objects.requireNonNull(prenotazione); }

	public Prodotto getProdotto() { return prodotto; }

	public void setProdotto(Prodotto prodotto) { this.prodotto = Objects.requireNonNull(prodotto); }

	public int getQuantita() { return quantita; }

	public void setQuantita(int quantita) { this.quantita = quantita; }
}