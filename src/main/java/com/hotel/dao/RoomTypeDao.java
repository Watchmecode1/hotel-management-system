package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.entity.RoomType;
import com.hotel.entity.RoomType.Type;
import com.hotel.util.HibernateUtil;

public class RoomTypeDao {
	public void saveRoomType(RoomType roomType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(roomType);
		session.getTransaction().commit();
	}
	
	public RoomType getById(Type type) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		RoomType roomType = session.get(RoomType.class, type);
		session.getTransaction().commit();
		return roomType;
	}
	
	public List<RoomType> getAll() {
		List<RoomType> tipiCamere;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		tipiCamere = session.createQuery("from RoomType", RoomType.class).getResultList();
		session.getTransaction().commit();
		return tipiCamere;
	}
}