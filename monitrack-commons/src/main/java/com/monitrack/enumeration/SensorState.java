package com.monitrack.enumeration;

import java.awt.Color;

public enum SensorState {


	DANGER(new Color(245,14,14)),
	CAUTION(new Color(255,255,51)),
	WARNING(new Color(255,119,0)),
	NORMAL(new Color(51,255,51)),	
	MISSING(new Color(128,128,128));
	
	private Color color;

	private SensorState(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}
