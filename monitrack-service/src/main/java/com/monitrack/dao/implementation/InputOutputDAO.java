package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.InputOutput;
import com.monitrack.entity.Sensor;

public class InputOutputDAO extends SensorDAO<InputOutput>{
	
	private static final Logger log = LoggerFactory.getLogger(InputOutputDAO.class);	
	private final Object lock = new Object();
	private final static String TABLE_NAME = "INPUT_OUTPUT";

	public InputOutputDAO(Connection connection) {
		super(connection, TABLE_NAME);
	}

	public InputOutput create(Sensor sensor) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null && sensor instanceof InputOutput) {
				InputOutput obj = (InputOutput)sensor;
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("", Statement.RETURN_GENERATED_KEYS);
					//FIXME
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						obj.setInputOutputId(lastCreatedId);
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of an input/output sensor : " + e.getMessage());
					e.printStackTrace();
				}
				return obj;
			}
			return (InputOutput)sensor;
		}
	}

	@Override
	protected InputOutput getSensorFromResultSet(ResultSet rs) {
		InputOutput inputOutput = null;
		//FIXME
		return inputOutput;
	}

}
