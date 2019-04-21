package com.monitrack.enumeration;

public enum RequestSender {
	
	CLIENT("client"),
	
	CLIENT_ALARM_UPDATE("client_for_alarm_update"),
	
	SENSOR("sensor");
	
	private String label;
	
	private RequestSender(String label) {
		this.label = label;
	}

	
	public String getLabel() {
		return label;
	}
	
	public static RequestSender getValueOf(String sender) {
		RequestSender[] values = RequestSender.values();
		
		for(RequestSender value : values) {
			if(value.getLabel().equals(sender))
				return value;
		}
		
		return null;
	}
}
