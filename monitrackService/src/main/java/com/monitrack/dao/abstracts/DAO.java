package com.monitrack.dao.abstracts;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
	
	protected Connection connection;

	public DAO(Connection connection) {
		this.connection = connection;
	}
	
	public abstract T create(T obj);
	
	public abstract void update(T obj);
	
	public abstract void delete(T obj);	
	
	public abstract List<T> find(List<String> fields, List<String> values);

}
