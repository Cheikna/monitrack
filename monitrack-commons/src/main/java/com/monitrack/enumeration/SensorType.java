package com.monitrack.enumeration;


public enum SensorType {

	//FIXME - Cheikna : Why do not I remove the english label and make a "this.toString().toLowerCase()" in the constructor ?
	SMOKE("Smoke", "Fumée", 2, 3, false, false),	
	FLOW("Flow", "Présence", 5, 7, false, true, "EMPTY", "NOT_EMPTY"),	
	DOOR("Door", "Porte", 11, 13, false, true, "CLOSED", "OPEN"),	
	TEMPERATURE("Temperature", "Température", 17,19, true, false),	
	WINDOW("Window", "Fenêtre", 23,29, false, true, "CLOSED", "OPEN"),	
	HUMIDITY("Humidity", "Humidité", 31, 37, false, false),	
	LIGHT("Light", "Lumière", 41,43, true, true, "SWITCHED_OFF", "SWITCHED_ON"),
	GAS("Gas", "Gaz", 47,53, false, false),
	MANUAL_TRIGGER("Manuel alarm trigger", "Déclencheur d'alarme manuel", 73,79, false, true, "NOT_TRIGGERED", "TRIGGERED"),
	ACCESS_CONTROL("Access controle", "Contrôle d'accès", 83,89, false, true, "GRANTED", "NOT_GRANTED"),
	FLOOD("Flood", "Inondation", 97, 101, false, false),
	GLASS_BREAKAGE("Glass breakage", "Bris de vitre", 97, 101, false, false);

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
	 * people in the room. Consequently, these sensors has only two states
	 */
	private Boolean isItBinary;
	
	/**
	 * The message is for the type which are binary (=which can only have two value)
	 * Because it is not interesting to have the precise value for those sensor
	 */
	private String normalMessage;
	private String dangerMessage;

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
	}
	
	SensorType(String englishLabel, String frenchLabel, Integer normalCode, Integer dangerCode, Boolean isGapAcceptable, Boolean isItBinary, String normalMessage, String dangerMessage) {
		this(englishLabel, frenchLabel, normalCode, dangerCode, isGapAcceptable, isItBinary);
		this.normalMessage = normalMessage;
		this.dangerMessage = dangerMessage;
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

	public static SensorAction getActionAssociatedToStopDanger(SensorType type) {
		switch(type) {
		case DOOR:
			return SensorAction.CLOSE_DOOR;
		case LIGHT:
			return SensorAction.SWITCH_OFF_LIGHT;
		case WINDOW:
			return SensorAction.CLOSE_WINDOW;
		case ACCESS_CONTROL:
			return SensorAction.UNLOCK_CODE;
		default:
			return SensorAction.STOP_DANGER_ALERT;
		}
	}

	public String getMessageAccordingToState(SensorState state) {
		if(state == SensorState.DANGER || (state == SensorState.WARNING && this == SensorType.ACCESS_CONTROL))
			return this.dangerMessage;
		return this.normalMessage;
	}	
}
