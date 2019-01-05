package com.monitrack.connectionPool.implementation;

import java.sql.Connection;
import java.util.ArrayList;

import com.monitrack.connectionPool.interfaces.IJDBCConnectionPool;

public class JDBCConnectionPool implements IJDBCConnectionPool {

	/**
	 * Attribut collectionnant les connexions physiques instances de la classe connexion
	 */
	private ArrayList<Connection> connections;
	
	public JDBCConnectionPool() {
		// TODO : créer l'instance de l'attribut connections
	}

	public void fillConnectionsList(int numberOfConnections) {
		// TODO Auto-generated method stub
		
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	public void putConnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	public boolean closeAllConnections() {
		// TODO Auto-generated method stub
		return false;
	}

}
