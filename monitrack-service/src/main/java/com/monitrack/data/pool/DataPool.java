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
import com.monitrack.dao.implementation.SensorConfigurationDAO;
import com.monitrack.entity.Message;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.entity.SensorConfigurationHistory;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;
import com.monitrack.util.Util;

public class DataPool {

	private final Object lock = new Object();

	private static final Logger log = LoggerFactory.getLogger(DataPool.class);
	private final long updateListFrequency = NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("update_time_ms"));
	private final long sleepTime = NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("sleep_time_ms"));
	private final List<String> fieldsForActiveSensors = Arrays.asList("ACTIVITY");
	private final List<String> valuesForActiveSensors = Arrays.asList(SensorActivity.ENABLED.toString());

	private Map<SensorConfiguration, SensorState> dataPoolCache;
	private List<SensorConfiguration> activeSensors;	
	private Timestamp currentTime;
	private Thread listUpdaterThread;
	private long counter;
	private Connection connection;

	public DataPool() {		
		dataPoolCache = Collections.synchronizedMap(new HashMap<SensorConfiguration, SensorState>());
		activeSensors = Collections.synchronizedList(new ArrayList<SensorConfiguration>());
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

	public synchronized List<SensorConfiguration> getCacheSensorsByState(SensorState sensorState) {
		List<SensorConfiguration> results = new ArrayList<SensorConfiguration>();		

		for(Map.Entry<SensorConfiguration, SensorState > mapEntry : dataPoolCache.entrySet()) {
			if(mapEntry.getValue() == sensorState) {
				results.add(mapEntry.getKey());
			}
		}

		return results;
	}

	public synchronized void processMessage(Message receivedMessage) {
		try {
			log.info("Processing sensor message");
			currentTime = new Timestamp(System.currentTimeMillis());
			boolean isSensorWithCorrectIntervalInCache = false;
			SensorConfiguration sensorFromCache = null;
			SensorState sensorStateFromCache = null;
			
			SensorConfiguration sensorFromMessage = receivedMessage.getSensor();
			SensorState sensorStateFromMessage = receivedMessage.getSensorState();
			
			log.info(sensorFromMessage.getStateInfo());
			int sensorId = sensorFromMessage.getId();
			
			for (Map.Entry<SensorConfiguration, SensorState> mapEntry : dataPoolCache.entrySet()) {
				if (mapEntry.getKey().equals(sensorFromMessage)) {
					sensorFromCache = mapEntry.getKey();
					sensorFromCache.setLastMessageDate();
					sensorStateFromCache = mapEntry.getValue();
					Long cacheTime = sensorFromCache.getLastMessageDate().getTime();
					
					if(sensorStateFromMessage == SensorState.NORMAL && sensorStateFromCache == SensorState.DANGER) {
						SensorConfigurationHistory hist = new SensorConfigurationHistory();
					}					
					else if ((cacheTime != null) && (currentTime.getTime() - cacheTime) <= sensorFromCache.getCheckFrequency()) {
						log.info("The sensor n°" + sensorId + " has been found in the cache");
						//FIXME Cheikna (= ME) should see if the date is not already updated
						//sensorFromCache.setLastMessageDate();
						log.info("Old value : " + sensorFromCache.getStateInfo());

						//FIXME put this time in a config file
						Thread.sleep(1000);
						
						// FIXME Checks if the value has decreased and if it is the case, save the value
						Float maxThreshold = sensorFromMessage.getMaxDangerThreshold();
						Float minThreshold = sensorFromMessage.getMinDangerThreshold();
						Float threshold = sensorFromMessage.getCurrentThreshold();
								
						if (threshold >= maxThreshold || threshold < minThreshold) {
							sensorStateFromMessage = SensorState.DANGER;
							//FIXME Save into the database because it is a danger
						}						
						else if(threshold < sensorFromCache.getCurrentThreshold()) {
							//FIXME Save in the history
							//FIXME Save the data in the database in order to save the pic
						}
						else if(sensorFromMessage.getCurrentThreshold() == 0) {
							sensorStateFromMessage = SensorState.NORMAL;
						}

						isSensorWithCorrectIntervalInCache = true;
					}
					break;
				}
			}
			if (!isSensorWithCorrectIntervalInCache) {
				log.info("The sensor n°" + sensorId
						+ " has not been found in the cache (or was old). It will be added (or updated) to it !");
				sensorFromMessage.setLastMessageDate();
			}
			
			//Inserts or updates the value in the cache
			dataPoolCache.put(sensorFromMessage, sensorStateFromMessage);
			
		} catch (Exception e) {
			log.error(e.getStackTrace().toString());
			e.printStackTrace();
		}

		/*for(Map.Entry<Sensor, SensorState > mapEntry : dataPoolCache.entrySet()) {
			System.out.println("In the cache, there is : " + mapEntry.getKey());
		}*/

		//updateActiveSensorsList();
		//FIXME Récupérer tous les capteurs d'une même salle*/

	}

	@SuppressWarnings("unchecked")
	private synchronized void updateActiveSensorsList() {
		try {			
			connection = DataSource.getConnection();
			activeSensors.clear();
			activeSensors.addAll((List<SensorConfiguration>)DAOFactory.execute(connection, SensorConfiguration.class, RequestType.SELECT, null, fieldsForActiveSensors, valuesForActiveSensors));
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
			for(SensorConfiguration sensorConfiguration : activeSensors) {
				for(Map.Entry<SensorConfiguration, SensorState> mapEntry : dataPoolCache.entrySet()) {
					SensorConfiguration cacheSensor = mapEntry.getKey();
					if(sensorConfiguration.getId() == cacheSensor.getId())
						System.out.println("The sensor with the id n°" + sensorConfiguration.getId() + " is in the cache");
					else
						System.err.println("The sensor with the id n°" + sensorConfiguration.getId() + " is not in the cache");	

				}
			}
		}
	}

	public synchronized void displayUpdatedSensors() {
		for(SensorConfiguration sensorConfiguration : activeSensors)
			System.out.println(sensorConfiguration);
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
		//Sensor sensor = (Sensor) new SensorDAO(connection).find(Arrays.asList("ID_SENSOR"), Arrays.asList("8")).get(0);
		SensorConfiguration sensorConfiguration = new SensorConfiguration(8, 0, SensorActivity.ENABLED, SensorType.FLOW, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 2500f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);
		sensorConfiguration.setLastMessageDate();
		for(Integer i = 29; i < 36; i++) {
			SensorConfiguration sensor2 = (SensorConfiguration) new SensorConfigurationDAO(connection).find(Arrays.asList("ID_SENSOR"), Arrays.asList(i.toString())).get(0);
			dataPoolCache.put(sensor2, SensorState.DANGER);
		}
		
		dataPoolCache.put(sensorConfiguration, SensorState.DANGER);
	}
	
	private void processSensorMesage(Message message) {
		//FIXME
	}
	
	private void processManualTrigger(Message message) {
		//FIXME
	}
	
	

}
