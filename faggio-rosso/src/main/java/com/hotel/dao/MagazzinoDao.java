package com.hotel.dao;

import org.hibernate.Session;

import com.hotel.entity.Magazzino;
import com.hotel.util.HibernateUtil;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class MagazzinoDao {

	public void saveMagazzino(Magazzino magazzino) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(magazzino);
		session.getTransaction().commit();
	}
	
	public void deleteMagazzino(Magazzino magazzino) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(magazzino);
		session.getTransaction().commit();
	}
}