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
		session.saveOrUpdate(order);
	}
	
	public void deleteOrder(Order order) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(order);
	}
	
	public List<Order> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.createQuery("from Order", Order.class).getResultList();
	}
	
	public List<Order> getExpiringOrders() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Query<Order> query = session.createQuery("from Order o WHERE o.expirationDate BETWEEN :immediateExpiration AND :expirationInTheWeek ORDER BY o.expirationDate", Order.class);
		query.setParameter("immediateExpiration", LocalDate.now());
		query.setParameter("expirationInTheWeek", LocalDate.now().plusWeeks(1));
		return query.getResultList();
	}
}