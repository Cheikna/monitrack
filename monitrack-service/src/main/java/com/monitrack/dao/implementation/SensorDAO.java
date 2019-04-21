package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Sensor;
import com.monitrack.entity.Location;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class SensorDAO<T> extends DAO<T> {
	
	private static final Logger log = LoggerFactory.getLogger(SensorDAO.class);
	private final Object lock = new Object();	
	private static String MOTHER_TABLE_NAME = "SENSOR";
	private String tableName;

	public SensorDAO(Connection connection, String tableName) {
		super(connection);
		this.tableName = tableName;
	}

	public SensorDAO(Connection connection) {
		super(connection);
		this.tableName = MOTHER_TABLE_NAME;
	}

	@Override
	public T create(T obj) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (obj instanceof Sensor && connection != null) {
				Sensor sensor = (Sensor)obj;
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("INSERT INTO ? (TYPE, ACTIVITY, ID_LOCATION, IP_ADDRESS, MAC_ADDRESS, "
															+ "SERIAL_NUMBER, HARDWARE_VERSION, SOFTWARE_VERSION, "
															+ " START_ACTIVITY_TIME, END_ACTIVITY_TIME, CHECK_FREQUENCY, "
															+ "MEASUREMENT_UNIT, CURRENT_DANGER_THRESHOLD, POSITION_X, POSITION_Y) "
															+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, MOTHER_TABLE_NAME);
					preparedStatement.setString(2, sensor.getSensorType().toString());
					preparedStatement.setString(3, sensor.getSensorActivity().toString());
					preparedStatement.setInt(4, sensor.getLocationId());
					preparedStatement.setString(5, sensor.getIpAddress());
					preparedStatement.setString(6, sensor.getMacAddress());
					preparedStatement.setString(7, sensor.getSerialNumber());
					preparedStatement.setFloat(8, sensor.getHardwareVersion());
					preparedStatement.setFloat(9, sensor.getSoftwareVersion());
					preparedStatement.setTime(10, sensor.getBeginTime());
					preparedStatement.setTime(11, sensor.getEndTime());
					preparedStatement.setFloat(12, sensor.getCheckFrequency());
					preparedStatement.setString(13, sensor.getMeasurementUnit());
					preparedStatement.setFloat(14, sensor.getDangerThreshold());
					preparedStatement.setFloat(15, sensor.getPositionX());
					preparedStatement.setFloat(16, sensor.getPositionY());
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						sensor.setId(lastCreatedId);
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of a location : " + e.getMessage());
					e.printStackTrace();
				}
				return (T)sensor;
			}
			return obj;
		}
	}

	@Override
	public void update(T obj) {
		// FIXME Auto-generated method stub
		
	}
	
	@Override
	public void delete(T obj) {
		if(obj instanceof Sensor && connection != null) {
			synchronized (lock) {
				try {
					Sensor sensor = (Sensor)obj;
					PreparedStatement preparedStatement = null;
					preparedStatement = connection.prepareStatement("DELETE FROM SENSOR where ID_SENSOR=(?)");
					preparedStatement.setInt(1, sensor.getId());					
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the delete of a location : " + e.getMessage());
					e.printStackTrace();
				}				
			}
			
		}		
	}

	@SuppressWarnings("unchecked")
	public List<T> find(List<String> fields, List<String> values) {
		synchronized (lock) {
			String sql = "";
			if(!tableName.equalsIgnoreCase(MOTHER_TABLE_NAME))
				sql = "select * from " + tableName +" first_table inner join SENSOR second_table on first_table.ID_SENSOR = second_table.ID_SENSOR";
			else
				sql = "select * from " + MOTHER_TABLE_NAME;
			
			List<T> sensors = new ArrayList<T>();
			sql += super.getRequestFilters(fields, values);
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					Sensor sensor;
					while (rs.next()) {
						sensor = getSensorFromResultSet(rs);
						if (sensor != null) {
							sensors.add((T) sensor);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding sensors : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return sensors;
		}
	}
	
	@SuppressWarnings("finally")
	protected Sensor getSensorFromResultSet(ResultSet rs) {
		Sensor sensor = null;
		try {
			sensor = new Sensor(rs.getInt("ID_SENSOR"), SensorActivity.getSensorActivity(rs.getString("ACTIVITY")), SensorType.getSensorType(rs.getString("TYPE")),
					rs.getInt("ID_LOCATION"), rs.getString("IP_ADDRESS"),
					rs.getString("MAC_ADDRESS"),rs.getString("SERIAL_NUMBER"), rs.getFloat("HARDWARE_VERSION"),rs.getFloat("SOFTWARE_VERSION"),
					rs.getTimestamp("CREATION_DATE"), rs.getTimestamp("LAST_MESSAGE_DATE"), rs.getTimestamp("LAST_CONFIGURATION_DATE"),
					rs.getTime("START_ACTIVITY_TIME"),rs.getTime("END_ACTIVITY_TIME"),rs.getFloat("CHECK_FREQUENCY"),
					rs.getString("MEASUREMENT_UNIT"),rs.getFloat("CURRENT_DANGER_THRESHOLD"),rs.getFloat("POSITION_X"),rs.getFloat("POSITION_Y"));
			
			sensor.setLocation(getSensorLocation(rs.getInt("ID_LOCATION")));
			
			
		} catch (SQLException e) {
			log.error("An error occurred when getting one Flow Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return sensor;
		}
	}
	
	protected Location getSensorLocation(Integer sensorId) {
		LocationDAO locationDAO = new LocationDAO(connection);
		Location location = locationDAO.find(Arrays.asList("ID_LOCATION"), Arrays.asList(sensorId.toString())).get(0);
		return location;
	}

}
