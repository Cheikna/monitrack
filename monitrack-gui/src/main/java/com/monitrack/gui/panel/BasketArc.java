package com.monitrack.gui.panel;

import java.util.ArrayList;



public class BasketArc {
	ArrayList<CommandLineArc> alCommandLineArc;

	public BasketArc()
	{
		super();
		this.alCommandLineArc = new ArrayList<CommandLineArc>();
	}

	public String toString()
	{
		if(this.alCommandLineArc.isEmpty())
		{
			return "Panier vide";
		}
		String résultat = "";
		for (CommandLineArc commandLine : alCommandLineArc)
		{
			résultat += commandLine+"\n";
		}
		return résultat;
	}

	public void addArc(ArchitectureBuilder arc)
	{
		CommandLineArc cl = new CommandLineArc(arc);
		this.alCommandLineArc.add(cl);
	}
	
	public void deleteArc(ArchitectureBuilder arc)
	{
		for (CommandLineArc commandLine : alCommandLineArc)
		{
			if(arc == commandLine.getArc())
			{
				this.alCommandLineArc.remove(commandLine);
				return;
			}
		}
	}
	public void clearBasket()
	{
		this.alCommandLineArc.clear();
	}
	
	public static void main(String[] args)
	{
		System.out.println("1 : création de trois arc");
		ArchitectureBuilder arc1 = new ArchitectureBuilder("Couloir", 44, 1, 3, true);
		ArchitectureBuilder arc2 = new ArchitectureBuilder("Piece", 20, 2, 1, false);
		ArchitectureBuilder arc3 = new ArchitectureBuilder("Piece", 50, 1, 2, true);
		System.out.println("arc 1 : "+ arc1);
		System.out.println("arc 2 : "+ arc2);
		System.out.println("arc 3 : "+ arc3);
		System.out.println();

		System.out.println("2 : création d'un panier");
		BasketArc panier = new BasketArc();
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
