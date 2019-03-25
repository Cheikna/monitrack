package com.monitrack.socket.client;

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
import com.monitrack.enumeration.RequestType;
import com.monitrack.socket.client.ClientSocket;
import com.monitrack.util.JsonUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientSocketTest {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private ClientSocket clientSocket;
	private ConnectionState connectionState;
	
	@Test
	public void testClientSocketStart()
	{		
		clientSocket = new ClientSocket();	
		connectionState = clientSocket.start();
		assertEquals(ConnectionState.SUCCESS, connectionState);
	}	

	@SuppressWarnings("unchecked")
	@Test
	public void testSendSelectRequestToServer() {
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Person.class, null, null, null);
			log.info("Request : " + JsonUtil.indentJsonOutput(jsonRequest));
			String response = clientSocket.sendRequestToServer(jsonRequest);
			List<Person> persons = (List<Person>) JsonUtil.deserializeObject(response);
			displayListElements(persons);
			assertNotNull(response);
		}  catch (IOException e) {
			log.error("The message was not sent to the server :\n" + e.getMessage());
		}
	}

	/*

	@After
	public void testExit() {
		clientSocket.exit();
		clientSocket = null;		
	}*/
	
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
