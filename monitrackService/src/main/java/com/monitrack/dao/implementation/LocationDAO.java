package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.monitrack.connectionpool.implementation.DataSource;
import com.monitrack.dao.interfaces.ILocationDAO;
import com.monitrack.entity.Location;

public class LocationDAO implements ILocationDAO {

	private final Logger log = LoggerFactory.getLogger(LocationDAO.class);	
	JsonFactory factory = new JsonFactory();

	public LocationDAO() {}

	public void create(Location location) {
		// Retrieves a connection from the connectionPool and allows us to use it
		Connection connection = DataSource.getConnection();

		// Checks if the connection is not null before using it
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO location (NAME, CENTER, ID_SENSOR, CREATION_DATE)"
								+ " VALUES (? , ? , ? , ? )");
				preparedStatement.setString(1, location.getNameLocation());
				preparedStatement.setString(2, location.getCenter());
				preparedStatement.setInt(3, location.getIdLocation());
				preparedStatement.setTimestamp(4, location.getCreationDate());
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the creation of a location : " + e.getMessage());
				e.printStackTrace();
			}

			// Puts the connection in the connection Pool because we've finished with it and we do not want to waste it
			DataSource.putConnection(connection);
		}		

	}

	public void delete(int locationId){
		// Retrieves a connection from the connectionPool and allows us to use it
		Connection connection = DataSource.getConnection();

		// Checks if the connection is not null before using it
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM location where ID_LOCATION=(?)");
				preparedStatement.setInt(1, locationId);
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the delete of a location : " + e.getMessage());
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "La suppression n'a pas été effectuée");
			}
			// Puts the connection in the connection Pool because we've finished with it and we do not want to waste it
			DataSource.putConnection(connection);
		}		
	}

	public void update(Location location) {
		// Retrieves a connection from the connectionPool and allows us to use it
		Connection connection = DataSource.getConnection();

		// Checks if the connection is not null before using it
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE location SET NAME = ? WHERE ID_LOCATION =" + location.getIdLocation());
				preparedStatement.setString(1, location.getNameLocation());
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the update of a location : " + e.getMessage());
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "La modification n'a pas été effectuée");
			}

			// Puts the connection in the connection Pool because we've finished with it and we do not want to waste it
			DataSource.putConnection(connection);
		}

	}


	public List<Location> findAll() {
		// Retrieves a connection from the connectionPool and allows us to use it
		Connection connection = DataSource.getConnection();
		List<Location> locations = new ArrayList<Location>();

		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM location");
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
			// Puts the connection in the connection Pool because we've finished with it and we do not want to waste it
			DataSource.putConnection(connection);
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

}
