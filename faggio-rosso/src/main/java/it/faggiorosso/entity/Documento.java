package it.faggiorosso.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Documento {
	
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String numero;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipoDocumento;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Rilascio rilascio;
	
	@Column(nullable = false)
	private LocalDate dataDiRilascio;
	
	@Column(nullable = false)
	private LocalDate dataDiScadenza;
	
	@Column(nullable = false)
	private String luogoDiRilascio;
	
	private String provinciaDiRilascio;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Cliente cliente;
	
	public enum TipoDocumento { 
		CARTA_IDENTITA_ELETTRONICA("IDELE"), CARTA_IDENTITA("IDENT"), PASSAPORTO("PASDI"), PATENTE_DI_GUIDA("PATEN");
		
		public String codice;
		
		TipoDocumento(String codice) {
			this.codice = codice;
		}
		
		public String getCodice() { return codice; }
	}
	
	public enum Rilascio { QUESTURA, PREFETTURA, MIT_UCO }

	public Documento(Cliente cliente, String numero, TipoDocumento tipoDocumento, Rilascio rilascio, LocalDate dataDiRilascio, LocalDate dataDiScadenza, String luogoDiRilascio, String provinciaDiRilascio) {
		this.cliente = Objects.requireNonNull(cliente);
		this.numero = Objects.requireNonNull(numero);
		this.tipoDocumento = Objects.requireNonNull(tipoDocumento);
		this.rilascio = Objects.requireNonNull(rilascio);
		this.dataDiRilascio = Objects.requireNonNull(dataDiRilascio);
		this.dataDiScadenza = Objects.requireNonNull(dataDiScadenza);
		this.luogoDiRilascio = Objects.requireNonNull(luogoDiRilascio);
		this.provinciaDiRilascio = provinciaDiRilascio;
	}
	
	public Documento() {}
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = Objects.requireNonNull(id); }

	public Cliente getCliente() { return cliente; }

	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public String getNumero() { return numero; }

	public void setNumero(String numero) { this.numero = numero; }

	public Rilascio getRilascio() { return rilascio; }

	public void setRilascio(Rilascio rilascio) { this.rilascio = Objects.requireNonNull(rilascio); }

	public TipoDocumento getTipoDocumento() { return tipoDocumento; }

	public void setTipoDocumento(TipoDocumento tipoDocumento) { this.tipoDocumento = Objects.requireNonNull(tipoDocumento); }
	
	public LocalDate getDataDiRilascio() { return dataDiRilascio; }
	
	public void setDataDiRilascio(LocalDate dataDiRilascio) { this.dataDiRilascio = dataDiRilascio; }
	
	public LocalDate getDataDiScadenza() { return dataDiScadenza; }
	
	public void setDataDiScadenza(LocalDate dataDiScadenza) { this.dataDiScadenza = dataDiScadenza; }
	
	public String getLuogoDiRilascio() { return luogoDiRilascio; }
	
	public void setLuogoDiRilascio(String luogoDiRilascio) { this.luogoDiRilascio = luogoDiRilascio; }
	
	public String getProvinciaDiRilascio() { return provinciaDiRilascio; }
	
	public void setProvinciaDiRilascio(String provinciaDiRilascio) { this.provinciaDiRilascio = provinciaDiRilascio; }
	
	@Override
	public String toString() {
		return tipoDocumento + " " + numero + " " + rilascio;
	}
}