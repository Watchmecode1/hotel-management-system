package com.hotel.service;

import java.util.List;

import com.hotel.dao.ConsumazioneDao;
import com.hotel.entity.Consumazione;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class ConsumazioneService {

	private ConsumazioneDao consumazioneDao = new ConsumazioneDao();
	
	public void saveConsumazione(Consumazione consumazione) {
		consumazioneDao.saveConsumazione(consumazione);
	}
	
	public void deleteConsumazione(Consumazione consumazione) {
		consumazioneDao.deleteConsumazione(consumazione);
	}
	
	public List<Consumazione> getAll() {
		return consumazioneDao.getAll();
	}
}