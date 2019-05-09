package com.monitrack.runnable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.SensorState;
import com.monitrack.gui.panel.SensorsAlertsTab;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class SensorsUpdater implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(SensorsUpdater.class);
	private SensorsAlertsTab sensorsAlertsTab;
	private Map<SensorState, List<SensorConfiguration>> sensorsConfigurationByState;
	
	public SensorsUpdater(SensorsAlertsTab sensorsAlertsTab) {
		this.sensorsAlertsTab = sensorsAlertsTab;
		sensorsConfigurationByState = Collections.synchronizedMap(new TreeMap<>());
	}

	@Override
	public void run() {
		while(true) {
			try {
				String jsonRequest = JsonUtil.serializeSensorsFromCacheRequest(RequestSender.CLIENT_FOR_SENSOR_STATE);
				String response = MonitrackGuiUtil.sendRequest(jsonRequest);	
				if(response != null) {
					sensorsConfigurationByState.clear();
					sensorsConfigurationByState.putAll(JsonUtil.deserializeCacheSensorsMap(response));						
				}
				sensorsAlertsTab.getListener().updatePanel(sensorsConfigurationByState);
				Thread.sleep(10000);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

	}

}
