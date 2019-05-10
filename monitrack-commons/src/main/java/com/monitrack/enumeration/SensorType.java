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
	/** 
	 * Accepts some variation like for the temperature because we do not want to raise an alert
	 * each time the temperature changes
	 */
	private Boolean isGapAcceptable;
	/**
	 * Checks if the sensors has only two values. For instance a door can only be opened or closed,
	 * same for the window. In the room there can be people or not, we do not count the number of
	 * people in the room
	 */
	private Boolean isItBinary;
	private SensorAction actionAssociatedToStopDanger;

	/**
	 * 
	 * @param englishLabel
	 * @param frenchLabel
	 * @param normalCode
	 * @param dangerCode
	 * @param isGapAcceptable
	 * @param isItBinary
	 */
	SensorType(String englishLabel, String frenchLabel, Integer normalCode, Integer dangerCode, Boolean isGapAcceptable, Boolean isItBinary) {
		this.englishLabel = englishLabel;
		this.frenchLabel = frenchLabel;
		this.normalCode = normalCode;
		this.dangerCode = dangerCode;
		this.isGapAcceptable = isGapAcceptable;
		this.isItBinary = isItBinary;
		this.actionAssociatedToStopDanger = setActionAssociatedToStopDanger();
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

	public SensorAction setActionAssociatedToStopDanger() {
		switch(this) {
		case DOOR:
			return SensorAction.CLOSE_DOOR;
		case LIGHT:
			return SensorAction.SWITCH_OFF_LIGHT;
		case WINDOW:
			return SensorAction.CLOSE_WINDOW;
		default:
			return SensorAction.STOP_DANGER_ALERT;
		}
	}


}
