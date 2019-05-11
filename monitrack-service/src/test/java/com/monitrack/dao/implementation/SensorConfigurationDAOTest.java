package com.monitrack.dao.implementation;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorSensitivity;
import com.monitrack.enumeration.SensorType;
import com.monitrack.util.JsonUtil;

public class SensorConfigurationDAOTest {
	
	@Before
	public void init() {
		DataSource.startConnectionPool();
	}

	@Test
	public void testSelect() throws Exception {
		Connection connection = DataSource.getConnection();
		@SuppressWarnings("unchecked")
		SensorConfiguration sensor = ((List<SensorConfiguration>)DAOFactory.execute(connection,
				SensorConfiguration.class, RequestType.SELECT, null, null, null, null)).get(0);
		System.out.println(sensor);
		sensor.setSensorActivity(SensorActivity.NOT_CONFIGURED);
		DAOFactory.execute(connection, sensor.getClass(), RequestType.UPDATE, sensor, null, null, null);
		DataSource.putConnection(connection);
	}
	
	@Test
	public void testInsert() throws Exception {
		Connection connection = DataSource.getConnection();
		SensorConfiguration s1 = new SensorConfiguration(0,0, SensorActivity.ENABLED, SensorType.FLOW, SensorSensitivity.HIGH,1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel",  0.0f, 5.0f, 6.23f, 4.94f);
		DAOFactory.execute(connection, SensorConfiguration.class, RequestType.INSERT, s1, null, null, null);
		DataSource.putConnection(connection);
	}

}
