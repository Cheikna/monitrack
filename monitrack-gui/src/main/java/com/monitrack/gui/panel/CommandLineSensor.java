package com.monitrack.gui.panel;
import com.monitrack.entity.SensorShop;
import com.monitrack.enumeration.SensorType;

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
		return "LigneCommande [quantité"+quantity+" "+"sensor ="+sensor+"]";
	}
	
	public static void main(String[] args)
	{
		System.out.println("1 : création de trois capteur s1 s2 s3");
		/*SensorShop s1 = new SensorShop(1,"TOSHIBA", SensorType.HUMIDITY, "00:ff:3c:d9", "hjqf64", 1.0f, 2.0f, 14.50, 100);
		System.out.println("s1 : "+s1);
		SensorShop s2 = new SensorShop(1,"SAMSUNG", SensorType.ACOUSTIC, "00:ff:3d:f4", "lkjl67", 1.0f, 2.0f, 23.99, 80);
		System.out.println("s2 : "+s2);
		SensorShop s3 = new SensorShop(1,"SONY", SensorType.DOOR, "00:ff:3f:a9", "jlknh72", 1.0f, 2.0f, 9.99, 120);
		System.out.println("s3 : "+s3);

		System.out.println("\n2 : création d'une ligne de commande lc du capteur s1");
		CommandLineSensor lc1 = new CommandLineSensor(1, s1);
		System.out.println("lc1 : "+lc1.toString());
		System.out.println("Marque de l's1 = "+s1.getSensorMark());
		System.out.println("Prix de s1 = "+s1.getSensorPrice());*/


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
