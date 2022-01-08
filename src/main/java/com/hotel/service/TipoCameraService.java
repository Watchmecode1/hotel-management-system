package com.hotel.service;

import java.util.List;

import com.hotel.dao.TipoCameraDao;
import com.hotel.entity.TipoCamera;
import com.hotel.entity.TipoCamera.Tipo;

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