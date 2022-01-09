package com.hotel.service;

import java.util.List;

import com.hotel.dao.RoomTypeDao;
import com.hotel.entity.RoomType;
import com.hotel.entity.RoomType.Type;

public class RoomTypeService {
	
	private final RoomTypeDao roomTypeDao = new RoomTypeDao();

	public void saveRoomType(RoomType roomType) {
		roomTypeDao.saveRoomType(roomType);
	}
	
	public RoomType getById(Type type) {
		return roomTypeDao.getById(type);
	}
	
	public List<RoomType> getAll() {
		return roomTypeDao.getAll();
	}
}