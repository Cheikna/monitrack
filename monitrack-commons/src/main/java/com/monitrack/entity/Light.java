package com.monitrack.entity;

import java.sql.Time;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class Light extends Sensor{
	

	@JsonProperty("light_id")
	private Integer lightId;
	@JsonProperty("measured_rate")
	private Float measuredRate;
	@JsonProperty("danger_threshold")
	private Float rateDangerThreshold;
	@JsonProperty("measurement_date")
	private Timestamp measurementDate;

	public Light(Integer id, SensorActivity sensorActivity, Integer locationId, String ipAddress,
			String macAddress, String serialNumber, Float hardwareVersion, Float softwareVersion,
			Timestamp creationDate, Timestamp lastMessageDate, Timestamp lastConfigurationDate, Time beginTime,
			Time endTime, Float checkFrequency, String measurementUnit, Float currentThreshold, Float dangerThreshold, Float positionX,
			Float positionY, Integer lightId, Float measuredRate, Float rateDangerThreshold,
			Timestamp measurementDate) {
		super(id, sensorActivity, SensorType.LIGHT, locationId, ipAddress, macAddress, serialNumber, hardwareVersion, softwareVersion,
				creationDate, lastMessageDate, lastConfigurationDate, beginTime, endTime, checkFrequency, measurementUnit,
				currentThreshold, dangerThreshold, positionX, positionY);
		this.lightId = lightId;
		this.measuredRate = measuredRate;
		this.rateDangerThreshold = rateDangerThreshold;
		this.measurementDate = measurementDate;
	}

	public Integer getLightId() {
		return lightId;
	}

	public void setLightId(Integer lightId) {
		this.lightId = lightId;
	}

	public Float getMeasuredRate() {
		return measuredRate;
	}

	public void setMeasuredRate(Float measuredRate) {
		this.measuredRate = measuredRate;
	}

	public Float getRateDangerThreshold() {
		return rateDangerThreshold;
	}

	public void setRateDangerThreshold(Float rateDangerThreshold) {
		this.rateDangerThreshold = rateDangerThreshold;
	}

	public Timestamp getMeasurementDate() {
		return measurementDate;
	}

	public void setMeasurementDate(Timestamp measurementDate) {
		this.measurementDate = measurementDate;
	}
	
}
