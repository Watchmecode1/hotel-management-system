package com.hotel.dao;

import java.time.LocalDate;
import java.util.List;

import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hotel.util.HibernateUtil;

public class ReservationDao {

	public void saveReservation(Reservation reservation) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(reservation);
	}
	
	public void deleteReservation(Reservation reservation) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		reservation.getConsumptions().forEach(session::delete);
		session.delete(reservation);
	}
	
	public List<Reservation> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.createQuery("from Reservation", Reservation.class).getResultList();
	}
	
	public List<Reservation> getCheckIn() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Query<Reservation> query = session.createQuery("from Reservation p WHERE p.startDate = :startDate", Reservation.class);
		query.setParameter("startDate", LocalDate.now());
		return query.getResultList();
	}
	
	public List<Reservation> getCheckOut() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Query<Reservation> query = session.createQuery("from Reservation p WHERE p.endDate = :endDate", Reservation.class);
		query.setParameter("endDate", LocalDate.now());
		return query.getResultList();
	}
	
	public List<Reservation> getReservationsByCustomerSurname(String surname) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		String queryString = """
				FROM Reservation p
				WHERE p.customers.surname = :surname
				""";
		Query<Reservation> query = session.createQuery(queryString, Reservation.class);
		query.setParameter("surname", surname);
		return query.getResultList();
	}
	
	public List<Reservation> getReservationsByRooms(List<Integer> roomNumbers) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		String queryString = """
				FROM Reservation p
				WHERE p.rooms.number IN :roomNumbers
				""";
		Query<Reservation> query = session.createQuery(queryString, Reservation.class);
		query.setParameter("roomNumbers", roomNumbers);
		return query.getResultList();
	}
	
	public List<Reservation> getReservationsByEndDate(LocalDate endDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Query<Reservation> query = session.createQuery("from Reservation p WHERE p.endDate = :endDate", Reservation.class);
		query.setParameter("endDate", endDate);
		return query.getResultList();
	}
	
	public List<Reservation> getReservationsByStartDate(LocalDate startDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Query<Reservation> query = session.createQuery("from Reservation p WHERE p.startDate = :startDate", Reservation.class);
		query.setParameter("startDate", startDate);
		return query.getResultList();
	}
	
	public Reservation getReservationById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Reservation reservation = session.get(Reservation.class, id);
		Hibernate.initialize(reservation.getRooms());
		Hibernate.initialize(reservation.getConsumptions());
		return reservation;
	}
	
	public Reservation findByRoomAndDate(Room room, LocalDate date) {
		Reservation reservation = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		String queryString = """
				SELECT p.id
				FROM Reservation p, Reservation_Room c
				WHERE
							p.id = c.reservations_id
						AND c.rooms_number = :roomNumber
						AND p.startDate <= :date1
						AND p.endDate >= :date2
				""";
		Query<Long> query = session.createNativeQuery(queryString, Long.class);
		query.setParameter("roomNumber", room.getNumber());
		query.setParameter("date1", date);
		query.setParameter("date2", date);
		
		Long id = query.getResultStream().findFirst().orElse(null);
		if(id != null)
			reservation = session.get(Reservation.class, id);
		return reservation;
	}
	
	public Reservation findByRoomAndStartDate(Room room, LocalDate date) {
		Reservation reservation = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		String queryString = """
				SELECT p.id
				FROM Reservation p, Reservation_Room c
				WHERE
							p.id = c.reservations_id
						AND c.rooms_number = :roomNumber
						AND p.startDate = :date
				""";
		Query<Long> query = session.createNativeQuery(queryString, Long.class);
		query.setParameter("roomNumber", room.getNumber());
		query.setParameter("date", date);
		
		Long id = query.getResultStream().findFirst().orElse(null);
		if(id != null)
			reservation = session.get(Reservation.class, id);
		return reservation;
	}
	
	public Reservation findByRoomAndEndDateAndStartDateInPreviousMonth(Room room, LocalDate date) {
		Reservation reservation = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		String queryString = """
				SELECT p.id
				FROM Reservation p, Reservation_Room c
				WHERE
							p.id = c.reservations_id
						AND c.rooms_number = :roomNumber
						AND p.endDate = :date
						AND p.startDate >= :previousMonthStart
						AND p.startDate <= :previousMonthEnd
				""";
		
		Query<Long> query = session.createNativeQuery(queryString, Long.class);
		LocalDate previousMonth = date.minusMonths(1);
		query.setParameter("roomNumber", room.getNumber());
		query.setParameter("date", date);
		query.setParameter("previousMonthStart", previousMonth.withDayOfMonth(1));
		query.setParameter("previousMonthEnd", previousMonth.withDayOfMonth(previousMonth.lengthOfMonth()));
		
		Long id = query.getResultStream().findFirst().orElse(null);
		if(id != null)
			reservation = session.get(Reservation.class, id);
		return reservation;
	}
	
	public Reservation findByRoomAndStartDateInPreviousMonth(Room room, LocalDate date) {
		Reservation reservation = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		String queryString = """
				SELECT p.id
				FROM Reservation p, Reservation_Room c
				WHERE
							p.id = c.reservations_id
						AND c.rooms_number = :roomNumber
						AND p.startDate >= :previousMonthStart
						AND p.startDate <= :previousMonthEnd
						AND p.endDate >= :thisMonthStart
				""";
		
		Query<Long> query = session.createNativeQuery(queryString, Long.class);
		LocalDate previousMonth = date.minusMonths(1);
		query.setParameter("roomNumber", room.getNumber());
		query.setParameter("previousMonthStart", previousMonth.withDayOfMonth(1));
		query.setParameter("previousMonthEnd", previousMonth.withDayOfMonth(previousMonth.lengthOfMonth()));
		query.setParameter("thisMonthStart", date.withDayOfMonth(1));
		
		Long id = query.getResultStream().findFirst().orElse(null);
		if(id != null)
			reservation = session.get(Reservation.class, id);
		return reservation;
	}
}