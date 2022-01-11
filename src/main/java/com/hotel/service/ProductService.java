package com.hotel.service;

import java.util.List;

import com.hotel.dao.ProductDao;
import com.hotel.entity.Product;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class ProductService {

	private final ProductDao productDao = new ProductDao();
	
	public void saveProduct(Product product) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		productDao.saveProduct(product);
		session.getTransaction().commit();
	}
	
//	public void deleteProdotto(Prodotto prodotto) {
//		prodottoDao.deleteProdotto(prodotto);
//	}
	
	public Product getByName(String nome) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Product product = productDao.getByName(nome);
		session.getTransaction().commit();
		return product;
	}
	
	public List<Product> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Product> products = productDao.getAll();
		session.getTransaction().commit();
		return products;
	}
}