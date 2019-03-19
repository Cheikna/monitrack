package com.monitrack.enumeration;

public enum ConnectionState {

	FAIL (-1,"The connection failed?"),
	TRY(0, "Trying the connection"),
	SUCCESS(1,"The connection succeeded");

	private Integer code;
	private String label;
	
	ConnectionState(Integer code, String label) {
		this.code=code;
		this.label=label;
	}

	public Integer getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}
	
}
