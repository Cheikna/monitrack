package com.monitrack.mock.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorState;
import com.monitrack.mock.panel.SensorsPage;
import com.monitrack.mock.runnable.SensorSignal;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class SensorsTableModel extends AbstractTableModel implements ListSelectionListener, ActionListener, DocumentListener {

	private static final Logger log = LoggerFactory.getLogger(SensorsTableModel.class);

	private JTable parentTable;
	private SensorsPage sensorsPage;
	private static final long serialVersionUID = 1L;
	private String[] header = {"ID", "TYPE", "ACTIVITY", "LOCATION ID", "MAC ADDRESS", "THRESHOLD MIN", "THRESHOLD MAX", "STATE"};
	private String [][] datas;
	private String [][] emptyDatas;
	private final int numberOfRows = 500;
	private final int numberOfColumns = 9;
	private int firstEmptyIndex;
	private List<SensorConfiguration> sensors;
	// Map to keep all launched Runnable
	private Map<Integer, SensorSignal> sensorSignalMap;
	private List<SensorConfiguration> filteredSensors;
	private float currentThresholdValue = 0;
	private int lastRowSelected = 0;
	private SensorConfiguration lastSensorSelected = null;
	private int lastIdSent = 0;


	public SensorsTableModel(SensorsPage sensorsPage, JTable parent) {
		this.sensorsPage = sensorsPage;
		this.parentTable = parent;
		parent.getSelectionModel().addListSelectionListener(this);
		parent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		parent.setModel(this);
		datas = new String[numberOfRows][numberOfColumns];
		emptyDatas = new String[numberOfRows][numberOfColumns];
		sensorSignalMap = new HashMap<Integer, SensorSignal>();
		updateTable();
		firstEmptyIndex = 0;
	}

	@SuppressWarnings("unchecked")
	private void loadDatabaseDatas() {
		datas = emptyDatas.clone();
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, null, null, null, null);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			sensors = (List<SensorConfiguration>) JsonUtil.deserializeObject(response);
			filteredSensors = new ArrayList<>(sensors);
			if(sensors != null) {
				int size = sensors.size();
				for(int i = 0; i < size; i++) {
					initSensorConfigurationDatas(sensors.get(i), "");					
				}				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadCacheDatas() {
		datas = emptyDatas.clone();
		SensorState[] states = SensorState.values();
		try {
			String jsonRequest = null;
			for(SensorState state : states) {
				String stateName = state.name();
				jsonRequest = JsonUtil.serializeSensorsFromCacheRequest(RequestSender.CLIENT_FOR_SENSOR_STATE);
				String response = MonitrackGuiUtil.sendRequest(jsonRequest);
				List<SensorConfiguration> sensors = (List<SensorConfiguration>)JsonUtil.deserializeObject(response);
				for(SensorConfiguration sensor : sensors) {		
					initSensorConfigurationDatas(sensor, stateName);
				}
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public int getRowCount() {
		return datas.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return datas[arg0][arg1];
	}

	public SensorConfiguration getValueAt(int line) {
		if(line >= 0 && line < sensors.size())
			return sensors.get(line);
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		return header[column];
	}

	public void initSensorConfigurationDatas(SensorConfiguration sensorConfiguration, String state) {
		String id = sensorConfiguration.getSensorConfigurationId().toString();
		String type = sensorConfiguration.getSensorType().getEnglishLabel();
		String activity = sensorConfiguration.getSensorActivity().name();
		String locationId = sensorConfiguration.getLocationId().toString();
		String macAddress = sensorConfiguration.getMacAddress();
		String minThreshold = sensorConfiguration.getMinDangerThreshold().toString();
		String maxThreshold = sensorConfiguration.getMaxDangerThreshold().toString();
		datas[firstEmptyIndex] = new String[]{id, type, activity, locationId, 
				macAddress, minThreshold, maxThreshold, 
		state};
		firstEmptyIndex++;
		updateTable(); 
	}

	private void updateTable() {
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
		fireTableDataChanged();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			lastRowSelected = parentTable.getSelectedRow();
			int filteredSize = filteredSensors.size();
			if(lastRowSelected >= 0 && lastRowSelected < filteredSize) {
				lastSensorSelected = filteredSensors.get(lastRowSelected);
			}
			sensorsPage.getRateValueLabel().setText("Current threshold : " + currentThresholdValue 
					+ " " + lastSensorSelected.getMeasurementUnit() + " (last id n°" + lastIdSent + ")");
		}
	}

	public void setParent(JTable parent) {
		this.parentTable = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		firstEmptyIndex = 0;
		if(e.getSource() == sensorsPage.getValidateFiltersButton()) {
			datas = emptyDatas.clone();
			if(sensors == null) {
				JOptionPane.showMessageDialog(null, "You need to load the datas first", "Datas not loaded", JOptionPane.ERROR_MESSAGE);
			}
			else {

				int size = sensors.size();
				filteredSensors.clear();
				for(int i = 0 ; i < size; i++) {
					SensorConfiguration sensor = sensors.get(i);	
					if(isSensorCorrect(sensor)) {
						initSensorConfigurationDatas(sensor, "");
						filteredSensors.add(sensor);
					}				
				}
			}
		}
		else if(e.getSource() == sensorsPage.getStartSendingMessageButton()) {
			startStopSensorSignal(true);
		}
		else if(e.getSource() == sensorsPage.getStopSendingMessageButton()) {
			startStopSensorSignal(false);
		}
		else if(e.getSource() == sensorsPage.getLoadDatasFromDatabaseButton()) {
			loadDatabaseDatas();			
		}
		else if(e.getSource() == sensorsPage.getLoadDatasFromServerCacheButton()) {
			loadCacheDatas();			
		}

		updateTable();
	}

	private boolean isSensorCorrect(SensorConfiguration sensor) {
		String idString = sensorsPage.getIdTextField().getText().trim();
		if(idString.length() > 0 && NumberUtils.toInt(idString) != sensor.getSensorConfigurationId())
			return false;
		String type = sensorsPage.getSensorTypeComboBox().getSelectedItem().toString();
		if(!type.equalsIgnoreCase("-") && !type.equalsIgnoreCase(sensor.getSensorType().name()))
			return false;
		
		String activity = sensorsPage.getSensorActivityComboBox().getSelectedItem().toString();
		if(!activity.equalsIgnoreCase("-") && !activity.equalsIgnoreCase(sensor.getSensorActivity().name()))
			return false;

		String locationIdFilter = sensorsPage.getLocationTextField().getText().trim();
		if(locationIdFilter.length() > 0 && !locationIdFilter.equalsIgnoreCase(sensor.getLocationId().toString()))
			return false;

		return true;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		getThreshold(e, sensorsPage.getRateValueTextField());				
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		getThreshold(e, sensorsPage.getRateValueTextField());		

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		getThreshold(e, sensorsPage.getRateValueTextField());		
	}

	private void getThreshold(DocumentEvent e, JTextField field) {
		if(e.getDocument() == sensorsPage.getRateValueTextField().getDocument()) {

			float defaultValue = 0.015498f;
			String str = field.getText().trim();
			Float value = NumberUtils.toFloat(str, defaultValue);
			if(value == defaultValue) {
				field.setBackground(Color.ORANGE);			
			} else {
				field.setBackground(Color.WHITE);	
				currentThresholdValue = value;
				String unit = "";
				if(lastSensorSelected != null)
					unit = lastSensorSelected.getMeasurementUnit();
				sensorsPage.getRateValueLabel().setText("Current threshold : " + currentThresholdValue + " " + unit 
				+ " (last id n°" + lastIdSent + ")");

			}
		}
	}

	private void startStopSensorSignal(boolean sendSignal) {
		if(lastSensorSelected != null) {
			int id = lastSensorSelected.getSensorConfigurationId();
			lastIdSent = id;
			SensorSignal signal = null;
			if(sensorSignalMap.containsKey(lastSensorSelected.getSensorConfigurationId())){
				signal = sensorSignalMap.get(id);
				signal.setThresholdReached(currentThresholdValue);
				signal.setSendMessage(sendSignal);
			}
			else {
				signal = new SensorSignal(id, currentThresholdValue, lastSensorSelected.getCheckFrequency().longValue());	
				signal.setSendMessage(sendSignal);		
				sensorSignalMap.put(id, signal);
				Thread thread = new Thread(signal);
				thread.start();
			}
		}

	}

}
