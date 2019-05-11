package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.AccessControlHistory;

public class AccessControlHistoryDAO extends DAO<AccessControlHistory> {

	private static final Logger log = LoggerFactory.getLogger(AccessControlHistoryDAO.class);
	
	public AccessControlHistoryDAO(Connection connection) {
		super(connection, "ACCESS_CONTROL_HISTORY");
	}

	@Override
	public AccessControlHistory create(AccessControlHistory obj) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("INSERT INTO " + tableName + " (ID_SENSOR_CONFIGURATION, ID_LOCATION, CODE_ENTERED, PERSON_INFORMATION, TRIGGERING_DATE, ACCESS_GRANTED)"
									+ " VALUES (? , ? , ? , ? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, obj.getSensorId());
					preparedStatement.setFloat(2, obj.getLocationId());
					preparedStatement.setString(3, obj.getCodeEntered());
					preparedStatement.setString(4, obj.getPersonInformation());
					preparedStatement.setTimestamp(5, obj.getTriggeringDate());
					preparedStatement.setInt(6, obj.accessGranted());
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						obj.setHistoryId(lastCreatedId);
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
	public void update(AccessControlHistory obj) {
		try {
			throw new Exception("A history from the access control can not be updated !");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

	@Override
	public void delete(AccessControlHistory obj) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = null;
					preparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " where ID_HISTORY=?");
					preparedStatement.setInt(1, obj.getHistoryId());					
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the delete of a location : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}	
		
	}

	@SuppressWarnings("finally")
	@Override
	protected AccessControlHistory getSingleValueFromResultSet(ResultSet rs) {
		AccessControlHistory accessControlHistory = null;
		try {
			accessControlHistory = new AccessControlHistory(rs.getInt("ID_HISTORY"), rs.getInt("ID_SENSOR_CONFIGURATION"), rs.getInt("ID_LOCATION"),
					rs.getString("CODE_ENTERED"), rs.getString("PERSON_INFORMATION"), rs.getTimestamp("TRIGGERING_DATE"), (rs.getInt("ACCESS_GRANTED") == 1));


		} catch (SQLException e) {
			log.error("An error occurred when getting one history from the access control from the resultSet : " + e.getMessage());
		}
		finally {
			return accessControlHistory;
		}
	}

}
