package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.util.List;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.SensorHistory;

public class SensorHistoryDAO extends DAO<SensorHistory> {

	public SensorHistoryDAO(Connection connection) {
		super(connection);
	}

	@Override
	public SensorHistory create(SensorHistory obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SensorHistory obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SensorHistory obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SensorHistory> find(List<String> fields, List<String> values) {
		// TODO Auto-generated method stub
		return null;
	}

}
