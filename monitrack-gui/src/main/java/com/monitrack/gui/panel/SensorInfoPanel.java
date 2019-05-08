package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorState;
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
		this.sensor = sensor;
		this.stateColor = sensorState.getColor();
		this.showColor = true;
		sensorInfoCenterPanel = new JPanel();
		listener = new SensorInfoListener(this);
		sensorInfoCenterPanel.setLayout(new BoxLayout(sensorInfoCenterPanel, BoxLayout.Y_AXIS));
		sendReparatorButton = new JButton("Envoyer l'�quipe de maintenance");
		sendReparatorButton.setEnabled(sensorState == SensorState.DANGER);
		sendReparatorButton.addActionListener(listener);
		JLabel label = new JLabel("Capteur n�" + sensor.getSensorConfigurationId());
		label.setFont(font);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sensorInfoCenterPanel.add(label);
		sensorInfoCenterPanel.add(new JLabel("@mac : "));
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
		if(sensorState != SensorState.NORMAL)
			thread.start();
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
