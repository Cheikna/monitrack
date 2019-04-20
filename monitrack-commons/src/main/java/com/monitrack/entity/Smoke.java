package com.monitrack.entity;

import java.sql.Time;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class Smoke extends Sensor{
	
	@JsonProperty("smoke_id")
	private Integer idSmoke;
	@JsonProperty("mesured_threshold")
	private Float mesuredThreshold;
	@JsonProperty("smoke_danger_threshold")
	private Float smokeDangerThreshold;
	@JsonProperty("measurement_date")
	private Timestamp measurementDate;
	
	public Smoke(Integer id, SensorActivity sensorActivity, Integer locationId, String ipAddress,
			String macAddress, String serialNumber, Float hardwareVersion, Float softwareVersion,
			Timestamp creationDate, Timestamp lastMessageDate, Timestamp lastConfigurationDate, Time beginTime,
			Time endTime, Float checkFrequency, String measurementUnit, Float dangerThreshold, Float positionX,
			Float positionY, Integer idSmoke, Float mesuredThreshold, Float smokeDangerThreshold,
			Timestamp measurementDate) {
		super(id, sensorActivity, SensorType.SMOKE, locationId, ipAddress, macAddress, serialNumber, hardwareVersion, softwareVersion,
				creationDate, lastMessageDate, lastConfigurationDate, beginTime, endTime, checkFrequency, measurementUnit,
				dangerThreshold, positionX, positionY);
		this.idSmoke = idSmoke;
		this.mesuredThreshold = mesuredThreshold;
		this.smokeDangerThreshold = smokeDangerThreshold;
		this.measurementDate = measurementDate;
	}

	public Integer getIdSmoke() {
		return idSmoke;
	}

	public void setIdSmoke(Integer idSmoke) {
		this.idSmoke = idSmoke;
	}

	public Float getMesuredThreshold() {
		return mesuredThreshold;
	}

	public void setMesuredThreshold(Float mesuredThreshold) {
		this.mesuredThreshold = mesuredThreshold;
	}

	public Float getSmokeDangerThreshold() {
		return smokeDangerThreshold;
	}

	public void setSmokeDangerThreshold(Float smokeDangerThreshold) {
		this.smokeDangerThreshold = smokeDangerThreshold;
	}

	public Timestamp getMeasurementDate() {
		return measurementDate;
	}

	public void setMeasurementDate(Timestamp measurementDate) {
		this.measurementDate = measurementDate;
	}

}
