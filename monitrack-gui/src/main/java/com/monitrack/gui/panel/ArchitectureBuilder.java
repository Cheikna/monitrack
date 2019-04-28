package com.monitrack.gui.panel;

public class ArchitectureBuilder {
	private String nom;
	private int area;
	private int stages;
	//private ArrayList<Sensor>;
	
	public ArchitectureBuilder(String nom, int area){
		super();
		this.nom = nom;
		this.area = area;
		
		
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the area
	 */
	public int getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(int area) {
		this.area = area;
	}

	/**
	 * @return the stages
	 */

	
	public String toString()
	{
		return "architecture [nom=" + nom + ", area= " + area +"m²]";
	}
	//TEST
	public static void main(String[] args){
		ArchitectureBuilder arc1 = new ArchitectureBuilder("couloir",22);
		System.out.println("premiere piece :"+arc1);
		ArchitectureBuilder arc2 = new ArchitectureBuilder("piece",46);
		System.out.println("deuxieme piece :"+arc2);
		ArchitectureBuilder arc3 = new ArchitectureBuilder("couloir",22);
		System.out.println("troisieme piece :"+arc3);
	}
}
