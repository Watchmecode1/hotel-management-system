package com.hotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.hotel.dao.RoomDao;
import com.hotel.entity.Room;

public class RoomService {

	private final RoomDao roomDao = new RoomDao();
	
	public void saveRoom(Room room) {
		roomDao.saveRoom(room);
	}
	
	public void deleteRoom(Room room) {
		roomDao.deleteRoom(room);
	}
	
	public Room getByNumber(int number) {
		return roomDao.getByNumber(number);
	}
	
	public List<Room> getAll() {
		return roomDao.getAll();
	}
	
	public Set<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
		return roomDao.findAvailableRooms(startDate, endDate);
	}
}