package com.monitrack.serversocket;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.connectionpool.implementation.DataSource;
import com.monitrack.enumeration.ConnectionState;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.util.Util;

public class Server {

	private final Logger log = LoggerFactory.getLogger(Server.class);
	private Connection connection;

	private ServerSocket serverSocket;
	private static final int PORT_NUMBER = Integer.parseInt(Util.getPropertyValueFromPropertiesFile("server_port_number"));

	public Server() {
		connection = null;
	}

	/**
	 * Starts the connection pool and enables client connections
	 */
	public void start()
	{
		log.info("Launching of the server...");
		DataSource.startConnectionPool();

		try{
			serverSocket = new ServerSocket(PORT_NUMBER);
			while(true)
			{
				/*
				 * The socket is used to communicate with the client. Many clients will use the same port 
				 * but different instance of socket.
				 * While no client is not connected to this socket, the accept method of method will pause the program.
				 */				
				if(DataSource.getRemaningConnections() > 0)
				{
					log.info("Waiting for a client connection...");
					Socket socket = serverSocket.accept();
					connection = DataSource.getConnection();
					System.out.println("A client is logged.");
					/*
					 * After a connection from a client to a server, the client will be handle on his own Thread
					 */
					ServerSocketController serverSocketController  = new ServerSocketController(socket, connection);
					Thread clientThread = new Thread(serverSocketController);
					clientThread.start();
				}

			}
		}
		catch(Exception e) {
			log.error("Server exception : " + e.getMessage());
			DataSource.closeConnectionPool();
		}
	}

}
