package com.hotel.service;

import java.util.List;

import com.hotel.dao.OrderDao;
import com.hotel.entity.Order;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class OrderService {

	private final OrderDao orderDao = new OrderDao();
	
	public void saveOrder(Order order) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		orderDao.saveOrder(order);
		session.getTransaction().commit();
	}
	
	public void deleteOrder(Order order) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		orderDao.deleteOrder(order);
		session.getTransaction().commit();
	}
	
	public List<Order> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Order> orders = orderDao.getAll();
		session.getTransaction().commit();
		return orders;
	}
	
	public List<Order> getExpiringOrders() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Order> expiringOrders = orderDao.getExpiringOrders();
		session.getTransaction().commit();
		return expiringOrders;
	}
}