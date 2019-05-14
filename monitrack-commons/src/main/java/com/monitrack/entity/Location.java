package com.monitrack.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Location {
	private int idLocation;
	private String nameLocation;
	private String center;
	private Timestamp creationDate;
	private int idSensor = 0;
	private int floor;
	private String wing;
	// The size of the room
	private int area;
	private List<Sensor> sensorConfigurations;
	@JsonProperty("position_x")
	private float positionX;
	@JsonProperty("position_y")
	private float positionY;
	@JsonProperty("width")
	private float width;
	@JsonProperty("height")
	private float height;
	
	public Location(String nameLocation, String center, int floor, String wing, int area) {
		this(0, nameLocation, center, new Timestamp(System.currentTimeMillis()), 0, floor, wing, area,0,0,0,0);
		
	}
	
	public Location() {
		super();
	}
	
	public Location(int idLocation, String nameLocation, String center, Timestamp creationDate, int idSensor, int floor, String wing, int area, float positionX, float positionY, float width, float height) {
		this.idLocation = idLocation;
		this.nameLocation = nameLocation;
		this.center = center;
		this.creationDate = creationDate;
		this.idSensor = idSensor;
		this.floor = floor;
		this.area = area;
		this.wing = wing;
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}

	
	@JsonGetter("id")
	public int getIdLocation() {
		return idLocation;
	}
	
	@JsonSetter("id")
	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}
	
	@JsonGetter("name")
	public String getNameLocation() {
		return nameLocation;
	}
	
	@JsonSetter("name")
	public void setNameLocation(String nameLocation) {
		this.nameLocation = nameLocation;
	}
	
	@JsonGetter("center")
	public String getCenter() {
		return center;
	}
	
	@JsonSetter("center")
	public void setCenter(String center) {
		this.center = center;
	}
	
	@JsonGetter("id_sensor")
	public int getIdSensor() {
		return idSensor;
	}
	
	@JsonSetter("id_sensor")
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	
	@JsonGetter("creation_date")
	public Timestamp getCreationDate() {
		return creationDate;
	}
	
	@JsonSetter("creation_date")
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}	
	
	@JsonGetter("floor")
	public int getFloor() {
		return floor;
	}
	
	@JsonSetter("floor")
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	@JsonGetter("wing")
	public String getWing() {
		return wing;
	}
	
	@JsonSetter("wing")
	public void setWing(String wing) {
		this.wing = wing;
	}
	
	@JsonGetter("area")
	public int getArea() {
		return area;
	}
	
	@JsonSetter("area")
	public void setArea(int area) {
		this.area = area;
	}
	
	@Override
	public String toString() {
		return idLocation + "#" + nameLocation + " - créé le " + creationDate;		
	}
	
	public String toStringFull() {
		return "- Emplacement n°" + idLocation + " :\n"
			 + "      => Nom : " + nameLocation + "\n"
			 + "      => Aile : " + wing + "\n"
			 + "      => Etage : " + floor + "\n"
			 + "      => Superficie : " + area + " m²\n"
		     + "      => Date de création : " + creationDate + "\n";
	}
	
	public String toStringListItem() {
		return "- Emplacement n°" + idLocation + " (Nom : " + nameLocation + " >>> Aile : " + wing + " >>> Etage : " + floor
			 + " >>> Superficie : " + area + " m² >>> Date de création : " + creationDate + ")";
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

	/**
	 * @return the sensors
	 */
	@JsonGetter("sensors")
	public List<Sensor> getSensors() {
		return sensorConfigurations;
	}

	/**
	 * @param sensorConfigurations the sensors to set
	 */
	@JsonSetter("sensors")
	public void setSensors(List<Sensor> sensorConfigurations) {
		this.sensorConfigurations = sensorConfigurations;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
}
