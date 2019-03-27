package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.monitrack.entity.Location;
import com.monitrack.entity.Person;
import com.monitrack.enumeration.RequestType;
import com.monitrack.gui.dialog.SuperUserModeDialog;

public class SuperUserModeDialogListener implements ActionListener {

	private SuperUserModeDialog superUserModeDialog;
	
	private RequestType requestType;
	
	public SuperUserModeDialogListener(SuperUserModeDialog superUserModeDialog) {
		this.superUserModeDialog = superUserModeDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == superUserModeDialog.getValidateChoicesButton())
		{
			String entityChoice = superUserModeDialog.getEntitiesCombobox().getSelectedItem().toString();
			Class<?> entityClass = null;
			
			if(entityChoice.equalsIgnoreCase("personnes"))
			{
				entityClass = Person.class;				
			}
			else if(entityChoice.equalsIgnoreCase("emplacements"))
			{
				entityClass = Location.class;				
			}
			
			
			if(superUserModeDialog.getFillRadioButton().isSelected())
			{
				requestType = RequestType.INSERT;	
				//FIXME
			}
			else if(superUserModeDialog.getEmptyRadioButton().isSelected())
			{
				requestType = RequestType.DELETE;		
				int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer les données de ["+ entityChoice + "]");
				if(choice == 0)
				{
					//FIXME
				}
			}

			System.out.println("entity : " + entityChoice);
			System.out.println("request : " + requestType);
		}
	}

}
