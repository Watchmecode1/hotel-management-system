package com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.dao.PrenotazioneDao;
import com.hotel.entity.Camera;
import com.hotel.entity.Prenotazione;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class PrenotazioneService {

	private PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
	
	public void savePrenotazione(Prenotazione prenotazione) {
		prenotazioneDao.savePrenotazione(prenotazione);
	}
	
	public void deletePrenotazione(Prenotazione prenotazione) {
		prenotazioneDao.deletePrenotazione(prenotazione);
	}
	
	public List<Prenotazione> getAll() {
		return prenotazioneDao.getAll();
	}
	
	public List<Prenotazione> getCheckIn() {
		return prenotazioneDao.getCheckIn();
	}
	
	public List<Prenotazione> getCheckOut() {
		return prenotazioneDao.getCheckOut();
	}
	
	public List<Prenotazione> getPrenotazioniByCognomeCliente(String cognome) {
		return prenotazioneDao.getPrenotazioniByCognomeCliente(cognome);
	}
	
	public List<Prenotazione> getPrenotazioneByCamere(List<Camera> camere) {
		List<Integer> numeriCamere = camere.stream().map(Camera::getNumero).toList();
		return prenotazioneDao.getPrenotazioniByCamere(numeriCamere);
	}
	
	public List<Prenotazione> getPrenotazioneByDataInizio(LocalDate dataInizio) {
		return prenotazioneDao.getPrenotazioniByDataInizio(dataInizio);
	}
	
	public List<Prenotazione> getPrenotazioneByDataFine(LocalDate dataFine) {
		return prenotazioneDao.getPrenotazioniByDataFine(dataFine);
	}
	
	public Prenotazione getPrenotazioneById(Long id) {
		return prenotazioneDao.getPrenotazioneById(id);
	}
	
	public Prenotazione findByCameraAndData(Camera camera, LocalDate data) {
		return prenotazioneDao.findByCameraAndData(camera, data);
	}
	
	public Prenotazione findByCameraAndDataInizio(Camera camera, LocalDate data) {
		return prenotazioneDao.findByCameraAndDataInizio(camera, data);
	}
	
	public Prenotazione findByCameraAndDataFineAndDataInizioInPreviousMonth(Camera camera, LocalDate data) {
		return prenotazioneDao.findByCameraAndDataFineAndDataInizioInPreviousMonth(camera, data);
	}
	
	public Prenotazione findByCameraAndDataInizioInPreviousMonth(Camera camera, LocalDate data) {
		return prenotazioneDao.findByCameraAndDataInizioInPreviousMonth(camera, data);
	}
}