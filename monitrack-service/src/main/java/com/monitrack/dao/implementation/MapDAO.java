package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Map;
import com.monitrack.entity.Person;

public class MapDAO extends DAO<Map>{
	
	private static final Logger log = LoggerFactory.getLogger(MapDAO.class);	
	//private JsonFactory factory = new JsonFactory();
	private final Object lock = new Object();

	public MapDAO(Connection connection) 
	{
		super(connection);
	}

	public List<Map> find(List<String> fields, List<String> values)
	{
		synchronized (lock) {
			List<Map> Maps = new ArrayList<Map>();
			String sql = "SELECT * FROM location" ;
			if (connection != null) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					Map Map;
					while (rs.next()) {
						Map = getMapFromResultSet(rs);
						if (Map != null) {
							Maps.add(Map);
						}
					}
				} catch (Exception e) {
					log.error("An error occurred when finding the Maps : " + e.getMessage());
					e.printStackTrace();
				}
			}
			return Map;
		}
	}

	@Override
	public Map create(Map obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Map obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Map obj) {
		// TODO Auto-generated method stub
		
	}
	

}


