package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Location;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.gui.panel.ConfigurationTab;
import com.monitrack.gui.panel.LocationsTab;

public class ConfigurationTabListener implements ActionListener {

	private static final Logger log = LoggerFactory.getLogger(ConfigurationTabListener.class);
	
	private ConfigurationTab configurationTab;
	private List<SensorConfiguration> sensors;
	private List<String> fields;
	private List<String> values;

	public ConfigurationTabListener(ConfigurationTab configurationTab)
	{
		this.configurationTab = configurationTab;
		sensors = new ArrayList<>();
		fields = new ArrayList<String>();
		values = new ArrayList<String>();	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
