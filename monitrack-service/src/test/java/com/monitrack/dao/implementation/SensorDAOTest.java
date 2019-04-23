package com.monitrack.dao.implementation;

import java.sql.Connection;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.entity.Sensor;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SensorDAOTest {
	
	private static final Sensor sensor = new Sensor(0, SensorActivity.ENABLED, SensorType.FLOW, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
			1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel", 4.0f,0.0f, 5.0f, 6.23f, 4.94f);
	
	@Before
	public void init() {
		DataSource.startConnectionPool();
	}

	@Test
	public void test1Insert() throws Exception {
		Connection connection = DataSource.getConnection();
		for (int i = 0; i < 7; i++) {
			Sensor s = (Sensor) DAOFactory.execute(connection, Sensor.class, RequestType.INSERT, sensor, null, null);
			/*System.out.println(s);
			Flow flow = new Flow(s, 0, 1, null);
			Flow f = (Flow) DAOFactory.execute(connection, Flow.class, RequestType.INSERT, flow, null,
					null);
			System.out.println(f);*/
		}
		DataSource.putConnection(connection);
		
	}
	
	public void test2Select() throws Exception {
		/*Connection connection = DataSource.getConnection();
		
		List<Flow> flows = (List<Flow>) DAOFactory.execute(connection, Flow.class, RequestType.SELECT, null, null, null);
		for(Flow flow : flows) {
			System.out.println(flow);
		}
		
		DataSource.putConnection(connection);*/		
		/*Flow flow = new Flow(sensor, 0, 1, null);
		Message message = new Message(SensorState.DANGER, sensor);
		String str = JsonUtil.serializeObject(message, message.getClass(), "");
		System.out.println(JsonUtil.indentJsonOutput(str));*/
	}
}
