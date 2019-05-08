package com.monitrack.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ConfigurationPage extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public ConfigurationPage()
	{
		super(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Configuration des capteurs", new ConfigurationTab());
		add(tabbedPane, BorderLayout.CENTER);
	}
	
}
