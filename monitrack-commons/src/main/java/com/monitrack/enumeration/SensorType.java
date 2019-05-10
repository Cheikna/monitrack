package com.monitrack.enumeration;


public enum SensorType {

	//FIXME - Cheikna : Why do not I remove the english label and make a "this.toString().toLowerCase()" in the constructor ?
	SMOKE("Smoke", "Fumée", 2, 3, false, false),	
	FLOW("Flow", "Présence", 5, 7, false, true),	
	DOOR("Door", "Porte", 11, 13, false, true),	
	TEMPERATURE("Temperature", "Température", 17,19, true, false),	
	WINDOW("Window", "Fenêtre", 23,29, false, true),	
	HUMIDITY("Humidity", "Humidité", 31, 37, false, false),	
	LIGHT("Light", "Lumière", 41,43, true, true),
	GAS("Gas", "Gaz", 47,53, false, false),
	MANUAL_TRIGGER("Manuel alarm trigger", "Déclencheur d'alarme manuel", 73,79, false, true),
	ACCESS_CONTROL("Access controle", "Contrôle d'accès", 83,89, false, true),
	FLOOD("Flood", "Inondation", 97, 101, false, false);

	// Checks if each multiplication of two numbers of more give a different number

	private String englishLabel;
	private String frenchLabel;	
	private Integer normalCode;
	private Integer dangerCode;
	private Boolean isGapAcceptable;
	private Boolean isItBinary;
	private SensorAction actionAssociatedToStopDanger;

	/**
	 * @param englishLabel
	 * @param frenchLabel
	 */
	SensorType(String englishLabel, String frenchLabel, Integer normalCode, Integer dangerCode, Boolean isGapAcceptable, Boolean isItBinary) {
		this.englishLabel = englishLabel;
		this.frenchLabel = frenchLabel;
		this.normalCode = normalCode;
		this.dangerCode = dangerCode;
		this.isGapAcceptable = isGapAcceptable;
		this.isItBinary = isItBinary;
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

	public Boolean getIsGapAcceptable() {
		return isGapAcceptable;
	}

	public Boolean getIsItBinary() {
		return isItBinary;
	}

	public int getCorrectCode(SensorState state) {
		if(state == SensorState.DANGER)
			return this.dangerCode;
		else
			return this.normalCode;
	}

	public SensorAction getActionAssociatedToStopDanger() {
		return actionAssociatedToStopDanger;
	}

	public void setActionAssociatedToStopDanger() {
		switch(this) {
		case FLOW:
			break;
		}
		if(this == SensorType.FLOW){
		}
		else if(sensorType == SensorType.SMOKE){	

		}
		else if(sensorType == SensorType.DOOR){	
		}
		else if(sensorType == SensorType.TEMPERATURE){	

		}
		else if(sensorType == SensorType.WINDOW){	

		}
		else if(sensorType == SensorType.HUMIDITY){	

		}
		else if(sensorType == SensorType.LIGHT){

		}
		else if(sensorType == SensorType.GAS){

		}
		else if(sensorType == SensorType.MANUAL_TRIGGER){

		}
		else if(sensorType == SensorType.FLOOD){

		}
	}


}
