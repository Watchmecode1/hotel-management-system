package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.entity.Product;
import com.hotel.util.HibernateUtil;

public class ProductDao {
	
	public void saveProduct(Product product) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(product);
	}

//	public void deleteProdotto(Prodotto prodotto) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		session.get(Prodotto.class, prodotto.getNome()).getConsumazioni().forEach(session::delete);
//		session.delete(prodotto);
//		session.getTransaction().commit();
//	}
	
	public List<Product> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.createQuery("from Product", Product.class).getResultList();
	}
	
	public Product getByName(String name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.get(Product.class, name);
	}
}