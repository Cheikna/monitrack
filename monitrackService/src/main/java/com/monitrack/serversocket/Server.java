package com.monitrack.serversocket;

import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.util.Util;

public class Server {

	private final Logger log = LoggerFactory.getLogger(Server.class);
			
	private ServerSocket serverSocket;
	private int portNumber;

	public Server() 
	{
		try 
		{
			portNumber = Integer.parseInt(Util.getPropertyValueFromPropertiesFile("server_port_number"));
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage());
		}
	}

	public void start()
	{
		try{
			serverSocket = new ServerSocket(portNumber);
			while(true)
			{
				log.info("Waiting for a client connection...");
				/*
				 * The socket is used to communicate with the client. Many clients will use the same port 
				 * but different instance of socket.
				 * While no client is not connected to this socket the accept method of method will pause the program.
				 */
				Socket socket = serverSocket.accept();
				System.out.println("A client is logged.");
				/*
				 * After a connection from a client to a server, the client will be handle on his own Thread
				 */
				ServerSocketRunnable serverSocketRunnable  = new ServerSocketRunnable(socket);
				Thread clientThread = new Thread(serverSocketRunnable);
				clientThread.start();
			}
		}
		catch(Exception e) {
			log.error("Server exception : " + e.getMessage());
		}
	}

}
