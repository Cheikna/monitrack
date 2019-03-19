package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Location;
import com.monitrack.enumeration.JSONField;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.gui.panel.HomePage;
import com.monitrack.shared.MonitrackGUIFactory;
import com.monitrack.util.Util;

public class HomePageListener implements ActionListener
{
	private static final Logger log = LoggerFactory.getLogger(HomePageListener.class);

	private HomePage homePage;

	public HomePageListener(HomePage homePage)
	{
		this.homePage = homePage;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== homePage.getJbValidate())
		{
			try
			{
				String name = homePage.getJtfName().getText().trim();
				if(name.length() <= 0)
				{
					JOptionPane.showMessageDialog(null, "Nom incorrect");
				}
				else
				{
					Location location = new Location(name, "");
					String serializedObject = Util.serializeObject(location, location.getClass(), "");
					String jsonRequest = Util.serializeRequest(RequestType.INSERT, Location.class, serializedObject, null, null);
					String response = MonitrackGUIFactory.getClientSocket().sendRequestToServer(jsonRequest);
					log.info("Response from the server :\n" + Util.indentJsonOutput(response));
					String error = Util.getJsonNodeValue(JSONField.ERROR_MESSAGE, response).trim();
					if(error.equals(""))
					{
						Location locationCreated = (Location)Util.deserializeObject(response);
						int id = locationCreated.getIdLocation();
						JOptionPane.showMessageDialog(homePage, "La location a �t� cr��e avec l'id " + id, "Location cr��e", JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						JOptionPane.showMessageDialog(homePage, error, "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}			
			}
			catch(Exception e1)
			{
				log.error(e1.getMessage());
			}
		}
		if(e.getSource()==homePage.getJbOverview())
		{
			String jsonRequest = Util.serializeRequest(RequestType.SELECT, Location.class, null, null, null);
			try 
			{
				String response = MonitrackGUIFactory.getClientSocket().sendRequestToServer(jsonRequest);
				log.info("Response from the server :\n" + Util.indentJsonOutput(response));
				String error = Util.getJsonNodeValue(JSONField.ERROR_MESSAGE, response).trim();
				if(error.equals(""))
				{
					List<Location> locations = (List<Location>) Util.deserializeObject(response);
					String locationsText = "";
					for(Location location : locations)
					{
						locationsText += location + "\n";
					}
					homePage.getjTArea().setText(locationsText);
				}
				else
				{
					JOptionPane.showMessageDialog(homePage, error, "Erreur", JOptionPane.ERROR_MESSAGE);
				}

			} 
			catch (IOException e1) 
			{
				log.error(e1.getMessage());
			} 
			catch (NoAvailableConnectionException e1) 
			{
				log.error(e1.getMessage());
			}
		}

		if(e.getSource() == homePage.getJbDelete())
		{
			String idInString = JOptionPane.showInputDialog(null, "Veuillez indiquez l'ID de la location � supprimer :"
					, "Suppression", JOptionPane.QUESTION_MESSAGE);

			try {				
				int id = Integer.parseInt(idInString);
				Location location = new Location(id, "", "", null, 0);
				String serializedObject = Util.serializeObject(location, Location.class, "");
				// FIXME personDAO.delete(id);
				String jsonRequest = Util.serializeRequest(RequestType.DELETE, Location.class, serializedObject, null, null);
				String response = MonitrackGUIFactory.getClientSocket().sendRequestToServer(jsonRequest);
				log.info("Response from the server :\n" + Util.indentJsonOutput(response));
				String error = Util.getJsonNodeValue(JSONField.ERROR_MESSAGE, response).trim();
				if(!error.equals(""))
				{
					JOptionPane.showMessageDialog(homePage, error, "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception exp)
			{
				log.error("The convertion into a Integer did not work");
				JOptionPane.showMessageDialog(null, "Vous n'avez pas entr� un bon entier", "Conversion Impossible", JOptionPane.WARNING_MESSAGE);
			}
		}

		if(e.getSource() == homePage.getJbUpdate())
		{
			String idInString = JOptionPane.showInputDialog(null, "Veuillez indiquer l'ID de la location � modifier :"
					, "Suppression", JOptionPane.QUESTION_MESSAGE);

			try {
				int id = Integer.parseInt(idInString);

				String newLocationName = JOptionPane.showInputDialog(null, "Veuillez entrer le nouveau nom de la location :"
						, "Suppression", JOptionPane.QUESTION_MESSAGE);

				Location locationToUpdate = new Location(id, newLocationName, "", null, 0);				
				String serializedObject = Util.serializeObject(locationToUpdate, locationToUpdate.getClass(), "");
				String jsonRequest = Util.serializeRequest(RequestType.UPDATE, Location.class, serializedObject, null, null);
				String response = MonitrackGUIFactory.getClientSocket().sendRequestToServer(jsonRequest);
				log.info("Response from the server :\n" + Util.indentJsonOutput(response));
				String error = Util.getJsonNodeValue(JSONField.ERROR_MESSAGE, response).trim();
				if(!error.equals(""))
				{
					JOptionPane.showMessageDialog(homePage, error, "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception exp)
			{
				log.error("The convertion into a Integer did not work");
				JOptionPane.showMessageDialog(null, "Vous n'avez pas entr� un bon entier", "Conversion Impossible", JOptionPane.WARNING_MESSAGE);
			}
		}
	}


}
