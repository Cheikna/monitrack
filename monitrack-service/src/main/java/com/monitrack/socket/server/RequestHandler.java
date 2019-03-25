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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.dao.abstracts.DAO;
import com.monitrack.dao.implementation.DAOFactory;
import com.monitrack.enumeration.JSONField;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.UnknownClassException;
import com.monitrack.util.JsonUtil;

/**
 * This class will have all the interaction with the database
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
			readFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writeToClient = new PrintWriter(socket.getOutputStream(), true);
			
			String requestOfClient = readFromClient.readLine();
			log.info("Request received from the client :\n" + JsonUtil.indentJsonOutput(requestOfClient) + "\n");
			String responseToClient = executeClientRequest(requestOfClient);
			log.info("Response to the client :\n" + JsonUtil.indentJsonOutput(responseToClient) + "\n");
			writeToClient.println(responseToClient);

		}
		catch (Exception e) 
		{
			log.error("Exception : The client's buffer is not reachable. He is diconnected");
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientSelectRequest(Class<?> entityClass, JsonNode requestNode) throws Exception
	{

		mapper = new ObjectMapper();
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
		result = JsonUtil.serializeObject(dao.find(fields, requiredValues), entityClass, "");

		return result;		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String executeClientInsertRequest(Class<?> entityClass, JsonNode requestNode, JsonNode serializedObjectNode) throws UnknownClassException
	{
		Object deserializedObject = JsonUtil.deserializeObject(serializedObjectNode.toString());
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		Object obj = dao.create(entityClass.cast(deserializedObject));
		String result = JsonUtil.serializeObject(entityClass.cast(obj), entityClass, "");
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientUpdateRequest(Class<?> entityClass, JsonNode requestNode, JsonNode dataNode) throws UnknownClassException
	{
		Object deserializedObject = JsonUtil.deserializeObject(dataNode.toString());		
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		dao.update(entityClass.cast(deserializedObject));
		String result = JsonUtil.serializeObject(null, entityClass, "");
		return result;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String executeClientDeleteRequest(Class<?> entityClass, JsonNode requestNode, JsonNode dataNode) throws UnknownClassException
	{
		Object deserializedObject = JsonUtil.deserializeObject(dataNode.toString());
		DAO dao = DAOFactory.getDAO(connection, entityClass);
		dao.delete(entityClass.cast(deserializedObject));
		String result = JsonUtil.serializeObject(null, entityClass, "");
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
		} 
		catch (IOException e) 
		{
			log.error("An error occured during the closure of a socket : " + e.getMessage());
		}
	}

}
