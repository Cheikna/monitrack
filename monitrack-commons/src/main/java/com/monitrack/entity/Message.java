package com.monitrack.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monitrack.enumeration.SensorState;

public class Message {
	
	@JsonProperty("sensor_state")
	private SensorState sensorState;
	@JsonProperty("sensor")
	private Sensor sensor;
	@JsonProperty("message_creation_date")
	private Timestamp creationDate;

	public Message(SensorState sensorState, Sensor sensor) {
		this.sensorState = sensorState;
		this.sensor = sensor;
		this.creationDate = new Timestamp(System.currentTimeMillis());
	}
	
	public Message() {
		
	}

	public SensorState getSensorState() {
		return sensorState;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		result = prime * result + ((sensorState == null) ? 0 : sensorState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		if (sensorState != other.sensorState)
			return false;
		return true;
	}
	
	

}
