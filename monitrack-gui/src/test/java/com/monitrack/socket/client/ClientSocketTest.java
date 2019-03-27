package com.monitrack.socket.client;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	
	private static final Logger log = LoggerFactory.getLogger(ClientSocketTest.class);
	
	private ClientSocket clientSocket;
	
	@Before
	public void initialize()
	{
		clientSocket = new ClientSocket();	
	}
	
	@Test
	public void testClientSocketStart()
	{		
		ConnectionState connectionState = clientSocket.start();
		assertEquals(ConnectionState.SUCCESS, connectionState);
	}	

	@SuppressWarnings("unchecked")
	@Test
	public void testSendSelectRequestToServer() {
		try 
		{
			ConnectionState connectionState = clientSocket.start();
			if(connectionState == ConnectionState.SUCCESS)
			{
				String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Person.class, null, null, null);
				log.info("Request : " + JsonUtil.indentJsonOutput(jsonRequest));
				String response = clientSocket.sendRequestToServer(jsonRequest);
				List<Person> persons = (List<Person>) JsonUtil.deserializeObject(response);
				displayListElements(persons);
				assertNotNull(response);
			}
			else
				fail("Error when sending the request to the server");
			
		}  
		catch (IOException e) 
		{
			log.error("The message was not sent to the server :\n" + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRequestWithFilters()
	{
		try 
		{
			List<String> fields = new ArrayList<>();
			List<String> values = new ArrayList<>();
			fields.add("name");
			String name = "Franck";
			values.add(name);
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Person.class, null, fields, values);
			
			ConnectionState connectionState = clientSocket.start();
			if(connectionState == ConnectionState.SUCCESS)
			{
				String response = clientSocket.sendRequestToServer(jsonRequest);
				List<Person> persons = (List<Person>) JsonUtil.deserializeObject(response);
				
				for (Person person : persons) 
				{
					assertEquals(person.getNamePerson(), name);
				}
			}
			else
				fail("The connection with the server failed !");
			 
		} 
		catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	
	private <T> void displayListElements(List<T> elements)
	{
		if(elements != null && elements.size() > 0)
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
