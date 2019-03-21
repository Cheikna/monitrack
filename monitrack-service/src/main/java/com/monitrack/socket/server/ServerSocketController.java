package com.monitrack.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.dao.abstracts.DAO;
import com.monitrack.dao.implementation.DAOFactory;
import com.monitrack.entity.Person;
import com.monitrack.enumeration.ConnectionState;
import com.monitrack.enumeration.JSONField;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.exception.UnknownClassException;
import com.monitrack.util.Util;

/**
 * This class will have all the interaction with the database
 */
public class ServerSocketController implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(ServerSocketController.class);

	//This reader will allow us to read the message received and so sent by the client
	private BufferedReader readFromClient;

	//This writer will allow us to send a response to the client
	private PrintWriter writeToClient;

	private Socket socket;
	private Connection connection;
	//For the JSON
	//private ObjectMapper mapper;

	public ServerSocketController(Socket socket, Connection connection) {
		this.socket = socket;
		this.connection = connection;
		//mapper = new ObjectMapper();
	}

	@Override
	public void run() {	

		try 
		{		
			readFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writeToClient = new PrintWriter(socket.getOutputStream(), true);
			
			/*
			 * If we do not write something to the client, he will wait until his timeout
			 * and it will cause an exception while the client is connected
			 */
			writeToClient.println("");
			String clientName = readFromClient.readLine();
			System.out.println("Client name : " + clientName);
			
			/* 
			 * This loop will allow the server to maintain a link with the client 
			 * unless the client stops the program
			 */
			while(true)
			{
				String requestOfClient = readFromClient.readLine();
				log.info("Request received from the client (" + clientName + "):\n" + Util.indentJsonOutput(requestOfClient) + "\n");
				String responseToClient = executeClientRequest(requestOfClient);
				log.info("Response to the client (" + clientName + "):\n" + Util.indentJsonOutput(responseToClient) + "\n");
				writeToClient.println(responseToClient);
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

	@SuppressWarnings("finally")
	public String executeClientRequest(String jsonFormattedRequest) 
	{		
		String result = "";

		try 
		{
			ObjectMapper mapper = new ObjectMapper();
			JsonNode json = mapper.readTree(jsonFormattedRequest);
			// JSON Node containing the request info
			JsonNode requestNode = json.get(JSONField.REQUEST_INFO.getLabel());	
			String requestEntity = requestNode.get(JSONField.REQUESTED_ENTITY.getLabel()).textValue();
			Class<?> entityClass = Class.forName(requestEntity);
			JsonNode serializedObjectNode = json.get(JSONField.SERIALIZED_OBJECT.getLabel());
			
			RequestType requestType = RequestType.getRequestType(requestNode.get(JSONField.REQUEST_TYPE.getLabel()).textValue());
			
			switch(requestType)
			{
			case SELECT:
				result = executeClientSelectRequest(entityClass, requestNode);
				break;
			case INSERT:
				result = executeClientInsertRequest(entityClass, requestNode, serializedObjectNode);
				break;
			case UPDATE:
				result = executeClientUpdateRequest(entityClass, requestNode, serializedObjectNode);
				break;
			case DELETE:
				result = executeClientDeleteRequest(entityClass, requestNode, serializedObjectNode);
				break;
			default:
				log.error("The request type does not exist !");
				break;
			}

		} 
		catch (Exception e) 
		{
			log.error("An error occured during the execution of the client request :\n" + e.getMessage());
		}
		finally
		{
			return result;
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientSelectRequest(Class<?> entityClass, JsonNode requestNode) throws Exception
	{

		ObjectMapper mapper = new ObjectMapper();
		String result = "";		
		String fieldsStringFromJson = requestNode.get(JSONField.REQUESTED_FIELDS.getLabel()).textValue();
		String valuesStringFromJson = requestNode.get(JSONField.REQUIRED_VALUES.getLabel()).textValue();

		List<String> fields = null;
		List<String> requiredValues = null;

		if(fieldsStringFromJson != null && valuesStringFromJson != null
				&& fieldsStringFromJson.trim().length() > 0 && valuesStringFromJson.trim().length() > 0)
		{
			fields = mapper.readValue(fieldsStringFromJson, mapper.getTypeFactory().constructCollectionType(List.class, String.class));
			requiredValues = mapper.readValue(valuesStringFromJson, mapper.getTypeFactory().constructCollectionType(List.class, String.class));

		}

		DAO dao = DAOFactory.getDAO(connection, entityClass);
		result = Util.serializeObject(dao.find(fields, requiredValues), entityClass, "");

		//FIXME the following if will be deleted when the thing upside works
		/*if(entityClass.getSimpleName().equalsIgnoreCase(Person.class.getSimpleName()))
		{
			PersonDAO personDAO = new PersonDAO(connection);
			result = Util.serializeObject(personDAO.findAll(), Person.class, "");
		}*/

		return result;		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String executeClientInsertRequest(Class<?> entityClass, JsonNode requestNode, JsonNode serializedObjectNode) throws UnknownClassException
	{
		Object deserializedObject = Util.deserializeObject(serializedObjectNode.toString());
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		Object obj = dao.create(entityClass.cast(deserializedObject));
		String result = Util.serializeObject(entityClass.cast(obj), entityClass, "");
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientUpdateRequest(Class<?> entityClass, JsonNode requestNode, JsonNode dataNode) throws UnknownClassException
	{
		Object deserializedObject = Util.deserializeObject(dataNode.toString());		
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		dao.update(entityClass.cast(deserializedObject));
		String result = Util.serializeObject(null, entityClass, "");
		return result;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientDeleteRequest(Class<?> entityClass, JsonNode requestNode, JsonNode dataNode) throws UnknownClassException
	{
		Object deserializedObject = Util.deserializeObject(dataNode.toString());
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		dao.delete(entityClass.cast(deserializedObject));
		String result = Util.serializeObject(null, entityClass, "");
		return result;
	}

	/**
	 * This method will give back the connection to the pool and close the socket
	 */
	public void exit()
	{
		try 
		{
			DataSource.putConnection(connection);
			this.connection = null;
			socket.close();
			log.info("A client is disconnected");
		} 
		catch (IOException e) 
		{
			log.error("An error occured during the closure of a socket : " + e.getMessage());
		}
	}

}
