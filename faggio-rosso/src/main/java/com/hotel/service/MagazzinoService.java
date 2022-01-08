package com.hotel.service;

import com.hotel.dao.MagazzinoDao;
import com.hotel.entity.Magazzino;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class MagazzinoService {

	private MagazzinoDao magazzinoDao = new MagazzinoDao();
	
	public void saveMagazzino(Magazzino magazzino) {
		magazzinoDao.saveMagazzino(magazzino);
	}
	
	public void deleteMagazzino(Magazzino magazzino) {
		magazzinoDao.deleteMagazzino(magazzino);
	}
}