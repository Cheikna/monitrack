package com.monitrack.listener;

import java.awt.event.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.event.WindowAdapter;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.shared.MonitrackGUIAttribute;

public class MonitrackListener extends WindowAdapter {
	
	private static Logger log = LoggerFactory.getLogger(MonitrackListener.class);
			
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
		MonitrackGUIAttribute.setWindowClosing(true);
		log.info("The application is closed");
		System.exit(0);		
	}	

}
