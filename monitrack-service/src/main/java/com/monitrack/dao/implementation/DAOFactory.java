package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.util.List;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Location;
import com.monitrack.entity.Person;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.UnknownClassException;

public class DAOFactory {	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object execute(Connection connection, Class<?> entityClass, RequestType requestType, Object object, List<String> fields, List<String> values) throws Exception
	{
		DAO dao = getDAO(connection, entityClass);
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
	
	private static DAO<?> getDAO(Connection connection, Class<?> entityClass) throws UnknownClassException
	{
		if(entityClass.equals(Person.class))
			return new PersonDAO(connection);
		else if(entityClass.equals(Location.class))
			return new LocationDAO(connection);
		else
			throw new UnknownClassException(entityClass);
	}

}
