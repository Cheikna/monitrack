package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.monitrack.connectionPool.implementation.DataSource;
import com.monitrack.dao.interfaces.IPersonDAO;
import com.monitrack.entity.Person;

public class PersonDAO implements IPersonDAO {
	
	private Connection connection = DataSource.getConnection();
	
	public PersonDAO() {

	}

	public void create(Person person) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSON (NAME, CREATION_DATE) VALUES (? , ?)");
			preparedStatement.setString(1, person.getNamePerson()); 
			preparedStatement.setDate(2, person.getCreateDate());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Person> findAll() {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON");
			ResultSet rs = preparedStatement.executeQuery();
			List<Person> persons = new ArrayList<Person>();
			Person person;
			while (rs.next()) {
				person = new Person(rs.getInt("id"), rs.getString("name"), rs.getDate("creation_date"));
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
