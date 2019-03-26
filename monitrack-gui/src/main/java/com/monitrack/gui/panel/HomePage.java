package com.monitrack.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class HomePage extends JPanel
{
	public HomePage()
	{
		super(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Personnes", new PersonsTab());
		tabbedPane.addTab("Emplacements", new LocationsTab());
		add(tabbedPane, BorderLayout.CENTER);
	}
	
	
	
	

}
