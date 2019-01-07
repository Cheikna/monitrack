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
    private static final String USER            =  Util.getPropertyValueFromPropertiesFile("username_dev");
    private static final String PSWD            =  Util.getPropertyValueFromPropertiesFile("password_dev");
	
	public JDBCConnectionPool() {
		connections = new Vector<Connection>();
		fillConnectionsList(1);
	} 
	

	public void fillConnectionsList(int numberOfConnections) {
        for (int i = 0; i < numberOfConnections; i++ ){
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
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PSWD);
        } catch (SQLException e) {
            System.out.println("Connexion échouée !");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Connexion échouée !");
        }

        return connection;
    }  

	

}
