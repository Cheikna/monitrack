package com.monitrack.entity;

import java.sql.Time;
import java.sql.Timestamp;

import com.monitrack.enumeration.SensorConfiguration;
import com.monitrack.enumeration.SensorType;

public class Light extends Sensor{
	public Light(Integer id, SensorConfiguration sensorConfiguration, SensorType sensorType, Location location, String ipAddress,
			String macAddress, float alertThreshold, Timestamp timeChange, Time beginTime, Time endTime,
			Timestamp creationDate) {
		super(id,SensorConfiguration.DISABLED,SensorType.LIGHT,location,ipAddress,macAddress,alertThreshold,timeChange,beginTime,endTime,creationDate );
	}
}