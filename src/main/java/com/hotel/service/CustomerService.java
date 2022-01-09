package com.hotel.service;

import java.util.List;

import com.hotel.dao.CustomerDao;
import com.hotel.entity.Customer;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class CustomerService {

	private final CustomerDao customerDao = new CustomerDao();
	
	public void saveCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		customerDao.saveCustomer(customer);
		session.getTransaction().commit();
	}
	
	public void deleteCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		customerDao.deleteCustomer(customer);
		session.getTransaction().commit();
	}
	
	public Customer getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Customer customer = customerDao.getById(id);
		session.getTransaction().commit();
		return customer;
	}
	
	public List<Customer> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Customer> customers = customerDao.getAll();
		session.getTransaction().commit();
		return customers;
	}
	
	public List<Customer> getCustomersBySurname(String surname) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Customer> customers = customerDao.getCustomerBySurname(surname);
		session.getTransaction().commit();
		return customers;
	}
	
	public Customer getCustomersByDocumentNumber(String documentNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Customer customer = customerDao.getCustomerByDocumentNumber(documentNumber);
		session.getTransaction().commit();
		return customer;
	}
}