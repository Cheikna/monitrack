package com.monitrack.enumeration;

public enum SensorType {
	
	SMOKE("somoke", "fum�e"),
	
	INPUT_OUTPUT("input/output", "entr�e/sortie"),
	
	FLOW("flow", "pr�sence");
	
	
	private String englishLabel;
	private String frenchLabel;	
	
	/**
	 * @param englishLabel
	 * @param frenchLabel
	 */
	SensorType(String englishLabel, String frenchLabel) {
		this.englishLabel = englishLabel;
		this.frenchLabel = frenchLabel;
	}


	/**
	 * @return the englishLabel
	 */
	public String getEnglishLabel() {
		return englishLabel;
	}


	/**
	 * @return the frenchLabel
	 */
	public String getFrenchLabel() {
		return frenchLabel;
	}
	
}