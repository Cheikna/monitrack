package com.monitrack.shared;

import com.monitrack.enumeration.ConnectionState;
import com.monitrack.socket.client.ClientSocket;

public class MonitrackGUIFactory {


	//Enables the communication with the server
	private static ClientSocket clientSocket;
	
	//Client name for the connection to the server
	private static String clientName = "";

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

	/**
	 * @return the clientName
	 */
	public static String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public static void setClientName(String clientName) {
		MonitrackGUIFactory.clientName = clientName;
	}
	
	
	
	
	
	

}
