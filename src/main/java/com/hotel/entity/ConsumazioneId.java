package com.hotel.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class ConsumazioneId implements Serializable {

	@Serial
	private static final long serialVersionUID = 4188650510728064111L;

	private Long prenotazioneId;
	
	private String nomeProdotto;
	
	public ConsumazioneId(Long prenotazioneId, String nomeProdotto) {
		this.prenotazioneId = Objects.requireNonNull(prenotazioneId);
		this.nomeProdotto = Objects.requireNonNull(nomeProdotto);
	}
	
	public ConsumazioneId() {}

	public Long getPrenotazioneId() { return prenotazioneId; }

	public void setPrenotazioneId(Long prenotazioneId) { this.prenotazioneId = Objects.requireNonNull(prenotazioneId); }

	public String getNomeProdotto() { return nomeProdotto; }

	public void setNomeProdotto(String nomeProdotto) { this.nomeProdotto = Objects.requireNonNull(nomeProdotto); }

	@Override
	public int hashCode() {
		int result = Long.hashCode(prenotazioneId);
		result = 31 * result + nomeProdotto.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof ConsumazioneId consumazioneId)) return false;
		return consumazioneId.getNomeProdotto().equals(nomeProdotto) &&
				consumazioneId.getPrenotazioneId().equals(prenotazioneId);
	}
}