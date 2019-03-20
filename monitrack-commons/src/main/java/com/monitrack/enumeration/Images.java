package com.monitrack.enumeration;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public enum Images {

	/**** The different pictures (located in the folder src/main/resources/images)  ****/
	PROJECT_LOGO("monitrack_logo.png"),
	
	GROUP_LOGO("climg_logo.png");

	/***** attribut of enums *****/
	private URL url;

	/**
	 * The chain of character past in parameters in this constructor is used for build 
	 * URL and path of the picture.
	 * 
	 * @param imageName
	 * 			name of the picture with its extension
	 */
	Images(String imageName)
	{
		url = getClass().getClassLoader().getResource("images/" + imageName);
	}

	public ImageIcon getIcon()
	{
		return new ImageIcon(url);
	}

	public Image getImage()
	{
		return getIcon().getImage();
	}

}
