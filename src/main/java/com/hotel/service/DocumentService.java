package com.hotel.service;

import com.hotel.dao.DocumentDao;
import com.hotel.entity.Document;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class DocumentService {
	
	private final DocumentDao documentDao = new DocumentDao();
	
	public void saveDocument(Document document) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		documentDao.saveDocument(document);
		session.getTransaction().commit();
	}
	
	public void deleteDocument(Document document) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		documentDao.deleteDocument(document);
		session.getTransaction().commit();
	}
	
	public Document getDocumentById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Document document = documentDao.getById(id);
		session.getTransaction().commit();
		return document;
	}
}