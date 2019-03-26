package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Person;
import com.monitrack.enumeration.JSONField;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.gui.panel.PersonsTab;
import com.monitrack.shared.MonitrackGUIFactory;
import com.monitrack.util.JsonUtil;
import com.monitrack.util.Util;

public class PersonsTabListener implements ActionListener {

	private static final Logger log = LoggerFactory.getLogger(PersonsTabListener.class);
	private PersonsTab personsTab;
	private List<Person> persons;
	private List<String> fields;
	private List<String> values;	
	private String personText = "";

	public PersonsTabListener(PersonsTab personsTab) {
		this.personsTab = personsTab;
		fields = new ArrayList<String>();
		values = new ArrayList<String>();	
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try
		{

			personsTab.getTextArea().setText("");
			//Case when the user chooses the CRUD Operation he wants to do
			if(e.getSource() instanceof JComboBox)
			{
				JComboBox combo = (JComboBox)e.getSource();
				personsTab.getNorthPanel().removeAll();
				personsTab.getNorthPanel().add(personsTab.getNorthPanelActionsChoice());
				if(combo.getSelectedItem().equals("Ajouter"))
				{
					personsTab.getNorthPanel().add(personsTab.getNorthPanelForCreate());			
				}
				else if(combo.getSelectedItem().equals("Modifier"))
				{	
					setComboboxWithPersons(personsTab.getModifyPersonsCombobox());
					personsTab.getNorthPanel().add(personsTab.getNorthPanelForModify());				
				}
				else if(combo.getSelectedItem().equals("Supprimer"))
				{
					setComboboxWithPersons(personsTab.getDeletePersonsCombobox());
					personsTab.getNorthPanel().add(personsTab.getNorthPanelForDelete());		
				}
				else if(combo.getSelectedItem().equals("Visualiser"))
				{
					personsTab.getNorthPanel().add(personsTab.getNorthPanelForShow());
				}
			}
			// Case when the user clicks on a confirm Button
			else if(e.getSource() instanceof JButton)
			{
				JButton clickedButton = (JButton)e.getSource();
				if(clickedButton == personsTab.getCreateButton())
				{
					//FIXME
				}
				else if(clickedButton == personsTab.getModifyButton())
				{
					//FIXME open a JOption Pane to modify the Person
				}
				else if(clickedButton == personsTab.getDeleteButton())
				{
					int comboboxIndex = personsTab.getDeletePersonsCombobox().getSelectedIndex();
					if(comboboxIndex >= 0 && comboboxIndex < persons.size())
					{
						Person personToDelete = persons.get(comboboxIndex);
						String serializedObject = JsonUtil.serializeObject(personToDelete, personToDelete.getClass(), "");
						String jsonRequest = JsonUtil.serializeRequest(RequestType.DELETE, Person.class, serializedObject, null, null);
						MonitrackGUIFactory.sendRequest(jsonRequest);
						setComboboxWithPersons(personsTab.getDeletePersonsCombobox());
					}
				}
				else if(clickedButton == personsTab.getShowButton())
				{					
					fields.clear();
					values.clear();
					boolean correctFilters = addFilters();

					if(correctFilters)
					{
						personText = "";
						String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Person.class, null, fields, values);
						System.out.println(JsonUtil.indentJsonOutput(jsonRequest));
						String response = MonitrackGUIFactory.sendRequest(jsonRequest);
						persons = (List<Person>)JsonUtil.deserializeObject(response);
						
						if(persons.size() > 0)
						{
							for(Person person : persons)
							{
								personText += person.toString() + "\n";
							}
						}
						else
						{
							JOptionPane.showMessageDialog(personsTab, "Aucune donnée ne correspond à vos filtres !", "Aucune donnée", JOptionPane.ERROR_MESSAGE);
						}
						
					}

					personsTab.getTextArea().setText(personText);
				}
			}

			//Updates the Panel
			personsTab.revalidate();
			personsTab.repaint();
		}
		catch(Exception e1)
		{
			log.error(e1.getMessage());
		}


	}

	private void setComboboxWithPersons(JComboBox<String> combobox)
	{
		String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Person.class, null, null, null);
		try 
		{
			String response = MonitrackGUIFactory.sendRequest(jsonRequest);
			persons = (List<Person>)JsonUtil.deserializeObject(response);
			combobox.removeAllItems();
			for(Person person : persons)
			{
				combobox.addItem(person.toString());
			}
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage());
		}
	}

	private boolean addFilters()
	{
		if(personsTab.getFilter1ForShowCombobox().getSelectedItem().equals("id"))
		{
			String value = personsTab.getFilter1TextField().getText().trim();
			if(value.length() > 0)
			{
				if(!NumberUtils.isParsable(value))
				{
					JOptionPane.showMessageDialog(personsTab, "Le champ du filtre ID ne doit contenir que des chiffres", "Filtre ID incorrect", JOptionPane.ERROR_MESSAGE);
					return false;
				}

				fields.add("id");
				values.add(value);
			}
		}
		if(personsTab.getFilter2ForShowCombobox().getSelectedItem().equals("nom"))
		{
			String value = personsTab.getFilter2TextField().getText().trim();
			if(value.length() > 0)
			{
				fields.add("name");
				values.add(value);
			}

		}
		return true;
	}

}
