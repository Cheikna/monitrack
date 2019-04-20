package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.entity.Flow;
import com.monitrack.enumeration.SensorActivity;

public class FlowDAO extends SensorDAO<Flow>{
	
	private static final Logger log = LoggerFactory.getLogger(FlowDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();
	private final static String TABLE_NAME = "FLOW";

	public FlowDAO(Connection connection) {
		super(connection, TABLE_NAME);
	}

	@Override
	public Flow create(Flow obj) {
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
						obj.setFlowId(lastCreatedId);
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
	public void update(Flow obj) {
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
	public List<Flow> find(List<String> fields, List<String> values) {
		return (List<Flow>)super.find(fields, values);
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
