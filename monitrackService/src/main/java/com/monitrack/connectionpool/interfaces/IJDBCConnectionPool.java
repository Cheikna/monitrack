package com.monitrack.connectionpool.interfaces;

import java.sql.Connection;

public interface IJDBCConnectionPool {
	
	/**
	 * Fills the connections attribute
	 */
	public void fillConnectionsList();
	
	
	/**
	 * 
	 * @return a connections of the connections attribute
	 * @throws Exception when there are no connections left
	 */
	public Connection getConnection() throws Exception;
	
	
	/**
	 * puts the parameter of type Connection inside the connections attribute
	 * 
	 * @param connection
	 */
	public void putConnection(Connection connection);
	
	
	/**
	 * Closes all connections of the connections attributes
	 */
	public void closeAllConnections();

}
