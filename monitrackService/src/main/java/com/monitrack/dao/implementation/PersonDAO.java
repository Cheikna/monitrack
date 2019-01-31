package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.connectionPool.implementation.DataSource;
import com.monitrack.dao.interfaces.IPersonDAO;
import com.monitrack.entity.Person;

public class PersonDAO implements IPersonDAO {

	private final Logger log = LoggerFactory.getLogger(PersonDAO.class);

	private Connection connection = DataSource.getConnection();

	public PersonDAO() {

	}

	public void create(Person person) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO PERSON (NAME, CREATION_DATE) VALUES (? , ?)");
			preparedStatement.setString(1, person.getNamePerson());
			preparedStatement.setTimestamp(2, person.getCreationDate());
			preparedStatement.execute();
		} catch (Exception e) {
			log.error("An error occurred durring the creation of a person : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
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
