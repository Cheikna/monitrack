package com.monitrack.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Properties;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Util {	

	private static final String ENTITY_PACKAGE_NAME = "com.monitrack.entity";
	private static Logger log = LoggerFactory.getLogger(Util.class);
	
	/**
	 * retourne la valeur de la propriété passée en paramètre
	 * 
	 * @param propertyName :
	 * 		nom de la propriété dans le fichier .properties
	 * @return
	 * 		valeur de la propriété du paramètre
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
			// TODO faire en sorte que ceci soit également afficher dans un fichier log
			e.printStackTrace();
		}
		return propertyValue;
	}	
	
	/**
	 * Cette méthode permettra de mettre la première lettre en majuscule et les autres en minuscule
	 * 
	 * @param stringToCapitalize
	 * @return
	 * 		le mot passé en paramètre avec sa première lettre en majuscule et les autres en minuscule
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
	 * Processus de serialisation : conversion d'un Object JAVA en String JSON
	 * @param object :
	 * 			object à convertir en JSON
	 * @return
	 * 			un string qui est l'objet en paramètre au format JSON
	 * @throws InvalidAttributeValueException
	 */
	public static String serialize(Object object) throws InvalidAttributeValueException
	{

		if(object != null)
		{
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();		
			String objectToJSON = null;
			String className = "";		

			try {
				/* On vérifie si l'object en paramètre est une liste est si c'est le cas, on récupère
				 * le type d'une instance. Sinon, l'entity aura pour valeur ArrayList par exemple			 * 
				 */
				if(object instanceof List)
				{
					List<Object> objects = (List)object;					

					node.put("number_of_entities", objects.size());
					/*
					 * Dans le cas d'une liste, on peut avoir plusieurs valeurs et c'est pourquoi
					 * la valeur associée à 'is_list_of_entities' est à 'true'
					 */
					node.put("is_list_of_entities", true);

					if(objects.size() > 0)
					{
						Object oneInstance = objects.get(0);
						className = oneInstance.getClass().getSimpleName();
					}
				}
				else
				{
					node.put("number_of_entities", 1);
					node.put("is_list_of_entities", false);
					className = object.getClass().getSimpleName();
				}
				//Ajout du nom de la classe dans le JSON afin de faire une seule méthode de deserialization qui vérifiera la classe associé à l'objet dans le JSON
				node.put("entity", className);
				node.putPOJO("datas", object);
				objectToJSON = mapper.writeValueAsString(node);
				log.info("Serialization into JSON succedeed : " + objectToJSON);
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
	 * processus de deserialization : conversion d'un String JSON en Object JAVA
	 * @param objectInJSONString :
	 * 			chaine de caractère au format JSON representant l'objet à convertir
	 * @return
	 * 			objet issue de la transformation de la chaine de caractère en JSON passé en paramètre
	 */
	public static Object deserialize(String objectInJSONString) {

		Object jsonConvertedToObject = null;
	    ObjectMapper mapper = new ObjectMapper();
	    
		try {
			// Conversion du string en un noeud JSON
		    JsonNode objectInNode = mapper.readTree(objectInJSONString);
		    // Noeud JSON contenant que le nom de l'entite correspondante
		    JsonNode entityNode = objectInNode.get("entity");
		    //Noeud JSON contenant les donnees de l'entite
		    JsonNode datasNode = objectInNode.get("datas");
		    // Noeud permettant de savoir si on a une liste d'entity (d'instance) ou seulement une seule entity
		    JsonNode quantityNode = objectInNode.get("is_list_of_entities");
		    //Noeud JSON contenant le nombre d'entity
		    JsonNode numberOfEntitiesNode = objectInNode.get("number_of_entities");
		    
		    // Recuperation du nom de l'entity qui est mis en capital afin de respecter les normes de nommages
			String className = Util.capitalize(entityNode.textValue());
			boolean isListOfEntities = quantityNode.booleanValue();
			int numberOfEntities = numberOfEntitiesNode.intValue();

			Class objectClass = Class.forName(ENTITY_PACKAGE_NAME + "." + className);
			
			if(isListOfEntities)
			{
				jsonConvertedToObject = mapper.readValue(datasNode.toString(), Array.newInstance(objectClass, numberOfEntities).getClass());
			}
			else
			{
				jsonConvertedToObject = mapper.readValue(datasNode.toString(), objectClass);
			}
			
			log.info("Deserialization into Java Object succedeed");
			
		} catch (Exception e) {
			log.error("Deserialization into Java Object failed : " + e.getStackTrace());
			e.printStackTrace();
		} 
		
		return jsonConvertedToObject;		
	}

}
