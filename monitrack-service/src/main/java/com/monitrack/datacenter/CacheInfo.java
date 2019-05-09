package com.monitrack.datacenter;

import java.sql.Timestamp;

import com.monitrack.enumeration.SensorState;
import com.monitrack.util.Util;

public class CacheInfo {
	
	private int warningCount;
	private Timestamp lastMessageDate;
	private Timestamp firstDangerMessageDate;
	private SensorState sensorState;
	private int history;
	private boolean isBeginDangerRegistered;
	private float threasholdReached;
	
	public CacheInfo(Timestamp lastMessageDate, Timestamp firstDangerMessageDate, SensorState sensorState, float threasholdReached) {
		this.warningCount = (sensorState == SensorState.WARNING) ? 1 : 0;
		this.lastMessageDate = lastMessageDate;
		this.firstDangerMessageDate = firstDangerMessageDate;
		this.sensorState = sensorState;
		this.history = 0;
		isBeginDangerRegistered = false;
		this.threasholdReached = threasholdReached;
	}

	public Timestamp getLastMessageDate() {
		return lastMessageDate;
	}

	public void setLastMessageDate(Timestamp lastMessageDate) {
		this.lastMessageDate = lastMessageDate;
	}

	public Timestamp getFirstDangerMessageDate() {
		return firstDangerMessageDate;
	}

	public void setFirstDangerMessageDate() {
		setFirstDangerMessageDate(Util.getCurrentTimestamp());;
	}

	public SensorState getSensorState() {
		return sensorState;
	}
	
	public void setFirstDangerMessageDate(Timestamp firstDangerMessageDate) {
		this.firstDangerMessageDate = firstDangerMessageDate;
	}

	public void setSensorState(SensorState sensorState) {
		this.sensorState = sensorState;
	}

	public SensorState addWarning(int maxWarningCount, Timestamp messageDate) {
		this.warningCount++;
		
		if(this.warningCount >= maxWarningCount) {
			sensorState = SensorState.DANGER;
			if(warningCount == maxWarningCount && messageDate != null) {
				firstDangerMessageDate = messageDate;
			}			
		}	
		else
			sensorState = SensorState.WARNING;
		
		return sensorState;
	}
	
	public SensorState addWarning(int maxWarningCount) {
		return addWarning(maxWarningCount, null);
	}
	
	public void reset() {
		this.warningCount = 0;
		firstDangerMessageDate = null;
		sensorState = SensorState.NORMAL;
		history = 0;
		isBeginDangerRegistered = false;
	}

	public int getWarningCount() {
		return warningCount;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public boolean isBeginDangerRegistered() {
		return isBeginDangerRegistered;
	}

	public void setBeginDangerRegistered(boolean isBeginDangerRegistered) {
		this.isBeginDangerRegistered = isBeginDangerRegistered;
	}

	public float getThreasholdReached() {
		return threasholdReached;
	}

	public void setThreasholdReached(float threasholdReached) {
		this.threasholdReached = threasholdReached;
	}
	
}
