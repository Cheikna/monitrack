package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.ManualTriggerHistory;

public class ManualTriggerHistoryDAO extends DAO<ManualTriggerHistory> {

	private final String TABLE_NAME = "MANUAL_TRIGGER_HISTORY";
	
	public ManualTriggerHistoryDAO(Connection connection) {
		super(connection);
	}

	@Override
	public ManualTriggerHistory create(ManualTriggerHistory obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ManualTriggerHistory obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ManualTriggerHistory obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ManualTriggerHistory> find(List<String> fields, List<String> values) {
		return super.find(fields, values, TABLE_NAME);
	}

	@Override
	protected ManualTriggerHistory getSingleValueFromResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
