package it.faggiorosso.dao;

import org.hibernate.Session;

import it.faggiorosso.entity.Documento;
import it.faggiorosso.util.HibernateUtil;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class DocumentoDao {

	public void saveDocumento(Documento documento) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(documento);
		session.getTransaction().commit();
	}
	
	public void deleteDocumento(Documento documento) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(documento);
		session.getTransaction().commit();
	}
	
	public Documento getById(String id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Documento documento = session.get(Documento.class, id);
		session.getTransaction().commit();
		return documento;
	}
}