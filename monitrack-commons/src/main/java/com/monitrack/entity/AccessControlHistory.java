package com.monitrack.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessControlHistory {
	
	@JsonProperty("history_id")
	private int historyId;
	@JsonProperty("sensor_id")
	private int sensorId;
	@JsonProperty("location_id")
	private int locationId;
	@JsonProperty("code_entered")
	private String codeEntered;
	@JsonProperty("person_information")
	private String personInformation;
	@JsonProperty("triggering_date")
	private Timestamp triggeringDate;
	@JsonProperty("access_granted")
	private boolean accessGranted;
	
			
	public AccessControlHistory(int historyId, int sensorId, int locationId, String codeEntered,
			String personInformation, Timestamp triggeringDate, boolean accessGranted) {
		this.historyId = historyId;
		this.sensorId = sensorId;
		this.locationId = locationId;
		this.codeEntered = codeEntered;
		this.personInformation = personInformation;
		this.triggeringDate = triggeringDate;
		this.accessGranted = accessGranted;
	}
	
	public AccessControlHistory() {}

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getCodeEntered() {
		return codeEntered;
	}

	public void setCodeEntered(String codeEntered) {
		this.codeEntered = codeEntered;
	}

	public Timestamp getTriggeringDate() {
		return triggeringDate;
	}

	public void setTriggeringDate(Timestamp triggeringDate) {
		this.triggeringDate = triggeringDate;
	}

	public boolean getAccessGranted() {
		return accessGranted;
	}

	public void setAccessGranted(boolean accessGranted) {
		this.accessGranted = accessGranted;
	}

	public String getPersonInformation() {
		return personInformation;
	}

	public void setPersonInformation(String personInformation) {
		this.personInformation = personInformation;
	}

	@Override
	public String toString() {
		return "AccessControlHistory [historyId=" + historyId + ", sensorId=" + sensorId + ", locationId=" + locationId
				+ ", codeEntered=" + codeEntered + ", personInformation=" + personInformation + ", triggeringDate="
				+ triggeringDate + ", isAccessGranted=" + accessGranted + "]";
	}
	
	public String toStringDetailsForLocation() {
		if(accessGranted) {
			return personInformation + " est entré(e) dans cette salle le " + triggeringDate + " avec le code " + codeEntered;
		}
		else {
			return "Une personne a essayé d'entrer dans cette salle le " + triggeringDate + " mais elle n'a pas entré le bon code !";
		}
	}

	public int accessGranted() {
		return (accessGranted) ? 1 : 0;
	}
	
	
	
	
}
