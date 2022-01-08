package com.hotel.service;

import com.hotel.dao.DocumentoDao;
import com.hotel.entity.Documento;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class DocumentoService {
	
	private DocumentoDao documentoDao = new DocumentoDao();
	
	public void saveDocumento(Documento documento) {
		documentoDao.saveDocumento(documento);
	}
	
	public void deleteDocumento(Documento documento) {
		documentoDao.deleteDocumento(documento);
	}
	
	public Documento getDocumentoById(String id) {
		return documentoDao.getById(id);
	}
}