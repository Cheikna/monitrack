package com.monitrack.entity;

import java.sql.Timestamp;

public class Location {
	private int idLocation;
	private String nameLocation;
	private String center;
	private Timestamp creationDate;
	private int idSensor = 0;
	
	public Location(String nameLocation, String center) {
		super();
		this.nameLocation = nameLocation;
		this.center = center;
	}
	public Location() {
		super();
	}
	public Location(int idLocation, String nameLocation, String center, Timestamp creationDate, int idSensor) {
		this.idLocation = idLocation;
		this.nameLocation = nameLocation;
		this.center = center;
		this.creationDate = creationDate;
		this.idSensor = idSensor;
	}

	
	@com.fasterxml.jackson.annotation.JsonProperty("ID_LOCATION")
	public int getIdLocation() {
		return idLocation;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("ID_LOCATION")
	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("NAME")
	public String getNameLocation() {
		return nameLocation;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("NAME")
	public void setNameLocation(String nameLocation) {
		this.nameLocation = nameLocation;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("CENTER")
	public String getCenter() {
		return center;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("CENTER")
	public void setCenter(String center) {
		this.center = center;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("ID_SENSOR")
	public int getIdSensor() {
		return idSensor;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("ID_SENSOR")
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("CREATION_DATE")
	public Timestamp getCreationDate() {
		return creationDate;
	}
	
	@com.fasterxml.jackson.annotation.JsonProperty("CREATION_DATE")
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "Location [idLocation=" + idLocation + ", nameLocation=" + nameLocation + ", center=" + center
				+ ", creationDate=" + creationDate + ", idSensor=" + idSensor + "]";
	}
	
	

	
	
}
