package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Flow;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public class FlowDAO  extends DAO<Flow>{
	
	private static final Logger log = LoggerFactory.getLogger(FlowDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();

	public FlowDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Flow create(Flow obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Flow obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Flow obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Flow> find(List<String> fields, List<String> values) {
		synchronized (lock) {
			List<Flow> flows = new ArrayList<Flow>();
			String sql = "SELECT * FROM FLOW" + super.getRequestFilters(fields, values);
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					Flow flow;
					while (rs.next()) {
						flow = getFlowSensorFromResultSet(rs);
						if (flow != null) {
							flows.add(flow);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding the flow's sensors : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return flows;
		}
	}
	
	@SuppressWarnings("finally")
	private Flow getFlowSensorFromResultSet(ResultSet rs)
	{
		return null;
	}

}
