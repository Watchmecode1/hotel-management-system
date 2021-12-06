package it.faggiorosso.service;

import java.util.List;

import it.faggiorosso.dao.OrdineDao;
import it.faggiorosso.entity.Ordine;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class OrdineService {

	private OrdineDao ordineDao = new OrdineDao();
	
	public void saveOrdine(Ordine ordine) {
		ordineDao.saveOrdine(ordine);
	}
	
	public void deleteOrdine(Ordine ordine) {
		ordineDao.deleteOrdine(ordine);
	}
	
	public List<Ordine> getAll() {
		return ordineDao.getAll();
	}
	
	public List<Ordine> getOrdiniInScadenza() {
		return ordineDao.getOrdiniInScadenza();
	}
}