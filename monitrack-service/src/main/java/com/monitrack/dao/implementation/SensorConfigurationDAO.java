package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.entity.Location;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorSensitivity;
import com.monitrack.enumeration.SensorType;

public class SensorConfigurationDAO extends DAO<SensorConfiguration> {

	private static final Logger log = LoggerFactory.getLogger(SensorConfigurationDAO.class);
	
	private static final String TABLE_NAME = "SENSOR_CONFIGURATION";

	public SensorConfigurationDAO(Connection connection) {
		super(connection, TABLE_NAME);
	}

	@Override
	public SensorConfiguration create(SensorConfiguration obj) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					//Creates the elements in the sensor table so that we can have the foreign key
					obj.setSensorId(SensorDAO.createSensor(obj, connection));
					
					PreparedStatement preparedStatement = connection
							.prepareStatement("INSERT INTO " + tableName + " (ID_SENSOR, ACTIVITY, ID_LOCATION, IP_ADDRESS, "
									+ " START_ACTIVITY_TIME, END_ACTIVITY_TIME, CHECK_FREQUENCY, "
									+ "MEASUREMENT_UNIT, MIN_DANGER_THRESHOLD,"
									+ "MAX_DANGER_THRESHOLD, POSITION_X, POSITION_Y, SENSITIVITY) "
									+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, obj.getSensorId());
					preparedStatement.setString(2, obj.getSensorActivity().toString());
					Integer locationId = obj.getLocationId();
					if(locationId != null)
						preparedStatement.setInt(3, locationId);
					else
						preparedStatement.setNull(3, Types.INTEGER);
						
					preparedStatement.setString(4, obj.getIpAddress());
					Time time = obj.getBeginTime();
					if(time != null)
						preparedStatement.setTime(5, time);
					else
						preparedStatement.setNull(5, Types.TIME);

					time = obj.getEndTime();
					if(time != null)
						preparedStatement.setTime(6, time);
					else
						preparedStatement.setNull(6, Types.TIME);
					
					Float frequency = obj.getCheckFrequency();
					if(frequency != null) {
						preparedStatement.setFloat(7, frequency);
					}else {
						preparedStatement.setNull(7, Types.FLOAT);
					}
					
					preparedStatement.setString(8, obj.getMeasurementUnit());
					preparedStatement.setFloat(9, obj.getMinDangerThreshold());
					preparedStatement.setFloat(10, obj.getMaxDangerThreshold());
					preparedStatement.setFloat(11, obj.getPositionX());
					preparedStatement.setFloat(12, obj.getPositionY());
					preparedStatement.setString(13, obj.getSensorSensitivity().toString());
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					if (rs.next()) {
						int id = rs.getInt(1);
						obj.setSensorConfigurationId(id);
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of a sensor configuration : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return obj;
		}
	}

	@Override
	public void update(SensorConfiguration obj) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("UPDATE " + tableName + " SET ACTIVITY = ?, ID_LOCATION = ?, IP_ADDRESS = ?, " + 
									"START_ACTIVITY_TIME = ?, END_ACTIVITY_TIME = ?, CHECK_FREQUENCY = ?, " + 
									" MIN_DANGER_THRESHOLD = ?,"+ 
									"MAX_DANGER_THRESHOLD = ?, POSITION_X = ?, POSITION_Y = ? AND SENSITIVITY=? "
									+ " WHERE ID_SENSOR_CONFIGURATION = ?");
					preparedStatement.setString(1, obj.getSensorActivity().toString());
					preparedStatement.setInt(2, obj.getLocationId());
					preparedStatement.setString(3, obj.getIpAddress());
					preparedStatement.setTime(4, obj.getBeginTime());
					preparedStatement.setTime(5, obj.getEndTime());
					preparedStatement.setFloat(6, obj.getCheckFrequency());
					preparedStatement.setFloat(7, obj.getMinDangerThreshold());
					preparedStatement.setFloat(8, obj.getMaxDangerThreshold());
					preparedStatement.setFloat(9, obj.getPositionX());
					preparedStatement.setFloat(10, obj.getPositionY());
					preparedStatement.setString(11, obj.getSensorSensitivity().toString());
					preparedStatement.setInt(12, obj.getSensorConfigurationId());
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the creation of a sensor configuration : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(SensorConfiguration obj) {
		synchronized (lock) {
			try {
				PreparedStatement preparedStatement = null;
				preparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " where ID_SENSOR_CONFIGURATION=(?)");
				preparedStatement.setInt(1, obj.getSensorConfigurationId());					
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the delete of a sensor configuration : " + e.getMessage());
				e.printStackTrace();
			}				
		}

	}

	private Location getSensorLocation(Integer sensorLocationId) {
		LocationDAO locationDAO = new LocationDAO(connection);
		Location location = locationDAO.find(Arrays.asList("ID_LOCATION"), Arrays.asList(sensorLocationId.toString()), null).get(0);
		return location;
	}
	
	@SuppressWarnings("finally")
	@Override
	protected SensorConfiguration getSingleValueFromResultSet(ResultSet rs) {
		SensorConfiguration sensorConfiguration = null;
		try {
			sensorConfiguration = new SensorConfiguration(rs.getInt("ID_SENSOR_CONFIGURATION"), rs.getInt("ID_SENSOR"), SensorActivity.getSensorActivity(rs.getString("ACTIVITY")), SensorType.getSensorType(rs.getString("TYPE")),
					SensorSensitivity.valueOf(rs.getString("SENSITIVITY")), rs.getInt("ID_LOCATION"), rs.getString("IP_ADDRESS"),
					rs.getString("MAC_ADDRESS"),rs.getString("SERIAL_NUMBER"), rs.getFloat("HARDWARE_VERSION"),rs.getFloat("SOFTWARE_VERSION"),
					rs.getTimestamp("CREATION_DATE"), rs.getTimestamp("LAST_CONFIGURATION_DATE"),
					rs.getTime("START_ACTIVITY_TIME"),rs.getTime("END_ACTIVITY_TIME"),rs.getFloat("CHECK_FREQUENCY"),
					rs.getString("MEASUREMENT_UNIT"),rs.getFloat("MIN_DANGER_THRESHOLD")
					,rs.getFloat("MAX_DANGER_THRESHOLD"),rs.getFloat("POSITION_X"),rs.getFloat("POSITION_Y"));

			sensorConfiguration.setLocation(getSensorLocation(rs.getInt("ID_LOCATION")));


		} catch (SQLException e) {
			log.error("An error occurred when getting one Flow Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return sensorConfiguration;
		}
	}

	public static String getFinalTableName() {
		return TABLE_NAME;
	}

}
