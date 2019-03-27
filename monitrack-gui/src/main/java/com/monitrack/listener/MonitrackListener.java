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

import com.monitrack.entity.Location;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.RequestType;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.shared.MonitrackGUIFactory;
import com.monitrack.util.JsonUtil;

public class MonitrackListener extends WindowAdapter implements ActionListener {

	private static final Logger log = LoggerFactory.getLogger(MonitrackListener.class);

	private MonitrackFrame monitrackFrame;	
	private boolean isInfiniteRequestActive = false;

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == monitrackFrame.getDeveloperModeButton())
		{
			JLabel label = new JLabel("Entrez le mot de passe :\n");
			JPasswordField passwordField = new JPasswordField(15);
			JPanel panel  = new JPanel();
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.add(label);
			panel.add(passwordField);
			
			int choice = JOptionPane.showConfirmDialog(monitrackFrame, panel, "Mode developpeur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, Images.DEVELOPER.getIcon());
			
			//If the user clicks on the ok button
			if(choice == 0)
			{
				String enteredPassword = String.valueOf(passwordField.getPassword());
				if(enteredPassword.equals("climg"))
				{
					//FIXME The password needs to be verify in order to enter in the super user mode, encode it
					monitrackFrame.setNorthPanel(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getSource() == monitrackFrame.getSuperUserModeButton())
		{
			monitrackFrame.getSuperUserModeDialog().setVisible(true);
		}
		else if(e.getSource() == monitrackFrame.getInfiniteRequestButton())
		{
			isInfiniteRequestActive = !isInfiniteRequestActive;
			startInfiniteRequest();
		}

	}

	public void startInfiniteRequest()
	{
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(isInfiniteRequestActive)
				{					
					try 
					{
						String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Location.class, null, null, null);
						String response = MonitrackGUIFactory.sendRequest(jsonRequest);
					}  
					catch (Exception e) 
					{
						log.error(e.getMessage());
					}
				}

			}
		});
		thread.start();
	}

}
