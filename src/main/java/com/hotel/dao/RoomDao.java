package com.hotel.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hotel.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hotel.util.HibernateUtil;

public class RoomDao {

	public void saveRoom(Room room) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(room);
		session.getTransaction().commit();
	}
	
	public void deleteRoom(Room room) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(room);
		session.getTransaction().commit();
	}
	
	public List<Room> getAll() {
		List<Room> camere;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		camere = session.createQuery("from Room", Room.class).getResultList();
		session.getTransaction().commit();
		return camere;
	}
	
	public Room getByNumber(int number) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Room room = session.get(Room.class, number);
		session.getTransaction().commit();
		return room;
	}
	
	private Room getByNumber(Integer number) {
		return getByNumber((int) number);
	}
	
	public Set<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
		Set<Room> availableRooms;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String queryString = """
				SELECT c.number
				FROM Room c
				WHERE
					c.number NOT IN (SELECT c.number
									FROM Reservation p, Reservation_room pc, Room c
									WHERE
											p.id = pc.reservation_id
										AND c.number = pc.rooms_number
										AND :startDate < p.endDate
										AND :endDate > p.startDate)
				""";
		Query<Integer> query = session.createNativeQuery(queryString, Integer.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		List<Integer> availableRoomsNumbers = query.getResultList();
		
		session.getTransaction().commit();
		
		availableRooms = availableRoomsNumbers.stream().map(this::getByNumber).collect(Collectors.toSet());
		
		return availableRooms;
	}
}