package com.monitrack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.monitrack.enumeration.SensorType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorShop extends Sensor{
	
	private String sensorMark;
	private double sensorPrice;
	private int sensorInterviewPrice;

	
	public SensorShop(Integer sensorId, String sensorMark, SensorType sensorType, String macAddress, String serialNumber, Float hardwareVersion,
			Float softwareVersion, double sensorPrice, int sensorInterviewPrice) {
		super(sensorId, sensorType, macAddress, serialNumber, hardwareVersion, softwareVersion);
		this.sensorMark = sensorMark;
		this.sensorPrice = sensorPrice;
		this.sensorInterviewPrice = sensorInterviewPrice;
	}
	
	public String toString(){
		return "Sensor = [Marque = "+sensorMark+" Type = "+sensorType.getFrenchLabel()+"  Prix = "+sensorPrice+" Cout de la maintenance = "+sensorInterviewPrice+"]";
	}

	/**
	 * @return the sensorMark
	 */
	public String getSensorMark() {
		return sensorMark;
	}

	/**
	 * @param sensorMark the sensorMark to set
	 */
	public void setSensorMark(String sensorMark) {
		this.sensorMark = sensorMark;
	}

	/**
	 * @return the sensorPrice
	 */
	public double getSensorPrice() {
		return sensorPrice;
	}

	/**
	 * @param sensorPrice the sensorPrice to set
	 */
	public void setSensorPrice(double sensorPrice) {
		this.sensorPrice = sensorPrice;
	}

	/**
	 * @return the sensorInterviewPrice
	 */
	public double getSensorInterviewPrice() {
		return sensorInterviewPrice;
	}

	/**
	 * @param sensorInterviewPrice the sensorInterviewPrice to set
	 */
	public void setSensorInterviewPrice(int sensorInterviewPrice) {
		this.sensorInterviewPrice = sensorInterviewPrice;
	}
	
	public static void main(String[] args)
	{
		SensorShop s1 = new SensorShop(1,"TOSHIBA", SensorType.HUMIDITY, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f, 14.50, 160);
		System.out.println(s1);
	}

}
