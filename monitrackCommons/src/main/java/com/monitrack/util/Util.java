package com.monitrack.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {	
	
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

}
