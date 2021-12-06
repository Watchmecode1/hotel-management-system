package it.faggiorosso.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import it.faggiorosso.entity.TipoCamera;
import it.faggiorosso.entity.TipoCamera.Tipo;
import it.faggiorosso.util.HibernateUtil;

public class TipoCameraDao {
	public void saveTipoCamera(TipoCamera tipoCamera) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(tipoCamera);
		session.getTransaction().commit();
	}
	
	public TipoCamera getById(Tipo tipo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TipoCamera tipoCamera = session.get(TipoCamera.class, tipo);
		session.getTransaction().commit();
		return tipoCamera;
	}
	
	public List<TipoCamera> getAll() {
		List<TipoCamera> tipiCamere = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		tipiCamere = session.createQuery("from TipoCamera", TipoCamera.class).getResultList();
		session.getTransaction().commit();
		return tipiCamere;
	}
}