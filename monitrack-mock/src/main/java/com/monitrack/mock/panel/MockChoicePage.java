package com.monitrack.mock.panel;

import javax.swing.JPanel;

import com.monitrack.enumeration.Images;
import com.monitrack.mock.frame.MockFrame;
import com.monitrack.mock.listener.MockChoiceListener;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MockChoicePage extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private JButton generateRandomSensorButton;
	private JButton configureSensorButton;
	private JButton createAlertButton;
	private JButton sensorOverviewButton;
	private Font font;
	private MockChoiceListener listener;

	public MockChoicePage(MockFrame mockFrame) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		font = new Font("Calibri", Font.PLAIN, 23);
		listener = new MockChoiceListener(mockFrame, this);
		this.add(Box.createVerticalGlue());
		
		JLabel label1 = new JLabel("Générer des capteurs");
		label1.setFont(font);
		generateRandomSensorButton = new JButton("Générer des capteurs", Images.CLOCK.getIcon());
		generateRandomSensorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		generateRandomSensorButton.setMaximumSize(new Dimension(400, 75));
		generateRandomSensorButton.setPreferredSize(new Dimension(400, 75));
		generateRandomSensorButton.addActionListener(listener);
		
		configureSensorButton = new JButton("Configurer les capteurs");
		configureSensorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		configureSensorButton.setMaximumSize(new Dimension(400, 75));
		configureSensorButton.setPreferredSize(new Dimension(400, 75));
		configureSensorButton.addActionListener(listener);

		createAlertButton = new JButton("Créer des alertes");
		createAlertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAlertButton.setMaximumSize(new Dimension(400, 75));
		createAlertButton.setPreferredSize(new Dimension(400, 75));
		createAlertButton.addActionListener(listener);

		sensorOverviewButton = new JButton("Visualiser l'état des capteurs");
		sensorOverviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sensorOverviewButton.setMaximumSize(new Dimension(400, 75));
		sensorOverviewButton.setPreferredSize(new Dimension(400, 75));
		sensorOverviewButton.addActionListener(listener);
		
		
		add(generateRandomSensorButton);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		add(configureSensorButton);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		add(createAlertButton);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		add(sensorOverviewButton);

		this.add(Box.createVerticalGlue());
	}

	public JButton getGenerateRandomSensorButton() {
		return generateRandomSensorButton;
	}

	public JButton getConfigureSensorButton() {
		return configureSensorButton;
	}

	public JButton getCreateAlertButton() {
		return createAlertButton;
	}

	public JButton getSensorOverviewButton() {
		return sensorOverviewButton;
	}
	
	
}
