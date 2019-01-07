package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.connectionPool.implementation.DataSource;
import com.monitrack.dao.interfaces.IPersonDAO;
import com.monitrack.entity.Person;

public class PersonDAO implements IPersonDAO {

	private final Logger slf4jLogger = LoggerFactory.getLogger(PersonDAO.class);

	private Connection connection = DataSource.getConnection();

	public PersonDAO() {

	}

	public void create(Person person) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO PERSON (NAME, CREATION_DATE) VALUES (? , ?)");
			preparedStatement.setString(1, person.getNamePerson());
			preparedStatement.setDate(2, person.getCreateDate());
			preparedStatement.execute();
		} catch (Exception e) {
			Date d = new Date();
			slf4jLogger.error("[" + d + "] Erreur lors de la création d'un utilisateur" + e.getMessage());
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
				person = new Person(rs.getInt("id"), rs.getString("name"), rs.getDate("creation_date"));
				persons.add(person);
			}
		} catch (Exception e) {
			
			Date d = new Date();
			slf4jLogger.error("[" + d + "] Erreur lors du chargement des utilisateurs" + e.getMessage());
			e.printStackTrace();
		}
		return persons;
	}

}
