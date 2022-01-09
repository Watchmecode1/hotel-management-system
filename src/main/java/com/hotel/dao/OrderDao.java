package com.hotel.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hotel.entity.Order;
import com.hotel.util.HibernateUtil;

public class OrderDao {

	public void saveOrder(Order order) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(order);
		session.getTransaction().commit();
	}
	
	public void deleteOrder(Order order) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(order);
		session.getTransaction().commit();
	}
	
	public List<Order> getAll() {
		List<Order> orders;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		orders = session.createQuery("from Order", Order.class).getResultList();
		session.getTransaction().commit();
		return orders;
	}
	
	public List<Order> getExpiringOrders() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Order> query = session.createQuery("from Order o WHERE o.expirationDate BETWEEN :immediateExpiration AND :expirationInTheWeek ORDER BY o.expirationDate", Order.class);
		query.setParameter("immediateExpiration", LocalDate.now());
		query.setParameter("expirationInTheWeek", LocalDate.now().plusWeeks(1));
		List<Order> expiringOrders = query.getResultList();
		
		session.getTransaction().commit();
		return expiringOrders;
	}
}