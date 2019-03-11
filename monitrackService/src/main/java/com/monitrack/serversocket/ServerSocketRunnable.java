package com.monitrack.serversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.connectionpool.implementation.DataSource;

/**
 * This class will have all the interaction with the database
 */
public class ServerSocketRunnable implements Runnable {
	
	private final Logger log = LoggerFactory.getLogger(ServerSocketRunnable.class);

	private Socket socket;
	private Connection connection;
	
	public ServerSocketRunnable(Socket socket) {
		this.socket = socket;
		connection = DataSource.getConnection();
	}
	
	@Override
	public void run() {
		try 
		{
			//This reader will allow us to read the message received and so sent by the client
			BufferedReader readFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			//This writer will allow us to send a response to the client
			PrintWriter writeToClient = new PrintWriter(socket.getOutputStream(), true);

			/* 
			 * This loop will allow the server to maintain a link with the client 
			 * unless the client calls socket.shutdownOutput()
			 */
			while(!socket.isOutputShutdown())
			{
				String requestOfClient = readFromClient.readLine();
				log.info("Request received from the client :\n" + requestOfClient);
				String responseToClient = executeClientRequest(requestOfClient);
				writeToClient.println("Reply from server :\n" + responseToClient);
			}

		} 
		catch (Exception e) 
		{
			log.error("Exception : " + e.getMessage());
		}
		finally 
		{
			exit();			
		}
	}
	
	public String executeClientRequest(String jsonFormattedRequest) 
	{
		// FIXME
		return "";
	}
	
	/**
	 * This method will give back the connection to the pool and close the socket
	 */
	public void exit()
	{
		try {
			socket.close();
			log.info("A client is disconnected");
		} catch (IOException e) {
			log.error("An error occured during the closure of a socket : " + e.getMessage());
		}
		DataSource.putConnection(connection);
		this.connection = null;
	}
	
}
