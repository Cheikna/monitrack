package com.monitrack.mock.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Message;
import com.monitrack.mock.main.MonitrackMockConsole;

public class SensorSignal implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(SensorSignal.class);
	private int sensorId;
	private float minThreshold;
	private float maxThreshold;
	private long interval;
	private boolean sendMessage;
	//private long oneMinute = DateTimeConstants.MILLIS_PER_MINUTE;
	private Boolean isNormalMessage;

	public SensorSignal(int sensorId, float minThreshold, float maxThreshold, Float interval, Boolean isNormalMessage) {
		this.sensorId = sensorId;
		this.minThreshold = minThreshold;
		this.maxThreshold = maxThreshold;
		this.interval = interval.longValue();
		if(this.interval >= 1000)
			this.interval -= 500;
		this.isNormalMessage = isNormalMessage;
		sendMessage = true;
	}

	@Override
	public void run() {
		Float threshold = minThreshold;
		try {
			while(true) {
				if(sendMessage) {
					if(isNormalMessage == null)
						threshold = minThreshold;
					else if(isNormalMessage)
						threshold = (float)(minThreshold + (Math.random() * (maxThreshold - minThreshold)));
					else if(!isNormalMessage)
						threshold = (float)(maxThreshold + (Math.random() * (maxThreshold - minThreshold)));

					boolean messageSent = MonitrackMockConsole.sendMessage(new Message(sensorId, threshold));
					
					if(!messageSent)
						throw new Exception("The server can not be reached at this time");
					Thread.sleep(interval);
					
					
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
		return maxThreshold;
	}

	public void setThresholdReached(float thresholdReached) {
		this.maxThreshold = thresholdReached;
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

	public float getMinThreshold() {
		return minThreshold;
	}

	public void setMinThreshold(float minThreshold) {
		this.minThreshold = minThreshold;
	}

	public float getMaxThreshold() {
		return maxThreshold;
	}

	public void setMaxThreshold(float maxThreshold) {
		this.maxThreshold = maxThreshold;
	}

	public boolean isNormalMessage() {
		return isNormalMessage;
	}

	public void setNormalMessage(boolean isNormalMessage) {
		this.isNormalMessage = isNormalMessage;
	}

	public void clone(SensorSignal signal) {
		this.sensorId = signal.sensorId;
		this.minThreshold = signal.minThreshold;
		this.maxThreshold = signal.maxThreshold;
		this.interval = signal.interval;
		this.sendMessage = signal.sendMessage;
		this.isNormalMessage = signal.isNormalMessage;
	}
}
