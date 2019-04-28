package com.monitrack.gui.panel;

import com.monitrack.entity.SensorShop;

public class LigneCommande {
	private ArchitectureBuilder arc;
	private SensorShop sensor;
	
	public LigneCommande(ArchitectureBuilder arc){
		super();
		this.arc = arc;
	}
	
	public LigneCommande(SensorShop sensor){
		super();
		this.sensor = sensor;
	}

	/**
	 * @return the arc
	 */
	public ArchitectureBuilder getArc() {
		return arc;
	}

	/**
	 * @param arc the arc to set
	 */
	public void setArc(ArchitectureBuilder arc) {
		this.arc = arc;
	}
	
	public SensorShop getSensor() {
		return sensor;
	}

	public void setSensor(SensorShop sensor) {
		this.sensor = sensor;
	}
	
	public String toString()
	{
		return "LigneCommande [architecureBuilder=" + arc + "]";
	}
	
	public String sensorToString()
	{
		return "LigneCommande [sensor ="+sensor+"]";
	}

	public static void main(String[] args)
	{
		System.out.println("1 : création de trois arc arc1, arc2, arc3");
		ArchitectureBuilder arc1 = new ArchitectureBuilder("Couloir", 22);
		System.out.println("arc1 : "+arc1);
		ArchitectureBuilder arc2 = new ArchitectureBuilder("Piece", 33);
		System.out.println("arc2 : "+arc2);
		ArchitectureBuilder arc3 = new ArchitectureBuilder("Piece", 50);
		System.out.println("arc3 : "+arc3);

		System.out.println("\n2 : création d'une ligne de commande lc de l'architectureBuilder arc1");
		LigneCommande lc1 = new LigneCommande(arc1);
		System.out.println("lc1 : "+lc1);
		System.out.println("Nom de l'arc1 = "+arc1.getNom());
		System.out.println("Superficie de l'arc1 = "+arc1.getArea());

		System.out.println("\n3 : lc représente maintenant une ligne de l'architectBuilder arc2");
		LigneCommande lc2 = new LigneCommande(arc2);
		System.out.println("lc2 : "+lc2);
		System.out.println("Nom de l'arc2 = "+arc2.getNom());
		System.out.println("Superficie de l'arc2 = "+arc2.getArea());

		System.out.println("\n4 : lc représente maintenant une ligne darc arc3");
		LigneCommande lc3 = new LigneCommande(arc3);
		System.out.println("lc3 : "+lc3);
		System.out.println("Nom de l'arc3 = "+arc3.getNom());
		System.out.println("Superficie de l'arc3 = "+arc3.getArea());
	}



}
