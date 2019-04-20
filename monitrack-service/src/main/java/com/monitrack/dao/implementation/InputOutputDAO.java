package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.InputOutput;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class InputOutputDAO extends SensorDAO<InputOutput>{
	
	private static final Logger log = LoggerFactory.getLogger(InputOutputDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();
	private final static String TABLE_NAME = "INPUT_OUTPUT";

	public InputOutputDAO(Connection connection) {
		super(connection, TABLE_NAME);
	}

	@Override
	public InputOutput create(InputOutput obj) {
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
						obj.setInputOutputId(lastCreatedId);
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
	public void update(InputOutput obj) {
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
	public List<InputOutput> find(List<String> fields, List<String> values) {
		return (List<InputOutput>)super.find(fields, values);
	}

	@Override
	protected InputOutput getSensorFromResultSet(ResultSet rs) {
		InputOutput inputOutput = null;
		return inputOutput;
	}

}
