package com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.dao.ReservationDao;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class ReservationService {

	private final ReservationDao reservationDao = new ReservationDao();
	
	public void saveReservation(Reservation reservation) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		reservationDao.saveReservation(reservation);
		session.getTransaction().commit();
	}
	
	public void deleteReservation(Reservation reservation) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		reservationDao.deleteReservation(reservation);
		session.getTransaction().commit();
	}
	
	public List<Reservation> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Reservation> reservations = reservationDao.getAll();
		session.getTransaction().commit();
		return reservations;
	}
	
	public List<Reservation> getCheckIn() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Reservation> checkIns = reservationDao.getCheckIn();
		session.getTransaction().commit();
		return checkIns;
	}
	
	public List<Reservation> getCheckOut() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Reservation> checkOuts = reservationDao.getCheckOut();
		session.getTransaction().commit();
		return checkOuts;
	}
	
	public List<Reservation> getReservationByCustomerSurname(String surname) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Reservation> reservations = reservationDao.getReservationsByCustomerSurname(surname);
		session.getTransaction().commit();
		return reservations;
	}
	
	public List<Reservation> getReservationByRooms(List<Room> rooms) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Integer> roomNumbers = rooms.stream().map(Room::getNumber).toList();
		List<Reservation> reservations = reservationDao.getReservationsByRooms(roomNumbers);
		session.getTransaction().commit();
		return reservations;
	}
	
	public List<Reservation> getReservationByStartDate(LocalDate startDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Reservation> reservations = reservationDao.getReservationsByStartDate(startDate);
		session.getTransaction().commit();
		return reservations;
	}
	
	public List<Reservation> getReservationByEndDate(LocalDate dataFine) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Reservation> reservations = reservationDao.getReservatrionsByEndDate(dataFine);
		session.getTransaction().commit();
		return reservations;
	}
	
	public Reservation getReservationById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Reservation reservation = reservationDao.getReservationById(id);
		session.getTransaction().commit();
		return reservation;
	}
	
	public Reservation findByRoomAndDate(Room room, LocalDate data) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Reservation reservation = reservationDao.findByRoomAndDate(room, data);
		session.getTransaction().commit();
		return reservation;
	}
	
	public Reservation findByRoomAndStartDate(Room room, LocalDate data) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Reservation reservation = reservationDao.findByRoomAndStartDate(room, data);
		session.getTransaction().commit();
		return reservation;
	}
	
	public Reservation findByRoomAndEndDateAndStartDateInPreviousMonth(Room room, LocalDate data) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Reservation reservation = reservationDao.findByRoomAndEndDateAndStartDateInPreviousMonth(room, data);
		session.getTransaction().commit();
		return reservation;
	}
	
	public Reservation findByRoomAndStartDateInPreviousMonth(Room room, LocalDate data) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Reservation reservation = reservationDao.findByRoomAndStartDateInPreviousMonth(room, data);
		session.getTransaction().commit();
		return reservation;
	}
}