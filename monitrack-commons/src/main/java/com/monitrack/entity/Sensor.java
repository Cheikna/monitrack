package com.monitrack.entity;

import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;

/**
 * 
 * @author Cheikna
 *
 */
public abstract class Sensor {
	
	private Integer id;
	private SensorState sensorState;
	private SensorType sensorType;	
	private Location location;

	public Sensor() {
		// TODO Auto-generated constructor stub
	}

}
