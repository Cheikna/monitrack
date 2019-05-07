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
 * then the list updater which retrieve the sensor from the database will not work
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
	private final List<String> valuesForActiveSensors = Arrays.asList(SensorActivity.ENABLED.toString());

	/********* Map for conserving the sensors warning datas ******/
	private Map<Integer, Integer> warningCountBySensor;
	private Map<Integer, Timestamp> lastMessageDateBySensor;
	private Map<Integer, Timestamp> firstDangerMessageDateBySensor;	
	private Map<Integer, SensorState> sensorStateBySensor;
	private List<SensorConfiguration> activeSensors;	
	
	private ComplementarySensorConfig complementarySensorConfig;

	private final long sensorActivityCheckerSleepTime = DateTimeConstants.MILLIS_PER_MINUTE * NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("sensor_activity_checker_in_minute"));
	private final long sensorStateCacheClearerSleepTime = DateTimeConstants.MILLIS_PER_DAY / NumberUtils.toLong(Util.getPropertyValueFromPropertiesFile("cache_clear_per_day"));

	private Thread listUpdaterThread;
	private long counter;
	private int maxWarningMessage;

	public AlertCenter() {		
	}

	public void startAlertCenterThreads() {
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
		for(SensorConfiguration sensor : activeSensors) {
			sensorId = sensor.getSensorConfigurationId();
			if(sensorStateBySensor.get(sensorId) == sensorState) {
				results.add(sensor);
			}
		}

		return results;
	}

	public synchronized void processMessage(Message receivedMessage) {
		
	}

	public synchronized void displayUpdatedSensors() {
		for(SensorConfiguration sensorConfiguration : activeSensors)
			System.out.println(sensorConfiguration);
	}

	

	/**
	 * Checks if all active sensors sent a message
	 */
	private void sensorActivityChecker() {
		
	}
	
	private void checkComplementarySensorsOfLocation(SensorConfiguration alertLauncherSensor) {
		synchronized (activeSensors) 
		{			
			int locationId = alertLauncherSensor.getLocationId();
			SensorType alertLauncherSensorType = alertLauncherSensor.getSensorType();
			complementarySensorConfig.addCodeType(alertLauncherSensorType.getDangerCode());
			SensorType typeOfListSensor = null;
			boolean isGoodCombinaison = false;
			for(SensorConfiguration sensor : activeSensors) {
				if(sensor.getLocationId() == locationId && !sensor.equals(alertLauncherSensor)) {
					typeOfListSensor = sensor.getSensorType();
					isGoodCombinaison = complementarySensorConfig.isComplementary(alertLauncherSensorType, typeOfListSensor);
					
					if(isGoodCombinaison) {
						if(sensorStateBySensor.get(sensor.getSensorConfigurationId()) == SensorState.DANGER) {
							complementarySensorConfig.addCodeType(typeOfListSensor.getDangerCode());
						}
					}
				}
			}
			
			String message = complementarySensorConfig.getMessage();
			if(message != null) {
				log.info("\n===> " + message);
			}
		}
	}

	/**
	 * Empty the cache with contains the sensor state of all sensors
	 */
	private void clearCacheSensorStateBySensor() {
		
	}
	

}
