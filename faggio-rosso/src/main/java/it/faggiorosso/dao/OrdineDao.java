package it.faggiorosso.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import it.faggiorosso.entity.Ordine;
import it.faggiorosso.util.HibernateUtil;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class OrdineDao {

	public void saveOrdine(Ordine ordine) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(ordine);
		session.getTransaction().commit();
	}
	
	public void deleteOrdine(Ordine ordine) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(ordine);
		session.getTransaction().commit();
	}
	
	public List<Ordine> getAll() {
		List<Ordine> ordini = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ordini = session.createQuery("from Ordine", Ordine.class).getResultList();
		session.getTransaction().commit();
		return ordini;
	}
	
	public List<Ordine> getOrdiniInScadenza() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Ordine> query = session.createQuery("from Ordine o WHERE o.dataScadenza BETWEEN :scadenzaImmediata AND :scadenzaInSettimana ORDER BY o.dataScadenza", Ordine.class);
		query.setParameter("scadenzaImmediata", LocalDate.now());
		query.setParameter("scadenzaInSettimana", LocalDate.now().plusWeeks(1));
		List<Ordine> ordiniInScadenza = query.getResultList();
		
		session.getTransaction().commit();
		return ordiniInScadenza;
	}
}