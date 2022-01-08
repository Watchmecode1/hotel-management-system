package com.hotel.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.hotel.entity.Consumazione;
import com.hotel.util.HibernateUtil;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class ConsumazioneDao {

	public void saveConsumazione(Consumazione consumazione) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(consumazione);
		session.getTransaction().commit();
	}
	
	public void deleteConsumazione(Consumazione consumazione) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(consumazione);
		session.getTransaction().commit();
	}
	
	public List<Consumazione> getAll() {
		List<Consumazione> consumazioni = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		consumazioni = session.createQuery("from Consumazione", Consumazione.class).getResultList();
		session.getTransaction().commit();
		return consumazioni;
	}
}