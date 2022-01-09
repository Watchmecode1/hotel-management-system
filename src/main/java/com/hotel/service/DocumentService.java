package com.hotel.service;

import com.hotel.dao.DocumentDao;
import com.hotel.entity.Document;

public class DocumentService {
	
	private final DocumentDao documentDao = new DocumentDao();
	
	public void saveDocument(Document document) {
		documentDao.saveDocument(document);
	}
	
	public void deleteDocument(Document document) {
		documentDao.deleteDocument(document);
	}
	
	public Document getDocumentById(Long id) {
		return documentDao.getById(id);
	}
}