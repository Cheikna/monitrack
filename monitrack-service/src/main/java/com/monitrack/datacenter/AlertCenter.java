package com.monitrack.datacenter;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.dao.implementation.DAOFactory;
import com.monitrack.entity.Message;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.entity.SensorConfigurationHistory;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorAction;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorSensitivity;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;
import com.monitrack.util.Util;
/**
 * 
 * @author Cheikna
 * 
 * We need to retrieve a connection from the dataSource every time we need it 
 * Because if we use the connection which is passed with the requestHandler and if no client is connected
 * then the list updater (which retrieve the sensors from the database) will not work
 *
 */
public class AlertCenter {

	private static final Logger log = LoggerFactory.getLogger(AlertCenter.class);
	private final long updateListFrequency = DateTimeConstants.MILLIS_PER_MINUTE;
	private final long sleepTime = DateTimeConstants.MILLIS_PER_SECOND;

	private final String alignFormat = "%-4s| %-4d | %-9s |%-8s| %-13s |%-6s|%n";
	private final String horizontalBorder      = "    +------+-----------+--------+---------------+------+%n";
	private final String header			 	   = "    |  ID  |   State   | Warn.  | Curr. Thresh. | Unit |%n";
	//private final String alignFormatActivityChecker = "| %-4d | %-20s | %-9s |%-8s|%n";
	//private final String headerActivityChecker = "|  ID  |         Type         |   State   | Warn.  |%n";

	/******** Element for searching in the database *******/
	private final List<String> fieldsForActiveSensors = Arrays.asList("ACTIVITY");
	private final List<String> testsForActiveSensors = Arrays.asList("=");
	private final List<String> valuesForActiveSensors = Arrays.asList(SensorActivity.ENABLED.toString());

	/********* Map for conserving the sensors warning datas ******/
	private Map<Integer, CacheInfo> cacheInfoBySensor;
	private List<SensorConfiguration> activeSensors;	
	
	private ComplementarySensorConfig complementarySensorConfig;

	private final long sensorActivityCheckerSleepTime = DateTimeConstants.MILLIS_PER_MINUTE * NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("sensor_activity_checker_in_minute"));
	private final long sensorStateCacheClearerSleepTime = DateTimeConstants.MILLIS_PER_DAY / NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("cache_clear_per_day"));

	private Thread listUpdaterThread;
	private long counter;
	private int maxWarningMessage;

	public AlertCenter() {				
		cacheInfoBySensor = Collections.synchronizedMap(new HashMap<Integer, CacheInfo>()); 
		activeSensors = Collections.synchronizedList(new ArrayList<SensorConfiguration>());
		counter = 0;
		complementarySensorConfig = new ComplementarySensorConfig();
		clearCacheSensorStateBySensor();
	}

	public void startAlertCenterThreads() {
		//Starts the thread who retrieves the sensors from the database
		activeSensorListUpdater();
		//Starts the thread which check if all active sensor sent a message
		sensorActivityChecker();
	}

	/**
	 * Starts the update list thread so that the data can be refresh every minutes
	 */
	public synchronized void activeSensorListUpdater() {
		listUpdaterThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					if(counter <= 0) {
						updateSensorsList();
					}else {
						try {
							Thread.sleep(DateTimeConstants.MILLIS_PER_SECOND);
							counter -= sleepTime;
						} catch (InterruptedException e) {
							log.error(e.getMessage());
						}
					}
				}
			}
		});
		listUpdaterThread.start();
	}

	public synchronized List<SensorConfiguration> getCacheSensorsByState(SensorState sensorState) {
		List<SensorConfiguration> results = new ArrayList<SensorConfiguration>();		
		Integer sensorId = null;
		CacheInfo info = null;
		for(SensorConfiguration sensor : activeSensors) {
			sensorId = sensor.getSensorConfigurationId();
			info = cacheInfoBySensor.get(sensorId);
			if(info != null && info.getSensorState() == sensorState) {
				results.add(sensor);
			}
		}

		return results;
	}
	
	public synchronized Map<SensorState, List<SensorConfiguration>> getAllActiveSensorByState(){
		Map<SensorState, List<SensorConfiguration>> map = new TreeMap<>();

		for(SensorState state : SensorState.values()) {
			map.put(state, getCacheSensorsByState(state));			
		}
		
		return map;
	}

	public synchronized void processMessage(Message receivedMessage) {
		try {	
			int sensorId = receivedMessage.getSensorId();
			SensorState sensorState = null;
			log.info("Message reveived from the sensor n°" + sensorId);
			Timestamp messageDate = receivedMessage.getCreationDate();
			float thresholdReached = receivedMessage.getThresholdReached();
			SensorConfiguration sensorConfiguration = null;
			// Searchs the sensor which has sent a message among the active sensors
			for(SensorConfiguration sensor : activeSensors) {
				if(sensor.getSensorConfigurationId() == sensorId) {
					sensorConfiguration = sensor;
					break;
				}
			}

			if(sensorConfiguration != null) {
				String message = "";
				maxWarningMessage = SensorSensitivity.getNumberOfMessages(sensorConfiguration.getSensorSensitivity());
				log.info("The sensor n°" + sensorId + " has reached " + thresholdReached + "/" + sensorConfiguration.getMaxDangerThreshold());

				//Checks the state of the sensor just with the data received from the message
				Float maxThreshold = sensorConfiguration.getMaxDangerThreshold();
				Float minThreshold = sensorConfiguration.getMinDangerThreshold();
				if (thresholdReached >= maxThreshold || thresholdReached < minThreshold)
					sensorState = SensorState.WARNING;
				else if(thresholdReached == minThreshold || sensorConfiguration.getSensorType().getIsGapAcceptable())
					sensorState = SensorState.NORMAL;
				else					
					sensorState = SensorState.CAUTION;

				CacheInfo info = cacheInfoBySensor.get(sensorId);
				//If the info is in the cache
				if(info != null) {
					info.setThreasholdReached(thresholdReached);
					Timestamp previousMessageTime = info.getLastMessageDate();
					// Case : the interval between the two message is correct
					if(previousMessageTime != null && (messageDate.getTime() - previousMessageTime.getTime()) <= sensorConfiguration.getCheckFrequency()) 
					{
						// Case : a warning is detected
						if(sensorState == SensorState.WARNING) {
							log.info("The sensor n°" + sensorId + " send another warning message");
							sensorState = info.addWarning(maxWarningMessage, messageDate);
							if(sensorState == SensorState.DANGER) {
								message = checkComplementarySensorsOfLocation(sensorConfiguration);
								if(!info.isBeginDangerRegistered()) {
									saveSensorConfigurationHistory(sensorConfiguration, null, "", info.getFirstDangerMessageDate(), false);
									info.setBeginDangerRegistered(true);									
								}
							}
						} 
						else if(sensorState == SensorState.NORMAL) 
						{
							Integer numberOfWarning = info.getWarningCount();
							// Case : Previously we had a warning
							Timestamp dangerTime = info.getFirstDangerMessageDate();
							// Case : The reparator have done their jobs
							if(numberOfWarning >= maxWarningMessage) {
								System.err.println(" /!\\ The maintainers repaired the sensor n°" + sensorId + " /!\\" );
								saveSensorConfigurationHistory(sensorConfiguration, SensorAction.STOP_DANGER_ALERT, "", dangerTime, true);
							}
							else if(numberOfWarning > 0 && numberOfWarning < maxWarningMessage) {
								log.info("A fake alert is detected for the sensor n°" + sensorId);
								saveSensorConfigurationHistory(sensorConfiguration, SensorAction.FAKE_ALERT, "", dangerTime, true);
							}
							// Removes the warning and Removes the first danger alert date
							info.reset();							
						}
						else
							info.setSensorState(sensorState);
					}
					// Case : the last alert in the cache was too old or there was not any message in the cache
					else 
					{
						if(sensorState == SensorState.WARNING) {
							// Sets the numbers of warning message sent by this sensor
							info.addWarning(maxWarningMessage);
						}
						else
							info.setSensorState(sensorState);
					}
					
				} else {
					info = new CacheInfo(messageDate, null, sensorState, thresholdReached);
					log.info("The sensor n°" + sensorId + " did not send any message or did it a long time ago");					
				}
				//Sets the last warning sent by this sensor
				info.setLastMessageDate(messageDate);
				// Puts or updates the info in the cache
				cacheInfoBySensor.put(sensorId, info);
				//Displays the info on the console
				log.info("Info about location n°" + sensorConfiguration.getLocationId());
				System.out.format(horizontalBorder);
				System.out.format(header);
				System.out.format(horizontalBorder);
				System.out.format(alignFormat, "" , sensorId, info.getSensorState().name(), info.getWarningCount() + "/" + maxWarningMessage, thresholdReached + "/" + sensorConfiguration.getMaxDangerThreshold(),
						"Unit");
				System.out.format(horizontalBorder);
				System.out.println(message);
			} else {
				log.error("The sensor n°" + sensorId + " sent a message but it should not !");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void saveSensorConfigurationHistory(SensorConfiguration sensor, SensorAction action, String message, Timestamp messageDate, boolean isAlertEnded) {
		int id = sensor.getSensorConfigurationId();
		try {
			Connection connection = DataSource.getConnection();
			if(connection != null) {
				SensorConfigurationHistory sensorHistory = null;
				// Case : the alert is ended so we have to update the end of the alert
				if(isAlertEnded) {
					Integer wantedId = cacheInfoBySensor.get(sensor.getSensorConfigurationId()).getHistory();
					sensorHistory = ((List<SensorConfigurationHistory>)DAOFactory.execute(connection, SensorConfigurationHistory.class, RequestType.SELECT, null, 
							Arrays.asList("ID_HISTORY"), Arrays.asList(wantedId.toString()), null)).get(0);
					if(sensorHistory != null) {
						sensorHistory.setEndAlertDate(messageDate);
						sensorHistory.setActionDone(action);
						DAOFactory.execute(connection, sensorHistory.getClass(), RequestType.UPDATE, sensorHistory, null, null, null);		
						log.info("The end date of the sensor n°" + id + " alert has been updated");
					}
					CacheInfo info = cacheInfoBySensor.get(id);
					info.reset();
					cacheInfoBySensor.put(id, info);
				}
				else {
					sensorHistory = new SensorConfigurationHistory(sensor, cacheInfoBySensor.get(id).getThreasholdReached(), messageDate, message, null);
					SensorConfigurationHistory result = (SensorConfigurationHistory)DAOFactory.execute(connection, sensorHistory.getClass(), RequestType.INSERT, sensorHistory, null, null, null);
					cacheInfoBySensor.get(sensor.getSensorConfigurationId()).setHistory(result.getIdHistory());	
					log.info("The begin date of the sensor n°" + id + " alert has been registered");
				}
				
				DataSource.putConnection(connection);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}	
	}

	public synchronized void displayUpdatedSensors() {
		for(SensorConfiguration sensorConfiguration : activeSensors)
			System.out.println(sensorConfiguration);
	}

	@SuppressWarnings("unchecked")
	public void updateSensorsList() {
		try {	
			Connection connection = DataSource.getConnection();
			if(connection != null) {		
				activeSensors.clear();
				activeSensors.addAll((List<SensorConfiguration>)DAOFactory.execute(connection, SensorConfiguration.class, RequestType.SELECT, null, fieldsForActiveSensors, valuesForActiveSensors, testsForActiveSensors));
				log.info("Active sensors list updated");
				DataSource.putConnection(connection);
			}
		} catch (Exception e) {
			log.error("An error occured during the update of the active sensors list : " + e.getMessage());
		}
		counter = updateListFrequency;
	}

	/**
	 * Checks if all active sensors sent a message
	 */
	private void sensorActivityChecker() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					//Sleeps this thread so that the active sensors list can be filled
					Thread.sleep(sleepTime);
					while(true) {					
						for(SensorConfiguration sensor : activeSensors) {
							int id = sensor.getSensorConfigurationId();
							CacheInfo info = cacheInfoBySensor.get(id);
							if(info == null) {
								info = new CacheInfo(null, null, SensorState.MISSING, 0);
								cacheInfoBySensor.put(id, info);
							}
							else {
								Timestamp lastMessageDate = info.getLastMessageDate();
								// Case : The sensor did not sent any message before OR the sensor sent a message but not in time
								if(lastMessageDate == null || 
										(Util.getCurrentTimestamp().getTime() - lastMessageDate.getTime()) > sensor.getCheckFrequency()) {
									info.reset();
									info.setLastMessageDate(null);
									info.setFirstDangerMessageDate(null);
									info.setSensorState(SensorState.MISSING);
									cacheInfoBySensor.put(id, info);
								}								
							}								
						}
						log.info("All active sensors have been checked");
						Thread.sleep(sensorActivityCheckerSleepTime);
					}

				} catch (Exception e) {
					log.error(e.getMessage());
				}				
			}
		});
		thread.start();
	}
	
	private String checkComplementarySensorsOfLocation(SensorConfiguration alertLauncherSensor) {
		synchronized (activeSensors) 
		{			
			int locationId = alertLauncherSensor.getLocationId();
			SensorType alertLauncherSensorType = alertLauncherSensor.getSensorType();
			complementarySensorConfig.addCodeType(alertLauncherSensorType.getDangerCode());
			SensorType typeOfListSensor = null;
			boolean isGoodCombinaison = false;
			for(SensorConfiguration sensor : activeSensors) {
				//Checks if the sensor is in the same room and it is not himself
				if(sensor.getLocationId() == locationId && !sensor.equals(alertLauncherSensor)) {
					typeOfListSensor = sensor.getSensorType();
					isGoodCombinaison = complementarySensorConfig.isComplementary(alertLauncherSensorType, typeOfListSensor);
					
					if(isGoodCombinaison) {
						CacheInfo info = cacheInfoBySensor.get(sensor.getSensorConfigurationId());
						if(info != null && info.getSensorState() == SensorState.DANGER) {
							complementarySensorConfig.addCodeType(typeOfListSensor.getDangerCode());
						}
					}
				}
			}
			
			String message = complementarySensorConfig.getMessage();
			return (message == null) ? "" : " >>> " + message;
		}
	}

	/**
	 * Empty the cache with contains the sensor state of all sensors
	 */
	private void clearCacheSensorStateBySensor() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(sensorStateCacheClearerSleepTime);
					//Use an Iterator because we want to modify the collection during the loop
					Iterator<Entry<Integer, CacheInfo>> iterator = cacheInfoBySensor.entrySet().iterator();
					boolean isInActiveSensorList = false;
					while (iterator.hasNext()) {
						Entry<Integer, CacheInfo> entry = (Entry<Integer, CacheInfo>)iterator.next();
						isInActiveSensorList = false;
						int id = entry.getKey();
						for (SensorConfiguration sensor : activeSensors) {
							if (sensor.getSensorConfigurationId() == id) {
								isInActiveSensorList = true;
								break;
							}
						}

						if (!isInActiveSensorList) {
							iterator.remove();
						}
					}
					log.info("The cache has been cleared");
				} catch (Exception e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
				
			}
		});
		thread.start();
	}
}
