package com.monitrack.entity;

import java.sql.Time;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class Flow extends Sensor{
	
	@JsonProperty("measurement_date")
	private Integer flowId;
	@JsonProperty("someone_detected")
	private Integer detected;
	@JsonProperty("was_someone_detected")
	private Boolean wasSomeoneDetected;
	@JsonProperty("measurement_date")
	private Timestamp measurementDate;
	
	public Flow(Integer id, SensorActivity sensorActivity, Integer locationId, String ipAddress,
			String macAddress, String serialNumber, Float hardwareVersion, Float softwareVersion,
			Timestamp creationDate, Timestamp lastMessageDate, Timestamp lastConfigurationDate, Time beginTime,
			Time endTime, Float checkFrequency, String measurementUnit, Float dangerThreshold, Float positionX,
			Float positionY, Integer flowId, Integer detected, Boolean wasSomeoneDetected, Timestamp measurementDate) {
		super(id, sensorActivity, SensorType.FLOW, locationId, ipAddress, macAddress, serialNumber, hardwareVersion, softwareVersion,
				creationDate, lastMessageDate, lastConfigurationDate, beginTime, endTime, checkFrequency, measurementUnit,
				dangerThreshold, positionX, positionY);
		this.flowId = flowId;
		this.detected = detected;
		this.wasSomeoneDetected = wasSomeoneDetected;
		this.measurementDate = measurementDate;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public Integer getDetected() {
		return detected;
	}

	public void setDetected(Integer detected) {
		this.detected = detected;
	}

	public Boolean getWasSomeoneDetected() {
		return (detected == 1);
	}

	public void setWasSomeoneDetected(Boolean wasSomeoneDetected) {
		this.wasSomeoneDetected = wasSomeoneDetected;
	}

	public Timestamp getMeasurementDate() {
		return measurementDate;
	}

	public void setMeasurementDate(Timestamp measurementDate) {
		this.measurementDate = measurementDate;
	}
	
}
