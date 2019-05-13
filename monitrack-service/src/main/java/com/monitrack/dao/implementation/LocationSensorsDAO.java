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
import com.monitrack.entity.Location;
import com.monitrack.entity.LocationSensors;

public class LocationSensorsDAO extends DAO<LocationSensors> {

	private static final Logger log = LoggerFactory.getLogger(LocationSensorsDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();

	public LocationSensorsDAO(Connection connection) 
	{
		super(connection);
	}

	public LocationSensors create(LocationSensors locationSensors) {

		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("INSERT INTO location_sensors (ID_LOCATION, ID_SENSOR)"
									+ " VALUES (? , ? )", Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, locationSensors.getIdLocation());
					preparedStatement.setInt(2, locationSensors.getIdSensor());

					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						locationSensors.setIdLocation(lastCreatedId);
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of a location : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return locationSensors;
		}

	}

	@Override
	public void delete(LocationSensors obj){

		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = null;
					preparedStatement = connection.prepareStatement("DELETE FROM location_sensors where ID_LOCATION=(?)");
					preparedStatement.setInt(1, obj.getIdLocation());					
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the delete of a location : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void update(LocationSensors locationSensors) {
		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(
							"UPDATE location_sensors SET ID_LOCATION = ?, ID_SENSOR = ?"
							+ " WHERE ID_LOCATION = ?");
					preparedStatement.setInt(1, locationSensors.getIdLocation());
					preparedStatement.setInt(2, locationSensors.getIdSensor());

					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the update of a location : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("finally")
	private LocationSensors getLocationFromResultSet(ResultSet rs)
	{
		LocationSensors location = null;
		try {
			location = new LocationSensors(rs.getInt("ID_LOCATION"), rs.getInt("ID_SENSOR"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Person from the resultSet : " + e.getMessage());
		}
		finally {
			return location;
		}
	}

	@Override
	public List<LocationSensors> find(List<String> fields, List<String> values) {
		synchronized (lock) {
			List<LocationSensors> locationSensors = new ArrayList<>();
			if (connection != null) {
				try {
					String sql = "SELECT * FROM location_sensors" + super.getRequestFilters(fields, values);
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					LocationSensors location;
					while (rs.next()) {
						location = getLocationFromResultSet(rs);
						if (location != null) {
							locationSensors.add(location);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding all of the persons : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return locationSensors;
		}
	}

}
