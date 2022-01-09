package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.entity.RoomType;
import com.hotel.entity.RoomType.Type;
import com.hotel.util.HibernateUtil;

public class RoomTypeDao {
	public void saveRoomType(RoomType roomType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(roomType);
	}
	
	public RoomType getById(Type type) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		RoomType roomType = session.get(RoomType.class, type);
		return roomType;
	}
	
	public List<RoomType> getAll() {
		List<RoomType> roomTypes;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		roomTypes = session.createQuery("from RoomType", RoomType.class).getResultList();
		return roomTypes;
	}
}