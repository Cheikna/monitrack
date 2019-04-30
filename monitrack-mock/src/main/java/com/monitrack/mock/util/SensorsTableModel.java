package com.monitrack.mock.util;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.monitrack.entity.SensorConfiguration;

public class SensorsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] header = {"ID", "TYPE", "ETAT", "EMPLACEMENT", "ADRESSE MAC", "SEUIL ACTUEL", "SEUIL MINIMUM", "SUEIL MAXIMAL"};
	private String [][] datas = {{"ID", "TYPE", "ETAT", "NOM DE L'EMPLACEMENT", "ADRESSE MAC", "SEUIL ACTUEL", "SEUIL MINIMUM", "SUEIL MAXIMAL"}};
	private List<SensorConfiguration> sensors;
	
	public SensorsTableModel() {
		//header = {"ID", "TYPE", "ETAT", "EMPLACEMENT", "ADRESSE MAC", "SEUIL ACTUEL", "SEUIL MINIMUM", "SUEIL MAXIMAL"};
		//datas = {{"ID", "TYPE", "ETAT", "NOM DE L'EMPLACEMENT", "ADRESSE MAC", "SEUIL ACTUEL", "SEUIL MINIMUM", "SUEIL MAXIMAL"}};
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
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String[][] getDatas() {
		return datas;
	}

	public void setDatas(String[][] datas) {
		this.datas = datas;
	}

	public List<SensorConfiguration> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorConfiguration> sensors) {
		this.sensors = sensors;
	}

}
