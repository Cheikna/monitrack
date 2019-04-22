package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Sensor;
import com.monitrack.entity.Smoke;
import com.monitrack.enumeration.SensorActivity;

public class SmokeDAO extends SensorDAO<Smoke>{
	
	private static final Logger log = LoggerFactory.getLogger(SmokeDAO.class);	
	private final Object lock = new Object();
	private final static String TABLE_NAME = "SMOKE";

	public SmokeDAO(Connection connection) {
		super(connection, TABLE_NAME);
	}

	public Smoke create(Sensor sensor) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null && sensor instanceof Smoke) {
				Smoke obj = (Smoke)sensor;
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("", Statement.RETURN_GENERATED_KEYS);
					//FIXME
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						obj.setIdSmoke(lastCreatedId);
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of a location : " + e.getMessage());
					e.printStackTrace();
				}
				return obj;
			}
			return (Smoke) sensor;
		}
	}

	@SuppressWarnings("finally")
	@Override
	protected Smoke getSensorFromResultSet(ResultSet rs) {
		Smoke smoke = null;
		try {
			smoke = new Smoke(rs.getInt("ID_SENSOR"), SensorActivity.getSensorActivity(rs.getString("ACTIVITY")),rs.getInt("ID_LOCATION"), rs.getString("IP_ADDRESS"),
					rs.getString("MAC_ADDRESS"),rs.getString("SERIAL_NUMBER"), rs.getFloat("HARDWARE_VERSION"),rs.getFloat("SOFTWARE_VERSION"),
					rs.getTimestamp("CREATION_DATE"), rs.getTimestamp("LAST_MESSAGE_DATE"), rs.getTimestamp("LAST_CONFIGURATION_DATE"),
					rs.getTime("START_ACTIVITY_TIME"),rs.getTime("END_ACTIVITY_TIME"),rs.getFloat("CHECK_FREQUENCY"),
					rs.getString("MEASUREMENT_UNIT"),rs.getFloat("CURRENT_THRESHOLD"), rs.getFloat("DANGER_THRESHOLD"),rs.getFloat("POSITION_X"),rs.getFloat("POSITION_Y"),
					rs.getInt("ID_SMOKE"), rs.getFloat("MEASURED_THRESHOLD"), rs.getFloat("SMOKE_DANGER_THRESHOLD"),
					rs.getTimestamp("MEASUREMENT_DATE"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Smoke Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return smoke;
		}
	}
}
