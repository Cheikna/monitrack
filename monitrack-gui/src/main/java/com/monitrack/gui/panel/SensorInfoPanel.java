package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.monitrack.entity.Location;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;
import com.monitrack.listener.SensorInfoListener;

public class SensorInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private SensorConfiguration sensor;

	private Color stateColor;
	private final Color defaultColor = Color.WHITE;
	private JButton sendReparatorButton;
	private JPanel sensorInfoCenterPanel;
	private boolean showColor;
	private SensorInfoListener listener;
	private final Font font = new Font("Arial", Font.PLAIN, 25);

	public SensorInfoPanel(SensorConfiguration sensor, SensorState sensorState) {
		super(new BorderLayout());
		this.setPreferredSize(new Dimension(300,145));
		this.sensor = sensor;
		this.stateColor = sensorState.getColor();
		this.showColor = true;
		sensorInfoCenterPanel = new JPanel();
		listener = new SensorInfoListener(this);
		sensorInfoCenterPanel.setLayout(new BoxLayout(sensorInfoCenterPanel, BoxLayout.Y_AXIS));
		String buttonText = getButtonText(sensor.getSensorType());		
		sendReparatorButton = new JButton(buttonText);
		sendReparatorButton.setEnabled(sensorState == SensorState.DANGER);
		sendReparatorButton.addActionListener(listener);
		JLabel label = new JLabel("Capteur n°" + sensor.getSensorConfigurationId());
		label.setFont(font);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sensorInfoCenterPanel.add(label);
		JLabel typeLabel = new JLabel("Type : " + sensor.getSensorType().getFrenchLabel());
		typeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		typeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sensorInfoCenterPanel.add(typeLabel);
		JLabel macLabel = new JLabel("@mac : " + sensor.getMacAddress());
		macLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sensorInfoCenterPanel.add(macLabel);
		String ipAddress = "non défini";
		if(sensor.getSensorActivity() == SensorActivity.ENABLED && sensorState != SensorState.MISSING)
			ipAddress = sensor.getIpAddress();
		JLabel ipLabel = new JLabel("@ip : " + ipAddress);
		ipLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sensorInfoCenterPanel.add(ipLabel);
		JLabel numberLocation = new JLabel("Numéro lieu : " + sensor.getLocationId());
		numberLocation.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sensorInfoCenterPanel.add(numberLocation);
		Location location = sensor.getLocation();
		String locationName = (location != null) ? location.getNameLocation() : "";
		JLabel locationLabel = new JLabel("Nom lieu : " + locationName);
		locationLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sensorInfoCenterPanel.add(locationLabel);
		sensorInfoCenterPanel.setBackground(stateColor);
		add(sensorInfoCenterPanel, BorderLayout.CENTER);
		add(sendReparatorButton, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						showColor = !showColor;
						repaint();
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});

		if(sensorState != SensorState.NORMAL && sensorState != SensorState.MISSING)
			thread.start();
	}

	private String getButtonText(SensorType sensorType) {
		if(sensorType == SensorType.LIGHT)
			return "Eteindre la lumière";
		return "Envoyer l'équipe d'intervention";
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(showColor)
			sensorInfoCenterPanel.setBackground(stateColor);
		else
			sensorInfoCenterPanel.setBackground(defaultColor);

	}

	public SensorConfiguration getSensor() {
		return sensor;
	}

	public JButton getSendReparatorButton() {
		return sendReparatorButton;
	}

}
