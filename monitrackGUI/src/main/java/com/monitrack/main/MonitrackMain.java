package com.monitrack.main;

import javax.swing.SwingUtilities;

import com.monitrack.connectionpool.implementation.DataSource;
import com.monitrack.gui.frame.MonitrackFrame;

public class MonitrackMain {

	public static void main(String[] args) {
		
		/*
		 * This thread will create the connection pool 
		 * while the next SwingUtilities thread will display the Graphical User Interface to the user
		 */
		Thread connectionPoolThread  = new  Thread(new Runnable() {
			
			public void run() {	
				DataSource.startConectionPool();
			}			
		});		
		connectionPoolThread.start();
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				MonitrackFrame monitrack = new MonitrackFrame();				
			}
		});
	}

}
