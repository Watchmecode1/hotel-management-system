package it.faggiorosso.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import it.faggiorosso.entity.Camera;
import it.faggiorosso.entity.Prenotazione;
import it.faggiorosso.util.HibernateUtil;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class PrenotazioneDao {

	public void savePrenotazione(Prenotazione prenotazione) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(prenotazione);
		session.getTransaction().commit();
	}
	
	public void deletePrenotazione(Prenotazione prenotazione) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		prenotazione.getConsumazioni().forEach(session::delete);
		session.delete(prenotazione);
		session.getTransaction().commit();
	}
	
	public List<Prenotazione> getAll() {
		List<Prenotazione> prenotazioni = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		prenotazioni = session.createQuery("from Prenotazione", Prenotazione.class).getResultList();
		session.getTransaction().commit();
		return prenotazioni;
	}
	
	public List<Prenotazione> getCheckIn() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Prenotazione> query = session.createQuery("from Prenotazione p WHERE p.dataInizio = :dataInizio", Prenotazione.class);
		query.setParameter("dataInizio", LocalDate.now());
		List<Prenotazione> checkIn = query.getResultList();
		
		session.getTransaction().commit();
		
		return checkIn;
	}
	
	public List<Prenotazione> getCheckOut() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Prenotazione> query = session.createQuery("from Prenotazione p WHERE p.dataFine = :dataFine", Prenotazione.class);
		query.setParameter("dataFine", LocalDate.now());
		List<Prenotazione> checkOut = query.getResultList();
		
		session.getTransaction().commit();
		
		return checkOut;
	}
	
	public List<Prenotazione> getPrenotazioniByCognomeCliente(String cognome) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String queryString = """
				FROM Prenotazione p
				WHERE p.clienti.cognome = :cognome
				""";
		Query<Prenotazione> query = session.createQuery(queryString, Prenotazione.class);
		query.setParameter("cognome", cognome);
		List<Prenotazione> prenotazioni = query.getResultList();
		
		session.getTransaction().commit();
		
		return prenotazioni;
	}
	
	public List<Prenotazione> getPrenotazioniByCamere(List<Integer> numeriCamere) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		String queryString = """
				FROM Prenotazione p
				WHERE p.camere.numero IN :numeriCamere
				""";
		Query<Prenotazione> query = session.createQuery(queryString, Prenotazione.class);
		query.setParameter("numeriCamere", numeriCamere);
		List<Prenotazione> prenotazioni = query.getResultList();
		
		session.getTransaction().commit();
		
		return prenotazioni;
	}
	
	public List<Prenotazione> getPrenotazioniByDataFine(LocalDate dataFine) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Prenotazione> query = session.createQuery("from Prenotazione p WHERE p.dataFine = :dataFine", Prenotazione.class);
		query.setParameter("dataFine", dataFine);
		List<Prenotazione> prenotazioni = query.getResultList();
		
		session.getTransaction().commit();
		
		return prenotazioni;
	}
	
	public List<Prenotazione> getPrenotazioniByDataInizio(LocalDate dataInizio) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<Prenotazione> query = session.createQuery("from Prenotazione p WHERE p.dataInizio = :dataInizio", Prenotazione.class);
		query.setParameter("dataInizio", dataInizio);
		List<Prenotazione> prenotazioni = query.getResultList();
		
		session.getTransaction().commit();
		
		return prenotazioni;
	}
	
	public Prenotazione getPrenotazioneById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Prenotazione prenotazione = session.get(Prenotazione.class, id);
		Hibernate.initialize(prenotazione.getCamere());
		Hibernate.initialize(prenotazione.getConsumazioni());
		session.getTransaction().commit();
		return prenotazione;
	}
	
	public Prenotazione findByCameraAndData(Camera camera, LocalDate data) {
		Prenotazione prenotazione = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String queryString = """
				SELECT p.id
				FROM Prenotazione p, Prenotazione_camera c
				WHERE
							p.id = c.prenotazione_id
						AND c.camere_numero = :cameraNumero
						AND p.dataInizio <= :data1
						AND p.dataFine >= :data2
				""";
		Query<Long> query = session.createNativeQuery(queryString, Long.class);
		query.setParameter("cameraNumero", camera.getNumero());
		query.setParameter("data1", data);
		query.setParameter("data2", data);
		
		Long id = query.getResultStream().findFirst().orElse(null);
		if(id != null)
			prenotazione = session.get(Prenotazione.class, id);
		session.getTransaction().commit();
		return prenotazione;
	}
	
	public Prenotazione findByCameraAndDataInizio(Camera camera, LocalDate data) {
		Prenotazione prenotazione = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String queryString = """
				SELECT p.id
				FROM Prenotazione p, Prenotazione_camera c
				WHERE
							p.id = c.prenotazione_id
						AND c.camere_numero = :cameraNumero
						AND p.dataInizio = :data
				""";
		Query<Long> query = session.createNativeQuery(queryString, Long.class);
		query.setParameter("cameraNumero", camera.getNumero());
		query.setParameter("data", data);
		
		Long id = query.getResultStream().findFirst().orElse(null);
		if(id != null)
			prenotazione = session.get(Prenotazione.class, id);
		session.getTransaction().commit();
		return prenotazione;
	}
	
	public Prenotazione findByCameraAndDataFineAndDataInizioInPreviousMonth(Camera camera, LocalDate data) {
		Prenotazione prenotazione = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String queryString = """
				SELECT p.id
				FROM Prenotazione p, Prenotazione_camera c
				WHERE
							p.id = c.prenotazione_id
						AND c.camere_numero = :cameraNumero
						AND p.dataFine = :data
						AND p.dataInizio >= :previousMonthStart
						AND p.dataInizio <= :previousMonthEnd
				""";
		
		Query<Long> query = session.createNativeQuery(queryString, Long.class);
		LocalDate previousMonth = data.minusMonths(1);
		query.setParameter("cameraNumero", camera.getNumero());
		query.setParameter("data", data);
		query.setParameter("previousMonthStart", previousMonth.withDayOfMonth(1));
		query.setParameter("previousMonthEnd", previousMonth.withDayOfMonth(previousMonth.lengthOfMonth()));
		
		Long id = query.getResultStream().findFirst().orElse(null);
		if(id != null)
			prenotazione = session.get(Prenotazione.class, id);
		session.getTransaction().commit();
		return prenotazione;
	}
	
	public Prenotazione findByCameraAndDataInizioInPreviousMonth(Camera camera, LocalDate data) {
		Prenotazione prenotazione = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String queryString = """
				SELECT p.id
				FROM Prenotazione p, Prenotazione_camera c
				WHERE
							p.id = c.prenotazione_id
						AND c.camere_numero = :cameraNumero
						AND p.dataInizio >= :previousMonthStart
						AND p.dataInizio <= :previousMonthEnd
				""";
		
		Query<Long> query = session.createNativeQuery(queryString, Long.class);
		LocalDate previousMonth = data.minusMonths(1);
		query.setParameter("cameraNumero", camera.getNumero());
		query.setParameter("previousMonthStart", previousMonth.withDayOfMonth(1));
		query.setParameter("previousMonthEnd", previousMonth.withDayOfMonth(previousMonth.lengthOfMonth()));
		
		Long id = query.getResultStream().findFirst().orElse(null);
		if(id != null)
			prenotazione = session.get(Prenotazione.class, id);
		session.getTransaction().commit();
		return prenotazione;
	}
}