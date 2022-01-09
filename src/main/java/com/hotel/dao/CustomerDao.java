package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hotel.entity.Customer;
import com.hotel.util.HibernateUtil;

public class CustomerDao {

	public void saveCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(customer);
		session.getTransaction().commit();
	}
	
	public void deleteCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(customer);
		session.getTransaction().commit();
	}
	
	public List<Customer> getAll() {
		List<Customer> customers;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		customers = session.createQuery("from Customer", Customer.class).getResultList();
		session.getTransaction().commit();
		return customers;
	}
	
	public Customer getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		session.getTransaction().commit();
		return customer;
	}
	
	public List<Customer> getCustomerBySurname(String surname) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Customer> query = session.createQuery("FROM Customer c WHERE c.surname = :surname", Customer.class);
		query.setParameter("surname", surname);
		List<Customer> customers = query.getResultList();
		
		session.getTransaction().commit();
		return customers;
	}
	
	public Customer getCustomerByDocumentNumber(String documentNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Customer> query = session.createQuery("FROM Customer c WHERE c.document.number = :documentNumber", Customer.class);
		query.setParameter("documentNumber", documentNumber);
		Customer customer = query.getSingleResult();
		
		session.getTransaction().commit();
		return customer;
	}
}