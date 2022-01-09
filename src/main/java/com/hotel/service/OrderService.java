package com.hotel.service;

import java.util.List;

import com.hotel.dao.OrderDao;
import com.hotel.entity.Order;

public class OrderService {

	private final OrderDao orderDao = new OrderDao();
	
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}
	
	public void deleteOrder(Order order) {
		orderDao.deleteOrder(order);
	}
	
	public List<Order> getAll() {
		return orderDao.getAll();
	}
	
	public List<Order> getExpiringOrders() {
		return orderDao.getExpiringOrders();
	}
}