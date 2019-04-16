package com.monitrack.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

import com.monitrack.enumeration.SensorConfiguration;
import com.monitrack.enumeration.SensorType;

public class Smoke extends Sensor{

	public Smoke(Integer id, SensorConfiguration sensorConfiguration, SensorType sensorType, Location location, String ipAddress,
			String macAddress, float alertThreshold, Timestamp timeChange, Time beginTime, Time endTime,
			Timestamp creationDate) {
		super(id,SensorConfiguration.DISABLED,SensorType.SMOKE,location,ipAddress,macAddress,alertThreshold,timeChange,beginTime,endTime,creationDate );
	}



	
}
