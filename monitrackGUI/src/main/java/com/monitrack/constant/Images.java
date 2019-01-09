package com.monitrack.constant;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public enum Images {

	PROJECT_LOGO("monitrack_logo.png"),
	
	GROUP_LOGO("climg_logo.png");

	/***** Attributs de l'�num�ration *****/
	private URL url;

	/**
	 * La cha�ne de caract�res pass�e en param�tre dans ce constructeur de cette enum va �tre utiliser 
	 * afin de construire l'URL de l'image, son chemin.
	 * 
	 * @param imageName
	 * 			nom de l'image (se trouvant dans le dossier src/main/resources/images)
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
