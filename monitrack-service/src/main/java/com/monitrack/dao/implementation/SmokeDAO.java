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
import com.monitrack.entity.Person;
import com.monitrack.entity.Sensor;
import com.monitrack.entity.Smoke;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;
import com.monitrack.enumeration.UserProfile;

public class SmokeDAO extends DAO<Smoke>{
	
	private static final Logger log = LoggerFactory.getLogger(SmokeDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();

	public SmokeDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Smoke create(Smoke obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Smoke obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Smoke obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Smoke> find(List<String> fields, List<String> values) {
		synchronized (lock) {
			List<Smoke> smokes = new ArrayList<Smoke>();
			String sql = "SELECT * FROM SMOKE" + super.getRequestFilters(fields, values);
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					Smoke smoke;
					while (rs.next()) {
						smoke = getSensorFromResultSet(rs);
						if (smoke != null) {
							smokes.add(smoke);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding the smoke's sensors : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return smokes;
		}
	}
	
	@SuppressWarnings("finally")
	private Smoke getSensorFromResultSet(ResultSet rs)
	{
		Smoke smoke = null;
		try {
			smoke = new Smoke(rs.getInt("id"),SensorState.getSensorState("state"),SensorType.getSensorType("type"),
					null, rs.getString("ip_address"),rs.getString("mac_address"),
					rs.getFloat("alert_treshold"), rs.getTimestamp("time_change"), rs.getTime("begin_time"),
					rs.getTime("end_time"),rs.getTimestamp("creation_date"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return smoke;
		}
	}
	
	

}
