package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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
			
			if(superUserModeDialog.getFillRadioButton().isSelected())
			{
				requestType = RequestType.INSERT;				
			}
			else if(superUserModeDialog.getEmptyRadioButton().isSelected())
			{
				requestType = RequestType.DELETE;		
				int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer les données de ["+ entityChoice + "]");
				if(choice == 0)
				{
					//FIXME JSON Request to send through socket
				}
			}

			System.out.println("entity : " + entityChoice);
			System.out.println("request : " + requestType);
		}
	}

}
