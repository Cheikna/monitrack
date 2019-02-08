package com.monitrack.util;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Properties;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.monitrack.entity.Person;

public class Util {	

	private static Logger log = LoggerFactory.getLogger(Util.class);
	
	/**
	 * returns the value of the property which is given in the parameter
	 * 
	 * @param propertyName :
	 * 		name of the property in the application.properties file
	 * @return
	 * 		value of the property
	 */
	public static String getPropertyValueFromPropertiesFile(String propertyName)
	{
		String propertyValue = null;
		Properties properties = new Properties();
		try 
		{
			InputStream propertiesFile = Util.class.getClassLoader().getResourceAsStream("application.properties");
			properties.load(propertiesFile);
			propertyValue = properties.getProperty(propertyName);
			propertiesFile.close();
		} 
		catch (Exception e) 
		{
			log.error("Error when getting the property called " + propertyName, e);
		}
		return propertyValue;
	}	
	
	/**
	 * Capitalize a word by putting the first character into upper case and the remaining ones into lowercase
	 * 
	 * @param stringToCapitalize
	 * @return
	 * 		the attribute capitalized
	 */
	public static String capitalize(String stringToCapitalize)
	{
		String stringCapitalized = "";
		stringToCapitalize = stringToCapitalize.trim().toUpperCase();
		int stringLength = stringToCapitalize.length();
		
		if(stringLength > 0)
		{
			stringCapitalized += stringToCapitalize.charAt(0);
			
			if(stringLength > 1)
			{				
				stringCapitalized += stringToCapitalize.substring(1, stringLength).toLowerCase();
			}
		}
		
		return stringCapitalized;
	}
	
	/**
	 * Converts a JAVA Object into a JSON format String
	 * 
	 * @param object :
	 * 			object to convert into JSON
	 * @param objectClass :
	 * 			the class of the object to serialize
	 * @return
	 * 			the JSON String representing the JAVA Object
	 * @throws InvalidAttributeValueException
	 */
	public static String serializeObject(Object object, Class objectClass) throws InvalidAttributeValueException
	{

		if(object != null)
		{
			ObjectMapper mapper = new ObjectMapper();
			// Allows us to have an indented JSON on the output and not a single line
		    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			ObjectNode node = mapper.createObjectNode();		
			String objectToJSON = null;
			String className = objectClass.getName();

			try {

				/*
				 * The object can be a list when we are retrieving all of the datas of a table.
				 * This usually happens because of the 'findAll()' method
				 */
				if(object instanceof List)
				{
					node.put("is_list_of_entities", true);				
				}
				else
				{
					node.put("is_list_of_entities", false);
				}
				
				/*
				 * We add the entity name in order to make the deserialization easier
				 * because we will have one deserialization process for all of the entities
				 */
				node.put("entity", className);
				node.putPOJO("datas", object);
				objectToJSON = mapper.writeValueAsString(node);
				log.info("Serialization into JSON succedeed :\n" + objectToJSON);
			} catch (Exception e) {
				log.error("Serialization into JSON failed : " + e.getStackTrace());
			}			

			return objectToJSON;
		}
		else
		{
			throw new InvalidAttributeValueException("The object you want to serialize is null. Consequently, the serialization can not happen !");
		}
	}
	
	/**
	 * Converts a JSON String into a JAVA Object
	 * @param objectInJSONString :
	 * 			JSON String representing the object to convert
	 * @return
	 * 			JAVA Object converted from the JSON String
	 */
	public static Object deserializeObject(String objectInJSONString) {

		Object jsonConvertedToObject = null;
	    ObjectMapper mapper = new ObjectMapper();
	    
		try {
			// Converts the String into a JSON Node
		    JsonNode objectFromStringNode = mapper.readTree(objectInJSONString);
		    // JSON Node containing the name of the entity
		    JsonNode entityNode = objectFromStringNode.get("entity");
		    //JSON Node containing the datas of the entity
		    JsonNode datasNode = objectFromStringNode.get("datas");
		    // Node which allows us to know if we have a list of entities (because of the method 'findAll()') or only one
		    JsonNode isListNode = objectFromStringNode.get("is_list_of_entities");
		    
		    // Gets the name of the entity we want to deserialize
			String className = entityNode.textValue();
			
			boolean isListOfEntities = isListNode.booleanValue();

			if(className.equalsIgnoreCase(Person.class.getName()))
			{
				if(isListOfEntities)
				{
					// java.util.LinkedHashMap cannot be cast to com.monitrack.entity.Person
					jsonConvertedToObject = mapper.readValue(datasNode.toString(), new TypeReference<List<Person>>(){});
				}
				else
				{
					jsonConvertedToObject = mapper.readValue(datasNode.toString(), Person.class);
				}
			}
			else
			{
				throw new Exception("The class '" + className + "' does not exist in the project ! The deserialization cannot happen.");
			}			
			
			log.info("Deserialization into Java Object succedeed");
			
		} catch (Exception e) {
			log.error("Deserialization into Java Object failed : " + e.getMessage());
			e.printStackTrace();
		} 
		
		return jsonConvertedToObject;		
	}

}
