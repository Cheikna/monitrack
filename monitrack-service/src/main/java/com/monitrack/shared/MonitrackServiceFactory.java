package com.monitrack.shared;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

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
		URL url = MonitrackServiceFactory.class.getClassLoader().getResource("ascii/" + name);
		try 
		{
			FileReader fileReader = new FileReader(url.getFile());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//Only the first line contains names
			String line = null;
			while((line = bufferedReader.readLine()) != null)
			{
				ascii += line + "\n";
			}
			
			fileReader.close();
			bufferedReader.close();
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
