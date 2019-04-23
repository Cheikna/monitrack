package com.monitrack.enumeration;


public enum SensorType {
	
	//FIXME Why do not I remove the english label and make a "this.toString().toLowerCase()" in the constructor ?
	SMOKE("Smoke", "Fumée"),	
	INPUT_OUTPUT("Input/Output", "Entrée/Sortie"),	
	FLOW("Flow", "Présence"),	
	DOOR("Door", "Porte"),	
	TEMPERATURE("Temperature", "Température"),	
	WINDOW("Window", "Fenêtre"),	
	HUMIDITY("Humidity", "Humidité"),	
	LIGHT("Light", "Lumière"),
	GAS("Gas", "Gaz"),
	GLASS_BREAKAGE("Glass breakage", "Bris de vitre"),
	ACOUSTIC("Acoustic", "Sonore"),
	MANUAL_TRIGGER("Manuel trigger", "Déclencheur d'alarme manuel"),
	ACCESS_CONTROL("Access controle", "Contrôle d'accès"),
	FLOOD("Flood", "Inondation");
	
	
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

	public static SensorType getSensorType(String sensorType)
	{
		SensorType[] values = SensorType.values();
		for(SensorType value : values)
		{
			if(value.toString().equalsIgnoreCase(sensorType))
				return value;
		}
		return null;
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
