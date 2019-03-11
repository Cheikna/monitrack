package com.monitrack.clientsocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.listener.MonitrackListener;
import com.monitrack.shared.MonitrackGUIAttribute;
import com.monitrack.util.Util;

public class ClientSocket {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final String SERVER_IP = Util.getPropertyValueFromPropertiesFile("server_ip");
	private final int PORT_NUMBER = Integer.parseInt(Util.getPropertyValueFromPropertiesFile("server_port_number"));
	private boolean isConnectionPossible = false;

	/**
	 * Maximum delay of response from the server in milliseconds.
	 * If the server does not response within this delay, we consider this server as not available
	 */
	private final int TIMEOUT = 5000;

	private Socket socket;

	public ClientSocket() {
		try 
		{
			// Connection to a socket
			socket = new Socket(SERVER_IP, PORT_NUMBER);

			/*
			 * The following method throws a "SocketTimeoutException" when the timeout is exceed.
			 * This will prevent the client from waiting a very long time 
			 * whereas the server is not accessible because of a problem.
			 */
			socket.setSoTimeout(TIMEOUT);

			isConnectionPossible = true;
		} 
		catch (SocketTimeoutException e) 
		{
			log.error("The socket timed out : " + e.getMessage() + ".\nThe server cannot  be reach and cannot response to your last request !");
		}
		catch (Exception e) {
			log.error("Client Error : " + e.getMessage());
		}
	}
	
	public void startCommunicationWithServer()
	{
		try 
		{
			if(!isConnectionPossible)
				throw new Exception("Cannot reach the server !");
			
			BufferedReader readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writeToServer = new PrintWriter(socket.getOutputStream(), true);

			String requestToSendToServer;
			String responseFromServer;
			
			do {
				requestToSendToServer = ""; //FIXME
				writeToServer.println(requestToSendToServer);
				
				responseFromServer = readFromServer.readLine();
				// FIXME System.out.println(responseFromServer);
			}
			while(!MonitrackGUIAttribute.isWindowClosing());		

			socket.shutdownOutput();
			
		} 
		catch (Exception e) 
		{
			log.error("There was a problem during the communication with the server !\n" + e.getMessage());
		}
	}

}
