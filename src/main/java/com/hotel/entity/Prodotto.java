package com.hotel.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 *
 * @author Matthew Mazzotta
 */
@Entity
public class Prodotto {

	@Id
	private String nome;
	
	@Column(nullable = false)
	private BigDecimal prezzo;
	
	@OneToMany(mappedBy = "prodotto")
	private Set<Consumazione> consumazioni;
	
//	@OneToOne(mappedBy = "prodotto", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//	private Magazzino magazzino;
	
	public Prodotto(String nome, BigDecimal prezzo) {
		this.nome = Objects.requireNonNull(nome);
		this.prezzo = Objects.requireNonNull(prezzo);
	}
	
	public Prodotto() {}

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = Objects.requireNonNull(nome); }

	public BigDecimal getPrezzo() { return prezzo; }

	public void setPrezzo(BigDecimal prezzo) { this.prezzo = Objects.requireNonNull(prezzo); }

	public Set<Consumazione> getConsumazioni() { return consumazioni; }

	public void setConsumazioni(Set<Consumazione> consumazioni) {
		this.consumazioni = Objects.requireNonNull(consumazioni);
	}

//	public Magazzino getMagazzino() { return magazzino; }
//
//	public void setMagazzino(Magazzino magazzino) { this.magazzino = Objects.requireNonNull(magazzino); }
	
	@Override
	public String toString() {
		return "Prodotto: " + nome + ", prezzo: " + prezzo;
	}
}