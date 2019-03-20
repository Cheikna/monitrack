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
				openingPage.getConnectionStateLabel().setText(ConnectionState.TRY.getFrenchLabel());
				//JOptionPane.showMessageDialog(null, ConnectionState.TRY.getFrenchLabel(), ConnectionState.TRY.getFrenchLabel(),JOptionPane.INFORMATION_MESSAGE);
				ConnectionState state = MonitrackGUIFactory.initializeSocket();
				openingPage.getConnectionStateLabel().setText("");
				switch(state)
				{					
					case FAIL:
						JOptionPane.showMessageDialog(null, ConnectionState.FAIL.getFrenchLabel(), ConnectionState.FAIL.getFrenchLabel(),JOptionPane.ERROR_MESSAGE);
						break;	
						
					case NO_CONNECTION:
						JOptionPane.showMessageDialog(null, ConnectionState.NO_CONNECTION.getFrenchLabel(), ConnectionState.NO_CONNECTION.getFrenchLabel(),JOptionPane.ERROR_MESSAGE);
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
