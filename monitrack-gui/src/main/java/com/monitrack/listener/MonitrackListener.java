package com.monitrack.listener;

import java.awt.event.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.event.WindowAdapter;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.shared.MonitrackGUIFactory;

public class MonitrackListener extends WindowAdapter {

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

}
