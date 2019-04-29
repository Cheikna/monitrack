package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.SensorShop;
import com.monitrack.enumeration.Energy;
import com.monitrack.enumeration.SensorType;

public class SensorShopDAO extends DAO<SensorShop> {

	public SensorShopDAO(Connection connection) {
		super(connection, "SENSOR_SHOP");
	}

	@Override
	public SensorShop create(SensorShop obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SensorShop obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SensorShop obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected SensorShop getSingleValueFromResultSet(ResultSet rs) {
		SensorShop sensorShop = null;
		try {
			sensorShop = new SensorShop(rs.getInt("ID_SENSOR_SHOP"), rs.getInt("ID_SENSOR"), rs.getString("MARK"), SensorType.valueOf(rs.getString("TYPE")),
					rs.getString("MAC_ADDRESS"), rs.getString("SERIAL_NUMBER"), rs.getFloat("HARDWARE_VERSION"), rs.getFloat("SOFTWARE_VERSION"), rs.getFloat("PURCHASE_COST"), 
					rs.getFloat("MAINTENANCE_COST"), Energy.getValueOf(rs.getString("ENERGY")), rs.getInt("LIFE_TIME"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sensorShop;
	}

}
