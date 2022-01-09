package com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.dao.ReservationDao;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;

public class ReservationService {

	private final ReservationDao reservationDao = new ReservationDao();
	
	public void saveReservation(Reservation reservation) {
		reservationDao.saveReservation(reservation);
	}
	
	public void deleteReservation(Reservation reservation) {
		reservationDao.deleteReservation(reservation);
	}
	
	public List<Reservation> getAll() {
		return reservationDao.getAll();
	}
	
	public List<Reservation> getCheckIn() {
		return reservationDao.getCheckIn();
	}
	
	public List<Reservation> getCheckOut() {
		return reservationDao.getCheckOut();
	}
	
	public List<Reservation> getReservationByCustomerSurname(String surname) {
		return reservationDao.getReservationsByCustomerSurname(surname);
	}
	
	public List<Reservation> getReservationByRooms(List<Room> rooms) {
		List<Integer> roomNumbers = rooms.stream().map(Room::getNumber).toList();
		return reservationDao.getReservationsByRooms(roomNumbers);
	}
	
	public List<Reservation> getReservationByStartDate(LocalDate startDate) {
		return reservationDao.getReservationsByStartDate(startDate);
	}
	
	public List<Reservation> getReservationByEndDate(LocalDate dataFine) {
		return reservationDao.getReservatrionsByEndDate(dataFine);
	}
	
	public Reservation getReservationById(Long id) {
		return reservationDao.getReservationById(id);
	}
	
	public Reservation findByRoomAndDate(Room room, LocalDate data) {
		return reservationDao.findByRoomAndDate(room, data);
	}
	
	public Reservation findByRoomAndStartDate(Room room, LocalDate data) {
		return reservationDao.findByRoomAndStartDate(room, data);
	}
	
	public Reservation findByRoomAndEndDateAndStartDateInPreviousMonth(Room room, LocalDate data) {
		return reservationDao.findByRoomAndEndDateAndStartDateInPreviousMonth(room, data);
	}
	
	public Reservation findByRoomAndStartDateInPreviousMonth(Room room, LocalDate data) {
		return reservationDao.findByRoomAndStartDateInPreviousMonth(room, data);
	}
}