package com.monitrack.gui.panel;

import java.util.ArrayList;

import com.monitrack.entity.SensorShop;

public class Basket {
	ArrayList<CommandLine> alCommandLine;

	public Basket()
	{
		super();
		this.alCommandLine = new ArrayList<CommandLine>();
	}

	public String toString()
	{
		if(this.alCommandLine.isEmpty())
		{
			return "Panier vide";
		}
		String résultat = "";
		for (CommandLine commandLine : alCommandLine)
		{
			résultat += commandLine.getArc().getNom()+"\n";
		}
		return résultat;
	}

	public void addArc(ArchitectureBuilder arc)
	{
		CommandLine cl = new CommandLine(arc);
		this.alCommandLine.add(cl);
	}
	
	public void deleteArc(ArchitectureBuilder arc)
	{
		for (CommandLine commandLine : alCommandLine)
		{
			if(arc == commandLine.getArc())
			{
				this.alCommandLine.remove(commandLine);
				return;
			}
		}
	}
	
	public void addSensor(SensorShop sensor)
	{
		CommandLine lc = new CommandLine(sensor);
		this.alCommandLine.add(lc);
	}
	
	public void deleteArc(SensorShop sensor)
	{
		for (CommandLine commandLine : alCommandLine)
		{
			if(sensor == commandLine.getSensor())
			{
				this.alCommandLine.remove(commandLine);
				return;
			}
		}
	}
	public void clearBasket()
	{
		this.alCommandLine.clear();
	}
	
	public static void main(String[] args)
	{
		System.out.println("1 : création de trois arc");
		ArchitectureBuilder arc1 = new ArchitectureBuilder("Couloir", 44);
		ArchitectureBuilder arc2 = new ArchitectureBuilder("Piece", 20);
		ArchitectureBuilder arc3 = new ArchitectureBuilder("Piece", 50);
		System.out.println("arc 1 : "+ arc1);
		System.out.println("arc 2 : "+ arc2);
		System.out.println("arc 3 : "+ arc3);
		System.out.println();

		System.out.println("2 : création d'un panier");
		Basket panier = new Basket();
		System.out.println(panier);

		System.out.println("3 : ajout au panier de arc1, arc2, arc3");
		panier.addArc(arc1);
		panier.addArc(arc2);
		panier.addArc(arc3);
		System.out.println(panier);


		System.out.println("4 : on ajoute au panier 1 produit1");
		panier.addArc(arc1);
		System.out.println(panier);


		System.out.println("5 : on supprime dans le panier le produit3");
		panier.deleteArc(arc3);
		System.out.println(panier);

		System.out.println("6 : on vide le panier");
		panier.clearBasket();
		System.out.println(panier);

	}


}
