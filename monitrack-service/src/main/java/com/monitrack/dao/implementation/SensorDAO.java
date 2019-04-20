package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Sensor;

public abstract class SensorDAO<T> extends DAO<T> {
	
	private static final Logger log = LoggerFactory.getLogger(SensorDAO.class);
	private final Object lock = new Object();	
	private String tableName;

	public SensorDAO(Connection connection, String tableName) {
		super(connection);
		this.tableName = tableName;
	}

	public int createSensor(T obj) {
		//FIXME
		int id = 1;
		return id;
	}

	public void updateSensor(T obj) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(T obj) {
		if(obj instanceof Sensor && connection != null) {
			synchronized (lock) {
				try {
					Sensor sensor = (Sensor)obj;
					PreparedStatement preparedStatement = null;
					preparedStatement = connection.prepareStatement("DELETE FROM SENSOR where ID_SENSOR=(?)");
					preparedStatement.setInt(1, sensor.getId());					
					preparedStatement.execute();
				} catch (Exception e) {
					log.error("An error occurred during the delete of a location : " + e.getMessage());
					e.printStackTrace();
				}				
			}
			
		}		
	}

	public List<T> find(List<String> fields, List<String> values) {
		synchronized (lock) {
			String sql = "select * from " + tableName +" first_table inner join SENSOR second_table on first_table.ID_SENSOR = second_table.ID_SENSOR";
			List<T> sensors = new ArrayList<T>();
			sql += super.getRequestFilters(fields, values);
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					T sensor;
					while (rs.next()) {
						sensor = getSensorFromResultSet(rs);
						if (sensor != null) {
							sensors.add(sensor);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding sensors : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return sensors;
		}
	}
	
	protected abstract T getSensorFromResultSet(ResultSet rs);

}
