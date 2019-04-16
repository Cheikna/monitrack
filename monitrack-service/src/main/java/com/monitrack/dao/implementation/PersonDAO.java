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
import com.monitrack.entity.Person;
import com.monitrack.enumeration.UserProfile;

public class PersonDAO extends DAO<Person>{

	private static final Logger log = LoggerFactory.getLogger(PersonDAO.class);
	private final Object lock = new Object();

	public PersonDAO(Connection connection) 
	{
		super(connection);
	}

	public Person create(Person person) {

		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO PERSON (NAME, CREATION_DATE) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, person.getNamePerson());
					preparedStatement.setTimestamp(2, person.getCreationDate());
					preparedStatement.execute();
					ResultSet rs = preparedStatement.getGeneratedKeys();
					int lastCreatedId = 0;
					if (rs.next()) {
						lastCreatedId = rs.getInt(1);
						person.setIdPerson(lastCreatedId);
					}
				} catch (Exception e) {
					log.error("An error occurred during the creation of a person : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return person;
		}

	}

	@Override
	public void delete(Person obj){

		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = null;
					preparedStatement = connection.prepareStatement("DELETE FROM PERSON where id=(?)");
					preparedStatement.setInt(1, obj.getIdPerson());
					
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the creation of a person : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}		
	}

	public void update(Person person) {

		synchronized (lock) {
			// Checks if the connection is not null before using it
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("UPDATE PERSON SET NAME = ? WHERE id =" + person.getIdPerson());
					preparedStatement.setString(1, person.getNamePerson());
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the creation of a person : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}

	}

	public List<Person> find(List<String> fields, List<String> values)
	{
		synchronized (lock) {
			List<Person> persons = new ArrayList<Person>();
			String sql = "SELECT * FROM PERSON" + super.getRequestFilters(fields, values);
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					Person person;
					while (rs.next()) {
						person = getPersonFromResultSet(rs);
						if (person != null) {
							persons.add(person);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding the persons : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return persons;
		}
	}

	@SuppressWarnings("finally")
	private Person getPersonFromResultSet(ResultSet rs)
	{
		Person person = null;
		try {
			person = new Person(rs.getInt("id"),rs.getString("user_name"),rs.getString("password"),rs.getString("name"), UserProfile.getUserProfile("userProfile"), rs.getTimestamp("creation_date"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Person from the resultSet : " + e.getMessage());
		}
		finally {
			return person;
		}
	}

}