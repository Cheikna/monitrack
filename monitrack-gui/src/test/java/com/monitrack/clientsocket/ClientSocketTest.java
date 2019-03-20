package com.monitrack.clientsocket;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.entity.Person;
import com.monitrack.enumeration.ConnectionState;
import com.monitrack.enumeration.JSONField;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientSocketTest {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private ClientSocket clientSocket;
	private ConnectionState connectionState;

	@Before
	public void testClientSocket() {
		clientSocket = new ClientSocket();	
		connectionState = clientSocket.start();
	}
	
	@Test
	public void testClientSocketStart()
	{		
		assertEquals(ConnectionState.SUCCESS, connectionState);
	}	

	@SuppressWarnings("unchecked")
	@Test
	public void testSendSelectRequestToServer() {
		try {
			String jsonRequest = Util.serializeRequest(RequestType.SELECT, Person.class, null, null, null);
			log.info("Request : " + Util.indentJsonOutput(jsonRequest));
			String response = clientSocket.sendRequestToServer(jsonRequest);
			List<Person> persons = (List<Person>) Util.deserializeObject(response);
			displayListElements(persons);
			assertNotNull(response);
		}  catch (NoAvailableConnectionException e) {
			log.error(e.getMessage());
		}catch (IOException e) {
			log.error("The message was not sent to the server :\n" + e.getMessage());
		}
	}
	
	@Test
	public void testTooMuchClientSocket()
	{
		int numberMaxOfConnections = Integer.parseInt(Util.getPropertyValueFromPropertiesFile("number_of_connections"));
		int i = 0;
		ConnectionState state;
		
		if(clientSocket != null)
		{
			numberMaxOfConnections -= 1;
		}
		
		if(i == numberMaxOfConnections)
		{
			state = new ClientSocket().start();
			assertEquals(ConnectionState.SUCCESS, state);
		}
		else
		{
			for(i = 0; i < numberMaxOfConnections; i++)
			{
				state = new ClientSocket().start();
				assertEquals(ConnectionState.SUCCESS, state);
			}
		}		
		
		state = new ClientSocket().start();
		assertEquals(ConnectionState.NO_CONNECTION, state);
		
	}
	

	@After
	public void testExit() {
		clientSocket.exit();
		clientSocket = null;		
	}
	
	private <T> void displayListElements(List<T> elements)
	{
		if(elements != null)
		{
			System.out.println("Displaying the elements of the list :\n");
			for(T element : elements)
			{
				System.out.println("===> " + element);
			}
		}
		else
		{
			System.out.println("The list is empty !");
		}
	}

}
