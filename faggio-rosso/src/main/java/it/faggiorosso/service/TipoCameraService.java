package it.faggiorosso.service;

import java.util.List;

import it.faggiorosso.dao.TipoCameraDao;
import it.faggiorosso.entity.TipoCamera;
import it.faggiorosso.entity.TipoCamera.Tipo;

public class TipoCameraService {
	
	private TipoCameraDao tipoCameraDao = new TipoCameraDao(); 

	public void saveTipoCamera(TipoCamera tipoCamera) {
		tipoCameraDao.saveTipoCamera(tipoCamera);
	}
	
	public TipoCamera getById(Tipo tipo) {
		return tipoCameraDao.getById(tipo);
	}
	
	public List<TipoCamera> getAll() {
		return tipoCameraDao.getAll();
	}
}