package com.hotel.service;

import java.util.List;

import com.hotel.dao.RoomTypeDao;
import com.hotel.entity.RoomType;
import com.hotel.entity.RoomType.Type;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;

public class RoomTypeService {
	
	private final RoomTypeDao roomTypeDao = new RoomTypeDao();

	public void saveRoomType(RoomType roomType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		roomTypeDao.saveRoomType(roomType);
		session.getTransaction().commit();
	}
	
	public RoomType getById(Type type) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		RoomType roomType =  roomTypeDao.getById(type);
		session.getTransaction().commit();
		return roomType;
	}
	
	public List<RoomType> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<RoomType> roomTypes = roomTypeDao.getAll();
		session.getTransaction().commit();
		return roomTypes;
	}
}