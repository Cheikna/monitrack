package com.monitrack.entity;
import java.sql.Time;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class InputOutput extends Sensor {
	
	@JsonProperty("input_output_id")
	private Integer inputOutputId;
	@JsonProperty("number_of_output")
	private Integer numberOfOutput;
	@JsonProperty("number_of_input")
	private Integer numberOfInput;
	@JsonProperty("day")
	private Timestamp day;
	
	
	public InputOutput(Integer id, SensorActivity sensorActivity, Integer locationId,
			String ipAddress, String macAddress, String serialNumber, Float hardwareVersion, Float softwareVersion,
			Timestamp creationDate, Timestamp lastMessageDate, Timestamp lastConfigurationDate, Time beginTime,
			Time endTime, Float checkFrequency, String measurementUnit, Float dangerThreshold, Float positionX,
			Float positionY, Integer inputOutputId, Integer numberOfOutput, Integer numberOfInput, Timestamp day) {
		super(id, sensorActivity, SensorType.INPUT_OUTPUT, locationId, ipAddress, macAddress, serialNumber, hardwareVersion, softwareVersion,
				creationDate, lastMessageDate, lastConfigurationDate, beginTime, endTime, checkFrequency, measurementUnit,
				dangerThreshold, positionX, positionY);
		this.inputOutputId = inputOutputId;
		this.numberOfOutput = numberOfOutput;
		this.numberOfInput = numberOfInput;
		this.day = day;
	}

	public Integer getInputOutputId() {
		return inputOutputId;
	}

	public void setInputOutputId(Integer inputOutputId) {
		this.inputOutputId = inputOutputId;
	}

	public Integer getNumberOfOutput() {
		return numberOfOutput;
	}

	public void setNumberOfOutput(Integer numberOfOutput) {
		this.numberOfOutput = numberOfOutput;
	}

	public Integer getNumberOfInput() {
		return numberOfInput;
	}

	public void setNumberOfInput(Integer numberOfInput) {
		this.numberOfInput = numberOfInput;
	}

	public Timestamp getDay() {
		return day;
	}

	public void setDay(Timestamp day) {
		this.day = day;
	}	
	
}
