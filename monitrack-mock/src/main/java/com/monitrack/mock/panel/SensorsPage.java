package com.monitrack.mock.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.monitrack.entity.Sensor;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class SensorsPage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
	private JPanel centerPanel;

	public SensorsPage() {
		super(new BorderLayout());
		
		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel("Rechercher les capteurs par : "));
		centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
		Sensor sensor = new Sensor(8, SensorActivity.ENABLED, SensorType.FLOW, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 2500f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);
		/*Sensor sensor2 = new Sensor(9, SensorActivity.ENABLED, SensorType.SMOKE, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);*/
		centerPanel.add(new SensorInfoPanel(sensor));
		//centerPanel.add(new SensorInfoPanel(sensor2));
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
	}
}
