package com.monitrack.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class History {
	
	private int idHistory;
	private int idSensorSource;
	private String description;
	private Timestamp date;
	private String actionsDone;
	
	public History(int idSensorSource, String description, Timestamp date, String actionsDone) {
		this(0, idSensorSource, description, date, actionsDone);
	}
	
	public History(int idHistory, int idSensorSource, String description, Timestamp date, String actionsDone) {
		this.idHistory = idHistory;
		this.idSensorSource = idSensorSource;
		this.description = description;
		this.date = date;
		this.actionsDone = actionsDone;
	}

	@JsonGetter("id_history")
	public int getIdHistory() {
		return idHistory;
	}

	@JsonSetter("id_history")
	public void setIdHistory(int idHistory) {
		this.idHistory = idHistory;
	}

	@JsonGetter("id_sensor_source")
	public int getIdSensorSource() {
		return idSensorSource;
	}

	@JsonSetter("id_sensor_source")
	public void setIdSensorSource(int idSensorSource) {
		this.idSensorSource = idSensorSource;
	}

	@JsonGetter("description")
	public String getDescription() {
		return description;
	}

	@JsonSetter("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonGetter("date")
	public Timestamp getDate() {
		return date;
	}

	@JsonSetter("date")
	public void setDate(Timestamp date) {
		this.date = date;
	}

	@JsonGetter("actions_done")
	public String getActionsDone() {
		return actionsDone;
	}

	@JsonSetter("actions_done")
	public void setActionsDone(String actionsDone) {
		this.actionsDone = actionsDone;
	}

}
