package com.monitrack.connectionPool;

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
	 * putConnection : remet une connexion dans l'attribut connectionPool
	 * 
	 * @param connection
	 */
	public static void putConnection(Connection connection)
	{
		//TODO
	}
	
	/**
	 * closeConnectionPool : cl�t l'attribut connectionPool
	 */
	public static void closeConnectionPool()
	{
		//TODO
	}

}
