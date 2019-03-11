package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.entity.Person;
import com.monitrack.gui.panel.HomePage;

public class HomePageListener implements ActionListener
{
	private static final Logger log = LoggerFactory.getLogger(HomePageListener.class);
	
	private HomePage homePage;
	// DAO for requests
	// FIXME private PersonDAO personDAO = new PersonDAO();
	
	public HomePageListener(HomePage homePage)
	{
		this.homePage = homePage;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== homePage.getJbValidate())
		{
			String name = homePage.getJtfName().getText().trim();
			if(name.length() <= 0)
			{
				JOptionPane.showMessageDialog(null, "Nom incorrect");
			}
			else
			{
				Person person = new Person(name);
				// FIXME personDAO.create(person);
			}
		}
		if(e.getSource()==homePage.getJbOverview())
		{
			List<Person> persons = null; // FIXME = personDAO.findAll();
			String personsText = "";
			for(Person person : persons)
			{
				personsText += person + "\n";
			}
			homePage.getjTArea().setText(personsText);
		}

		if(e.getSource() == homePage.getJbDelete())
		{
			String idInString = JOptionPane.showInputDialog(null, "Veuillez indiquez l'ID de la personne à supprimer :"
					, "Suppression", JOptionPane.QUESTION_MESSAGE);
			
			try {
				int id = Integer.parseInt(idInString);
				// FIXME personDAO.delete(id);
			}
			catch(Exception exp)
			{
				log.error("The convertion into a Integer did not work");
				JOptionPane.showMessageDialog(null, "Vous n'avez pas entré un bon entier", "Conversion Impossible", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if(e.getSource() == homePage.getJbUpdate())
		{
			String idInString = JOptionPane.showInputDialog(null, "Veuillez indiquez l'ID de la personne à modifier :"
					, "Suppression", JOptionPane.QUESTION_MESSAGE);
			
			try {
				int id = Integer.parseInt(idInString);
				
				String newPersonName = JOptionPane.showInputDialog(null, "Veuillez entrer le nouveau nom de la personne :"
						, "Suppression", JOptionPane.QUESTION_MESSAGE);
				
				Person personToUpdate = new Person(id, newPersonName, null);				
				// FIXME personDAO.update(personToUpdate);
			}
			catch(Exception exp)
			{
				log.error("The convertion into a Integer did not work");
				JOptionPane.showMessageDialog(null, "Vous n'avez pas entré un bon entier", "Conversion Impossible", JOptionPane.WARNING_MESSAGE);
			}
		}
	}


}
