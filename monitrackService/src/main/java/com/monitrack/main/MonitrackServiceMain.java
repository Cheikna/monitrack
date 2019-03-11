package com.monitrack.main;

import com.monitrack.connectionpool.implementation.DataSource;
import com.monitrack.serversocket.Server;

/**
 * This main class must initiate the connection pool and write some info
 * In order to know how much connections have been taken
 */
public class MonitrackServiceMain {

	public static void main(String [] args) {
		DataSource.startConnectionPool();
		
		Server server = new Server();
		// Starts the server and enables client connections
		server.start();
		
	}
}
