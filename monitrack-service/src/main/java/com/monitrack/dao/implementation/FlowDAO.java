package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.entity.Flow;
import com.monitrack.entity.Sensor;
import com.monitrack.enumeration.SensorActivity;

public class FlowDAO extends SensorDAO<Flow>{
	
	private static final Logger log = LoggerFactory.getLogger(FlowDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();
	private final static String TABLE_NAME = "FLOW";

	public FlowDAO(Connection connection) {
		super(connection, TABLE_NAME);
	}

	public Flow create(Sensor sensor) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null && sensor instanceof Flow) {
				Flow obj = (Flow)sensor;
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("INSERT INTO FLOW (ID_SENSOR, WAS_SOMEONE_DETECTED, MEASUREMENT_DATE) VALUES (?,?,?) ", Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, obj.getId());
					preparedStatement.setInt(2, obj.getDetected());
					preparedStatement.setTimestamp(3, obj.getMeasurementDate());
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						obj.setFlowId(lastCreatedId);
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of a flow sensor : " + e.getMessage());
					e.printStackTrace();
				}
				return obj;
			}
			return (Flow)sensor;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	protected Flow getSensorFromResultSet(ResultSet rs) {
		Flow flow = null;
		try {
			flow = new Flow(rs.getInt("ID_SENSOR"), SensorActivity.getSensorActivity(rs.getString("ACTIVITY")),rs.getInt("ID_LOCATION"), rs.getString("IP_ADDRESS"),
					rs.getString("MAC_ADDRESS"),rs.getString("SERIAL_NUMBER"), rs.getFloat("HARDWARE_VERSION"),rs.getFloat("SOFTWARE_VERSION"),
					rs.getTimestamp("CREATION_DATE"), rs.getTimestamp("LAST_MESSAGE_DATE"), rs.getTimestamp("LAST_CONFIGURATION_DATE"),
					rs.getTime("START_ACTIVITY_TIME"),rs.getTime("END_ACTIVITY_TIME"),rs.getFloat("CHECK_FREQUENCY"),
					rs.getString("MEASUREMENT_UNIT"),rs.getFloat("CURRENT_DANGER_THRESHOLD"),rs.getFloat("POSITION_X"),rs.getFloat("POSITION_Y"),
					rs.getInt("ID_FLOW"), rs.getInt("WAS_SOMEONE_DETECTED"), rs.getTimestamp("MEASUREMENT_DATE"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Flow Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return flow;
		}
	}

}
