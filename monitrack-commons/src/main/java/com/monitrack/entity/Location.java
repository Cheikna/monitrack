package com.monitrack.entity;

import java.sql.Timestamp;

public class Location {
	private int idLocation;
	private String nameLocation;
	private String center;
	private Timestamp creationDate;
	private int idSensor = 0;
	
	public Location(String nameLocation, String center) {
		this(0, nameLocation, center, new Timestamp(System.currentTimeMillis()), 0);
		/*this.nameLocation = nameLocation;
		this.center = center;*/
		
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
		return idLocation + "#" + nameLocation + " - créé le " + creationDate;
		/*return "Location [idLocation=" + idLocation + ", nameLocation=" + nameLocation + ", center=" + center
				+ ", creationDate=" + creationDate + ", idSensor=" + idSensor + "]";*/
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + idLocation;
		result = prime * result + idSensor;
		result = prime * result + ((nameLocation == null) ? 0 : nameLocation.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (idLocation != other.idLocation)
			return false;
		if (idSensor != other.idSensor)
			return false;
		if (nameLocation == null) {
			if (other.nameLocation != null)
				return false;
		} else if (!nameLocation.equals(other.nameLocation))
			return false;
		return true;
	}
	
	
	
	

	
	
}
