package com.monitrack.connectionpool.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.sql.Connection;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.util.Util;

public class DataSourceTest {

	private static final Logger log = LoggerFactory.getLogger(DataSourceTest.class);
	private int numberOfConnections = 0;
	
	@Before
	public void initializeDataSource() throws Exception {
		DataSource.startConnectionPool();
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
	
	@Test
	public void getTooMuchConnectionsFromThePool()
	{
		try
		{
			int numberOfAvailableConnections = DataSource.getRemaningConnections();
			int i = 1;
			int tooMuchConnections = numberOfAvailableConnections + 3;
			Connection connection = null;
			while(i < tooMuchConnections) {
				String order = i + "/" + numberOfAvailableConnections;
				// Number of loop if the datasource contains as much connections as the tooMuchConnections variables
				String maxLoop = "(loop n°" + i + "/" + tooMuchConnections + ").";
				log.info("Trying to access to the connection number " + order + " of the connection pool during the test " + maxLoop);
				connection = DataSource.getConnection();
				assertNotNull(connection);
				i++;
			}
		}
		catch(Exception e)
		{
			fail("Cannot read the number of connections ! The error type is : " + e.getClass().getCanonicalName());
		}
	}

	
	
	
}
