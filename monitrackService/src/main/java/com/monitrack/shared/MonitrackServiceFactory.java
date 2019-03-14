package com.monitrack.shared;

/**
 * 
 * Class which contains the shared attributes between all of the classes
 *
 */
public class MonitrackServiceFactory {
	
	private static Integer numberOfConnectedClients = 0;

	/**
	 * @return the numberOfConnectedClient
	 */
	public static synchronized Integer getNumberOfConnectedClients() {
		return numberOfConnectedClients;
	}

	/**
	 * @param numberOfConnectedClients the numberOfConnectedClients to add or to remove
	 */
	public static synchronized void setNumberOfConnectedClients(Integer numberOfConnectedClients) {
		MonitrackServiceFactory.numberOfConnectedClients += numberOfConnectedClients;
	}
	
	

}
