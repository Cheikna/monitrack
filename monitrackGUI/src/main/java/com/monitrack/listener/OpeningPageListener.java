package com.monitrack.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.monitrack.gui.frame.MonitrackFrame;

public class OpeningPageListener implements KeyListener {

	private MonitrackFrame monitrackFrame;

	public OpeningPageListener(MonitrackFrame monitrackFrame) 
	{
		this.monitrackFrame = monitrackFrame;
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		
		//monitrackFrame.changePage("YELLOW" );
		//Updates the monitrackFrame
		monitrackFrame.setVisible(true);	
	}

	public void keyReleased(KeyEvent e) {}

}
