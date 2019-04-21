package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.util.List;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.*;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.UnknownClassException;

public class DAOFactory {	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object execute(Connection connection, Class<?> entityClass, RequestType requestType, Object object, List<String> fields, List<String> values) throws Exception
	{
		DAO dao = null;
		
		if(entityClass.equals(Person.class))
			dao =  new PersonDAO(connection);
		else if(entityClass.equals(Location.class))
			dao = new LocationDAO(connection);
		else if(entityClass.equals(Flow.class))
			dao = new FlowDAO(connection);
		else if(entityClass.equals(InputOutput.class))
			dao = new InputOutputDAO(connection);
		else if(entityClass.equals(Light.class))
			dao = new LightDAO(connection);
		else if(entityClass.equals(Sensor.class))
			dao = new SensorDAO(connection);
		else if(entityClass.equals(Smoke.class))
			dao = new SmokeDAO(connection);
		else
			throw new UnknownClassException(entityClass);		
		
		//The code goes here if the correct DAO was found
		Object result = null;
		
		switch(requestType)
		{
			case SELECT:
				result = dao.find(fields, values);
				break;
			case INSERT:
				result = dao.create(entityClass.cast(object));
				break;
			case UPDATE:
				dao.update(entityClass.cast(object));
				break;
			case DELETE:
				dao.delete(entityClass.cast(object));
				break;
			default:
				throw new Exception("The request type does not exist !");
		}
		return result;
		
	}

}
