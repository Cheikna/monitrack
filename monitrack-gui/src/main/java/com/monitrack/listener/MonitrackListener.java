package com.monitrack.listener;

import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import com.monitrack.enumeration.ConnectionState;
import com.monitrack.enumeration.Images;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.shared.MonitrackGUIFactory;

public class MonitrackListener extends WindowAdapter implements ActionListener {

	private static final Logger log = LoggerFactory.getLogger(MonitrackListener.class);

	private MonitrackFrame monitrackFrame;	

	/**
	 * 
	 * @param monitrackFrame
	 */
	public MonitrackListener(MonitrackFrame monitrackFrame) {
		this.monitrackFrame = monitrackFrame;
	}	

	@Override
	public void windowClosing(WindowEvent e) {
		exit();
	}	

	private void exit()
	{
		log.info("The application is closed");
		try {
			MonitrackGUIFactory.getClientSocket().exit();
		} catch (Exception e) {
			log.error("An error occured during the closure of the socket :\n" + e.getMessage());
		}
		System.exit(0);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == monitrackFrame.getSuperUserModeButton())
		{
			if(MonitrackGUIFactory.getSocketState() == ConnectionState.SUCCESS)
			{
				JLabel label = new JLabel("Entrez le mot de passe :\n");
				JPasswordField passwordField = new JPasswordField(15);
				JPanel panel  = new JPanel();
				
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.add(label);
				panel.add(passwordField);
				
				int choice = JOptionPane.showConfirmDialog(monitrackFrame, panel, "Super Mode", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, Images.SUPER.getIcon());
				
				//If the user clicks on the ok button
				if(choice == 0)
				{
					String enteredPassword = String.valueOf(passwordField.getPassword());
					if(enteredPassword.equals("climg"))
					{
						//FIXME The password needs to be verify in order to enter in the super user mode, encode it
						monitrackFrame.getSuperUserModeDialog().setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(monitrackFrame, "Vous devez être connecté aux serveurs pour utiliser cette fonctionnalité", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
