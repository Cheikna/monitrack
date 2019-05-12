package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;
import com.monitrack.gui.panel.SensorInfoPanel;
import com.monitrack.gui.panel.SensorsAlertsTab;
import com.monitrack.util.ComplementarySensorDictionnary;
import com.monitrack.util.Util;

public class SensorsAlertsTabListener implements ActionListener {

	private SensorsAlertsTab sensorsAlertsTab;
	private ComplementarySensorDictionnary complementarySensorDictionnary;

	public SensorsAlertsTabListener(SensorsAlertsTab sensorsAlertsTab) {
		this.sensorsAlertsTab = sensorsAlertsTab;
		complementarySensorDictionnary = new ComplementarySensorDictionnary();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sensorsAlertsTab.getCreateSensorButton()) {
			//FIXME
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updatePanel(Map<SensorState, List<SensorConfiguration>> sensorsConfigurationByState) {
		complementarySensorDictionnary.resetDangerBountyByLocation();
		Iterator iterator = sensorsConfigurationByState.entrySet().iterator();
		sensorsAlertsTab.getSensorsPanel().removeAll();
		while(iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			SensorState state = (SensorState)entry.getKey();
			List<SensorConfiguration> sensors = (List<SensorConfiguration>) entry.getValue();
			for(SensorConfiguration sensor : sensors) {
				if(state == SensorState.DANGER || (sensor.getSensorType() == SensorType.FLOW)) {
					complementarySensorDictionnary.addClientCode(sensor.getLocationId(), sensor.getSensorType().getCorrectCode(state));
				}
				sensorsAlertsTab.getSensorsPanel().add(new SensorInfoPanel(sensor, state));					
			}
		}
		String message = complementarySensorDictionnary.getAllMessagesForClient();
		sensorsAlertsTab.getLastRefreshDateLabel().setText("Dernière actualisation le : " + Util.getCurrentTimestamp());
		sensorsAlertsTab.getInfosTextArea().setText(message);
		sensorsAlertsTab.getSensorsPanel().revalidate();
		sensorsAlertsTab.getSensorsPanel().updateUI();
	}


}
