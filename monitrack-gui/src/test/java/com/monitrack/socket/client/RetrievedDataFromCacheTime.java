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

public class RetrievedDataFromCacheTime {

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
		Timestamp beginTime = Util.getCurrentTimestamp();
		String jsonRequest = JsonUtil.serializeSensorsFromCacheRequest(RequestSender.CLIENT_FOR_SENSOR_STATE);
		String response = MonitrackGuiUtil.sendRequest(jsonRequest);	
		Map<SensorState,List<SensorConfiguration>> map2 = JsonUtil.deserializeCacheSensorsMap(response);
		Timestamp end = Util.getCurrentTimestamp();
		System.err.println("The datas from the cache have been retrieved in " + (end.getTime() - beginTime.getTime()) + " milliseconds");

		
	}

}
