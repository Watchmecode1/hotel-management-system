package com.hotel.dao;

import org.hibernate.Session;

import com.hotel.entity.Document;
import com.hotel.util.HibernateUtil;

public class DocumentDao {

	public void saveDocument(Document document) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(document);
		session.getTransaction().commit();
	}
	
	public void deleteDocument(Document document) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(document);
		session.getTransaction().commit();
	}
	
	public Document getById(String id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Document document = session.get(Document.class, id);
		session.getTransaction().commit();
		return document;
	}
}