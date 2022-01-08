package com.hotel.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Cliente {
	
	public static int ETA_BAMBINO = 3;
	public static int ETA_MINORE = 14;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private LocalDate dataDiNascita;
	
	@Column(nullable = false)
	private String statoDiNascita;
	
	private String comuneDiNascita;
	
	private String provinciaDiNascita;
	
	@Column(nullable = false)
	private String cittadinanza;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Eta eta;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Sesso sesso;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Alloggiato alloggiato;
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Documento documento;
	
	@ManyToMany(mappedBy = "clienti")
	private Set<Prenotazione> prenotazioni;
	
	public enum Sesso { 
		MASCHIO(1), FEMMINA(2);
		
		public int number;
		
		Sesso(int number) {
			this.number = number;
		}
		
		public int getNumber() { return number; }
	}
	
	public enum Eta { BAMBINO, MINORE, ADULTO }
	
	public enum Alloggiato {
		
		OSPITE_SINGOLO(16), CAPO_FAMIGLIA(17), CAPO_GRUPPO(18), FAMILIARE(19), MEMBRO_GRUPPO(20);
		
		private int numero;
		
		Alloggiato(int numero) {
			this.numero = numero;
		}
		
		public int getNumero() { return numero; }
	}
	
	public Cliente(String nome, String cognome, Sesso sesso, LocalDate dataDiNascita, String statoDiNascita, String comuneDiNascita,
			String provinciaDiNascita, String cittadinanza, Alloggiato alloggiato) {
		this.nome = Objects.requireNonNull(nome);
		this.cognome = Objects.requireNonNull(cognome);
		this.sesso = sesso;
		this.dataDiNascita = Objects.requireNonNull(dataDiNascita);
		this.statoDiNascita = Objects.requireNonNull(statoDiNascita);
		this.comuneDiNascita = comuneDiNascita;
		this.provinciaDiNascita = provinciaDiNascita;
		this.cittadinanza = Objects.requireNonNull(cittadinanza);
		this.alloggiato = Objects.requireNonNull(alloggiato);
		setEta();
	}
	
	public Cliente() {}
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = Objects.requireNonNull(id); }

	public Set<Prenotazione> getPrenotazioni() { return prenotazioni; }

	public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
		this.prenotazioni = Objects.requireNonNull(prenotazioni);
	}

	public Documento getDocumento() { return documento; }

	public void setDocumento(Documento documento) { this.documento = Objects.requireNonNull(documento); }

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = Objects.requireNonNull(nome); }

	public String getCognome() { return cognome; }

	public void setCognome(String cognome) { this.cognome = Objects.requireNonNull(cognome); }
	
	public Sesso getSesso() { return sesso; }

	public void setSesso(Sesso sesso) { this.sesso = Objects.requireNonNull(sesso); }

	public LocalDate getDataDiNascita() { return dataDiNascita; }

	public void setDataDiNascita(LocalDate dataDiNascita) { this.dataDiNascita = Objects.requireNonNull(dataDiNascita); }
	
	public String getComuneDiNascita() { return comuneDiNascita; }

	public void setComuneDiNascita(String comuneDiNascita) { this.comuneDiNascita = comuneDiNascita; }

	public String getProvinciaDiNascita() { return provinciaDiNascita; }

	public void setProvinciaDiNascita(String provinciaDiNascita) { this.provinciaDiNascita = provinciaDiNascita; }
	
	public Eta getEta() { return eta; }
	
	private void setEta() {
		eta = dataDiNascita.plusYears(ETA_MINORE).isBefore(LocalDate.now()) ? Eta.ADULTO : dataDiNascita.plusYears(ETA_BAMBINO).isBefore(LocalDate.now()) ? Eta.MINORE : Eta.BAMBINO;
	}
	
	public String getStatoDiNascita() { return statoDiNascita; }
	
	public void setStatoDiNascita(String statoDiNascita) { this.statoDiNascita = statoDiNascita; }
	
	public String getCittadinanza() { return cittadinanza; }
	
	public void setCittadinanza(String cittadinanza) { this.cittadinanza = cittadinanza; }
	
	public Alloggiato getAlloggiato() { return alloggiato; }
	
	public void setAlloggiato(Alloggiato alloggiato) { this.alloggiato = alloggiato; }

	@Override
	public String toString() {
		return getCognome() + " " + getNome() + " " + getDataDiNascita().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + eta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cittadinanza, cognome, comuneDiNascita, dataDiNascita, eta, id, nome, provinciaDiNascita,
				sesso, statoDiNascita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cittadinanza, other.cittadinanza) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(comuneDiNascita, other.comuneDiNascita)
				&& Objects.equals(dataDiNascita, other.dataDiNascita) && eta == other.eta
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(provinciaDiNascita, other.provinciaDiNascita) && sesso == other.sesso
				&& Objects.equals(statoDiNascita, other.statoDiNascita);
	}
}