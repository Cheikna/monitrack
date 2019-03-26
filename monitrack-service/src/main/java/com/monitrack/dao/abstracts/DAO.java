package com.monitrack.dao.abstracts;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

public abstract class DAO<T> {
	
	protected Connection connection;

	public DAO(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Creates the object in the database
	 * @param obj
	 * @return the object with its id retrieved from the database
	 */
	public abstract T create(T obj);
	
	/**
	 * Updates an object
	 * @param obj
	 */
	public abstract void update(T obj);
	
	/**
	 * Deletes an object
	 * @param obj
	 */
	public abstract void delete(T obj);	
	
	/**
	 * Finds the objects in the database
	 * @param fields : the fields we want to filter
	 * @param values : the values required for the fields
	 * @return
	 */
	public abstract List<T> find(List<String> fields, List<String> values);
	
	/**
	 * Allows to add quote if needeed in the sql request
	 * @param value
	 * @return the value with quote if it is not a number
	 */
	protected String addNecessaryQuotes(String value)
	{
		if(!NumberUtils.isParsable(value))
			value = "'" + value + "'";
		return value;
	}
	
	
	/**
	 * Allows to specified the constraints in the request
	 * @param fields
	 * @param values
	 * @return
	 */
	protected String getRequestFilters(List<String> fields, List<String> values)
	{
		String sql = "";
		
		if(fields != null && values != null)
		{
			int fieldsListSize = fields.size();
			if(fieldsListSize >= 1 && fieldsListSize == values.size())
			{
				sql += " WHERE ";
				int i;
				
				for(i = 0; i < fieldsListSize - 1; i++)
					sql += fields.get(i) + "=" + addNecessaryQuotes(values.get(i)) + " AND ";
				
				sql += fields.get(i) + "= " + addNecessaryQuotes(values.get(i));
			}
			
		}
		
		return sql;
	}

}
