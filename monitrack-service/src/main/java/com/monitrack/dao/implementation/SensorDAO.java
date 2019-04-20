package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Flow;
import com.monitrack.entity.Sensor;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public abstract class SensorDAO<T> extends DAO<T> {
	
	private static final Logger log = LoggerFactory.getLogger(SensorDAO.class);
	private final Object lock = new Object();	
	private String tableName;

	public SensorDAO(Connection connection, String tableName) {
		super(connection);
		this.tableName = tableName;
	}

	public int createSensor(T obj) {
		//FIXME
		int id = 1;
		return id;
	}

	public void updateSensor(T obj) {
		//FIXME
		
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

	public List<T> find(List<String> fields, List<String> values) {
		synchronized (lock) {
			String sql = "select * from " + tableName +" first_table inner join SENSOR second_table on first_table.ID_SENSOR = second_table.ID_SENSOR";
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
	
	protected Sensor getSensorFromResultSet(ResultSet rs) {
		Sensor sensor = null;
		try {
			sensor = new Sensor(rs.getInt("ID_SENSOR"), SensorActivity.getSensorActivity(rs.getString("ACTIVITY")), SensorType.getSensorType(rs.getString("TYPE")),
					rs.getInt("ID_LOCATION"), rs.getString("IP_ADDRESS"),
					rs.getString("MAC_ADDRESS"),rs.getString("SERIAL_NUMBER"), rs.getFloat("HARDWARE_VERSION"),rs.getFloat("SOFTWARE_VERSION"),
					rs.getTimestamp("CREATION_DATE"), rs.getTimestamp("LAST_MESSAGE_DATE"), rs.getTimestamp("LAST_CONFIGURATION_DATE"),
					rs.getTime("START_ACTIVITY_TIME"),rs.getTime("END_ACTIVITY_TIME"),rs.getFloat("CHECK_FREQUENCY"),
					rs.getString("MEASUREMENT_UNIT"),rs.getFloat("CURRENT_DANGER_THRESHOLD"),rs.getFloat("POSITION_X"),rs.getFloat("POSITION_Y"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Flow Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return sensor;
		}
		
	}

}
