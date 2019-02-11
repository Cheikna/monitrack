package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.monitrack.dao.implementation.PersonDAO;
import com.monitrack.entity.Person;
import com.monitrack.gui.frame.MonitrackFrame;

public class MonitrackListener implements ActionListener {
	
	private MonitrackFrame monitrackFrame;
	private PersonDAO personDAO;

	/**
	 * 
	 * @param monitrackFrame
	 */
	public MonitrackListener(MonitrackFrame monitrackFrame) {
		personDAO = new PersonDAO();
		//TODO
	}	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/* En fonction des différents boutons cliqués (qui seront accessible grâce aux getters/setters,
		 * il faut effectuer l'action appropriée.
		 * Il suffit de copier-coller la méthode actionPerformed de la classe MonitrackFrame et d'accéder aux
		 * boutons grâces aux getters et setters
		 */
	}

}
