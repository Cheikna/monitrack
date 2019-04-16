package com.monitrack.enumeration;
/**
 * 
 * @author Cheikna
 *
 */
public enum SensorState {
	
	ENABLED,
	
	DISABLED,
	
	NOT_CONFIGURED;
	
	public static SensorState getSensorState(String sensorState)
	{
		SensorState[] values = SensorState.values();
		for(SensorState value : values)
		{
			if(value.toString().equalsIgnoreCase(sensorState))
				return value;
		}
		return null;
	}
}
