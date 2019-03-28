package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Location;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.RequestType;
import com.monitrack.gui.panel.LocationsTab;
import com.monitrack.shared.MonitrackGUIFactory;
import com.monitrack.util.JsonUtil;

public class LocationsTabListener implements ActionListener {

	private static final Logger log = LoggerFactory.getLogger(LocationsTabListener.class);

	private LocationsTab locationsTab;
	private List<Location> locations;
	private List<String> fields;
	private List<String> values;
	private String personText = "";	

	public LocationsTabListener(LocationsTab locationsTab)
	{
		this.locationsTab = locationsTab;
		locations = new ArrayList<>();
		fields = new ArrayList<String>();
		values = new ArrayList<String>();	
	}

	@SuppressWarnings("rawtypes")
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof JComboBox)
		{
			JComboBox combo = (JComboBox)e.getSource();
			if(combo == locationsTab.getActionsCombobox())
			{
				locationsTab.getNorthPanel().removeAll();
				locationsTab.getNorthPanel().add(locationsTab.getNorthPanelActionsChoice());
				if(combo.getSelectedItem().equals("Ajouter"))
				{
					locationsTab.getNorthPanel().add(locationsTab.getNorthPanelForCreate());			
				}
				else if(combo.getSelectedItem().equals("Modifier"))
				{	
					setComboboxWithLocations(locationsTab.getModifyLocationsCombobox());
					locationsTab.getNorthPanel().add(locationsTab.getNorthPanelForModify());				
				}
				else if(combo.getSelectedItem().equals("Supprimer"))
				{
					setComboboxWithLocations(locationsTab.getDeleteLocationsCombobox());
					locationsTab.getNorthPanel().add(locationsTab.getNorthPanelForDelete());		
				}
				else if(combo.getSelectedItem().equals("Visualiser"))
				{
					locationsTab.getNorthPanel().add(locationsTab.getNorthPanelForShow());
				}
			}
		}
		else if(e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton)e.getSource();
			if(clickedButton == locationsTab.getCreateButton())
			{
				int choice = JOptionPane.showConfirmDialog(locationsTab, locationsTab.getCreateLocationPopupPanel(), 
						"Créer un emplacement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, Images.MODIFY_ICON.getIcon());
				
				//If the user clicks on OK Button
				if(choice == 0)
				{
					//FIXME 1
					// Vérifier que tous les champs ne sont pas vides 
					//Si tous les champs ne sont pas vides alors créer un objet de type Location avec les données des champs de texte
					//Serialiser l'objet
					//Faire une requete JSON
					//Envoyer cette requête JSON via une socket
					//Recevoir la réponse
				}
			}
			else if(clickedButton == locationsTab.getDeleteButton())
			{
				//FIXME 2
				// Récupérer l'index de l'élément sélectionné dans le combobox
				// Vérifier que l'index est compris entre 0 et la taille de la liste
				// Grâce à l'index, récupérer l'élement dans la liste "persons" déclarée en attribut
				//Serialiser l'objet
				//Faire une requete JSON de suppression
				//Envoyer cette requête JSON via une socket
			}
			else if(clickedButton == locationsTab.getModifyButton())
			{
				int choice = JOptionPane.showConfirmDialog(locationsTab, locationsTab.getModifyLocationPopupPanel(), 
						"Modifier un emplacement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, Images.MODIFY_ICON.getIcon());
				
				//FIXME 3 préremplir les champs pour avoir une prévisualisation
				
				if(choice == 0)
				{
					//FIXME 3
					// Récupérer l'index de l'élément sélectionné dans le combobox
					// Vérifier que l'index est compris entre 0 et la taille de la liste
					// Grâce à l'index, récupérer l'élement dans la liste "persons" déclarée en attribut
					//Serialiser l'objet
					//Faire une requete JSON de update
					//Envoyer cette requête JSON via une socket
				}
				
				
			}
			else if(clickedButton == locationsTab.getShowButton())
			{
				//FIXME 4
				// Récupérer les valeurs dans les filtres
				// Faire une requête JSON pour les données correspondants à ces filtres
				// Afficher les résultats sur la JTextArea
			}
		}
		//Updates the Panel
		locationsTab.revalidate();
		locationsTab.repaint();
	}

	@SuppressWarnings("unchecked")
	private void setComboboxWithLocations(JComboBox<String> combobox)
	{
		String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Location.class, null, null, null);
		try 
		{
			String response = MonitrackGUIFactory.sendRequest(jsonRequest);
			locations = (List<Location>)JsonUtil.deserializeObject(response);
			combobox.removeAllItems();
			for(Location location : locations)
			{
				combobox.addItem(location.toString());
			}
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage());
		}
	}

	/*@SuppressWarnings("unchecked")
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
	}*/

}
