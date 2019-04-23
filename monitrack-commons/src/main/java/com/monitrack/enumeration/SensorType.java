package com.monitrack.enumeration;


public enum SensorType {
	
	//FIXME Why do not I remove the english label and make a "this.toString().toLowerCase()" in the constructor ?
	SMOKE("Smoke", "Fum�e"),	
	INPUT_OUTPUT("Input/Output", "Entr�e/Sortie"),	
	FLOW("Flow", "Pr�sence"),	
	DOOR("Door", "Porte"),	
	TEMPERATURE("Temperature", "Temp�rature"),	
	WINDOW("Window", "Fen�tre"),	
	HUMIDITY("Humidity", "Humidit�"),	
	LIGHT("Light", "Lumi�re"),
	GAS("Gas", "Gaz"),
	GLASS_BREAKAGE("Glass breakage", "Bris de vitre"),
	ACOUSTIC("Acoustic", "Sonore"),
	MANUAL_TRIGGER("Manuel trigger", "D�clencheur d'alarme manuel"),
	ACCESS_CONTROL("Access controle", "Contr�le d'acc�s"),
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
