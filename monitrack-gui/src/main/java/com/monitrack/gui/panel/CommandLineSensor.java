package com.monitrack.gui.panel;
import java.util.List;

import com.monitrack.entity.SensorShop;
import com.monitrack.enumeration.RequestType;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class CommandLineSensor {
	private int quantity;
	private SensorShop sensor;
	
	public CommandLineSensor(int quantity, SensorShop sensor){
		super();
		this.quantity = quantity;
		this.sensor = sensor;
	}

	public String toString()
	{
		return "LigneCommande [[quantité= "+quantity+"] - sensor =["+sensor+"]]";
	}
	
	public double totalSensorPrice()
	{
		double résultat = 0;
		résultat = this.quantity*this.sensor.getSensorPrice();
		return résultat;
	}

	//MontantTTC
	public double totalSensorInterviewPrice()
	{
		double résultat = 0;
		résultat = this.quantity*this.sensor.getSensorInterviewPrice();
		return résultat;
	}
	
	public static void main(String[] args)
	{
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorShop.class, null, null, null,
					null);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			// Retrieves all the sensor from the database
			@SuppressWarnings("unchecked")
			List<SensorShop> shops = (List<SensorShop>) JsonUtil.deserializeObject(response);
			System.out.println("1 : création d'un capteurs s1");
			SensorShop s1 = shops.get(0);
			System.out.println(s1);
			System.out.println("\n2 : création d'une ligne de commande lc du capteur s1");
			CommandLineSensor lc1 = new CommandLineSensor(3, s1);
			System.out.println("lc1 : " + lc1.toString());
			System.out.println("Marque de s1 = " + s1.getSensorMark());
			System.out.println("Prix de s1 = " + s1.getSensorPrice());
			System.out.println("prix de la lc : " + lc1.totalSensorPrice() + "€");
			System.out.println("Durée de vie moyenne de s1:"+s1.getLifeTime());
			System.out.println("Classe énergétique de s1 : "+s1.getEnergy().getLabel());
			System.out.println("cout de maintenance de la lc : " + lc1.totalSensorInterviewPrice() + "€");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the sensor
	 */
	public SensorShop getSensor() {
		return sensor;
	}

	/**
	 * @param sensor the sensor to set
	 */
	public void setSensor(SensorShop sensor) {
		this.sensor = sensor;
	}	

}
