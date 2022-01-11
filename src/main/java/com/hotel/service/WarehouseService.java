package com.hotel.service;

import com.hotel.dao.WarehouseDao;
import com.hotel.entity.Warehouse;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class WarehouseService {

	private final WarehouseDao warehouseDao = new WarehouseDao();
	
	public void saveWarehouse(Warehouse warehouse) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		warehouseDao.saveWarehouse(warehouse);
		session.getTransaction().commit();
	}
	
	public void deleteWarehouse(Warehouse warehouse) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		warehouseDao.deleteWarehouse(warehouse);
		session.getTransaction().commit();
	}
}