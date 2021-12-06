package it.faggiorosso.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.faggiorosso.entity.Prodotto;
import it.faggiorosso.util.HibernateUtil;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class ProdottoDao {
	
	public void saveProdotto(Prodotto prodotto) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(prodotto);
		session.getTransaction().commit();
	}
	
	public void deleteProdotto(Prodotto prodotto) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(prodotto);
		session.getTransaction().commit();
	}
	
	public List<Prodotto> getAll() {
		List<Prodotto> prodotti = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		prodotti = session.createQuery("from Prodotto", Prodotto.class).getResultList();
		session.getTransaction().commit();
		return prodotti;
	}
	
	public Prodotto getByNome(String nome) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Prodotto prodotto = session.get(Prodotto.class, nome);
		session.getTransaction().commit();
		return prodotto;
	}
}