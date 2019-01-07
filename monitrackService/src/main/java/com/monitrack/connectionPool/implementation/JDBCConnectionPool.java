package com.monitrack.connectionPool.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.monitrack.connectionPool.interfaces.IJDBCConnectionPool;
import com.monitrack.util.Util;

public class JDBCConnectionPool implements IJDBCConnectionPool {
	
    private Vector<Connection> connections;
    private static final String URL             =  Util.getPropertyValueFromPropertiesFile("url_dev");
    private static final String DRIVER          =  Util.getPropertyValueFromPropertiesFile("driver");
    private static final String USER            =  Util.getPropertyValueFromPropertiesFile("username_dev");
    private static final String PSWD            =  Util.getPropertyValueFromPropertiesFile("password_dev");
	
	public JDBCConnectionPool() {
		connections = new Vector<Connection>();
		fillConnectionsList(1);
		System.out.println(Util.getPropertyValueFromPropertiesFile("password_dev"));
	} 
	

	public void fillConnectionsList(int numberOfConnections) {
        for (int i=0; i<numberOfConnections; i++ ){
            connections.addElement(this.createConnection());
        }
	}

	public Connection getConnection() {
		Connection connection = connections.lastElement();
        connections.removeElement(connection);
        return connection;      
	}

	public void putConnection(Connection connection) {
		connections.addElement(connection);		
	}

	public boolean closeAllConnections() {
		for(Connection connection : connections)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
    private Connection createConnection(){
    	/*System.out.println("-------- MySQL "
                + "JDBC Connection Testing ------------");
        try {
            Class.forName("org.mysql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            
        }
        System.out.println("MySQL JDBC Driver Registered!");*/
        Connection connection = null;
        try {
            String lien = Util.getPropertyValueFromPropertiesFile("complete_link_dev");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:5432/moinitrack_dev", USER,
                    PSWD);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            return connection;
        } else {
            System.out.println("Failed to make connection!");
            return null;
        }
    }  

	

}
