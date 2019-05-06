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
import com.monitrack.util.JsonUtil;

public class SensorConfigurationDAOTest {
	
	@Before
	public void init() {
		DataSource.startConnectionPool();
	}

	@Test
	public void test() throws Exception {
		Connection connection = DataSource.getConnection();
		@SuppressWarnings("unchecked")
		SensorConfiguration sensor = ((List<SensorConfiguration>)DAOFactory.execute(connection,
				SensorConfiguration.class, RequestType.SELECT, null, null, null, null)).get(0);
		System.out.println(sensor);
		sensor.setSensorActivity(SensorActivity.NOT_CONFIGURED);
		DAOFactory.execute(connection, sensor.getClass(), RequestType.UPDATE, sensor, null, null, null);
		DataSource.putConnection(connection);
	}

}
