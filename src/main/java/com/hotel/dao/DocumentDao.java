package com.hotel.dao;

import org.hibernate.Session;

import com.hotel.entity.Document;
import com.hotel.util.HibernateUtil;

public class DocumentDao {

	public void saveDocument(Document document) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(document);
	}
	
	public void deleteDocument(Document document) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(document);
	}
	
	public Document getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.get(Document.class, id);
	}
}