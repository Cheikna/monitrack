package com.monitrack.listener;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorState;
import com.monitrack.gui.panel.SensorInfoPanel;
import com.monitrack.gui.panel.SensorsAlertsTab;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class SensorsAlertsTabListener implements ActionListener {

	private SensorsAlertsTab sensorsAlertsTab;

	public SensorsAlertsTabListener(SensorsAlertsTab sensorsAlertsTab) {
		this.sensorsAlertsTab = sensorsAlertsTab;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void updatePanel(Map<SensorState, List<SensorConfiguration>> sensorsConfigurationByState) {
		JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 20));
		Iterator iterator = sensorsConfigurationByState.entrySet().iterator();
		sensorsAlertsTab.getSensorsPanel().removeAll();
		while(iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			SensorState state = (SensorState)entry.getKey();
			List<SensorConfiguration> sensors = (List<SensorConfiguration>) entry.getValue();
			for(SensorConfiguration sensor : sensors) {
				sensorsAlertsTab.getSensorsPanel().add(new SensorInfoPanel(sensor, state));
				System.out.println(state + "  =======  " + sensor);
			}
		}
		sensorsAlertsTab.getSensorsPanel().revalidate();
		sensorsAlertsTab.getSensorsPanel().updateUI();
	}

}
