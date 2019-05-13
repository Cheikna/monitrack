package com.monitrack.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LocationSensors {
	private int idLocation;
	private int idSensor;
	
	
	
	public LocationSensors(int idLocation, int idSensor) {
		super();
		this.idLocation = idLocation;
		this.idSensor = idSensor;
	}

	@JsonGetter("idLocation")
	public int getIdLocation() {
		return idLocation;
	}
	
	@JsonSetter("idLocation")
	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}
	
	@JsonGetter("idSensor")
	public int getIdSensor() {
		return idSensor;
	}
	
	
	@JsonSetter("idSensor")
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLocation;
		result = prime * result + idSensor;


		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		LocationSensors other = (LocationSensors) obj;
		if (idLocation != other.idLocation)
			return false;
		if (idSensor != other.idSensor)
			return false;
		return true;
	}
	
}
