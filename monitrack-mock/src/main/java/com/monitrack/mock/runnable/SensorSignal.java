package com.monitrack.mock.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.entity.Message;
import com.monitrack.mock.util.MockUtil;

public class SensorSignal implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(SensorSignal.class);
	private int sensorId;
	private float thresholdReached;
	private long interval;
	private boolean sendMessage;

	public SensorSignal(int sensorId, float thresholdReached, long interval) {
		this.sensorId = sensorId;
		this.thresholdReached = thresholdReached;
		this.interval = interval;
		sendMessage = false;
	}

	@Override
	public void run() {
		try {
			while(true) {
				if(sendMessage) {
					boolean messageSent = MockUtil.sendMessage(new Message(sensorId, thresholdReached));
					if(!messageSent)
						throw new Exception("The server can not be reached at this time");
					Thread.sleep(interval - 500);
				}					
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	public void setSendMessage(boolean sendMessage) {
		this.sendMessage = sendMessage;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	public float getThresholdReached() {
		return thresholdReached;
	}

	public void setThresholdReached(float thresholdReached) {
		this.thresholdReached = thresholdReached;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public boolean isSendMessage() {
		return sendMessage;
	}

}
