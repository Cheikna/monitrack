package com.monitrack.mock.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.math.NumberUtils;

import com.monitrack.entity.Location;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestType;
import com.monitrack.mock.panel.SensorsPage;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class SensorsTableModel extends AbstractTableModel implements ListSelectionListener, ActionListener, ChangeListener {
	
	
	private JTable parent;
	private SensorsPage sensorsPage;
	private static final long serialVersionUID = 1L;
	private String[] header = {"ID", "TYPE", "ACTIVITY", "LOCATION", "MAC ADDRESS", "CURRENT THRESHOLD", "THRESHOLD MIN", "THRESHOLD MAX", "STATE"};
	private String [][] datas;
	private String [][] emptyDatas;
	private final int numberOfRows = 500;
	private final int numberOfColumns = 9;
	private int firstEmptyIndex;
	private List<SensorConfiguration> sensors;
	private List<SensorConfiguration> filteredSensors;
	
	@SuppressWarnings("unchecked")
	public SensorsTableModel(SensorsPage sensorsPage, JTable parent) {
		this.sensorsPage = sensorsPage;
		this.parent = parent;
		parent.getSelectionModel().addListSelectionListener(this);
		parent.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		parent.setModel(this);
		datas = new String[numberOfRows][numberOfColumns];
		emptyDatas = new String[numberOfRows][numberOfColumns];
		firstEmptyIndex = 0;
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, null, null, null);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			sensors = (List<SensorConfiguration>) JsonUtil.deserializeObject(response);
			filteredSensors = new ArrayList<>(sensors);
			if(sensors != null) {
				int size = sensors.size();
				for(int i = 0; i < size; i++) {
					initSensorConfigurationDatas(sensors.get(i));					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void initSensorConfigurationDatas(SensorConfiguration sensorConfiguration) {
		String id = sensorConfiguration.getSensorConfigurationId().toString();
		String type = sensorConfiguration.getSensorType().getEnglishLabel();
		String activity = sensorConfiguration.getSensorActivity().name();
		String location = "";
		String macAddress = sensorConfiguration.getMacAddress();
		String currentThreshold = sensorConfiguration.getCurrentThreshold().toString();
		String minThreshold = sensorConfiguration.getMinDangerThreshold().toString();
		String maxThreshold = sensorConfiguration.getMaxDangerThreshold().toString();
		datas[firstEmptyIndex] = new String[]{id, type, activity, location, 
				macAddress, currentThreshold, 
				minThreshold, maxThreshold, 
				"STATE"};
		firstEmptyIndex++;
		updateTable(); 
	}

	private void updateTable() {
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
		fireTableDataChanged();
	}

	public List<SensorConfiguration> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorConfiguration> sensors) {
		this.sensors = sensors;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			int[] rows =  parent.getSelectedRows();
			for(int i : rows) {
				System.out.println(i);
			}
		}
		
	}

	public void setParent(JTable parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		firstEmptyIndex = 0;
		datas = emptyDatas.clone();
		filteredSensors.clear();
		if(e.getSource() == sensorsPage.getValidateFiltersButton()) {
			int size = sensors.size();
			for(int i = 0 ; i < size; i++) {
				SensorConfiguration sensor = sensors.get(i);	
				if(isSensorCorrect(sensor)) {
					initSensorConfigurationDatas(sensor);
					filteredSensors.add(sensor);
				}				
			}
		}
		updateTable();
		
	}
	
	private boolean isSensorCorrect(SensorConfiguration sensor) {
		String idString = sensorsPage.getIdTextField().getText().trim();
		if(idString.length() > 0 && NumberUtils.toInt(idString) != sensor.getSensorConfigurationId())
			return false;
		if(!sensorsPage.getSensorTypeComboBox().getSelectedItem().toString().equalsIgnoreCase(sensor.getSensorType().name()))
			return false;
		if(!sensorsPage.getSensorActivityComboBox().getSelectedItem().toString().equalsIgnoreCase(sensor.getSensorActivity().name()))
			return false;
		
		String locationFilter = sensorsPage.getLocationTextField().getText().trim();
		Location location = sensor.getLocation();
		String locationName = (location != null) ? location.getNameLocation() : "";
		if(!locationFilter.equalsIgnoreCase(locationName))
			return false;
		
		return true;
	}

	@Override//Slider
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() instanceof JSlider) {
			JSlider slider = (JSlider)e.getSource();
			if(!slider.getValueIsAdjusting() && slider == sensorsPage.getSensorSlider()) {
				int value = sensorsPage.getSensorSlider().getValue();
				System.out.println("=======> slider value : " + value);
				
			}
		}
		
	}

}
