package it.faggiorosso.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Camera {
	
	@Id
	private int numero;
	
	@ManyToOne
	@JoinColumn(name = "tipo_camera_id")
	private TipoCamera tipoCamera;
	
	@Column(nullable = false)
	private Piano piano;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Balcone balcone;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Stato stato = Stato.LIBERA;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Condizione condizione = Condizione.PULITA;
	
	private String note = "";
	
	@ManyToMany(mappedBy = "camere")
	private Set<Prenotazione> prenotazione;
	
	public enum Piano {
		
		PRIMO(1), SECONDO(2), TERZO(3), QUARTO(4), QUINTO(5);
		
		private int piano;
		
		Piano(int piano) {
			this.piano = piano;
		}
		
		public int getPiano() { return piano; }
	}
	
	public enum Balcone { SENZA_BALCONE, CON_BALCONE }
	
	public enum Stato { LIBERA, OCCUPATA }
	
	public enum Condizione { PULITA, DA_PULIRE }

	public Camera(int numero, TipoCamera tipoCamera, Piano piano, Balcone balcone) {
		this.numero = numero;
		this.tipoCamera = Objects.requireNonNull(tipoCamera);
		this.piano = Objects.requireNonNull(piano);
		this.balcone = Objects.requireNonNull(balcone);
	}
	
	public Camera() {}

	public int getNumero() { return numero; }

	public void setNumero(int numero) {this.numero = numero;}
	
	public TipoCamera getTipoCamera() { return tipoCamera; }

	public void setTipoCamera(TipoCamera tipoCamera) { this.tipoCamera = Objects.requireNonNull(tipoCamera); }

	public Stato getStato() { return stato; }

	public void setStato(Stato stato) { this.stato = Objects.requireNonNull(stato); }

	public Condizione getCondizione() { return condizione; }

	public void setCondizione(Condizione condizione) { this.condizione = Objects.requireNonNull(condizione); }

	public Set<Prenotazione> getPrenotazione() { return prenotazione; }

	public void setPrenotazione(Set<Prenotazione> prenotazione) {
		this.prenotazione = Objects.requireNonNull(prenotazione);
	}
	
	public Balcone getBalcone() { return balcone; }

	public void setBalcone(Balcone balcone) { this.balcone = Objects.requireNonNull(balcone); }
	
	public Piano getPiano() { return piano; }

	public void setPiano(Piano piano) { this.piano = Objects.requireNonNull(piano); }
	
	public String getNote() { return note; }
	
	public void setNote(String note) { this.note = note; }

	@Override
	public String toString() {
		return "CAMERA " + getNumero() + "(" + getTipoCamera().getTipo() + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero, piano);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Camera))
			return false;
		Camera other = (Camera) obj;
		return numero == other.numero && piano == other.piano;
	}
}