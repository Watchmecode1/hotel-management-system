package com.hotel.service;

import java.util.List;

import com.hotel.dao.CustomerDao;
import com.hotel.entity.Customer;

public class CustomerService {

	private final CustomerDao customerDao = new CustomerDao();
	
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
	}
	
	public void deleteCustomer(Customer customer) {
		customerDao.deleteCustomer(customer);
	}
	
	public Customer getById(Long id) {
		return customerDao.getById(id);
	}
	
	public List<Customer> getAll() {
		return customerDao.getAll();
	}
	
	public List<Customer> getCustomersBySurname(String surname) {
		return customerDao.getCustomerBySurname(surname);
	}
	
	public Customer getCustomersByDocumentNumber(String documentNumber) {
		return customerDao.getCustomerByDocumentNumber(documentNumber);
	}
}