package com.monitrack.mock.enumeration;

public enum Page {
	
	SENSOR_OVERVIEW("sensor_overview_page");
	
	private String name;

	
	public String getName() {
		return name;
	}

	
	private Page(String name) {
		this.name = name;
	}
	
	

}
