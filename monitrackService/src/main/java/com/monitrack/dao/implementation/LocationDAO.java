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
import com.fasterxml.jackson.core.JsonFactory;
import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Location;

public class LocationDAO extends DAO<Location> {

	private final Logger log = LoggerFactory.getLogger(LocationDAO.class);	
	private JsonFactory factory = new JsonFactory();

	public LocationDAO(Connection connection) 
	{
		super(connection);
	}

	public Location create(Location location) {

		// Checks if the connection is not null before using it
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO LOCATION (NAME, CENTER, ID_SENSOR, CREATION_DATE)"
								+ " VALUES (? , ? , ? , ? )", Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, location.getNameLocation());
				preparedStatement.setString(2, location.getCenter());
				preparedStatement.setInt(3, location.getIdLocation());
				preparedStatement.setTimestamp(4, location.getCreationDate());
				preparedStatement.execute();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				int lastCreatedId = 0;
				if(rs.next()){
					lastCreatedId = rs.getInt(1);
					location.setIdLocation(lastCreatedId);
				}
			} catch (Exception e) {
				log.error("An error occurred during the creation of a location : " + e.getMessage());
				e.printStackTrace();
			}
		}	
		
		return location;

	}

	public void delete(int locationId){

		// Checks if the connection is not null before using it
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM LOCATION where ID_LOCATION=(?)");
				preparedStatement.setInt(1, locationId);
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the delete of a location : " + e.getMessage());
				e.printStackTrace();
			}
		}		
	}

	public void update(Location location) {
		// Checks if the connection is not null before using it
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE LOCATION SET NAME = ? WHERE ID_LOCATION =" + location.getIdLocation());
				preparedStatement.setString(1, location.getNameLocation());
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the update of a location : " + e.getMessage());
				e.printStackTrace();
			}
		}

	}


	public List<Location> findAll() {
		List<Location> locations = new ArrayList<Location>();

		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM LOCATION");
				ResultSet rs = preparedStatement.executeQuery();
				Location location;
				while (rs.next()) {
					location = getPersonFromResultSet(rs);
					if(location != null)
					{
						locations.add(location);
					}
				}
			} catch (Exception e) {
				log.error("An error occurred when finding all of the persons : " + e.getMessage());
				e.printStackTrace();
			}
		}

		return locations;
	}

	@SuppressWarnings("finally")
	private Location getPersonFromResultSet(ResultSet rs)
	{
		Location location = null;
		try {
			location = new Location(rs.getInt("ID_LOCATION"), rs.getString("NAME"), rs.getString("CENTER")
					, rs.getTimestamp("CREATION_DATE"), rs.getInt("ID_SENSOR"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Person from the resultSet : " + e.getMessage());
		}
		finally {
			return location;
		}
	}

	@Override
	public List<Location> find(List<String> fields, List<String> values) {
		List<Location> locations = new ArrayList<Location>();

		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM LOCATION");
				ResultSet rs = preparedStatement.executeQuery();
				Location location;
				while (rs.next()) {
					location = getPersonFromResultSet(rs);
					if(location != null)
					{
						locations.add(location);
					}
				}
			} catch (Exception e) {
				log.error("An error occurred when finding all of the persons : " + e.getMessage());
				e.printStackTrace();
			}
		}

		return locations;
	}

	@Override
	public void delete(Location obj) {
		delete(obj.getIdLocation());		
	}

}
