package it.faggiorosso.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import it.faggiorosso.entity.Cliente.Eta;
import it.faggiorosso.exception.GuiInputFieldValueException;
import it.faggiorosso.service.CameraService;

@Entity
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate dataInizio;
	@Column(nullable = false)
	private LocalDate dataFine;
	@Column(nullable = false)
	private int numeroAnimali;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Pagato pagato;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Pensione pensione;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Fonte fonte;
	@Column(nullable = false)
	private String cognome;
	@Column(nullable = false)
	private String numeroDiTelefono;
	@Column(nullable = false)
	private String email;
	
	private BigDecimal deposito;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Cliente> clienti;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Camera> camere;
	
	@OneToMany(fetch = FetchType.EAGER , mappedBy = "prenotazione")
	private Set<Consumazione> consumazioni;
	
	public enum Pagato { NON_PAGATO, DEPOSITO, PAGATO }
	
	public enum Pensione { MEZZA_PENSIONE, PENSIONE_COMPLETA, BNB }
	
	public enum Fonte { BOOKING, HOTEL }
	
	public Prenotazione(String cognome, String email, String numeroDiTelefono, LocalDate dataInizio, LocalDate dataFine, 
			int numeroAnimali, Pagato pagato, BigDecimal deposito,
			Pensione pensione, Fonte fonte, Set<Cliente> clienti, Set<Camera> camere) {
		this.cognome = Objects.requireNonNull(cognome);
		this.email = Objects.requireNonNull(email);
		this.numeroDiTelefono = Objects.requireNonNull(numeroDiTelefono);
		this.dataInizio = Objects.requireNonNull(dataInizio);
		this.dataFine = Objects.requireNonNull(dataFine);
		this.numeroAnimali = Objects.requireNonNull(numeroAnimali);
		this.pagato = Objects.requireNonNull(pagato);
		this.deposito = Objects.requireNonNull(deposito);
		this.pensione = Objects.requireNonNull(pensione);
		this.fonte = Objects.requireNonNull(fonte);
		this.clienti = Objects.requireNonNull(clienti);
		setCamere(camere);
	}

	public Prenotazione() {}
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public LocalDate getDataInizio() { return dataInizio; }

	public void setDataInizio(LocalDate dataInizio) { this.dataInizio = Objects.requireNonNull(dataInizio); }

	public LocalDate getDataFine() { return dataFine; }

	public void setDataFine(LocalDate dataFine) { this.dataFine = Objects.requireNonNull(dataFine); }

	public int getNumeroAnimali() { return numeroAnimali; }

	public void setNumeroAnimali(int numeroAnimali) throws IllegalArgumentException {
		if(numeroAnimali < 0) throw new IllegalArgumentException("Il numero di animali deve essere 0 o più");
		this.numeroAnimali = numeroAnimali;
	}

	public Pagato getPagato() { return pagato; }

	public void setPagato(Pagato pagato) { this.pagato = Objects.requireNonNull(pagato); }

	public Pensione getPensione() { return pensione; }

	public void setPensione(Pensione pensione) { this.pensione = Objects.requireNonNull(pensione); }

	public Set<Cliente> getClienti() { return clienti; }

	public void setClienti(Set<Cliente> clienti) { this.clienti = Objects.requireNonNull(clienti); }

	public Set<Camera> getCamere() { return camere; }

	public void setCamere(Set<Camera> camere) {
		Set<Camera> availableRooms = new CameraService().findAvailableRooms(dataInizio, dataFine);
		//TODO gestire in qualche modo la modifica di una prenotazione sulle stesse camere e altri giorni
		if(this.camere != null)
			availableRooms.addAll(this.camere);
		if(camere == null || !availableRooms.containsAll(camere)) 
			throw new GuiInputFieldValueException("SELEZIONA DELLE CAMERE PRENOTABILI PER LE DATE SELEZIONATE");
		this.camere = Objects.requireNonNull(camere);
	}

	public Set<Consumazione> getConsumazioni() { return consumazioni; }

	public void setConsumazioni(Set<Consumazione> consumazioni) {
		this.consumazioni = Objects.requireNonNull(consumazioni);
	}
	
	public Fonte getFonte() { return fonte; }
	
	public void setFonte(Fonte fonte) { this.fonte = fonte; }
	
	public void addConsumazione(Consumazione consumazione) { consumazioni.add(consumazione); }
	
	public void addCliente(Cliente cliente) { clienti.add(cliente); }
	
	public String getCognome() { return cognome; }
	
	public void setCognome(String cognome) { this.cognome = cognome; }
	
	@Override
	public String toString() {
		return cognome + " - Da: " + dataInizio.toString() + " A: " + dataFine.toString();
	}
	
	public int getNumeroDiAdulti() {
		return (int) clienti.stream().filter(c -> c.getEta() == Eta.ADULTO).count();
	}
	
	public int getNumeroDiMinori() {
		return (int) clienti.stream().filter(c -> c.getEta() == Eta.MINORE).count();
	}
	
	public int getNumeroDiBambini() {
		return (int) clienti.stream().filter(c -> c.getEta() == Eta.BAMBINO).count();
	}
	
	public String getNumeroDiTelefono() { return numeroDiTelefono; }
	
	public void setNumeroDiTelefono(String numeroDiTelefono) { this.numeroDiTelefono = numeroDiTelefono; }
	
	public String getEmail() { return email; }
	
	public void setEmail(String email) { this.email = email; }
	
	public BigDecimal getCostoTotale() {
		int giorniTotali = (int) ChronoUnit.DAYS.between(getDataInizio(), getDataFine());
		BigDecimal prezzoCamere = BigDecimal.valueOf(camere.stream().mapToInt(camera -> camera.getTipoCamera().getPrezzo().intValue() * giorniTotali).sum());
		BigDecimal prezzoConsumazioni = BigDecimal.ZERO;
		if(consumazioni != null)
			prezzoConsumazioni = BigDecimal.valueOf(consumazioni.stream().mapToDouble(consumazione -> consumazione.getQuantita() * consumazione.getProdotto().getPrezzo().doubleValue()).sum());
		BigDecimal prezzoTotale = prezzoCamere.add(prezzoConsumazioni);
		return prezzoTotale;
	}
	
	public BigDecimal getDeposito() { return deposito; }
	
	public void setDeposito(BigDecimal deposito) { this.deposito = deposito; }
	
	public String getPdf() {
		StringBuilder sb = new StringBuilder();
		sb.append("PRENOTAZIONE ID: ").append(id).append("\n")
		.append("FONTE: ").append(fonte).append("\n")
		.append("TIPO PENSIONE: ").append(pensione).append("\n")
		.append("NOMINATIVO: ").append(cognome.toUpperCase()).append("\n")
		.append("EMAIL: ").append(email).append("\n")
		.append("TELEFONO: ").append(numeroDiTelefono).append("\n")
		.append("DATA ARRIVO: ").append(dataInizio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n")
		.append("DATA PARTENZA: ").append(dataFine.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n")
		.append("NUMERO PERSONE: ").append(clienti.size()).append("\n")
		.append("ADULTI: ").append(getNumeroDiAdulti()).append("\n")
		.append("MINORI: ").append(getNumeroDiMinori()).append("\n")
		.append("BAMBINI: ").append(getNumeroDiBambini()).append("\n")
		.append("NUMERO ANIMALI: ").append(getNumeroAnimali()).append("\n")
		.append("CAMERE OCCUPATE: ");
		
		for (Camera camera : getCamere())
			sb.append(camera.getNumero()).append(" ");
		
		sb.append("\n")
		.append("TOTALE SOGGIORNO: €. " + getCostoTotale()).append("\n");
		
		switch(getPagato()) {
			case PAGATO 	-> sb.append("PAGATO INTERAMENTE");
			case NON_PAGATO -> sb.append("PAGAMENTO DA EFFETTUARE IN STRUTTURA");
			case DEPOSITO 	-> sb.append("DEPOSITO VERSATO: " + deposito.doubleValue());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataFine, dataInizio, email, id, numeroDiTelefono, pensione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Prenotazione))
			return false;
		Prenotazione other = (Prenotazione) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataFine, other.dataFine)
				&& Objects.equals(dataInizio, other.dataInizio) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(numeroDiTelefono, other.numeroDiTelefono)
				&& pensione == other.pensione;
	}
}