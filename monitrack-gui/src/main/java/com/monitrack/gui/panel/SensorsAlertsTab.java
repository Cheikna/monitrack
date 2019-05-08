package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.monitrack.listener.SensorsAlertsTabListener;

public class SensorsAlertsTab extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel sensorsPanel;
	private SensorsAlertsTabListener listener;
	
	public SensorsAlertsTab() {
		sensorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 20));
		sensorsPanel.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),2000));
		listener = new SensorsAlertsTabListener(this);
		setLayout(new BorderLayout());
//		for(int i = 0; i <= 400; i++) {
//			JButton b = new JButton("Button " + i);
//			//b.addActionListener(this);
//			//add(new SensorInfoPanel());
//			//sensorsPanel.add(new SensorInfoPanel());
//			//FIXME
//		}
		

		JScrollPane scroll = new JScrollPane(sensorsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scroll);
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
}
