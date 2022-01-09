package com.hotel.service;

import java.util.List;

import com.hotel.dao.ConsumptionDao;
import com.hotel.entity.Consumption;

public class ConsumptionService {

	private final ConsumptionDao consumptionDao = new ConsumptionDao();
	
	public void saveConsumption(Consumption consumption) {
		consumptionDao.saveConsumption(consumption);
	}
	
	public void deleteConsumption(Consumption consumption) {
		consumptionDao.deleteConsumption(consumption);
	}
	
	public List<Consumption> getAll() {
		return consumptionDao.getAll();
	}
}