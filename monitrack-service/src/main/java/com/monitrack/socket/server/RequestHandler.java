package com.monitrack.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.dao.abstracts.DAO;
import com.monitrack.dao.implementation.DAOFactory;
import com.monitrack.enumeration.ConnectionState;
import com.monitrack.enumeration.JSONField;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.UnknownClassException;
import com.monitrack.util.JsonUtil;

/**
 * This class will execute the client request
 */
public class RequestHandler implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	//This reader will allow us to read the message received and so sent by the client
	private BufferedReader readFromClient;

	//This writer will allow us to send a response to the client
	private PrintWriter writeToClient;

	private Socket socket;
	private Connection connection;
	//For the JSON
	private ObjectMapper mapper;

	public RequestHandler(Socket socket, Connection connection) {
		this.socket = socket;
		this.connection = connection;
		mapper = new ObjectMapper();
	}

	@Override
	public void run() {	

		try 
		{		
			log.info("Client connected");
			readFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			writeToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			writeToClient.println();
			
			String requestOfClient = readFromClient.readLine();
			String reservedConnectionCode = ConnectionState.RESERVED_CONNECTION.getCode().toString();
			
			//Checks if the client (= super user) wants to reserve the connection
			if(requestOfClient.trim().equalsIgnoreCase(reservedConnectionCode))
			{
				int reservedTimeInMilliseconds = 17000;
				String reservedTime = (new Integer(reservedTimeInMilliseconds / 1000)).toString();
				log.info("A client has reserved a connection for " + reservedTime + " sec\n");
				String message = "Votre connexion ne sera pas utilisable par les autres durant " + reservedTime + " sec-" + reservedTime;
				writeToClient.println(message);
				//Sleeps the Thread in order to make the connection no accessible by another person
				Thread.sleep(reservedTimeInMilliseconds);
				log.info("A client has release its reserved connection !");
			}
			else
			{
				log.info("Request received from the client :\n" + JsonUtil.indentJsonOutput(requestOfClient) + "\n");
				String responseToClient = executeClientRequest(requestOfClient);
				log.info("Response to the client :\n" + JsonUtil.indentJsonOutput(responseToClient) + "\n");
				writeToClient.println(responseToClient);				
			}

		}
		catch (Exception e) 
		{
			log.error("Exception : The client is disconnected");
		}
		finally 
		{			
			exit();			
		}
	}

	/**
	 * Filter the request in order to know which type of request it is
	 * 
	 * @param jsonFormattedRequest
	 * @return
	 */
	@SuppressWarnings("finally")
	public String executeClientRequest(String jsonFormattedRequest) 
	{		
		String result = "";

		try 
		{
			mapper = new ObjectMapper();
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
	
	/**
	 * Executes a select request
	 * 
	 * @param entityClass
	 * @param requestNode: contains the filters (like the required fields and values)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientSelectRequest(Class<?> entityClass, JsonNode requestNode) throws Exception
	{

		mapper = new ObjectMapper();
		String result = "";
		// The fields we wants to filter
		String fieldsStringFromJson = requestNode.get(JSONField.REQUESTED_FIELDS.getLabel()).toString();
		// The values of the filters we want to filter
		String valuesStringFromJson = requestNode.get(JSONField.REQUIRED_VALUES.getLabel()).toString();

		List<String> fields = null;
		List<String> requiredValues = null;

		if(fieldsStringFromJson != null && valuesStringFromJson != null)
		{
			fields = mapper.readValue(fieldsStringFromJson, mapper.getTypeFactory().constructCollectionType(List.class, String.class));
			requiredValues = mapper.readValue(valuesStringFromJson, mapper.getTypeFactory().constructCollectionType(List.class, String.class));		
		}	
		
		// Retrieves the DAO according to the entity we want
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		/* The dao.find() method will return a list of object that we serialize directly.
		 * We serialize the list directly in order to avoid to cast the object
		 */
		result = JsonUtil.serializeObject(dao.find(fields, requiredValues), entityClass, "");

		return result;		
	}

	/**
	 * Executes a client insert request
	 * 
	 * @param entityClass
	 * @param requestNode
	 * @param serializedObjectNode : the serialized object to Insert
	 * @return
	 * @throws UnknownClassException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String executeClientInsertRequest(Class<?> entityClass, JsonNode requestNode, JsonNode serializedObjectNode) throws UnknownClassException
	{
		Object deserializedObject = JsonUtil.deserializeObject(serializedObjectNode.toString());
		
		// Retrieves the DAO according to the entity we want
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		// The result is the object we sent plus its id set according to the database
		Object obj = dao.create(entityClass.cast(deserializedObject));
		String result = JsonUtil.serializeObject(entityClass.cast(obj), entityClass, "");
		return result;
	}

	/**
	 * Executes a client update request
	 * 
	 * @param entityClass
	 * @param requestNode
	 * @param serializedObjectNode : the serialized object to update
	 * @return
	 * @throws UnknownClassException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientUpdateRequest(Class<?> entityClass, JsonNode requestNode, JsonNode serializedObjectNode) throws UnknownClassException
	{
		Object deserializedObject = JsonUtil.deserializeObject(serializedObjectNode.toString());	
		
		// Retrieves the DAO according to the entity we want	
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		//We cast the object with the class type so that we do not have write a case for each entity
		dao.update(entityClass.cast(deserializedObject));
		String result = JsonUtil.serializeObject(null, entityClass, "");
		return result;
	}


	/**
	 * Executes a client delete request
	 * 
	 * @param entityClass
	 * @param requestNode
	 * @param serializedObjectNode : the serialized object to delete
	 * @return
	 * @throws UnknownClassException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientDeleteRequest(Class<?> entityClass, JsonNode requestNode, JsonNode serializedObjectNode) throws UnknownClassException
	{
		Object deserializedObject = JsonUtil.deserializeObject(serializedObjectNode.toString());

		// Retrieves the DAO according to the entity we want
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		dao.delete(entityClass.cast(deserializedObject));
		//We cast the object with the class type so that we do not have write a case for each entity
		String result = JsonUtil.serializeObject(null, entityClass, "");
		return result;
	}

	/**
	 * This method will give back the connection to the pool and close the socket
	 */
	private void exit()
	{
		try 
		{
			//Give back to connection to the pool
			DataSource.putConnection(connection);
			this.connection = null;
			socket.close();
			readFromClient.close();
			writeToClient.close();
		} 
		catch (IOException e) 
		{
			log.error("An error occured during the closure of a socket : " + e.getMessage());
		}
	}

}
