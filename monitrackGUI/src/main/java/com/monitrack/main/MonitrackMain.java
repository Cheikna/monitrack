package com.monitrack.main;

import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.monitrack.connectionpool.implementation.DataSource;
import com.monitrack.gui.frame.MonitrackFrame;

public class MonitrackMain {

	private static Logger log = LoggerFactory.getLogger(MonitrackMain.class);

	public static void main(String[] args) {
		
		/*
		 * This thread will create the connection pool 
		 * while the next SwingUtilities thread will display the Graphical User Interface to the user
		 */
		Thread connectionPoolThread  = new  Thread(new Runnable() {
			
			public void run() {	
				DataSource.startConnectionPool();
			}			
		});		
		connectionPoolThread.start();		
		/*
		 * The swing objects are not thread-safe which means that some errors can happens when we are accessing to the same data
		 * In order to prevent this from happening, we have to use a special Thread called 'SwingUtilities.invokeLater'
		 */
		SwingUtilities.invokeLater(new Runnable() {
			
			@SuppressWarnings("unused")
			public void run() {
				log.info("Launching of Monitrack");
				MonitrackFrame monitrack = new MonitrackFrame();			
			}
		});

	}
	
}