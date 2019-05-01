package com.monitrack.dao.implementation;

import java.sql.Connection;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.entity.Location;
import com.monitrack.entity.Person;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.exception.UnknownClassException;

public class DAOFactory {

	public static DAO<?> getDAO(Connection connection, Class<?> entityClass) throws UnknownClassException
	{
		if(entityClass.equals(Person.class))
			return new PersonDAO(connection);
		else if(entityClass.equals(Location.class))
			return new LocationDAO(connection);
		else if(entityClass.equals(SensorConfiguration.class))
			return new SensorConfigurationDAO(connection);
		else
			throw new UnknownClassException(entityClass);
	}

}
