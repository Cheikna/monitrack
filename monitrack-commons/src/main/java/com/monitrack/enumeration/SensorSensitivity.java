package com.monitrack.enumeration;

import org.apache.commons.lang3.math.NumberUtils;

import  com.monitrack.util.*;

public enum SensorSensitivity {
	
	LOW,
	MEDIUM,
	HIGH,
	VERY_HIGH;

	public static int getNumberOfMessages(SensorSensitivity sensitivity) {
		switch(sensitivity) {
		case LOW:
			return NumberUtils.toInt(Util.getSensorsPropertyValueFromPropertiesFile("low_level"));
		case MEDIUM :
			return NumberUtils.toInt(Util.getSensorsPropertyValueFromPropertiesFile("medium_level"));
		case HIGH :
			return NumberUtils.toInt(Util.getSensorsPropertyValueFromPropertiesFile("high_level"));
		default:
			return NumberUtils.toInt(Util.getSensorsPropertyValueFromPropertiesFile("very_high_level"));
		}
			
	}
}
