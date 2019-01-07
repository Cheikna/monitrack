package com.monitrack.connectionPool.implementation;

import java.sql.Connection;

public class DataSource {
	
	private static JDBCConnectionPool connectionPool;

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
		//TODO
		return null;
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
	 * Clôt l'attribut connectionPool
	 */
	public static void closeConnectionPool()
	{
		//TODO
	}

}
