package com.monitrack.gui.panel;

import java.util.ArrayList;
import java.util.List;

import com.monitrack.entity.SensorShop;
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
		String résultat = "";
		for (CommandLineSensor commandLine : alCommandLineSensor)
		{
			résultat += commandLine+"\n";
		}
		return résultat;
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
	
	public static void main(String[] args)
	{
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorShop.class, null, null, null,
					null);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			// Retrieves all the sensor from the database
			List<SensorShop> shops = (List<SensorShop>) JsonUtil.deserializeObject(response);
			System.out.println("1 : création de trois arc");
			SensorShop s1 = shops.get(0);
			SensorShop s2 = shops.get(1);
			SensorShop s3 = shops.get(2);
			System.out.println("arc 1 : " + s1);
			System.out.println("arc 2 : " + s2);
			System.out.println("arc 3 : " + s3);
			System.out.println();
			System.out.println("2 : création d'un panier");
			BasketSensor bsk = new BasketSensor();
			System.out.println(bsk);

			System.out.println("3 : ajout au panier de s1, S2, S3");
			bsk.addSensor(1, s1);
			bsk.addSensor(3, s2);;
			bsk.addSensor(2, s3);
			System.out.println(bsk);
			


			System.out.println("4 : on ajoute au panier 1 produit1");
			bsk.addSensor(3, s1);;
			System.out.println(bsk);
		} catch (Exception e) {
			e.printStackTrace();
		}



//
//
//		System.out.println("5 : on supprime dans le panier le produit3");
//		panier.deleteArc(arc3);
//		System.out.println(panier);
//
//		System.out.println("6 : on vide le panier");
//		panier.clearBasket();
//		System.out.println(panier);

	}


}
