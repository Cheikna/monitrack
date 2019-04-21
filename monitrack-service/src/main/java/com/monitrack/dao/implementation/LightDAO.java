package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Light;
import com.monitrack.entity.Sensor;

public class LightDAO extends SensorDAO<Light>{
	
	private static final Logger log = LoggerFactory.getLogger(LightDAO.class);	
	private final Object lock = new Object();
	private final static String TABLE_NAME = "LIGHT";

	public LightDAO(Connection connection) {
		super(connection, TABLE_NAME);
	}

	public Light create(Sensor sensor) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null && sensor instanceof Light) {
				Light obj = (Light)sensor;
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("", Statement.RETURN_GENERATED_KEYS);
					//FIXME
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						obj.setLightId(lastCreatedId);
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of a location : " + e.getMessage());
					e.printStackTrace();
				}
				return obj;
			}
			return (Light)sensor;
		}
	}

	@Override
	protected Light getSensorFromResultSet(ResultSet rs) {
		Light light = null;
		return light;
	}

}
