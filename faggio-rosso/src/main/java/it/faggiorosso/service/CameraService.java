package it.faggiorosso.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import it.faggiorosso.dao.CameraDao;
import it.faggiorosso.entity.Camera;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class CameraService {

	private CameraDao cameraDao = new CameraDao();
	
	public void saveCamera(Camera camera) {
		cameraDao.saveCamera(camera);
	}
	
	public void deleteCamera(Camera camera) {
		cameraDao.deleteCamera(camera);
	}
	
	public Camera getByNumero(int numero) {
		return cameraDao.getByNumero(numero);
	}
	
	public List<Camera> getAll() {
		return cameraDao.getAll();
	}
	
	public Set<Camera> findAvailableRooms(LocalDate dataInizio, LocalDate dataFine) {
		return cameraDao.findAvailableRooms(dataInizio, dataFine);
	}
}