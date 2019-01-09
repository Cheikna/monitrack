package com.monitrack.connectionPool.implementation;

import java.sql.Connection;

public class DataSource {
	
	private static JDBCConnectionPool connectionPool = new JDBCConnectionPool();

	public DataSource() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return Connection :
	 * 		retourne une connexion de l'attribut connectionPool
	 */
	public static Connection getConnection()
	{
		return connectionPool.getConnection();
	}
	
	/**
	 * Remet une connexion dans l'attribut connectionPool
	 * 
	 * @param connection
	 */
	public static void putConnection(Connection connection)
	{
		//TODO
	}
	
	/**
	 * Cl�t l'attribut connectionPool
	 */
	public static void closeConnectionPool()
	{
		//TODO
	}

}
