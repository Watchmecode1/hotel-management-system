package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.entity.Consumption;
import com.hotel.util.HibernateUtil;

public class ConsumptionDao {

	public void saveConsumption(Consumption consumption) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(consumption);
		session.getTransaction().commit();
	}
	
	public void deleteConsumption(Consumption consumption) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(consumption);
		session.getTransaction().commit();
	}
	
	public List<Consumption> getAll() {
		List<Consumption> consumptions;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		consumptions = session.createQuery("from Consumption", Consumption.class).getResultList();
		session.getTransaction().commit();
		return consumptions;
	}
}