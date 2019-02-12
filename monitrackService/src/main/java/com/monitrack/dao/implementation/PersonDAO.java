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
import com.monitrack.connectionpool.implementation.DataSource;
import com.monitrack.dao.interfaces.IPersonDAO;
import com.monitrack.entity.Person;

public class PersonDAO implements IPersonDAO {

	private final Logger log = LoggerFactory.getLogger(PersonDAO.class);	
	
	public PersonDAO() {}

	public void create(Person person) {
		// Retrieves a connection from the connectionPool and allows us to use it
		Connection connection = DataSource.getConnection();

		// Checks if the connection is not null before using it
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO PERSON (NAME, CREATION_DATE) VALUES (? , ?)");
				preparedStatement.setString(1, person.getNamePerson());
				preparedStatement.setTimestamp(2, person.getCreationDate());
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the creation of a person : " + e.getMessage());
				e.printStackTrace();
			}
			
			// Puts the connection in the connection Pool because we've finished with it and we do not want to waste it
			DataSource.putConnection(connection);
		}		
		
	}
	
	public void delete(int personId){
		// Retrieves a connection from the connectionPool and allows us to use it
				Connection connection = DataSource.getConnection();

				// Checks if the connection is not null before using it
				if(connection != null)
				{
					try {
						PreparedStatement preparedStatement = connection
								.prepareStatement("DELETE FROM PERSON where id=(?)");
						preparedStatement.setInt(1, personId);
						preparedStatement.execute();
					} catch (Exception e) {
						log.error("An error occurred during the creation of a person : " + e.getMessage());
						e.printStackTrace();
					    JOptionPane.showMessageDialog(null, "La suppression n'a pas été effectuée");
					}
					// Puts the connection in the connection Pool because we've finished with it and we do not want to waste it
					DataSource.putConnection(connection);
				}		
	}
	
	public void update(Person person) {
		// Retrieves a connection from the connectionPool and allows us to use it
		Connection connection = DataSource.getConnection();

		// Checks if the connection is not null before using it
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE PERSON SET NAME = ? WHERE id =" + person.getIdPerson());
				preparedStatement.setString(1, person.getNamePerson());
				preparedStatement.execute();
			} catch (Exception e) {
				log.error("An error occurred during the creation of a person : " + e.getMessage());
				e.printStackTrace();
			    JOptionPane.showMessageDialog(null, "La modification n'a pas été effectuée");
			}
			
			// Puts the connection in the connection Pool because we've finished with it and we do not want to waste it
			DataSource.putConnection(connection);
		}
		
	}
	
	

	public List<Person> findAll() {
		// Retrieves a connection from the connectionPool and allows us to use it
		Connection connection = DataSource.getConnection();
		List<Person> persons = new ArrayList<Person>();
		
		if(connection != null)
		{
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON");
				ResultSet rs = preparedStatement.executeQuery();
				Person person;
				while (rs.next()) {
					person = getPersonFromResultSet(rs);
					if(person != null)
					{
						persons.add(person);
					}
				}
			} catch (Exception e) {
				log.error("An error occurred when finding all of the persons : " + e.getMessage());
				e.printStackTrace();
			}
			// Puts the connection in the connection Pool because we've finished with it and we do not want to waste it
			DataSource.putConnection(connection);
		}
		
		return persons;
	}
	
	@SuppressWarnings("finally")
	private Person getPersonFromResultSet(ResultSet rs)
	{
		Person person = null;
		try {
			person = new Person(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("creation_date"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Person from the resultSet : " + e.getMessage());
		}
		finally {
			return person;
		}
	}

}
