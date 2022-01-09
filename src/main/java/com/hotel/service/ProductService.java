package com.hotel.service;

import java.util.List;

import com.hotel.dao.ProductDao;
import com.hotel.entity.Product;

public class ProductService {

	private final ProductDao productDao = new ProductDao();
	
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}
	
//	public void deleteProdotto(Prodotto prodotto) {
//		prodottoDao.deleteProdotto(prodotto);
//	}
	
	public Product getByName(String nome) {
		return productDao.getByName(nome);
	}
	
	public List<Product> getAll() {
		return productDao.getAll();
	}
}