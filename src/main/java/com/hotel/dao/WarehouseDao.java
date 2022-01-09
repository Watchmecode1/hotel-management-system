package com.hotel.dao;

import org.hibernate.Session;

import com.hotel.entity.Warehouse;
import com.hotel.util.HibernateUtil;

public class WarehouseDao {

	public void saveWarehouse(Warehouse warehouse) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(warehouse);
		session.getTransaction().commit();
	}
	
	public void deleteWarehouse(Warehouse warehouse) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(warehouse);
		session.getTransaction().commit();
	}
}