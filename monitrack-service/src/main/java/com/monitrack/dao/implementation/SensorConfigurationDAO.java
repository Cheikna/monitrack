package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.entity.Location;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class SensorConfigurationDAO extends DAO<SensorConfiguration> {

	private static final Logger log = LoggerFactory.getLogger(SensorConfigurationDAO.class);

	public SensorConfigurationDAO(Connection connection) {
		super(connection, "SENSOR_CONFIGURATION_HISTORY");
	}

	@Override
	public SensorConfiguration create(SensorConfiguration obj) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("INSERT INTO " + tableName + " (TYPE, ACTIVITY, ID_LOCATION, IP_ADDRESS, MAC_ADDRESS, "
									+ "SERIAL_NUMBER, HARDWARE_VERSION, SOFTWARE_VERSION, "
									+ " START_ACTIVITY_TIME, END_ACTIVITY_TIME, CHECK_FREQUENCY, "
									+ "MEASUREMENT_UNIT, CURRENT_THRESHOLD, MIN_DANGER_THRESHOLD,"
									+ "MAX_DANGER_THRESHOLD, POSITION_X, POSITION_Y) "
									+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, obj.getSensorType().toString());
					preparedStatement.setString(2, obj.getSensorActivity().toString());
					preparedStatement.setInt(3, obj.getLocationId());
					preparedStatement.setString(4, obj.getIpAddress());
					preparedStatement.setString(5, obj.getMacAddress());
					preparedStatement.setString(6, obj.getSerialNumber());
					preparedStatement.setFloat(7, obj.getHardwareVersion());
					preparedStatement.setFloat(8, obj.getSoftwareVersion());
					preparedStatement.setTime(9, obj.getBeginTime());
					preparedStatement.setTime(10, obj.getEndTime());
					preparedStatement.setFloat(11, obj.getCheckFrequency());
					preparedStatement.setString(12, obj.getMeasurementUnit());
					preparedStatement.setFloat(13, obj.getCurrentThreshold());
					preparedStatement.setFloat(14, obj.getMinDangerThreshold());
					preparedStatement.setFloat(15, obj.getMaxDangerThreshold());
					preparedStatement.setFloat(16, obj.getPositionX());
					preparedStatement.setFloat(17, obj.getPositionY());
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					if (rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
						obj.setMacAddress(retrieveMacAddress(id));
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of a sensor : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return obj;
		}
	}

	@Override
	public void update(SensorConfiguration obj) {
		// FIXME Auto-generated method stub

	}

	@Override
	public void delete(SensorConfiguration obj) {
		synchronized (lock) {
			try {
				PreparedStatement preparedStatement = null;
				preparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " where ID_SENSOR=(?)");
				preparedStatement.setInt(1, obj.getId());					
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the delete of a location : " + e.getMessage());
				e.printStackTrace();
			}				
		}

	}

	private Location getSensorLocation(Integer sensorLocationId) {
		LocationDAO locationDAO = new LocationDAO(connection);
		Location location = locationDAO.find(Arrays.asList("ID_LOCATION"), Arrays.asList(sensorLocationId.toString())).get(0);
		return location;
	}
	
	private String retrieveMacAddress(int sensorId) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAC_ADDRESS FROM SENSOR WHERE ID_SENSOR = ? ");
		preparedStatement.setInt(1, sensorId);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.first()) {
			return rs.getString(1);
		}
		return null;
	}
	
	@SuppressWarnings("finally")
	@Override
	protected SensorConfiguration getSingleValueFromResultSet(ResultSet rs) {
		SensorConfiguration sensorConfiguration = null;
		try {
			sensorConfiguration = new SensorConfiguration(rs.getInt("ID_SENSOR_CONFIGURATION"), rs.getInt("ID_SENSOR"), SensorActivity.getSensorActivity(rs.getString("ACTIVITY")), SensorType.getSensorType(rs.getString("TYPE")),
					rs.getInt("ID_LOCATION"), rs.getString("IP_ADDRESS"),
					rs.getString("MAC_ADDRESS"),rs.getString("SERIAL_NUMBER"), rs.getFloat("HARDWARE_VERSION"),rs.getFloat("SOFTWARE_VERSION"),
					rs.getTimestamp("CREATION_DATE"), rs.getTimestamp("LAST_MESSAGE_DATE"), rs.getTimestamp("LAST_CONFIGURATION_DATE"),
					rs.getTime("START_ACTIVITY_TIME"),rs.getTime("END_ACTIVITY_TIME"),rs.getFloat("CHECK_FREQUENCY"),
					rs.getString("MEASUREMENT_UNIT"),rs.getFloat("CURRENT_THRESHOLD"),rs.getFloat("MIN_DANGER_THRESHOLD")
					,rs.getFloat("MAX_DANGER_THRESHOLD"),rs.getFloat("POSITION_X"),rs.getFloat("POSITION_Y"));

			sensorConfiguration.setLocation(getSensorLocation(rs.getInt("ID_LOCATION")));


		} catch (SQLException e) {
			log.error("An error occurred when getting one Flow Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return sensorConfiguration;
		}
	}


}
