package com.monitrack.connectionpool.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class DataSourceTest {

	private int numberOfConnections = 0;
	
	@Before
	public void initializeDataSource() throws Exception {
		DataSource.startConectionPool();
		numberOfConnections= DataSource.getRemaningConnections();
	}

	@Test
	public void remaningConnectionsDuringUse() {
		Connection connection =  DataSource.getConnection();
		if(connection == null){
		fail("The connection retrieved from the datasource is null");
		}else {
			int remainingConnection = DataSource.getRemaningConnections();
			assertEquals(numberOfConnections - 1 , remainingConnection);
			DataSource.putConnection(connection);
			remainingConnection = DataSource.getRemaningConnections();
			assertEquals(numberOfConnections, remainingConnection);
		}
	}

	
	
	
}
