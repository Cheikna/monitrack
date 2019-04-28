package com.monitrack.mock.dialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JDialog;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;
import com.monitrack.mock.panel.SensorInfoPanel;

public class SensorDialog extends JDialog {
	
	public SensorDialog(Frame owner, String locationName) {
		super(owner, "Capteur de : " + locationName, true);
		//setLocationRelativeTo(null);
		setSize(1200, 800);
		setLayout(new FlowLayout(FlowLayout.LEFT, 25, 25));
		SensorConfiguration sensorConfiguration = null;
		for(int i = 1; i < 5; i++) {
			sensorConfiguration = new SensorConfiguration(i,i, SensorActivity.ENABLED, SensorType.FLOW, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
					1.0f, 2.0f, null, null, null, null, null, 2500f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);
			this.add(new SensorInfoPanel(sensorConfiguration));
			
		}
	}


}
