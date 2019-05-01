package com.monitrack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.monitrack.enumeration.Energy;
import com.monitrack.enumeration.SensorType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorShop extends Sensor{
	
	private int sensorShopId;
	private String sensorMark;
	private float sensorPrice;
	private float sensorInterviewPrice;
	private Energy energy;
	private Integer lifeTime;
	
//	public SensorShop(String sensorMark, SensorType sensorType, double sensorPrice, int sensorInterviewPrice)
//	{
//		super();
//		this.sensorMark = sensorMark;
//		this.sensorType = sensorType;
//		this.sensorPrice = sensorPrice;
//		this.sensorInterviewPrice = sensorInterviewPrice;
//	}
	
	public SensorShop(Integer sensorShopId, Integer sensorId, String sensorMark, SensorType sensorType, String macAddress, String serialNumber, Float hardwareVersion,
			Float softwareVersion, float sensorPrice, float sensorInterviewPrice, Energy energy, Integer lifeTime) {
		super(sensorId, sensorType, macAddress, serialNumber, hardwareVersion, softwareVersion);
		this.sensorShopId = sensorShopId;
		this.sensorMark = sensorMark;
		this.sensorPrice = sensorPrice;
		this.sensorInterviewPrice = sensorInterviewPrice;
		this.energy = energy;
		this.lifeTime = lifeTime;
	}
	
	public SensorShop() {}
	
	public int getSensorShopId() {
		return sensorShopId;
	}

	public void setSensorShopId(int sensorShopId) {
		this.sensorShopId = sensorShopId;
	}



	public String toString(){
		return "Marque = "+sensorMark+" - Type = "+sensorType.getFrenchLabel()+"- Prix = "+sensorPrice+"€ - Cout de la maintenance = "+sensorInterviewPrice+"€/an - Classe énergétique : "+energy+" - Durée de vie moyenne : "+lifeTime;
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
	public void setSensorPrice(float sensorPrice) {
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
	
	
	public Energy getEnergy() {
		return energy;
	}

	public void setEnergy(Energy energy) {
		this.energy = energy;
	}
	
	

	public Integer getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(Integer lifeTime) {
		this.lifeTime = lifeTime;
	}

	public static void main(String[] args)
	{
		SensorShop s1 = new SensorShop(1 ,1,"TOSHIBA", SensorType.HUMIDITY, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f, 14.50f, 160f,Energy.A_PLUS, 7);
		System.out.println(s1);
	}

}
