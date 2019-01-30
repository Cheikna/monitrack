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

}
