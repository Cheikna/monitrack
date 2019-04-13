package com.monitrack.entity;

import com.monitrack.enumeration.SensorState;

/**
 * 
 * @author Cheikna
 *
 */
public abstract class Sensor {
	
	private Integer id;
	private SensorState sensorState;	
	private Location location;

	public Sensor() {
		// TODO Auto-generated constructor stub
	}

}
