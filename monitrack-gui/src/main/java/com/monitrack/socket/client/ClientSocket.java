package com.monitrack.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.enumeration.ConnectionState;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.shared.MonitrackGUIFactory;
import com.monitrack.util.JsonUtil;
import com.monitrack.util.Util;

public class ClientSocket {

	private static final Logger log = LoggerFactory.getLogger(ClientSocket.class);
	private final String SERVER_IP = Util.getPropertyValueFromPropertiesFile("server_ip");
	private final int PORT_NUMBER = Integer.parseInt(Util.getPropertyValueFromPropertiesFile("server_port_number"));
	private BufferedReader readFromServer;
	private PrintWriter writeToServer;

	/**
	 * Maximum delay of response from the server in milliseconds.
	 * If the server does not response within this delay, we consider this server as not available
	 */
	private final int TIMEOUT = 5000;

	private Socket socket;

	public ClientSocket() {

	}

	public ConnectionState start()
	{
		try 
		{
			log.info("Connection to the server " + SERVER_IP + ":" + PORT_NUMBER + "...");

			// Connection to a socket
			socket = new Socket(SERVER_IP, PORT_NUMBER);

			/*
			 * The following method throws a "SocketTimeoutException" when the timeout is exceed.
			 * This will prevent the client from waiting a very long time 
			 * whereas the server is not accessible because of a problem.
			 */
			socket.setSoTimeout(TIMEOUT);

			readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writeToServer = new PrintWriter(socket.getOutputStream(), true);		
			
			//We read to see if the thread has been launch on the server side 
			readFromServer.readLine();
			// Sends the client name to the server
			writeToServer.println(MonitrackGUIFactory.getClientName());
			
			log.info("Connected to the server");
			
			return ConnectionState.SUCCESS;
		}
		catch (SocketTimeoutException e) 
		{
			log.error("The socket timed out : " + e.getMessage() + ".\nThe server cannot  be reach and cannot response to your last request !");
		}
		catch (Exception e) {
			log.error("Disconnected from server - Client Error");
		}
		
		socket = null;
		return ConnectionState.NO_CONNECTION;
	}

	/**
	 * 
	 * @param requestToSendToServer : the request to send to the server
	 * @return the response from the server
	 * @throws IOException 
	 * @throws NoAvailableConnectionException 
	 */
	public String sendRequestToServer(String requestToSendToServer) throws IOException, NoAvailableConnectionException
	{
		String responseFromServer = "";
		
		log.info("Request sent to the server :\n" + JsonUtil.indentJsonOutput(requestToSendToServer));
		
		// Sends the request to the server
		writeToServer.println(requestToSendToServer);

		// Receives the response from the server
		responseFromServer = readFromServer.readLine();
		
		/*FIXME
		if(responseFromServer.equalsIgnoreCase(ConnectionState.NO_CONNECTION.getEnglishLabel()))
			throw new NoAvailableConnectionException();*/

		return responseFromServer;
	}

	public void exit()
	{
		try {
			readFromServer.close();
			writeToServer.close();
			socket.close();
			log.info("The communication with the server is closed");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

}
