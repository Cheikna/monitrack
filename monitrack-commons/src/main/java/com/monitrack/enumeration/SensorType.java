package com.monitrack.enumeration;


public enum SensorType {
	
	//FIXME Why do not I remove the english label and make a "this.toString().toLowerCase()" in the constructor ?
	SMOKE("smoke", "fum�e"),	
	INPUT_OUTPUT("input/output", "entr�e/sortie"),	
	FLOW("flow", "pr�sence"),	
	DOOR("door", "porte"),	
	TEMPERATURE("temperature", "temp�rature"),	
	WINDOW("window", "fen�tre"),	
	HUMIDITY("humidity", "humidit�"),	
	LIGHT("light", "lumi�re"),
	GAS("gas", "gaz"),
	GLASS_BREAKAGE("glass breakage", "bris de vitre"),
	ACOUSTIC("acoustic", "sonore"),
	MANUAL_TRIGGER("manuel trigger", "d�clencheur d'alarme manuel"),
	ACCESS_CONTROL("access controle", "contr�le d'acc�s"),
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
