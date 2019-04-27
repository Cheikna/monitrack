package com.monitrack.mock.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Message;
import com.monitrack.entity.Sensor;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorState;
import com.monitrack.mock.util.MockUtil;

public class SensorSignal implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(SensorSignal.class);
	private Sensor sensor;
	private boolean isInSendingWarningMessageMode;
	private boolean sendMessage;
	private SensorState sensorState;

	public SensorSignal(Sensor sensor) {
		this.sensor = sensor;	
		isInSendingWarningMessageMode = false;
		sendMessage = false;
		this.sensorState = SensorState.NORMAL;
	}

	@Override
	public void run() {
		try {
			while(true) {
				sendSignal();				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	public void sendSignal() throws Exception {
		if(sendMessage) {
			if(sensor.getSensorActivity() == SensorActivity.ENABLED) {
				sensorState = (isInSendingWarningMessageMode) ? SensorState.WARNING : SensorState.NORMAL;
				boolean messageSent = MockUtil.sendMessage(new Message(sensorState, sensor));
				if(!messageSent)
					throw new Exception("The server can not be reached at this time");
				Thread.sleep(sensor.getCheckFrequency().longValue());
			}					
		}
	}
	
	public SensorState getSensorState() {
		return sensorState;
	}

	public void setSensorState(SensorState sensorState) {
		this.sensorState = sensorState;
	}

	public boolean isInSendingWarningMessageMode() {
		return isInSendingWarningMessageMode;
	}

	public void isInSendingWarningMessageMode(boolean isInSendingWarningMessageMode) {
		this.isInSendingWarningMessageMode = isInSendingWarningMessageMode;
	}

	public void setSendMessage(boolean sendMessage) {
		this.sendMessage = sendMessage;
	}
	
	

}
