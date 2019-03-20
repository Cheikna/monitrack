package com.monitrack.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitrack.entity.Person;
import com.monitrack.enumeration.JSONField;
import com.monitrack.enumeration.RequestType;

public class UtilTest {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void checkDatabaseInformation()
	{
		assertEquals(Util.getPropertyValueFromPropertiesFile("database"), "monitrack");
		assertEquals(Util.getPropertyValueFromPropertiesFile("driver"), "mysql");
	}
	
	@Test
	public void capitalize()
	{
		String strCapitalized = Util.capitalize("                hHVDhkdHJGDfhksd               ");
		assertEquals(strCapitalized, "Hhvdhkdhjgdfhksd");
	}
	
	@Test
	public void serializeRequest()
	{
		
		try {
			Person p = new Person("Request");
			String objectSerialized = Util.serializeObject(p, p.getClass(), "");
			List<String> fields = new ArrayList<>();
			fields.add("id");
			fields.add("name");	
			List<String> values = new ArrayList<>();
			values.add("1");
			values.add("Cheikna");			
			String jsonRequest = Util.serializeRequest(RequestType.SELECT, p.getClass(), objectSerialized, fields, values);
			System.out.println("JSON Indent 1 \n" + Util.indentJsonOutput(jsonRequest));
			// Converts the String into a JSON Node
			JsonNode jsonNode = mapper.readTree(jsonRequest);
			//Person per = (Person) Util.deserializeObject(jsonRequest);
			assertEquals("SELECT", jsonNode.get(JSONField.REQUEST_INFO.getLabel()).get(JSONField.REQUEST_TYPE.getLabel()).textValue());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Test
	public void testRequestTypeNode() throws IOException
	{
		String jsonFormattedRequest = "{\"request_info\":{\"request_type\":\"SELECT\",\"requested_entity\":\"PERSON\"},\"serialized_object\":null}";
		
		JsonNode json = mapper.readTree(jsonFormattedRequest);
		// JSON Node containing the request info
		JsonNode requestNode = json.get(JSONField.REQUEST_INFO.getLabel());	
		
		
		RequestType requestType = RequestType.getRequestType(requestNode.get(JSONField.REQUEST_TYPE.getLabel()).textValue());
		assertEquals(requestType, RequestType.SELECT);		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testListOfEntitiesDeserialization()
	{
		Person p1 = new Person("climg");
		Person p2 = new Person("monitrack");
		Person p3 = new Person("smartHomeService");
		
		List<Person> list1 = new ArrayList<Person>();
		list1.add(p1);
		list1.add(p2);
		list1.add(p3);
		
		List<Person> list2 = new ArrayList<Person>();
		list2.add(p1);
		list2.add(p2);
		list2.add(p3);
		
		String list1JSON = Util.serializeObject(list1, Person.class, "");
		String list2JSON = Util.serializeObject(list2, Person.class, "");
		
		log.info("List 1 in Json :\n" + Util.indentJsonOutput(list1JSON));
		log.info("List 2 in Json :\n" + Util.indentJsonOutput(list2JSON));
		
		List<Person> persons1 = (List<Person>) Util.deserializeObject(list1JSON);
		List<Person> persons2 = (List<Person>) Util.deserializeObject(list2JSON);
		
		assertEquals(true, equalsList(persons1, persons2));

	}

	@Test
	public void testEntityDeserialization()
	{
		log.info("entity deserialization :");
		Person p = new Person("climg");
		String json = Util.serializeObject(p, p.getClass(), "");
		Person p2 = (Person)Util.deserializeObject(json);
		log.info("Json string : " + json);
		assertEquals(p, p2);
	}

	@Test
	public void testEntityDeserializationFromRequest() throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();

		Consumer<JsonNode> data = (JsonNode node) -> System.out.println("node => " + node.asText());
		Person p =new Person("climg");
		String objectSerialized = Util.serializeObject(p, p.getClass(), null);
		String jsonRequest = Util.serializeRequest(RequestType.SELECT, p.getClass(), objectSerialized, null, null);
		System.out.println("JSON Indented request : " + Util.indentJsonOutput(jsonRequest));
		
		JsonNode rootNode = mapper.readTree(jsonRequest);
		JsonNode objectNode = rootNode.get(JSONField.SERIALIZED_OBJECT.getLabel()).get(JSONField.DATAS.getLabel());
		System.out.println("objectNode : " + objectNode.toString());
		Person pers = (Person) Util.deserializeObject(rootNode.get(JSONField.SERIALIZED_OBJECT.getLabel()).toString());
		System.out.println("Person : " + pers.toString());
		
	}
	
	
	public <T> boolean equalsList(List<T> list1, List<T> list2)
	{
		if(list1 == null && list2 == null)
			return true;
		
		if(list1 == null || list2 == null)
			return false;
		
		int list1Size = list1.size();
		int list2Size = list2.size();
		
		if(list1Size != list2Size)
			return false;
		
		for(int i = 0; i < list1Size; i++)
		{
			T t1 = list1.get(i);
			T t2 = list2.get(i);
			if(!t1.equals(t2))
			{
				log.error("Here are the first two differences:\n" + t1.toString() + "\n" + t2.toString());
				return false;
			}
		}		
		
		return true;
	}
	
	@Test
	public void testObjectSerialization()
	{
		Person p = new Person("climg");
		String str = Util.serializeObject(p, p.getClass(), "");
		System.out.println(Util.indentJsonOutput(str));
	}
	
	@Test
	public void testgetJsonNodeValue()
	{
		String message = "my own message";
		String json = Util.serializeObject(null	, null, message);
		System.err.println(json);
		assertEquals(message, Util.getJsonNodeValue(JSONField.ERROR_MESSAGE, json));
	}

}
