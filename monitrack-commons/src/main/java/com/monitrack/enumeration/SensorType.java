package com.monitrack.enumeration;

public enum SensorType {
	
	SMOKE("somoke", "fumée"),
	
	INPUT_OUTPUT("input/output", "entrée/sortie"),
	
	FLOW("flow", "présence");
	
	
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
