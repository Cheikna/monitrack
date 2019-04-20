package com.monitrack.enumeration;


public enum SensorType {
	
	//FIXME Why do not I remove the english label and make a "this.toString().toLowerCase()" in the constructor ?
	SMOKE("smoke", "fumée"),	
	INPUT_OUTPUT("input/output", "entrée/sortie"),	
	FLOW("flow", "présence"),	
	DOOR("door", "porte"),	
	TEMPERATURE("temperature", "température"),	
	WINDOW("window", "fenêtre"),	
	HUMIDITY("humidity", "humidité"),	
	LIGHT("light", "lumière"),
	GAS("gas", "gaz"),
	GLASS_BREAKAGE("glass breakage", "bris de vitre"),
	ACOUSTIC("acoustic", "sonore"),
	MANUAL_TRIGGER("manuel trigger", "déclencheur d'alarme manuel"),
	ACCESS_CONTROL("access controle", "contrôle d'accès"),
	FLOOD("flood", "inondation");
	
	
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
