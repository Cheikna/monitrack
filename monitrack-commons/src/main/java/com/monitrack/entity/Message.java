package com.monitrack.entity;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	
	@JsonProperty("sensor_id")
	private Integer sensorId;
	@JsonProperty("threshold_reached")
	private Float thresholdReached;
	@JsonProperty("message_creation_date")
	private Timestamp creationDate;

	public Message(Integer sensorId, Float thresholdReached) {
		this.sensorId = sensorId;
		this.thresholdReached = thresholdReached;
		this.creationDate = new Timestamp(System.currentTimeMillis());
	}
	
	public Message() { }

	public Integer getSensorId() {
		return sensorId;
	}

	public Float getThresholdReached() {
		return thresholdReached;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}	

}
