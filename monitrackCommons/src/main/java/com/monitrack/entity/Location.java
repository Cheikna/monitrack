package com.monitrack.entity;

import java.sql.Timestamp;

public class Location {
	private int idLocation;
	private String nameLocation;
	private String center;
	private Timestamp creationDate;
	private int idSensor;
	
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
	public int getIdLocation() {
		return idLocation;
	}
	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}
	public String getNameLocation() {
		return nameLocation;
	}
	public void setNameLocation(String nameLocation) {
		this.nameLocation = nameLocation;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public int getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
