package com.monitrack.gui.panel;

public class ArchitectureBuilder {
	private String nom;
	private int area;
	private int doorsNumber;
	private int windowsNumber;
	private boolean accessControl;
	//private ArrayList<Sensor>;
	
	public ArchitectureBuilder(String nom, int area, int doorsNumber, int windowsNumber, boolean accessControl){
		super();
		this.nom = nom;
		this.area = area;
		this.doorsNumber = doorsNumber;
		this.windowsNumber = windowsNumber;
		this.accessControl = accessControl;
		
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
		return "Pièce = [nom=" + nom + ", area= " + area +"m², nombre de portes= "+doorsNumber+", nombre de fenêtres= "+windowsNumber+"]";
	}
	//TEST
	public static void main(String[] args){
		ArchitectureBuilder arc1 = new ArchitectureBuilder("couloir",22,2,4, true);
		System.out.println("premiere piece :"+arc1);
		ArchitectureBuilder arc2 = new ArchitectureBuilder("piece",46,1,2, false);
		System.out.println("deuxieme piece :"+arc2);
		ArchitectureBuilder arc3 = new ArchitectureBuilder("couloir",22,1,3, false);
		System.out.println("troisieme piece :"+arc3);
	}

	/**
	 * @return the doorsNumber
	 */
	public int getDoorsNumber() {
		return doorsNumber;
	}

	/**
	 * @param doorsNumber the doorsNumber to set
	 */
	public void setDoorsNumber(int doorsNumber) {
		this.doorsNumber = doorsNumber;
	}

	/**
	 * @return the windowsNumber
	 */
	public int getWindowsNumber() {
		return windowsNumber;
	}

	/**
	 * @param windowsNumber the windowsNumber to set
	 */
	public void setWindowsNumber(int windowsNumber) {
		this.windowsNumber = windowsNumber;
	}

	/**
	 * @return the accessControl
	 */
	public boolean isAccessControl() {
		return accessControl;
	}

	/**
	 * @param accessControl the accessControl to set
	 */
	public void setAccessControl(boolean accessControl) {
		this.accessControl = accessControl;
	}
	
}
