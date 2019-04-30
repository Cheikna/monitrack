package com.monitrack.mock.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;
import com.monitrack.exception.DeprecatedVersionException;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.mock.dialog.SensorDialog;
import com.monitrack.mock.listener.SensorsListener;
import com.monitrack.mock.util.MockUtil;
import com.monitrack.mock.util.SensorsTableModel;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class SensorsPage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
	private JPanel centerPanel;
	private TextArea textArea;
	private GridBagConstraints c;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private SensorsListener listener = new SensorsListener(this);
	private JScrollPane sensorsTableScrollPane;
	private JTable sensorsTable;

	public SensorsPage() {
		super(new GridBagLayout());
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.addListSelectionListener(listener);
		listModel.addElement("Test");
		listModel.addElement("Test 1");
		listModel.addElement("Test 2");
		initSensorsTable();
		/*northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel("Rechercher les capteurs par : "));
		centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
		Sensor sensor = new Sensor(8, SensorActivity.ENABLED, SensorType.FLOW, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 2500f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);
		Sensor sensor2 = new Sensor(9, SensorActivity.ENABLED, SensorType.SMOKE, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);*/
		//centerPanel.add(new SensorInfoPanel(sensor));
		//centerPanel.add(new SensorInfoPanel(sensor2));		
		
		//centerPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(new JButton("Super"), c);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.weightx = 0.4;
		c.weighty = 1;
		add(sensorsTableScrollPane, c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.1;
		add(new JButton("de"), c);
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.1;
		add(new JButton("la"), c);
	}
	
	private void initSensorsTable() {
		sensorsTable = new JTable(new SensorsTableModel());
		sensorsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sensorsTableScrollPane = new JScrollPane(sensorsTable);
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public JList<String> getList() {
		return list;
	}
	
	
}
