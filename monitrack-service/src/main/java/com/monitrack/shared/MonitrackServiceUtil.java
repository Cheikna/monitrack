package com.monitrack.shared;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.util.Util;

/**
 * 
 * Class which contains the shared attributes between all of the classes
 *
 */
public class MonitrackServiceFactory 
{	
	private static final Logger log = LoggerFactory.getLogger(MonitrackServiceFactory.class);
			
	private static final String APPLICATION_VERSION = Util.getPropertyValueFromPropertiesFile("version");

	public static String getASCII(String name)
	{
		String ascii = "";

		try 
		{
			InputStream inputStream = MonitrackServiceFactory.class.getClassLoader().getResourceAsStream("ascii/" + name);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			
			//Only the first line contains names
			String line = null;
			while((line = bufferedReader.readLine()) != null)
			{
				ascii += line + "\n";				
			}
			
			inputStream.close();
		} 
		catch (Exception e) 
		{
			log.error("Error when getting the ASCII of '" + name + "'");
		}
		return ascii;
	}

	/**
	 * @return the applicationVersion
	 */
	public static String getApplicationVersion() {
		return APPLICATION_VERSION;
	}
	
	


}
