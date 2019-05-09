package com.monitrack.util;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {	

	private static final Logger log = LoggerFactory.getLogger(Util.class);

	/**
	 * returns the value of the property which is given in the parameter
	 * 
	 * @param propertyName :
	 * 		name of the property in the application.properties file
	 * @return
	 * 		value of the property
	 */
	private static String getPropertyValueFromPropertiesFile(String propertyName, String fileName)
	{
		String propertyValue = null;
		Properties properties = new Properties();
		try 
		{
			InputStream propertiesFile = Util.class.getClassLoader().getResourceAsStream(fileName);
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
	
	public static String getPropertyValueFromPropertiesFile(String propertyName) {
		return getPropertyValueFromPropertiesFile(propertyName, "application.properties");
	}
	
	public static String getSensorsPropertyValueFromPropertiesFile(String propertyName) {
		return getPropertyValueFromPropertiesFile(propertyName, "sensors.properties");
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
	
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static <T> void displayListElements(List<T> elements, String newLineIndicator)
	{
		if(elements != null && elements.size() > 0)
		{
			for(T element : elements)
			{
				System.out.println(newLineIndicator + element);
			}
		}
		else
		{
			log.error("The list is empty !");
		}
	}
	
	public static String getVersionSpliter() {
		return getPropertyValueFromPropertiesFile("version_split");
	}
	
}
