package com.monitrack.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.monitrack.enumeration.Images;
import com.monitrack.runnable.SensorsUpdater;

public class HomePage extends JPanel
{
	private static final long serialVersionUID = 1L;

	public HomePage()
	{
		super(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Personnes", new PersonsTab());
		tabbedPane.addTab("Emplacements surveillés", Images.LOCATION_ICON.getIcon(), new LocationsTab());
		tabbedPane.add("Carte", new MapPage());
		SensorsAlertsTab sensorsAlertsTab = new SensorsAlertsTab();
		tabbedPane.add("Etat des capteurs", sensorsAlertsTab);
		add(tabbedPane, BorderLayout.CENTER);
		
		

		Thread thread = new Thread(new SensorsUpdater(sensorsAlertsTab));
		thread.start();
		tabbedPane.setSelectedIndex(3);
	}

}
