package com.monitrack.socket.client;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorState;
import com.monitrack.exception.DeprecatedVersionException;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;
import com.monitrack.util.Util;

public class RetrievedDataFromDataBaseTime {

	/***
	 * The state (danger, missing,..) is not represented in the database.
	 * However we retrieve the different state from the cache
	 * So we will replace it by the activity (enabled)
	 * => In the cache we will search according to the state
	 * => In the dataBase we will search according the activity
	 * 
	 * @throws NoAvailableConnectionException
	 * @throws IOException
	 * @throws DeprecatedVersionException
	 */
	
	@Test
	public void retrieveFromDatabase() throws NoAvailableConnectionException, IOException, DeprecatedVersionException {
		Timestamp beginTimeFromDatabase = Util.getCurrentTimestamp();
		List<String> fields = Arrays.asList("ACTIVITY");
		List<String> value1 = Arrays.asList(SensorActivity.ENABLED.name());
		List<String> value2 = Arrays.asList(SensorActivity.DISABLED.name());
		List<String> value3 = Arrays.asList(SensorActivity.NOT_CONFIGURED.name());
		
		String jsonRequest1 = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, fields, value1, null, RequestSender.CLIENT);
		String jsonRequest2 = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, fields, value2, null, RequestSender.CLIENT);
		String jsonRequest3 = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, fields, value3, null, RequestSender.CLIENT);
		Map<SensorActivity,List<SensorConfiguration>> map = new HashMap<>();
		String response1 = MonitrackGuiUtil.sendRequest(jsonRequest1);
		List<SensorConfiguration> sensors = (List<SensorConfiguration>)JsonUtil.deserializeObject(response1);
		map.put(SensorActivity.ENABLED, sensors);
		String response2 = MonitrackGuiUtil.sendRequest(jsonRequest2);
		sensors = (List<SensorConfiguration>)JsonUtil.deserializeObject(response2);
		map.put(SensorActivity.DISABLED, sensors);
		String response3 = MonitrackGuiUtil.sendRequest(jsonRequest3);
		sensors = (List<SensorConfiguration>)JsonUtil.deserializeObject(response3);
		map.put(SensorActivity.NOT_CONFIGURED, sensors);
		Timestamp endTimeFromDatabase = Util.getCurrentTimestamp();
		//Util.displayListElements(sensors, "===>");
		System.err.println("The datas from the database have been retrieved in " + (endTimeFromDatabase.getTime() - beginTimeFromDatabase.getTime()) + " milliseconds");

		
	}

}
