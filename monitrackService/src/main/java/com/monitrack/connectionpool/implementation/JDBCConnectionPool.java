package com.monitrack.connectionpool.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.connectionpool.interfaces.IJDBCConnectionPool;
import com.monitrack.util.Util;

public class JDBCConnectionPool implements IJDBCConnectionPool {
	
    private Vector<Connection> connections;
    private static final Logger log = LoggerFactory.getLogger(JDBCConnectionPool.class);
    private static final String URL             =  Util.getPropertyValueFromPropertiesFile("url");
    private static final String USER            =  Util.getPropertyValueFromPropertiesFile("username");
    private static final String PSWD            =  Util.getPropertyValueFromPropertiesFile("password");
	
	public JDBCConnectionPool() {
		connections = new Vector<Connection>();
	} 
	

	public void fillConnectionsList() {
		int numberOfConnections = 0;
		
		try
		{
			numberOfConnections = Integer.parseInt(Util.getPropertyValueFromPropertiesFile("number_of_connections"));
			log.info(numberOfConnections + " connection(s) will be put inside the connection pool.");
		}
		catch(Exception e)
		{
			log.error("The number of connections could not be read from the .properties file. Consequently, we will create 10 connections !");
			numberOfConnections = 10;
		}
		
        for (int i = 0; i < numberOfConnections; i++ )
        {
            connections.addElement(this.createConnection());
        }
	}

	public Connection getConnection() throws Exception {
		if(!connections.isEmpty())
		{
			Connection connection = connections.lastElement();
	        connections.removeElement(connection);
			log.info("A connection is being retrieved from the conection pool.");
	        return connection; 
		}
		else
		{
			throw new Exception("There are no connections left in the connection pool ! Please try later.");
		}     
	}

	public void putConnection(Connection connection) {
		if(connection != null)
		{
			connections.addElement(connection);	
			log.info("A connection is being added to the conection pool.");			
		}
		
	}

	public void closeAllConnections() {
		for(Connection connection : connections)
		{
			try {
				if(!connection.isClosed())
				{
					connection.close();	
					log.info("A connection has been closed.");
				}
			} catch (SQLException e) {
	            log.error("An error occurs during the closing of the connection :\n" + e.getMessage());
			}
		}
	}
	
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PSWD);
        } catch (SQLException e) {
            log.error("A SQL Exception has been raised during the creation of a connection :\n" + e.getMessage());
        }
        if (connection != null) {
            log.info("A connection has been created.");
        } else {
            log.error("An error occurs during the creation of a connection because the connection equals to null !");
        }

        return connection;
    }  

	

}
