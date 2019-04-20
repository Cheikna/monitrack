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
import com.monitrack.entity.Light;
import com.monitrack.enumeration.SensorConfiguration;
import com.monitrack.enumeration.SensorType;

public class LightDAO extends DAO<Light>{
	
	private static final Logger log = LoggerFactory.getLogger(LightDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();

	public LightDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Light create(Light obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Light obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Light obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Light> find(List<String> fields, List<String> values) {
		synchronized (lock) {
			List<Light> lights = new ArrayList<Light>();
			String sql = "SELECT * FROM LIGHT" + super.getRequestFilters(fields, values);
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					Light light;
					while (rs.next()) {
						light = getLightSensorFromResultSet(rs);
						if (light != null) {
							lights.add(light);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding the light's sensors : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return lights;
		}
	}
	
	@SuppressWarnings("finally")
	private Light getLightSensorFromResultSet(ResultSet rs)
	{
		Light light = null;
		try {
			light = new Light(rs.getInt("id"),SensorConfiguration.getSensorConfiguration("state"),SensorType.getSensorType("type"),
					null, rs.getString("ip_address"),rs.getString("mac_address"),
					rs.getFloat("alert_treshold"), rs.getTimestamp("time_change"), rs.getTime("begin_time"),
					rs.getTime("end_time"),rs.getTimestamp("creation_date"));
		} catch (SQLException e) {
			log.error("An error occurred when getting one Light Sensor from the resultSet : " + e.getMessage());
		}
		finally {
			return light;
		}
	}

}
