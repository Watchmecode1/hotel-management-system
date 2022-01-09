package com.hotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.hotel.dao.RoomDao;
import com.hotel.entity.Room;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class RoomService {

	private final RoomDao roomDao = new RoomDao();
	
	public void saveRoom(Room room) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		roomDao.saveRoom(room);
		session.getTransaction().commit();
	}
	
	public void deleteRoom(Room room) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		roomDao.deleteRoom(room);
		session.getTransaction().commit();
	}
	
	public Room getByNumber(int number) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Room room = roomDao.getByNumber(number);
		session.getTransaction().commit();
		return room;
	}
	
	public List<Room> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Room> rooms = roomDao.getAll();
		session.getTransaction().commit();
		return rooms;
	}
	
	public Set<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Room> availableRooms = roomDao.findAvailableRooms(startDate, endDate);
		session.getTransaction().commit();
		return availableRooms;
	}
}