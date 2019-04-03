package com.monitrack.exception;

public class DeprecatedVersionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param minimumVersion - the version of the server
	 */
	public DeprecatedVersionException(String minimumVersion) 
	{
		super("The client application is a deprecated version. The version application must at least be " + minimumVersion + " !");
	}

}
