package com.monitrack.enumeration;


public enum SensorType {
	
	//FIXME - Cheikna : Why do not I remove the english label and make a "this.toString().toLowerCase()" in the constructor ?
	SMOKE("Smoke", "Fum�e", 2, 3),	
	FLOW("Flow", "Pr�sence", 5, 7),	
	DOOR("Door", "Porte", 11, 13),	
	TEMPERATURE("Temperature", "Temp�rature", 17,19),	
	WINDOW("Window", "Fen�tre", 23,29),	
	HUMIDITY("Humidity", "Humidit�", 31, 37),	
	LIGHT("Light", "Lumi�re", 41,43),
	GAS("Gas", "Gaz", 47,53),
	MANUAL_TRIGGER("Manuel alarm trigger", "D�clencheur d'alarme manuel", 73,79),
	ACCESS_CONTROL("Access controle", "Contr�le d'acc�s", 83,89),
	FLOOD("Flood", "Inondation", 97, 101);
	
	// Checks if each multiplication of two numbers of more give a different number
	
	private String englishLabel;
	private String frenchLabel;	
	private Integer normalCode;
	private Integer dangerCode;
	
	/**
	 * @param englishLabel
	 * @param frenchLabel
	 */
	SensorType(String englishLabel, String frenchLabel, Integer normalCode, Integer dangerCode) {
		this.englishLabel = englishLabel;
		this.frenchLabel = frenchLabel;
		this.normalCode = normalCode;
		this.dangerCode = dangerCode;
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

	public String getEnglishLabel() {
		return englishLabel;
	}
	
	public String getFrenchLabel() {
		return frenchLabel;
	}

	public Integer getNormalCode() {
		return normalCode;
	}

	public Integer getDangerCode() {
		return dangerCode;
	}
}
