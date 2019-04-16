package com.monitrack.enumeration;
/**
 * 
 * @author Cheikna
 *
 */
public enum SensorConfiguration {
	
	ENABLED,
	
	DISABLED,
	
	NOT_CONFIGURED;
	
	public static SensorConfiguration getSensorConfiguration(String sensorState)
	{
		SensorConfiguration[] values = SensorConfiguration.values();
		for(SensorConfiguration value : values)
		{
			if(value.toString().equalsIgnoreCase(sensorState))
				return value;
		}
		return null;
	}
}
