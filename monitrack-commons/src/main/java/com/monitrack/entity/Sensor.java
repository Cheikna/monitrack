package com.monitrack.entity;
import java.sql.Time;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class Sensor {
	
	@JsonProperty("sensor_id")
	private Integer id;
	@JsonProperty("sensor_activity")
	private SensorActivity sensorActivity;
	@JsonProperty("sensor_type")
	private SensorType sensorType;
	@JsonProperty("location_id")	
	private Integer locationId;
	@JsonProperty("ip_address")
	private String ipAddress;
	@JsonProperty("mac_address")
	private String macAddress;
	@JsonProperty("serial_number")
	private String serialNumber;
	@JsonProperty("hardware_version")
	private Float hardwareVersion;
	@JsonProperty("software_version")
	private Float softwareVersion;
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
	
	public Sensor() {
		//Empty constructor
	}
	
	public Sensor(Integer id, SensorActivity sensorActivity, SensorType sensorType, Integer locationId,
			String ipAddress, String macAddress, String serialNumber, Float hardwareVersion, Float softwareVersion,
			Timestamp creationDate, Timestamp lastMessageDate, Timestamp lastConfigurationDate, Time beginTime,
			Time endTime, Float checkFrequency, String measurementUnit, Float currentThreshold, Float minDangerThreshold,
			Float maxDangerThreshold, Float positionX,
			Float positionY) {
		this.id = id;
		this.sensorActivity = sensorActivity;
		this.sensorType = sensorType;
		this.locationId = locationId;
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
		this.serialNumber = serialNumber;
		this.hardwareVersion = hardwareVersion;
		this.softwareVersion = softwareVersion;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SensorActivity getSensorActivity() {
		return sensorActivity;
	}

	public void setSensorActivity(SensorActivity sensorActivity) {
		this.sensorActivity = sensorActivity;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	/*//FIXME In real world can a sensor change type ?
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}*/

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

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Float getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(Float hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public Float getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(Float softwareVersion) {
		this.softwareVersion = softwareVersion;
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

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", sensorActivity=" + sensorActivity + ", sensorType=" + sensorType
				+ ", locationId=" + locationId + ", ipAddress=" + ipAddress + ", macAddress=" + macAddress
				+ ", serialNumber=" + serialNumber + ", hardwareVersion=" + hardwareVersion + ", softwareVersion="
				+ softwareVersion + ", creationDate=" + creationDate + ", lastMessageDate=" + lastMessageDate
				+ ", lastConfigurationDate=" + lastConfigurationDate + ", beginTime=" + beginTime + ", endTime="
				+ endTime + ", checkFrequency=" + checkFrequency + ", measurementUnit=" + measurementUnit
				+ ", maxDangerThreshold=" + maxDangerThreshold + ", positionX=" + positionX + ", positionY=" + positionY
				+ "]";
	}
	
	public boolean raiseDangerAlert() {
		return currentThreshold >= maxDangerThreshold || currentThreshold < minDangerThreshold;
	}
	
	public String getStateInfo() {
		//FIXME
		return "";
	}
	
}
