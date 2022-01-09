package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hotel.entity.Customer;
import com.hotel.util.HibernateUtil;

public class CustomerDao {

	public void saveCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(customer);
	}
	
	public void deleteCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(customer);
	}
	
	public List<Customer> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.createQuery("from Customer", Customer.class).getResultList();
	}
	
	public Customer getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.get(Customer.class, id);
	}
	
	public List<Customer> getCustomerBySurname(String surname) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Query<Customer> query = session.createQuery("FROM Customer c WHERE c.surname = :surname", Customer.class);
		query.setParameter("surname", surname);
		return query.getResultList();
	}
	
	public Customer getCustomerByDocumentNumber(String documentNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Query<Customer> query = session.createQuery("FROM Customer c WHERE c.document.number = :documentNumber", Customer.class);
		query.setParameter("documentNumber", documentNumber);
		return query.getSingleResult();
	}
}