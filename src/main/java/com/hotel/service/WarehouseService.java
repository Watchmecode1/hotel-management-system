package com.hotel.service;

import com.hotel.dao.WarehouseDao;
import com.hotel.entity.Warehouse;

public class WarehouseService {

	private final WarehouseDao warehouseDao = new WarehouseDao();
	
	public void saveWarehouse(Warehouse warehouse) {
		warehouseDao.saveWarehouse(warehouse);
	}
	
	public void deleteWarehouse(Warehouse warehouse) {
		warehouseDao.deleteWarehouse(warehouse);
	}
}