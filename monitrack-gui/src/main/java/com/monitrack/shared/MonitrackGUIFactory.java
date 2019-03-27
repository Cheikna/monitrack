package com.monitrack.shared;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.enumeration.ConnectionState;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.JSONField;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.socket.client.ClientSocket;
import com.monitrack.util.JsonUtil;

public class MonitrackGUIFactory {

	private static final Logger log = LoggerFactory.getLogger(MonitrackGUIFactory.class);
			
	public static void showNoConnectionMessage() {
		JOptionPane.showMessageDialog(null, ConnectionState.NO_CONNECTION.getFrenchLabel(), "Erreur", JOptionPane.ERROR_MESSAGE, Images.NO_CONNECTION.getIcon());
	}
	
	public static String sendRequest(String jsonRequest) throws NoAvailableConnectionException, IOException
	{
		String response = "";
		ClientSocket clientSocket = new ClientSocket();
		ConnectionState state = clientSocket.start();
		if(state == ConnectionState.SUCCESS)
		{
			response = clientSocket.sendRequestToServer(jsonRequest);
			
			//Checks if we reserved a connection, because the response is not in json format
			if(jsonRequest.trim().equals(ConnectionState.RESERVED_CONNECTION.getCode().toString()))
			{
				response = ConnectionState.RESERVED_CONNECTION.getFrenchLabel();
			}
			else
			{
				String error = JsonUtil.getJsonNodeValue(JSONField.ERROR_MESSAGE, response).trim();
				
				if(!error.equals(""))
				{
					JOptionPane.showMessageDialog(null, error, "Erreur", JOptionPane.ERROR_MESSAGE);
					throw new NoAvailableConnectionException();
				}
				
				log.info("Response from the server :\n" + JsonUtil.indentJsonOutput(response));
			}
			
			return response;
		}
		else
		{	
			throw new NoAvailableConnectionException();
		}
	}

}
