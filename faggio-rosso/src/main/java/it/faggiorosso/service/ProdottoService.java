package it.faggiorosso.service;

import java.util.List;

import it.faggiorosso.dao.ProdottoDao;
import it.faggiorosso.entity.Prodotto;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class ProdottoService {

	private ProdottoDao prodottoDao = new ProdottoDao();
	
	public void saveProdotto(Prodotto prodotto) {
		prodottoDao.saveProdotto(prodotto);
	}
	
	public void deleteProdotto(Prodotto prodotto) {
		prodottoDao.deleteProdotto(prodotto);
	}
	
	public Prodotto getByNome(String nome) {
		return prodottoDao.getByNome(nome);
	}
	
	public List<Prodotto> getAll() {
		return prodottoDao.getAll();
	}
}