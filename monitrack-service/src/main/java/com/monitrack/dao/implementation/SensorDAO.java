package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.util.List;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Sensor;

public class SensorDAO extends DAO<Sensor>{

	public SensorDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Sensor create(Sensor obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Sensor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Sensor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Sensor> find(List<String> fields, List<String> values) {
		// TODO Auto-generated method stub
		return null;
	}

}
