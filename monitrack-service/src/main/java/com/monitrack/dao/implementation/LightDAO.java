package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Light;

public class LightDAO extends SensorDAO<Light>{
	
	private static final Logger log = LoggerFactory.getLogger(LightDAO.class);	
	private final Object lock = new Object();
	private final static String TABLE_NAME = "LIGHT";

	public LightDAO(Connection connection) {
		super(connection, TABLE_NAME);
	}

	@Override
	public Light create(Light obj) {
		int id = super.createSensor(obj);
		obj.setId(id);
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
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
			}
			return obj;
		}
	}

	@Override
	public void update(Light obj) {
		super.updateSensor(obj);
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("");
					//FIXME
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the update of a location : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public List<Light> find(List<String> fields, List<String> values) {
		return (List<Light>)super.find(fields, values);
	}
	
	@Override
	protected Light getSensorFromResultSet(ResultSet rs) {
		Light light = null;
		return light;
	}

}
