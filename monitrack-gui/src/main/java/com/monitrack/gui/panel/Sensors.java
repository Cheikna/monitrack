package com.monitrack.gui.panel;


public class Sensors {
	private String sensorMark;
	private String sensorType;
	private double sensorPrice;
	private int sensorInterviewPrice;

	
	public Sensors(String sensorMark, String sensorType, double sensorPrice, int sensorInterviewPrice) {
		super();
		this.sensorMark = sensorMark;
		this.sensorType = sensorType;
		this.sensorPrice = sensorPrice;
		this.sensorInterviewPrice = sensorInterviewPrice;
	}
	
	public String toString(){
		return "Sensor = [Marque = "+sensorMark+" Type = "+sensorType+" Prix = "+sensorPrice+" Cout de la maintenance = "+sensorInterviewPrice+"]";
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
	 * @return the sensorType
	 */
	public String getSensorType() {
		return sensorType;
	}

	/**
	 * @param sensorType the sensorType to set
	 */
	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
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
		Sensors s1 = new Sensors("TOSHIBA", "Capteur d'humidité", 14.50, 160);
		System.out.println(s1);
	}

}
