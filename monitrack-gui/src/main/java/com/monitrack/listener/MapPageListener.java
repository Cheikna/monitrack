package com.monitrack.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

public class MapPageListener extends MouseAdapter{
	JPopupMenu popup; 
	
	public MapPageListener(JPopupMenu popup_desktop) {
		// TODO Auto-generated constructor stub
		popup = popup_desktop;
	}

	

		public void mousePressed(MouseEvent e) { 
		maybeShowPopup(e); 
		} 

		public void mouseReleased(MouseEvent e) { 
		maybeShowPopup(e); 
		} 

		private void maybeShowPopup(MouseEvent e) { 
		if (e.isPopupTrigger()) { 
		popup.show(e.getComponent(), 
		e.getX(), e.getY()); 
		} 
		} 

}
