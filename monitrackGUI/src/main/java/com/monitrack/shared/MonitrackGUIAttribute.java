package com.monitrack.shared;

public class MonitrackGUIAttribute {

	private static boolean isWindowClosing = false;

	/**
	 * @return the isWindowClosing
	 */
	public static boolean isWindowClosing() {
		return isWindowClosing;
	}

	/**
	 * @param isWindowClosing the isWindowClosing to set
	 */
	public static void setWindowClosing(boolean isWindowClosing) {
		MonitrackGUIAttribute.isWindowClosing = isWindowClosing;
	}
	
	

}
