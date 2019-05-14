package com.monitrack.entity;
import java.sql.Time;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorConfiguration extends Sensor {
	
	@JsonProperty("sensor_configuration_id")
	private Integer sensorConfigurationId;
	@JsonProperty("sensor_id")
	private Integer sensorId;
	@JsonProperty("location_id")	
	private Integer locationId;
	@JsonProperty("ip_address")
	private String ipAddress;
	@JsonProperty("creation_date")
	private Timestamp creationDate;
	@JsonProperty("date_of_last_message")
	private Timestamp lastMessageDate;
	@JsonProperty("last_configuration_date")
	private Timestamp lastConfigurationDate;
	@JsonProperty("time_of_begin_activity")
	private Time beginTime;
	@JsonProperty("time_of_end_activity")
	private Time endTime;
	//In milliseconds
	@JsonProperty("check_frequency")
	private Float checkFrequency;
	@JsonProperty("measurement_unit")
	private String measurementUnit;	
	@JsonProperty("current_threshold")
	private Float currentThreshold;
	@JsonProperty("min_danger_threshold")
	private Float minDangerThreshold;
	@JsonProperty("max_danger_threshold")
	private Float maxDangerThreshold;
	@JsonProperty("position_x")
	private Float positionX;
	@JsonProperty("position_y")
	private Float positionY;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("sensor_type")
	private SensorType sensorType;
	@JsonProperty("sensor_state")
	private SensorState sensorState;

	public SensorConfiguration(Integer sensorConfigurationId, Integer sensorId, SensorType sensorType, Integer locationId,
			String ipAddress,
			Timestamp creationDate, Timestamp lastMessageDate, Timestamp lastConfigurationDate, Time beginTime,
			Time endTime, Float checkFrequency, String measurementUnit, Float currentThreshold, Float minDangerThreshold,
			Float maxDangerThreshold, Float positionX,
			Float positionY) {
		this.sensorConfigurationId = sensorConfigurationId;
		this.locationId = locationId;
		this.ipAddress = ipAddress;
		this.creationDate = (creationDate != null) ? creationDate : new Timestamp(System.currentTimeMillis());
		this.lastMessageDate = lastMessageDate;
		this.lastConfigurationDate = lastConfigurationDate;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.checkFrequency = checkFrequency;
		this.measurementUnit = measurementUnit;
		this.currentThreshold = currentThreshold;
		this.minDangerThreshold = minDangerThreshold;
		this.maxDangerThreshold = maxDangerThreshold;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public SensorConfiguration() {}
	
	public Integer getSensorConfigurationId() {
		return sensorConfigurationId;
	}

	public void setSensorConfigurationId(Integer sensorConfigurationId) {
		this.sensorConfigurationId = sensorConfigurationId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getLastMessageDate() {
		return lastMessageDate;
	}

	public void setLastMessageDate(Timestamp lastMessageDate) {
		this.lastMessageDate = lastMessageDate;
	}

	public Timestamp getLastConfigurationDate() {
		return lastConfigurationDate;
	}

	public void setLastConfigurationDate(Timestamp lastConfigurationDate) {
		this.lastConfigurationDate = lastConfigurationDate;
	}

	public Time getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Float getCheckFrequency() {
		return checkFrequency;
	}

	public void setCheckFrequency(Float checkFrequency) {
		this.checkFrequency = checkFrequency;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public Float getCurrentThreshold() {
		return currentThreshold;
	}

	public void setCurrentThreshold(Float currentThreshold) {
		this.currentThreshold = currentThreshold;
	}

	public Float getMinDangerThreshold() {
		return minDangerThreshold;
	}

	public void setMinDangerThreshold(Float minDangerThreshold) {
		this.minDangerThreshold = minDangerThreshold;
	}

	public Float getMaxDangerThreshold() {
		return maxDangerThreshold;
	}

	public void setMaxDangerThreshold(Float maxDangerThreshold) {
		this.maxDangerThreshold = maxDangerThreshold;
	}

	public Float getPositionX() {
		return positionX;
	}

	public void setPositionX(Float positionX) {
		this.positionX = positionX;
	}

	public Float getPositionY() {
		return positionY;
	}

	public void setPositionY(Float positionY) {
		this.positionY = positionY;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	

	public Integer getSensorId() {
		return sensorId;
	}



	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}



	public SensorType getSensorType() {
		return sensorType;
	}



	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}



	public void setLastMessageDate() {
		this.setLastMessageDate(new Timestamp(System.currentTimeMillis()));
	}

	
	


	public SensorState getSensorState() {
		return sensorState;
	}

	public void setSensorState(SensorState sensorState) {
		this.sensorState = sensorState;
	}

	@Override
	public String toString() {
		return "SensorConfiguration [sensorConfigurationId=" + sensorConfigurationId + ", sensorId=" + sensorId
				+ ", ipAddress=" + ipAddress + ", creationDate=" + creationDate
				+ ", lastMessageDate=" + lastMessageDate + ", lastConfigurationDate=" + lastConfigurationDate
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + ", checkFrequency=" + checkFrequency
				+ ", measurementUnit=" + measurementUnit + ", currentThreshold=" + currentThreshold
				+ ", minDangerThreshold=" + minDangerThreshold + ", maxDangerThreshold=" + maxDangerThreshold
				+ ", positionX=" + positionX + ", positionY=" + positionY + ", location=" + location + ", sensorType="
				+ sensorType + "]";
	}
	
	
}
