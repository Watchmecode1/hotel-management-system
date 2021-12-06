package it.faggiorosso.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import it.faggiorosso.entity.Camera;
import it.faggiorosso.util.HibernateUtil;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class CameraDao {

	public void saveCamera(Camera camera) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(camera);
		session.getTransaction().commit();
	}
	
	public void deleteCamera(Camera camera) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(camera);
		session.getTransaction().commit();
	}
	
	public List<Camera> getAll() {
		List<Camera> camere = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		camere = session.createQuery("from Camera", Camera.class).getResultList();
		session.getTransaction().commit();
		return camere;
	}
	
	public Camera getByNumero(int numero) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Camera camera = session.get(Camera.class, numero);
		session.getTransaction().commit();
		return camera;
	}
	
	private Camera getByNumero(Integer numero) {
		return getByNumero((int) numero);
	}
	
	public Set<Camera> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
		Set<Camera> availableRooms = new HashSet<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String queryString = """
				SELECT c.numero
				FROM Camera c
				WHERE
					c.numero NOT IN (SELECT c.numero
									FROM Prenotazione p, Prenotazione_camera pc, Camera c
									WHERE
											p.id = pc.prenotazione_id
										AND c.numero = pc.camere_numero
										AND :startDate < p.dataFine
										AND :endDate > p.dataInizio)
				""";
		Query<Integer> query = session.createNativeQuery(queryString, Integer.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		List<Integer> availableRoomsNumbers = query.getResultList();
		
		session.getTransaction().commit();
		
		availableRooms = availableRoomsNumbers.stream().map(this::getByNumero).collect(Collectors.toSet());
		
		return availableRooms;
	}
}