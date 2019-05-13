package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.monitrack.listener.SensorsAlertsTabListener;

public class SensorsAlertsTab extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
	private JButton createSensorButton;
	private JPanel sensorsPanel;
	private SensorsAlertsTabListener listener;
	private JLabel lastRefreshDateLabel;
	private JTextArea infosTextArea;
	
	public SensorsAlertsTab() {
		super(new BorderLayout());
		sensorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 20));
		sensorsPanel.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 1000,4000));
		listener = new SensorsAlertsTabListener(this);
		northPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		/*createSensorButton = new JButton("Ajouter un capteur");
		createSensorButton.addActionListener(listener);
		northPanel.add(createSensorButton);*/
		lastRefreshDateLabel = new JLabel("Dernière actualisation le :");
		lastRefreshDateLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lastRefreshDateLabel.setForeground(Color.RED);
		northPanel.add(lastRefreshDateLabel);
		
		JScrollPane scroll = new JScrollPane(sensorsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		infosTextArea = new JTextArea(20, 35);
		infosTextArea.setFont(new Font("Calibri", Font.PLAIN, 20));
		infosTextArea.setLineWrap(true);
		infosTextArea.setWrapStyleWord(true);
		infosTextArea.setEditable(false);
		JScrollPane scrollTextArea = new JScrollPane(infosTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(northPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(scrollTextArea, BorderLayout.EAST);
	}


	public JPanel getSensorsPanel() {
		return sensorsPanel;
	}

	public void setSensorsPanel(JPanel sensorsPanel) {
		this.sensorsPanel = sensorsPanel;
	}

	public SensorsAlertsTabListener getListener() {
		return listener;
	}

	public JLabel getLastRefreshDateLabel() {
		return lastRefreshDateLabel;
	}


	public JButton getCreateSensorButton() {
		return createSensorButton;
	}

	public JTextArea getInfosTextArea() {
		return infosTextArea;
	}
	
	
}
