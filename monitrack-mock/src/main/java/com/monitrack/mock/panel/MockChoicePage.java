package com.monitrack.mock.panel;

import javax.swing.JPanel;

import com.monitrack.enumeration.Images;
import com.monitrack.mock.frame.MockFrame;
import com.monitrack.mock.listener.MockChoiceListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class MockChoicePage extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private JButton generateRandomSensorButton;
	private JButton sensorOverviewButton;
	private Font font;
	private MockChoiceListener listener;

	public MockChoicePage(MockFrame mockFrame) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		font = new Font("Calibri", Font.PLAIN, 23);
		listener = new MockChoiceListener(mockFrame, this);
		this.add(Box.createVerticalGlue());
		
		JLabel label1 = new JLabel("G�n�rer des capteurs");
		label1.setFont(font);
		generateRandomSensorButton = new JButton("G�n�rer des capteurs", Images.CLOCK.getIcon());
		generateRandomSensorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		generateRandomSensorButton.setMaximumSize(new Dimension(400, 75));
		generateRandomSensorButton.setPreferredSize(new Dimension(400, 75));
		generateRandomSensorButton.addActionListener(listener);

		sensorOverviewButton = new JButton("Visualiser et configurer les capteurs");
		sensorOverviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sensorOverviewButton.setMaximumSize(new Dimension(400, 75));
		sensorOverviewButton.setPreferredSize(new Dimension(400, 75));
		sensorOverviewButton.addActionListener(listener);
		
		
		add(generateRandomSensorButton);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		add(sensorOverviewButton);

		this.add(Box.createVerticalGlue());
	}

	public JButton getGenerateRandomSensorButton() {
		return generateRandomSensorButton;
	}

	public JButton getSensorOverviewButton() {
		return sensorOverviewButton;
	}
	
	
}
