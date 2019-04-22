package com.monitrack.entity;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorHistory {
	
	@JsonProperty("history_id")
	private int idHistory;
	@JsonProperty("sensor_id")
	private int idSensorSource;
	@JsonProperty("measured_threshold")
	private Float measuredThreshold;
	@JsonProperty("danger_threshold")
	private Float dangerThreshlod;
	@JsonProperty("measurement_date")
	private Timestamp date;
	@JsonProperty("description")
	private String description;
	@JsonProperty("actions_done")
	private String actionsDone;
	
	public SensorHistory() {
	}

	public SensorHistory(int idHistory, int idSensorSource, Float measuredThreshold, Float dangerThreshlod,
			Timestamp date, String description, String actionsDone) {
		this.idHistory = idHistory;
		this.idSensorSource = idSensorSource;
		this.measuredThreshold = measuredThreshold;
		this.dangerThreshlod = dangerThreshlod;
		this.date = date;
		this.description = description;
		this.actionsDone = actionsDone;
	}

	public int getIdHistory() {
		return idHistory;
	}

	public void setIdHistory(int idHistory) {
		this.idHistory = idHistory;
	}

	public int getIdSensorSource() {
		return idSensorSource;
	}

	public void setIdSensorSource(int idSensorSource) {
		this.idSensorSource = idSensorSource;
	}

	public Float getMeasuredThreshold() {
		return measuredThreshold;
	}

	public void setMeasuredThreshold(Float measuredThreshold) {
		this.measuredThreshold = measuredThreshold;
	}

	public Float getDangerThreshlod() {
		return dangerThreshlod;
	}

	public void setDangerThreshlod(Float dangerThreshlod) {
		this.dangerThreshlod = dangerThreshlod;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActionsDone() {
		return actionsDone;
	}

	public void setActionsDone(String actionsDone) {
		this.actionsDone = actionsDone;
	}

}
