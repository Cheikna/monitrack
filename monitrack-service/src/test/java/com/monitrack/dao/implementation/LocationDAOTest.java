package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.entity.Location;
import com.monitrack.enumeration.RequestType;

public class LocationDAOTest {

	@Before
	public void init() {
		DataSource.startConnectionPool();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		System.out.println("LocationDAO === SELECT");
		Connection connection = DataSource.getConnection();
		List<Location> locations = null;
		try {
			locations = (List<Location>)DAOFactory.execute(connection, Location.class, RequestType.SELECT, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		DataSource.putConnection(connection);
		for(Location location : locations) {
			System.out.println(location);
		}
	}

}
