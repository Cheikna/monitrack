package com.monitrack.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.monitrack.enumeration.Images;

public class HomePage extends JPanel
{
	private static final long serialVersionUID = 1L;

	public HomePage()
	{
		super(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Personnes", new PersonsTab());
		tabbedPane.addTab("Emplacements", Images.LOCATION_ICON.getIcon(), new LocationsTab());
		add(tabbedPane, BorderLayout.CENTER);
	}
	
	
	
	

}
