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
		/* En fonction des diff�rents boutons cliqu�s (qui seront accessible gr�ce aux getters/setters,
		 * il faut effectuer l'action appropri�e.
		 * Il suffit de copier-coller la m�thode actionPerformed de la classe MonitrackFrame et d'acc�der aux
		 * boutons gr�ces aux getters et setters
		 */
	}

}
