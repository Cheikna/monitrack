package com.monitrack.enumeration;

public enum SensorAction {
	
	CONFIGURATION,
	
	STOP_DANGER_ALERT, 
	
	FAKE_ALERT;
	
	public static SensorAction getValueOf(String str) {
		SensorAction[] values = SensorAction.values();
		for(SensorAction action : values) {
			if(action.name().equalsIgnoreCase(str))
				return action;
		}
		return null;
	}

}
