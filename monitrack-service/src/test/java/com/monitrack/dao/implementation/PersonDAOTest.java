package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.entity.Person;
import com.monitrack.enumeration.RequestType;

public class PersonDAOTest {
	
	@Before
	public void init() {
		DataSource.startConnectionPool();
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void test(){
		System.out.println("PersonDAO === SELECT");
		Connection connection = DataSource.getConnection();
		List<Person> persons = null;
		try {
			persons = (List<Person>)DAOFactory.execute(connection, Person.class, RequestType.SELECT, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		DataSource.putConnection(connection);
		for(Person person : persons) {
			System.out.println(person);
		}
	}

}
