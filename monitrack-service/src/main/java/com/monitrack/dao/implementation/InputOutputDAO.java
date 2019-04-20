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
import com.monitrack.entity.InputOutput;
import com.monitrack.enumeration.SensorConfiguration;
import com.monitrack.enumeration.SensorType;

public class InputOutputDAO extends DAO<InputOutput>{
	
	private static final Logger log = LoggerFactory.getLogger(InputOutputDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();

	public InputOutputDAO(Connection connection) {
		super(connection);
	}

	@Override
	public InputOutput create(InputOutput obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(InputOutput obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(InputOutput obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InputOutput> find(List<String> fields, List<String> values) {
		synchronized (lock) {
			List<InputOutput> inputoutputs = new ArrayList<InputOutput>();
			String sql = "SELECT * FROM INPUTOUTPUT" + super.getRequestFilters(fields, values);
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					InputOutput inputoutput;
					while (rs.next()) {
						inputoutput = getInputOutPutSensorFromResultSet(rs);
						if (inputoutput != null) {
							inputoutputs.add(inputoutput);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding the flow's sensors : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return inputoutputs;
		}
	}
	
	@SuppressWarnings("finally")
	private InputOutput getInputOutPutSensorFromResultSet(ResultSet rs)
	{
		InputOutput inputoutput = null;
		try {
			inputoutput = new InputOutput(rs.getInt("id"),SensorConfiguration.getSensorConfiguration("configuration"),SensorType.getSensorType("type"),
					null, rs.getString("ip_address"),rs.getString("mac_address"),
					rs.getFloat("alert_treshold"), rs.getTimestamp("time_change"), rs.getTime("begin_time"),
					rs.getTime("end_time"),rs.getTimestamp("creation_date"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Flow Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return inputoutput;
		}
	}

}
