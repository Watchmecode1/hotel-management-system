package it.faggiorosso.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import it.faggiorosso.entity.Cliente;
import it.faggiorosso.util.HibernateUtil;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class ClienteDao {

	public void saveCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(cliente);
		session.getTransaction().commit();
	}
	
	public void deleteCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(cliente);
		session.getTransaction().commit();
	}
	
	public List<Cliente> getAll() {
		List<Cliente> clienti = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		clienti = session.createQuery("from Cliente", Cliente.class).getResultList();
		session.getTransaction().commit();
		return clienti;
	}
	
	public Cliente getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Cliente cliente = session.get(Cliente.class, id);
		session.getTransaction().commit();
		return cliente;
	}
	
	public List<Cliente> getClientiByCognome(String cognome) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Cliente> query = session.createQuery("FROM Cliente c WHERE c.cognome = :cognome", Cliente.class);
		query.setParameter("cognome", cognome);
		List<Cliente> clienti = query.getResultList();
		
		session.getTransaction().commit();
		return clienti;
	}
	
	public Cliente getClienteByNumeroDocumento(String numeroDocumento) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Cliente> query = session.createQuery("FROM Cliente c WHERE c.documento.numero = :numeroDocumento", Cliente.class);
		query.setParameter("numeroDocumento", numeroDocumento);
		Cliente cliente = query.getSingleResult();
		
		session.getTransaction().commit();
		return cliente;
	}
}