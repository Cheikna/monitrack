package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Sensor;

public abstract class SensorDAO {
	private static final Logger log = LoggerFactory.getLogger(SensorDAO.class);
	
	public static Integer createSensor(Sensor obj, Connection connection) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO SENSOR (TYPE, MAC_ADDRESS,SERIAL_NUMBER ,HARDWARE_VERSION , "
							+ " SOFTWARE_VERSION) "
							+ "VALUES (?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, obj.getSensorType().name());
			preparedStatement.setString(2, obj.getMacAddress());
			preparedStatement.setString(3, obj.getSerialNumber());
			preparedStatement.setFloat(4, obj.getHardwareVersion());
			preparedStatement.setFloat(5, obj.getSoftwareVersion());
			preparedStatement.execute();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				return id;
			}
		} catch (Exception e) {
			log.error("An error occurred during the creation of a sensor : " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

}
