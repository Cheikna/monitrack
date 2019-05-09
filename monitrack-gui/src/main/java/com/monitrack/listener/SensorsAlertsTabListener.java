package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorState;
import com.monitrack.gui.panel.SensorInfoPanel;
import com.monitrack.gui.panel.SensorsAlertsTab;
import com.monitrack.util.ComplementarySensorConfig;
import com.monitrack.util.Util;

public class SensorsAlertsTabListener implements ActionListener {

	private SensorsAlertsTab sensorsAlertsTab;
	private ComplementarySensorConfig complementarySensorConfig;

	public SensorsAlertsTabListener(SensorsAlertsTab sensorsAlertsTab) {
		this.sensorsAlertsTab = sensorsAlertsTab;
		complementarySensorConfig = new ComplementarySensorConfig();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sensorsAlertsTab.getCreateSensorButton()) {
			//FIXME
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updatePanel(Map<SensorState, List<SensorConfiguration>> sensorsConfigurationByState) {
		complementarySensorConfig.resetDangerBountyByLocation();
		Iterator iterator = sensorsConfigurationByState.entrySet().iterator();
		sensorsAlertsTab.getSensorsPanel().removeAll();
		while(iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			SensorState state = (SensorState)entry.getKey();
			List<SensorConfiguration> sensors = (List<SensorConfiguration>) entry.getValue();
			for(SensorConfiguration sensor : sensors) {
				if(state == SensorState.DANGER)
					complementarySensorConfig.addClientCode(sensor.getLocationId(), sensor.getSensorType().getCorrectCode(state));
				sensorsAlertsTab.getSensorsPanel().add(new SensorInfoPanel(sensor, state));
			}
		}
		String message = complementarySensorConfig.getAllMessagesForClient();
		sensorsAlertsTab.getLastRefreshDateLabel().setText("Dernière actualisation le : " + Util.getCurrentTimestamp());
		sensorsAlertsTab.getInfosTextArea().setText(message);
		sensorsAlertsTab.getSensorsPanel().revalidate();
		sensorsAlertsTab.getSensorsPanel().updateUI();
	}


}
