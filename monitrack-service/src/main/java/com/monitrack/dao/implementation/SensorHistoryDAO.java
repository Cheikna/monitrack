package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.SensorHistory;

public class SensorHistoryDAO extends DAO<SensorHistory> {
	
	private static final Logger log = LoggerFactory.getLogger(SensorHistoryDAO.class);
	private final Object lock = new Object();

	public SensorHistoryDAO(Connection connection) {
		super(connection);
	}

	@Override
	public SensorHistory create(SensorHistory obj) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("INSERT INTO SENSOR_HISTORY (ID_SENSOR, MEASURED_THRESHOLD, MIN_DANGER_THRESHOLD, MAX_DANGER_THRESHOLD, MEASUREMENT_DATE, DESCRIPTION, ACTIONS_DONE)"
									+ " VALUES (? , ? , ? , ? , ? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, obj.getIdSensorSource());
					preparedStatement.setFloat(2, obj.getMeasuredThreshold());
					preparedStatement.setFloat(3, obj.getMinDangerThreshlod());
					preparedStatement.setFloat(4, obj.getMaxDangerThreshlod());
					preparedStatement.setTimestamp(5, obj.getDate());
					preparedStatement.setString(6, obj.getDescription());
					preparedStatement.setString(7, obj.getActionsDone());
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						obj.setIdHistory(lastCreatedId);
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
	public void update(SensorHistory obj) {
		try {
			throw new Exception("A history can not be updated !");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void delete(SensorHistory obj) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = null;
					preparedStatement = connection.prepareStatement("DELETE FROM SENSOR_HISTORY where ID_LOCATION=(?)");
					preparedStatement.setInt(1, obj.getIdHistory());					
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the delete of a location : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}	
		
	}

	@Override
	public List<SensorHistory> find(List<String> fields, List<String> values) {
		return super.find(fields, values, "SENSOR_HISTORY");
	}

	@SuppressWarnings("finally")
	@Override
	protected SensorHistory getSingleValueFromResultSet(ResultSet rs) {
		SensorHistory sensorHistory = null;
		try {
			sensorHistory = new SensorHistory(rs.getInt("ID_HISTORY"), rs.getInt("ID_SENSOR"), rs.getFloat("MEASURED_THRESHOLD"), rs.getFloat("MIN_DANGER_THRESHOLD"),rs.getFloat("MAX_DANGER_THRESHOLD"),
					rs.getTimestamp("MEASUREMENT_DATE"), rs.getString("DESCRIPTION"), rs.getString("ACTIONS_DONE"));


		} catch (SQLException e) {
			log.error("An error occurred when getting one Flow Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return sensorHistory;
		}
	}

}
