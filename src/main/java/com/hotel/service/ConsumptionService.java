package com.hotel.service;

import java.util.List;

import com.hotel.dao.ConsumptionDao;
import com.hotel.entity.Consumption;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class ConsumptionService {

	private final ConsumptionDao consumptionDao = new ConsumptionDao();
	
	public void saveConsumption(Consumption consumption) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		consumptionDao.saveConsumption(consumption);
		session.getTransaction().commit();
	}
	
	public void deleteConsumption(Consumption consumption) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		consumptionDao.deleteConsumption(consumption);
		session.getTransaction().commit();
	}
	
	public List<Consumption> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Consumption> consumptions = consumptionDao.getAll();
		session.getTransaction().commit();
		return consumptions;
	}
}