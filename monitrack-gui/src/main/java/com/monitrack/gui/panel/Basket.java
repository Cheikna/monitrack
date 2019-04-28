package com.monitrack.gui.panel;

import java.util.ArrayList;

public class Basket {
	ArrayList<LigneCommande> alCommandLine;

	public Basket()
	{
		super();
		this.alCommandLine = new ArrayList<LigneCommande>();
	}

	public String toString()
	{
		if(this.alCommandLine.isEmpty())
		{
			return "Panier vide";
		}
		String résultat = "";
		for (LigneCommande ligneCommande : alCommandLine)
		{
			résultat += ligneCommande.getArc().getNom()+"\n";
		}
		return résultat;
	}

	public void ajouter(ArchitectureBuilder arc)
	{
		LigneCommande lc = new LigneCommande(arc);
		this.alCommandLine.add(lc);
	}
	
	public void supprimer(ArchitectureBuilder arc)
	{
		for (LigneCommande ligneCommande : alCommandLine)
		{
			if(arc == ligneCommande.getArc())
			{
				this.alCommandLine.remove(ligneCommande);
				return;
			}
		}
	}

	public void vider()
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
		panier.ajouter(arc1);
		panier.ajouter(arc2);
		panier.ajouter(arc3);
		System.out.println(panier);


		System.out.println("4 : on ajoute au panier 1 produit1");
		panier.ajouter(arc1);
		System.out.println(panier);


		System.out.println("5 : on supprime dans le panier le produit3");
		panier.supprimer(arc3);
		System.out.println(panier);

		System.out.println("6 : on vide le panier");
		panier.vider();
		System.out.println(panier);

	}


}
