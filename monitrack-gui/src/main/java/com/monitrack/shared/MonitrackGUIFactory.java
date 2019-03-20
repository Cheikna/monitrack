package com.monitrack.shared;

import com.monitrack.clientsocket.ClientSocket;
import com.monitrack.enumeration.ConnectionState;

public class MonitrackGUIFactory {


	//Enables the communication with the server
	private static ClientSocket clientSocket;

	/**
	 * Initializes the socket
	 */
	public static ConnectionState initializeSocket()
	{
		clientSocket = new ClientSocket();
		ConnectionState socketState = clientSocket.start();
		return socketState;
	}

	/**
	 * @return the clientSocket
	 */
	public static ClientSocket getClientSocket() {
		return clientSocket;
	}
	
	
	
	

}
