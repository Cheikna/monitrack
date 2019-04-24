package com.monitrack.data.pool;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.dao.implementation.DAOFactory;
import com.monitrack.dao.implementation.SensorDAO;
import com.monitrack.entity.Message;
import com.monitrack.entity.Sensor;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorState;
import com.monitrack.util.Util;

public class DataPool {

	private final Object lock = new Object();

	private static final Logger log = LoggerFactory.getLogger(DataPool.class);
	private final long updateListFrequency = NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("update_time_ms"));
	private final long sleepTime = NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("sleep_time_ms"));
	private final List<String> fieldsForActiveSensors = Arrays.asList("ACTIVITY");
	private final List<String> valuesForActiveSensors = Arrays.asList(SensorActivity.ENABLED.toString());

	private Map<Sensor, SensorState> dataPoolCache;
	private List<Sensor> activeSensors;	
	private Timestamp currentTime;
	private Thread listUpdaterThread;
	private long counter;
	private Connection connection;

	public DataPool() {
		connection = DataSource.getConnection();
		Sensor sensor = (Sensor) new SensorDAO(connection).find(Arrays.asList("ID_SENSOR"), Arrays.asList("8")).get(0);
		Sensor sensor2 = (Sensor) new SensorDAO(connection).find(Arrays.asList("ID_SENSOR"), Arrays.asList("10")).get(0);
		DataSource.putConnection(connection);
		dataPoolCache = Collections.synchronizedMap(new HashMap<Sensor, SensorState>());
		dataPoolCache.put(sensor, SensorState.DANGER);
		dataPoolCache.put(sensor2, SensorState.NORMAL);
		activeSensors = Collections.synchronizedList(new ArrayList<Sensor>());
		System.out.println(sensor);
		System.out.println(sensor2);
		counter = updateListFrequency;
	}
	
	/**
	 * Starts the update list thread to that the data can be refresh every 10 seconds
	 */
	public synchronized void startListUpdaterThread() {
		listUpdaterThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					if(counter <= 0) {
						//FIXME updateActiveSensorsList();
					}else {
						try {
							Thread.sleep(sleepTime);
							counter -= sleepTime;
						} catch (InterruptedException e) {
							log.error(e.getMessage());
						}
					}
				}
			}
		});
		//updateActiveSensorsList();
		listUpdaterThread.start();
	}

	public synchronized List<Sensor> getCacheSensorsByState(SensorState sensorState) {
		List<Sensor> results = new ArrayList<Sensor>();		

		for(Map.Entry<Sensor, SensorState > mapEntry : dataPoolCache.entrySet()) {
			if(mapEntry.getValue() == sensorState) {
				results.add(mapEntry.getKey());
			}
		}

		return results;
	}

	public synchronized void processMessage(Message receivedMessage) {
		currentTime = new Timestamp(System.currentTimeMillis());
		Sensor sensor = receivedMessage.getSensor();
		System.err.println("=========> Sensor : " + sensor.getSensorType());
		//System.err.println("=========> LEVEL : " + sensor.getCurrentThreshold() + "/" + sensor.getMaxDangerThreshold());
		System.out.println("==========> State : " + receivedMessage.getSensorState());
		if(sensor.raiseDangerAlert())
			System.err.println("/!\\ DANGER DANGER /!\\");
		//updateActiveSensorsList();
		//FIXME Récupérer tous les capteurs d'une même salle

	}

	@SuppressWarnings("unchecked")
	private synchronized void updateActiveSensorsList() {
		try {			
			connection = DataSource.getConnection();
			activeSensors.clear();
			activeSensors.addAll((List<Sensor>)DAOFactory.execute(connection, Sensor.class, RequestType.SELECT, null, fieldsForActiveSensors, valuesForActiveSensors));
			DataSource.putConnection(connection);
			connection = null;
			log.info("active sensors list updated");
			checkAllSensorsActivity();
		} catch (Exception e) {
			log.error("An error occured during the update of the active sensors list : " + e.getMessage());
		}
		counter = updateListFrequency;
	}

	/**
	 * Checks if all sensors work correctly = If they all send message
	 */
	public void checkAllSensorsActivity() {
		synchronized (lock) {
			System.out.println("===> size : " + activeSensors.size());
			for(Sensor sensor : activeSensors) {
				for(Map.Entry<Sensor, SensorState> mapEntry : dataPoolCache.entrySet()) {
					Sensor cacheSensor = mapEntry.getKey();
					if(sensor.getId() == cacheSensor.getId())
						System.out.println("The sensor with the id n°" + sensor.getId() + " is in the cache");
					else
						System.err.println("The sensor with the id n°" + sensor.getId() + " is not in the cache");	

				}
			}
		}
	}

	public synchronized void displayUpdatedSensors() {
		for(Sensor sensor : activeSensors)
			System.out.println(sensor);
	}

}
