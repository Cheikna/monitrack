package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.entity.Person;
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
					Person person = new Person(name);
					String serializedObject = Util.serializeObject(person, person.getClass(), "");
					String jsonRequest = Util.serializeRequest(RequestType.INSERT, Person.class, serializedObject, null, null);
					String response = MonitrackGUIFactory.getClientSocket().sendRequestToServer(jsonRequest);
					log.info("Response from the server :\n" + Util.indentJsonOutput(response));
					String error = Util.getJsonNodeValue(JSONField.ERROR_MESSAGE, response).trim();
					if(error.equals(""))
					{
						Person personCreated = (Person)Util.deserializeObject(response);
						int id = personCreated.getIdPerson();
						JOptionPane.showMessageDialog(homePage, "La personne a été créée avec l'id " + id, "Personne créée", JOptionPane.INFORMATION_MESSAGE);

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
			String jsonRequest = Util.serializeRequest(RequestType.SELECT, Person.class, null, null, null);
			try 
			{
				String response = MonitrackGUIFactory.getClientSocket().sendRequestToServer(jsonRequest);
				log.info("Response from the server :\n" + Util.indentJsonOutput(response));
				String error = Util.getJsonNodeValue(JSONField.ERROR_MESSAGE, response).trim();
				if(error.equals(""))
				{
					List<Person> persons = (List<Person>) Util.deserializeObject(response);
					String personsText = "";
					for(Person person : persons)
					{
						personsText += person + "\n";
					}
					homePage.getjTArea().setText(personsText);
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
			String idInString = JOptionPane.showInputDialog(null, "Veuillez indiquez l'ID de la personne à supprimer :"
					, "Suppression", JOptionPane.QUESTION_MESSAGE);

			try {				
				int id = Integer.parseInt(idInString);
				Person person = new Person(id, "", null);
				String serializedObject = Util.serializeObject(person, person.getClass(), "");
				// FIXME personDAO.delete(id);
				String jsonRequest = Util.serializeRequest(RequestType.DELETE, Person.class, serializedObject, null, null);
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
				JOptionPane.showMessageDialog(null, "Vous n'avez pas entré un bon entier", "Conversion Impossible", JOptionPane.WARNING_MESSAGE);
			}
		}

		if(e.getSource() == homePage.getJbUpdate())
		{
			String idInString = JOptionPane.showInputDialog(null, "Veuillez indiquer l'ID de la personne à modifier :"
					, "Suppression", JOptionPane.QUESTION_MESSAGE);

			try {
				int id = Integer.parseInt(idInString);

				String newPersonName = JOptionPane.showInputDialog(null, "Veuillez entrer le nouveau nom de la personne :"
						, "Suppression", JOptionPane.QUESTION_MESSAGE);

				Person personToUpdate = new Person(id, newPersonName, null);				
				String serializedObject = Util.serializeObject(personToUpdate, personToUpdate.getClass(), "");
				String jsonRequest = Util.serializeRequest(RequestType.UPDATE, Person.class, serializedObject, null, null);
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
				JOptionPane.showMessageDialog(null, "Vous n'avez pas entré un bon entier", "Conversion Impossible", JOptionPane.WARNING_MESSAGE);
			}
		}
	}


}
