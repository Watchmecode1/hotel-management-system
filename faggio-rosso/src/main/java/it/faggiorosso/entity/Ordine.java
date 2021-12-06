package it.faggiorosso.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import it.faggiorosso.util.DateUtils;

/**
 *
 *
 * @author Matthew Mazzotta
 */
@Entity
public class Ordine {

	@Id
	private String numeroLotto;
	@Column(nullable = false)
	private String fornitore;
	@Column(nullable = false)
	private LocalDate dataConsegna;
	
	private LocalDate dataScadenza;
	@Column(nullable = false)
	private String nomeProdotto;
	@Column(nullable = false)
	private BigDecimal prezzo;
	@Column(nullable = false)
	private int quantita;
	
	public Ordine(String numeroLotto, String fornitore, LocalDate dataConsegna, LocalDate dataScadenza,
			String nomeProdotto, int quantita, BigDecimal prezzo) {
		this.numeroLotto = Objects.requireNonNull(numeroLotto);
		this.fornitore = Objects.requireNonNull(fornitore);
		this.dataConsegna = Objects.requireNonNull(dataConsegna);
		this.dataScadenza = dataScadenza;
		this.nomeProdotto = Objects.requireNonNull(nomeProdotto);
		this.quantita = quantita;
		this.prezzo = Objects.requireNonNull(prezzo);
	}
	
	public Ordine() {}

	public String getNumeroLotto() { return numeroLotto; }

	public void setNumeroLotto(String numeroLotto) { this.numeroLotto = Objects.requireNonNull(numeroLotto); }

	public String getFornitore() { return fornitore; }

	public void setFornitore(String fornitore) { this.fornitore = Objects.requireNonNull(fornitore); }

	public LocalDate getDataConsegna() { return dataConsegna; }

	public void setDataConsegna(LocalDate dataConsegna) { this.dataConsegna = Objects.requireNonNull(dataConsegna); }

	public LocalDate getDataScadenza() { return dataScadenza; }

	public void setDataScadenza(LocalDate dataScadenza) { this.dataScadenza = Objects.requireNonNull(dataScadenza); }

	public String getNomeProdotto() { return nomeProdotto; }

	public void setNomeProdotto(String nomeProdotto) { this.nomeProdotto = Objects.requireNonNull(nomeProdotto); }

	public BigDecimal getPrezzo() { return prezzo; }

	public void setPrezzo(BigDecimal prezzo) { this.prezzo = Objects.requireNonNull(prezzo); }
	
	public int getQuantita() { return quantita; }
	
	public void setQuantita(int quantita) { this.quantita = quantita; }
	
	@Override
	public String toString() {
		return "ID: " + numeroLotto + " PRODOTTO: " + nomeProdotto + " DATA SCADENZA: " + DateUtils.prettyLocalDate(dataScadenza);
	}
}