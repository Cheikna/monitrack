package com.monitrack.connectionpool.implementation;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSource {
	
	private static final Logger log = LoggerFactory.getLogger(DataSource.class);
	private static JDBCConnectionPool connectionPool = new JDBCConnectionPool();

	public DataSource() {}
	
	/**
	 * @return Connection :
	 * 		connection of the connectionPool attribute
	 */
	public static Connection getConnection()
	{
		try {
			return connectionPool.getConnection();
		} catch (Exception e) {
			log.error("The cconnections are not availabe. Check if the virtual machines are turned on :\n" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Puts a connection in the connectionPool attribute
	 * 
	 * @param connection
	 */
	public static void putConnection(Connection connection)
	{
		connectionPool.putConnection(connection);
	}
	
	/**
	 * Closes the attribute connectionPool
	 */
	public static void closeConnectionPool()
	{
		connectionPool.closeAllConnections();
	}

}
