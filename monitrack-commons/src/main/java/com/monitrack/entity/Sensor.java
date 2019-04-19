package com.monitrack.entity;

import java.sql.Time;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.monitrack.enumeration.SensorConfiguration;
import com.monitrack.enumeration.SensorType;
import com.monitrack.util.MessageSender;


public abstract class Sensor {
	
	protected Integer id;
	protected SensorConfiguration sensorConfiguration;
	protected SensorType sensorType;	
	protected Location location;
	protected String ipAddress;
	protected String macAddress;
	protected float alertThreshold;
	protected Timestamp timeChange;
	protected Time beginTime;
	protected Time endTime;
	protected Timestamp creationDate;
	protected Timestamp lastMessageDate;
	protected MessageSender messageSender;

	public Sensor() {
		// TODO Auto-generated constructor stub
	}

	

	public Sensor(Integer id, SensorConfiguration sensorConfiguration, SensorType sensorType, Location location, String ipAddress,
			String macAddress, float alertThreshold, Timestamp timeChange, Time beginTime2, Time endTime2,
			Timestamp creationDate) {
		super();
		this.id = id;
		this.sensorConfiguration = sensorConfiguration;
		this.sensorType = sensorType;
		this.location = location;
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
		this.alertThreshold = alertThreshold;
		this.timeChange = timeChange;
		this.beginTime = beginTime2;
		this.endTime = endTime2;
		this.creationDate = creationDate;
	}



	@JsonGetter("id")
	public Integer getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonGetter("configuration")
	public SensorConfiguration getsensorConfiguration() {
		return sensorConfiguration;
	}

	@JsonSetter("configuration")
	public void setsensorConfiguration(SensorConfiguration sensorConfiguration) {
		this.sensorConfiguration = sensorConfiguration;
	}

	@JsonGetter("type")
	public SensorConfiguration getSensorType() {
		return sensorConfiguration;
	}

	@JsonSetter("type")
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	@JsonGetter("location")
	public Location getLocation() {
		return location;
	}
	
	@JsonSetter("location")
	public void setLocation(Location location) {
		this.location = location;
	}

	@JsonGetter("ip_address")
	public String getIpAddress() {
		return ipAddress;
	}

	@JsonSetter("ip_address")
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@JsonGetter("mac_address")
	public String getMacAddress() {
		return macAddress;
	}

	@JsonSetter("mac_address")
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	@JsonSetter("alert_treshold")
	public float getAlertThreshold() {
		return alertThreshold;
	}

	@JsonSetter("alert_treshold")
	public void setAlertThreshold(float alertThreshold) {
		this.alertThreshold = alertThreshold;
	}

	@JsonSetter("time_change")
	public Timestamp getTimeChange() {
		return timeChange;
	}

	@JsonSetter("time_change")
	public void setTimeChange(Timestamp timeChange) {
		this.timeChange = timeChange;
	}

	@JsonSetter("begin_time")
	public Time getBeginTime() {
		return beginTime;
	}

	@JsonSetter("begin_time")
	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}

	@JsonSetter("end_time")
	public Time getEndTime() {
		return endTime;
	}

	@JsonSetter("end_time")
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@JsonGetter("creation_date")
	public Timestamp getCreationDate() {
		return creationDate;
	}

	@JsonSetter("creation_date")
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@JsonGetter("last_message_date")
	public Timestamp getLastMessageDate() {
		return lastMessageDate;
	}


	@JsonSetter("last_message_date")
	public void setLastMessageDate(Timestamp lastMessageDate) {
		this.lastMessageDate = lastMessageDate;
	}

}
