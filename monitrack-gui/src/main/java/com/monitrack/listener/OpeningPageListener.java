package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import com.monitrack.enumeration.ConnectionState;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.gui.panel.OpeningPage;
import com.monitrack.shared.MonitrackGUIFactory;

public class OpeningPageListener implements ActionListener {

	private MonitrackFrame monitrackFrame;
	private OpeningPage openingPage;

	public OpeningPageListener(MonitrackFrame monitrackFrame, OpeningPage openingPage) 
	{
		this.monitrackFrame = monitrackFrame;
		this.openingPage = openingPage;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton)e.getSource();

			if(clickedButton == openingPage.getAccessToServerButton())
			{				
				if(isClientNameValid())
				{
					openingPage.getConnectionStateLabel().setText("Connexion au serveur...");
					openingPage.revalidate();
					//JOptionPane.showMessageDialog(null, ConnectionState.TRY.getFrenchLabel(), ConnectionState.TRY.getFrenchLabel(),JOptionPane.INFORMATION_MESSAGE);
					ConnectionState state = MonitrackGUIFactory.initializeSocket();
					openingPage.getConnectionStateLabel().setText("");
					String closeMessage = "\nFermeture de l'application";
					switch(state)
					{					
					case FAIL:
						JOptionPane.showMessageDialog(null, ConnectionState.FAIL.getFrenchLabel() + closeMessage, ConnectionState.FAIL.getFrenchLabel(),JOptionPane.ERROR_MESSAGE);
						System.exit(0);
						break;	

					case NO_CONNECTION:
						JOptionPane.showMessageDialog(null, ConnectionState.NO_CONNECTION.getFrenchLabel() + closeMessage, ConnectionState.NO_CONNECTION.getFrenchLabel(),JOptionPane.ERROR_MESSAGE);
						System.exit(0);
						break;

					case SUCCESS:
						JOptionPane.showMessageDialog(null, ConnectionState.SUCCESS.getFrenchLabel(), ConnectionState.SUCCESS.getFrenchLabel(),JOptionPane.INFORMATION_MESSAGE);
						// Changes the pages in order to have the home page
						monitrackFrame.changePage(monitrackFrame.getHomePageName());
						//Updates the monitrackFrame
						monitrackFrame.setVisible(true);	
						break;
					}
				}
			}
		}

	}

	/**
	 * Shows a dialog to the client to enter his name so that the server can recognize him
	 * @return
	 * 	@true if the client enter a name
	 * 	@false if the client quits the window
	 */
	private boolean isClientNameValid()
	{
		String clientName = null;
		do {
			clientName = JOptionPane.showInputDialog(null, "Entrez votre nom (au moins 3 lettres) :", "Nom", JOptionPane.INFORMATION_MESSAGE);

			// The clientName equals to null in the loop when the user presses the cancel button or quits the window
			if (clientName == null)
				break;

		} while (clientName.trim().length() < 3);

		if (clientName != null) {
			MonitrackGUIFactory.setClientName(clientName);
			return true;
		} else {
			String errorMessage = "La connexion au serveur n'a pas pu se faire !\nVous n'avez pas entré votre nom.";
			JOptionPane.showMessageDialog(null, errorMessage, "Connexion refusée", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

}
