package com.monitrack.datacenter;

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
import com.monitrack.enumeration.SensorAction;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;
import com.monitrack.shared.MonitrackServiceUtil;
import com.monitrack.util.Util;

public class DataCenter {

	private final Object lock = new Object();

	private static final Logger log = LoggerFactory.getLogger(DataCenter.class);
	private final long updateListFrequency = NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("update_time_ms"));
	private final long sleepTime = NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("sleep_time_ms"));
	private final List<String> fieldsForActiveSensors = Arrays.asList("ACTIVITY");
	private final List<String> valuesForActiveSensors = Arrays.asList(SensorActivity.ENABLED.toString());
	private final Long loopSleep = MonitrackServiceUtil.getDataPoolLoopSleep();
	private final String alignFormat = "| %-4d | %-13s |%-6s| %-9s |%-8s|%n";
	private final String horizontalBorder = "+------+---------------+------+-----------+--------+%n";

	private Map<SensorConfiguration, SensorState> dataPoolCache;
	private Map<Integer, Integer> dangerWarningCountBySensors;
	private final int maxWarningMessage = NumberUtils.toInt(Util.getPropertyValueFromPropertiesFile("max_warning_message"));
	private List<SensorConfiguration> activeSensors;	
	private Timestamp currentTime;
	private Thread listUpdaterThread;
	private long counter;
	private Connection connection;

	public DataCenter() {		
		dataPoolCache = Collections.synchronizedMap(new HashMap<SensorConfiguration, SensorState>());
		dangerWarningCountBySensors = Collections.synchronizedMap(new HashMap<Integer, Integer>());
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
		dataPoolCache.containsKey(new SensorConfiguration());
		for(Map.Entry<SensorConfiguration, SensorState > mapEntry : dataPoolCache.entrySet()) {
			if(mapEntry.getValue() == sensorState) {
				results.add(mapEntry.getKey());
			}
		}

		return results;
	}

	public synchronized void processMessage(Message receivedMessage) {
		try {
			boolean isSensorWithCorrectIntervalInCache = false;
			boolean isInCache = false;
			Timestamp warningStartTime = null;
			SensorConfiguration sensorFromCache = null;
			
			SensorConfiguration sensorFromMessage = receivedMessage.getSensor();
			SensorState sensorState = checkSensorState(sensorFromMessage);
			
			
			log.info(sensorFromMessage.getStateInfo());
			int sensorId = sensorFromMessage.getSensorConfigurationId();

			
			for (SensorConfiguration cacheSensor : dataPoolCache.keySet()) {
				if (cacheSensor.equals(sensorFromMessage)) {
					isInCache = true;
					sensorFromCache = cacheSensor;
					warningStartTime = sensorFromCache.getDangerStartDate();
					Long cacheTime = sensorFromCache.getLastMessageDate().getTime();  
					currentTime = Util.getCurrentTimestamp();
					isSensorWithCorrectIntervalInCache = (cacheTime != null) && (currentTime.getTime() - cacheTime) <= sensorFromCache.getCheckFrequency();
					
					if(isSensorWithCorrectIntervalInCache) {
						/* Checks it is was a danger before and according to the number of danger Message
						 * it will either be a reparation that has been made or a false alert
						 */
						int numberOfWarningMessage = dangerWarningCountBySensors.get(sensorId);
						if(sensorState == SensorState.NORMAL) {
							// Case : the reparators have done their job
							if(numberOfWarningMessage >= maxWarningMessage) {
								dangerWarningCountBySensors.replace(sensorId, 0);
								System.err.println("======>  /!\\ The sensor n°" + sensorId + " has been repaired /!\\" );
								saveSensorConfigurationHistory(sensorFromCache, SensorAction.STOP_DANGER_ALERT, "");
							}
							// Case : Fake Alert
							else if(numberOfWarningMessage > 0) {
								dangerWarningCountBySensors.replace(sensorId, 0);
								saveSensorConfigurationHistory(sensorFromCache, SensorAction.FAKE_ALERT, "");
							}
						}
						else if(sensorState == SensorState.MISSING) {
							continue;
						}
						else if(sensorState == SensorState.WARNING) {
							System.err.println("WARNING");
							int newNumberOfWarningMessage = numberOfWarningMessage + 1;
							dangerWarningCountBySensors.replace(sensorId, newNumberOfWarningMessage);
							if(newNumberOfWarningMessage == 1) 
							{
								warningStartTime = Util.getCurrentTimestamp();
							} 
							else if(newNumberOfWarningMessage >= maxWarningMessage) 
							{
								sensorState = SensorState.DANGER;
								System.err.println(" /!\\ The sensor n°" + sensorId + " is in real DANGER /!\\" );
							}
						}	
						
						//Removes it from the cache because the new value will be added						
						dataPoolCache.remove(sensorFromMessage);
					}	
					//System.err.println("======> Traitement time : " + (Util.getCurrentTimestamp().getTime() - currentTime.getTime()));
					break;					
				}
			}

			if (!isSensorWithCorrectIntervalInCache) {						
				if(isInCache) {
					log.info("The sensor n°" + sensorId + " was old in the cache. It will be updated !");
					//Remove the old value from the cache
					dataPoolCache.remove(sensorFromMessage);
					
					//Update the value of warning messages received
					dangerWarningCountBySensors.replace(sensorId, 0);
				}
				else {
					log.info("The sensor n°" + sensorId + " has not been found in the cache. It will be added to it !");
					int warningMessage = (sensorState == SensorState.WARNING) ? 1 : 0; 
					if(warningMessage == 1)
						sensorFromMessage.setDangerStartDate(Util.getCurrentTimestamp());
					dangerWarningCountBySensors.put(sensorId, warningMessage);
				}
			}

			log.info("Sensors states : ");
			displaySensorsStatesDatas(sensorFromMessage, sensorState);
			
			sensorFromMessage.setDangerStartDate(warningStartTime);
			sensorFromMessage.setLastMessageDate(Util.getCurrentTimestamp());
			dataPoolCache.put(sensorFromMessage, sensorState);
			
			//FIXME Display info
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		//updateActiveSensorsList();
		//FIXME Récupérer tous les capteurs d'une même salle*/

	}
		
	private void displaySensorsStatesDatas(SensorConfiguration messageSensor, SensorState state) {		
		System.out.format(horizontalBorder);
		System.out.format("|  ID  | Curr. Thresh. | Unit |   State   | danger |%n");
		System.out.format(horizontalBorder);
		// Prevent from checking a value on a null object
		int sensorId = messageSensor.getSensorConfigurationId();
		System.out.format(alignFormat, sensorId, messageSensor.getCurrentThreshold() + "/" + messageSensor.getMaxDangerThreshold(),
				"Unit",  state.name(), dangerWarningCountBySensors.get(sensorId) + "/" + maxWarningMessage);
		System.out.format(horizontalBorder);		
	}
	
	private SensorState checkSensorState(SensorConfiguration sensorFromMessage) {
		Float maxThreshold = sensorFromMessage.getMaxDangerThreshold();
		Float minThreshold = sensorFromMessage.getMinDangerThreshold();
		Float thresholdFromMessage = sensorFromMessage.getCurrentThreshold();
		
		if (thresholdFromMessage >= maxThreshold || thresholdFromMessage < minThreshold) {
			return SensorState.WARNING;
		}						
		return SensorState.NORMAL;
	}
	
	private void saveSensorConfigurationHistory(SensorConfiguration sensor, SensorAction action, String message) {
		int id = sensor.getSensorConfigurationId();
		try {
			SensorConfigurationHistory sensorHistory = new SensorConfigurationHistory(sensor, message, action);
			DAOFactory.execute(connection, sensorHistory.getClass(), RequestType.INSERT, sensorHistory, null, null);
			dangerWarningCountBySensors.replace(id, 0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}	
	}

	//FIXME used this fonction
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
					if(sensorConfiguration.getSensorConfigurationId() == cacheSensor.getSensorConfigurationId())
						System.out.println("The sensor with the id n°" + sensorConfiguration.getSensorConfigurationId() + " is in the cache");
					else
						System.err.println("The sensor with the id n°" + sensorConfiguration.getSensorConfigurationId() + " is not in the cache");	

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
	}

}
