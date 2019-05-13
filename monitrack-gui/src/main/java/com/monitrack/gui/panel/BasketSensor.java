package com.monitrack.gui.panel;

import java.util.ArrayList;
import java.util.List;

import com.monitrack.entity.SensorShop;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;


public class BasketSensor {
	ArrayList<CommandLineSensor> alCommandLineSensor;

	public BasketSensor()
	{
		super();
		this.alCommandLineSensor = new ArrayList<CommandLineSensor>();
	}

	public String toString()
	{
		if(this.alCommandLineSensor.isEmpty())
		{
			return "Panier vide";
		}
		String result = "";
		for (CommandLineSensor commandLine : alCommandLineSensor)
		{
			result += commandLine+"\n";
		}
		return result;
	}
	
	public void addSensor(int sensorQuantity, SensorShop sensor)
	{
		// vérifier si le produit est déja dans la liste
		for (CommandLineSensor cmd : alCommandLineSensor)
		{
			if(sensor == cmd.getSensor())
			{
				cmd.setQuantity(cmd.getQuantity()+sensorQuantity);
				return;
			}
		}
		CommandLineSensor cl = new CommandLineSensor(sensorQuantity, sensor);
		this.alCommandLineSensor.add(cl);
	}
	
	public void modifySensorQuantity(int newQuantity, SensorShop sensor)
	{
		for (CommandLineSensor lc : alCommandLineSensor)
		{
			if(sensor == lc.getSensor())
			{
				lc.setQuantity(newQuantity);
				return;
			}
		}
	}
	
	public void deleteSensor(SensorShop sensor)
	{
		for (CommandLineSensor cl : alCommandLineSensor)
		{
			if(sensor == cl.getSensor())
			{
				this.alCommandLineSensor.remove(cl);
				return;
			}
		}
	}
	
	public void clearBasket()
	{
		this.alCommandLineSensor.clear();
	}
	
	public double totalBasketPrice(){
		double result = 0;
		for (CommandLineSensor cmd : alCommandLineSensor){
			
			if(this.alCommandLineSensor.isEmpty())
			{
				return result = 0;
			}
			result += cmd.totalSensorPrice();			;
		}
		return result;
		
	}
	
	public double totalBasketInterviewPrice(){
		double result = 0;
		for (CommandLineSensor cmd : alCommandLineSensor){
			
			if(this.alCommandLineSensor.isEmpty())
			{
				return result = 0;
			}
			result += cmd.totalSensorInterviewPrice();			;
		}
		return result;
	}
	
	public double totalProvisionnal(int year){
		double result = 0;
		for(CommandLineSensor cmd : alCommandLineSensor)
		{
			if(this.alCommandLineSensor.isEmpty())
			{
				return result = 0;
			}
			result += cmd.provisional(year);
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorShop.class, null, null, null,
					null, RequestSender.CLIENT);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			// Retrieves all the sensor from the database
			@SuppressWarnings("unchecked")
			List<SensorShop> shops = (List<SensorShop>) JsonUtil.deserializeObject(response);
			System.out.println("1 : création de trois capteurs");
			SensorShop s1 = shops.get(0);
			SensorShop s2 = shops.get(1);
			SensorShop s3 = shops.get(2);
			System.out.println("s1 : " + s1);
			System.out.println("s2 : " + s2);
			System.out.println("s3 : " + s3);
			System.out.println("2 : création d'un panier");
			BasketSensor bsk = new BasketSensor();
			System.out.println(bsk);

			System.out.println("3 : ajout au panier de s1, S2, S3");
			bsk.addSensor(1, s1);
			bsk.addSensor(3, s2);;
			bsk.addSensor(2, s3);
			System.out.println(bsk);
			System.out.println(bsk.totalBasketPrice());
			System.out.println(bsk.totalBasketInterviewPrice());
			System.out.println(bsk.totalProvisionnal(30));
			


			System.out.println("4 : on ajoute au panier 3 produit1");
			bsk.addSensor(3, s1);;
			System.out.println(bsk);
			System.out.println(bsk.totalBasketPrice());
			System.out.println(bsk.totalBasketInterviewPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
