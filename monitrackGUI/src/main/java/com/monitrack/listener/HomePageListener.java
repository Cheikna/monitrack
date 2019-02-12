package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import com.monitrack.dao.implementation.PersonDAO;
import com.monitrack.entity.Person;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.gui.panel.HomePage;

public class HomePageListener implements ActionListener
{
	private HomePage homePage;
	// DAO for request
	private PersonDAO personDAO = new PersonDAO();
	public HomePageListener(HomePage homePage)
	{
		this.homePage = homePage;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== homePage.getJbValidate())
		{
			String name = homePage.getJtfName().getText().trim();
			if(name.length() <= 0)
			{
				JOptionPane.showMessageDialog(null, "Nom incorrect");
			}
			else
			{
				Date sqlCurrentDate = new Date(Calendar.getInstance().getTime().getTime());
				Person person = new Person(name);
				personDAO.create(person);
			}
		}
		if(e.getSource()==homePage.getJbOverview())
		{
			List<Person> persons = personDAO.findAll();
			String personsText = "";
			for(Person person : persons)
			{
				personsText += person + "\n";
			}
			homePage.getjTArea().setText(personsText);
		}
	}


}
