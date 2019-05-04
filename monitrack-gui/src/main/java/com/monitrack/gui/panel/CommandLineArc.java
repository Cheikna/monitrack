package com.monitrack.gui.panel;


public class CommandLineArc {
	private ArchitectureBuilder arc;
	private double numberOfSensor;
	
	public CommandLineArc(ArchitectureBuilder arc){
		super();
		this.arc = arc;
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
	
	
	public String toString()
	{
		return "Ligne de commande [architecureBuilder=" +arc+ "]";
	}
	/**
	 * @return the numberOfSensor
	 */
	public double getNumberOfSensor(int scope) {
		double numberOfSensor = 0;
		numberOfSensor += arc.getArea() /scope;
		if((numberOfSensor * scope) < arc.getArea())
		{
			numberOfSensor +=1;
		}
		else{return Math.round(numberOfSensor);}
		return Math.round(numberOfSensor);
	}
	/**
	 * @param numberOfSensor the numberOfSensor to set
	 */
	public void setNumberOfSensor(double numberOfSensor) {
		this.numberOfSensor = numberOfSensor;
	}

	public static void main(String[] args)
	{
		System.out.println("1 : création de trois arc arc1, arc2, arc3");
		ArchitectureBuilder arc1 = new ArchitectureBuilder("Couloir", 68, 1, 4, true);
		System.out.println("arc1 : "+arc1);
		ArchitectureBuilder arc2 = new ArchitectureBuilder("Piece", 45, 2, 1, true);
		System.out.println("arc2 : "+arc2);
		ArchitectureBuilder arc3 = new ArchitectureBuilder("Piece", 20, 1, 2, false);
		System.out.println("arc3 : "+arc3);

		System.out.println("\n2 : création d'une ligne de commande lc de l'architectureBuilder arc1");
		CommandLineArc lc1 = new CommandLineArc(arc1);
		System.out.println("lc1 : "+lc1.toString());
		System.out.println("Nom de l'arc1= "+arc1.getNom());
		System.out.println("Superficie de l'arc1= "+arc1.getArea());
		System.out.println("Nombre de portes de l'arc1= "+arc1.getDoorsNumber());
		System.out.println("Nombre de fenetres de l'arc1= "+arc1.getWindowsNumber());

		System.out.println("\n3 : lc représente maintenant une ligne de l'architectBuilder arc2");
		CommandLineArc lc2 = new CommandLineArc(arc2);
		System.out.println("lc2 : "+lc2.toString());
		System.out.println("Nom de l'arc2= "+arc2.getNom());
		System.out.println("Superficie de l'arc2= "+arc2.getArea());
		System.out.println("Nombre de portes de l'arc2= "+arc2.getDoorsNumber());
		System.out.println("Nombre de fenetres de l'arc2= "+arc2.getWindowsNumber());

		System.out.println("\n4 : lc représente maintenant une ligne darc arc3");
		CommandLineArc lc3 = new CommandLineArc(arc3);
		System.out.println("lc3 : "+lc3.toString());
		System.out.println("Nom de l'arc3= "+arc3.getNom());
		System.out.println("Superficie de l'arc3= "+arc3.getArea());
		System.out.println("Nombre de portes de l'arc3= "+arc3.getDoorsNumber());
		System.out.println("Nombre de fenetres de l'arc3= "+arc3.getWindowsNumber());
		System.out.println(lc1.getNumberOfSensor(20));
		System.out.println(lc2.getNumberOfSensor(20));
		System.out.println(lc3.getNumberOfSensor(20));
	}
}
