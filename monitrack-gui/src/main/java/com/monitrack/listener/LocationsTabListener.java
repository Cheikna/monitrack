package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Location;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.gui.panel.LocationsTab;
import com.monitrack.shared.MonitrackGUIFactory;
import com.monitrack.util.JsonUtil;

public class LocationsTabListener implements ActionListener {

	private static final Logger log = LoggerFactory.getLogger(LocationsTabListener.class);

	private LocationsTab locationsTab;

	public LocationsTabListener(LocationsTab locationsTab)
	{
		this.locationsTab = locationsTab;
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== locationsTab.getJbValidate())
		{
			try
			{
				String name = locationsTab.getJtfName().getText().trim();
				if(name.length() <= 0)
				{
					JOptionPane.showMessageDialog(locationsTab, "Nom incorrect");
				}
				else
				{	
					Location location = new Location(name, "");
					String serializedObject = JsonUtil.serializeObject(location, location.getClass(), "");
					String jsonRequest = JsonUtil.serializeRequest(RequestType.INSERT, Location.class, serializedObject, null, null);
					
					
					String response = MonitrackGUIFactory.sendRequest(jsonRequest);
					Location locationCreated = (Location)JsonUtil.deserializeObject(response);
					int id = locationCreated.getIdLocation();
					JOptionPane.showMessageDialog(locationsTab, "La location a été créée avec l'id " + id, "Location créée", JOptionPane.INFORMATION_MESSAGE);
					
				}			
			}
			catch(Exception e1)
			{
				log.error(e1.getMessage());
			}
		}
		if(e.getSource()==locationsTab.getJbOverview())
		{
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Location.class, null, null, null);
			try 
			{		
				String response = MonitrackGUIFactory.sendRequest(jsonRequest);
				List<Location> locations = (List<Location>) JsonUtil.deserializeObject(response);
				String locationsText = "";
				for(Location location : locations)
				{
					locationsText += location + "\n";
				}
				locationsTab.getjTArea().setText(locationsText);

			} 
			catch (IOException | NoAvailableConnectionException e1) 
			{
				log.error(e1.getMessage());
			}
		}

		if(e.getSource() == locationsTab.getJbDelete())
		{
			String idInString = JOptionPane.showInputDialog(null, "Veuillez indiquez l'ID de la location à supprimer :"
					, "Suppression", JOptionPane.QUESTION_MESSAGE);

			try {				
				int id = Integer.parseInt(idInString);
				Location location = new Location(id, "", "", null, 0);
				String serializedObject = JsonUtil.serializeObject(location, Location.class, "");
				String jsonRequest = JsonUtil.serializeRequest(RequestType.DELETE, Location.class, serializedObject, null, null);
				String response = MonitrackGUIFactory.sendRequest(jsonRequest);
			}
			catch(Exception exp)
			{
				log.error("The convertion into a Integer did not work");
				JOptionPane.showMessageDialog(null, "Vous n'avez pas entré un bon entier", "Conversion Impossible", JOptionPane.WARNING_MESSAGE);
			}
		}

		if(e.getSource() == locationsTab.getJbUpdate())
		{
			String idInString = JOptionPane.showInputDialog(null, "Veuillez indiquer l'ID de la location à modifier :"
					, "Suppression", JOptionPane.QUESTION_MESSAGE);

			try {
				int id = Integer.parseInt(idInString);

				String newLocationName = JOptionPane.showInputDialog(null, "Veuillez entrer le nouveau nom de la location :"
						, "Suppression", JOptionPane.QUESTION_MESSAGE);

				Location locationToUpdate = new Location(id, newLocationName, "", null, 0);				
				String serializedObject = JsonUtil.serializeObject(locationToUpdate, locationToUpdate.getClass(), "");
				String jsonRequest = JsonUtil.serializeRequest(RequestType.UPDATE, Location.class, serializedObject, null, null);
				String response = MonitrackGUIFactory.sendRequest(jsonRequest);
			}
			catch(Exception exp)
			{
				log.error("The convertion into a Integer did not work");
				JOptionPane.showMessageDialog(null, "Vous n'avez pas entré un bon entier", "Conversion Impossible", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
