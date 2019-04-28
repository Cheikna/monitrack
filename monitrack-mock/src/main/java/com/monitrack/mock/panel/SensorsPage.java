package com.monitrack.mock.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;
import com.monitrack.exception.DeprecatedVersionException;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.mock.dialog.SensorDialog;
import com.monitrack.mock.util.MockUtil;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class SensorsPage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
	private JPanel centerPanel;
	private TextArea textArea;

	public SensorsPage() {
		super(new BorderLayout());
		
		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel("Rechercher les capteurs par : "));
		/*centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
		Sensor sensor = new Sensor(8, SensorActivity.ENABLED, SensorType.FLOW, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 2500f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);
		Sensor sensor2 = new Sensor(9, SensorActivity.ENABLED, SensorType.SMOKE, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);*/
		//centerPanel.add(new SensorInfoPanel(sensor));
		//centerPanel.add(new SensorInfoPanel(sensor2));		
		
		centerPanel = new JPanel();
		textArea = new TextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		centerPanel.add(scroll);
		
		
		add(northPanel, BorderLayout.NORTH);

		/*SensorDialog sdialog = new SensorDialog(null, "Jeux");
		sdialog.setVisible(true);*/
		add(centerPanel, BorderLayout.CENTER);
	}
}
