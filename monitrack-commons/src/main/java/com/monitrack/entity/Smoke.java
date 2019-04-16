package com.monitrack.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;

public class Smoke extends Sensor{

	public Smoke(Integer id, SensorState sensorState, SensorType sensorType, Location location, String ipAddress,
			String macAddress, float alertThreshold, Timestamp timeChange, Time beginTime, Time endTime,
			Timestamp creationDate) {
		super(id,SensorState.DISABLED,SensorType.SMOKE,location,ipAddress,macAddress,alertThreshold,timeChange,beginTime,endTime,creationDate );
	}



	
}
