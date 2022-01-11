package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.entity.Consumption;
import com.hotel.util.HibernateUtil;

public class ConsumptionDao {

	public void saveConsumption(Consumption consumption) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(consumption);
	}
	
	public void deleteConsumption(Consumption consumption) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(consumption);
	}
	
	public List<Consumption> getAll() {
		List<Consumption> consumptions;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		consumptions = session.createQuery("from Consumption", Consumption.class).getResultList();
		return consumptions;
	}
}