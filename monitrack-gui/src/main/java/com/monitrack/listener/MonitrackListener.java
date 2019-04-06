package com.monitrack.listener;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import com.monitrack.entity.Location;
import com.monitrack.entity.Person;
import com.monitrack.enumeration.ConnectionState;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.RequestType;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.shared.MonitrackGUIFactory;
import com.monitrack.util.JsonUtil;
import com.monitrack.util.Util;

public class MonitrackListener extends WindowAdapter implements ActionListener {

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
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == monitrackFrame.getDeveloperModeButton())
		{
			JLabel label = new JLabel("Entrez le mot de passe :\n");
			JPasswordField passwordField = new JPasswordField(15);
			JPanel panel  = new JPanel();
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.add(label);
			panel.add(passwordField);
			
			int choice = JOptionPane.showConfirmDialog(monitrackFrame, panel, "Mode développeur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, Images.DEVELOPER.getIcon());
			
			//If the user clicks on the ok button
			if(choice == 0)
			{
				String correctPassword = Util.getPropertyValueFromPropertiesFile("gui_password");
				String enteredPassword = String.valueOf(passwordField.getPassword());
				if(enteredPassword.equals(correctPassword))
				{
					monitrackFrame.setNorthPanel(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getSource() == monitrackFrame.getSuperUserModeButton())
		{
			JComboBox entitiesCombobox = new JComboBox(new String[]{"Personnes", "Emplacements"});
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(new JLabel("Choisissez le type d'entité à générer :"));
			panel.add(entitiesCombobox);
			int choice = JOptionPane.showConfirmDialog(monitrackFrame, panel, "Générateur de valeurs aléatoires", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, Images.SUPER.getIcon());
			
			if(choice == 0)
			{
				String entityChoice = entitiesCombobox.getSelectedItem().toString();
				
				if(entityChoice.equalsIgnoreCase("personnes"))
				{
					generateRandomPersons();				
				}
				else if(entityChoice.equalsIgnoreCase("emplacements"))
				{
					generateRandomLocations();			
				}
			}
		}
		else if(e.getSource() == monitrackFrame.getReserveConnectionButton())
		{
			try 
			{
				String[] response = MonitrackGUIFactory.sendRequest(ConnectionState.RESERVED_CONNECTION.getCode().toString()).split("-");
				String message = response[0];
				String time = response[1];
				displayConnectionTimeLeft(time);
				JOptionPane.showMessageDialog(monitrackFrame, message, "Connexion réservée", JOptionPane.INFORMATION_MESSAGE);
			} 
			catch (Exception e1) 
			{
				log.error(e1.getMessage());
			}			
		}
		else if(e.getSource() == monitrackFrame.getOpenLogFileButton())
		{
			try 
			{
				File logFile = new File("monitrack.log");
				if(Desktop.isDesktopSupported())
				{
					Desktop.getDesktop().open(logFile);
				}
				else
				{
					JOptionPane.showMessageDialog(monitrackFrame, "Le fichier monitrack.log ne peut pas être ouvert", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} 
			catch (Exception e1) 
			{
				log.error(e1.getMessage());
			}			
		}
	}
	
	
	private void displayConnectionTimeLeft(String time) {
		Thread timer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				monitrackFrame.getTimerLabel().setVisible(true);
				monitrackFrame.getReserveConnectionButton().setEnabled(false);
				try 
				{
					int timeLeft = Integer.parseInt(time);
					while(timeLeft >= 0)
					{
						monitrackFrame.getTimerLabel().setText(timeLeft + "''");
						Thread.sleep(999);
						timeLeft--;
					}
				} 
				catch (Exception e) 
				{
					log.error(e.getMessage());
				}

				monitrackFrame.getReserveConnectionButton().setEnabled(true);
				monitrackFrame.getTimerLabel().setVisible(false);
				
			}
		});
		timer.start();
		
	}

	private static void generateRandomPersons()
	{
		try 
		{
			InputStream inputStream = MonitrackListener.class.getClassLoader().getResourceAsStream("mocks/persons.txt");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			Person person = null;
			
			//Only the first line contains names
			String line = bufferedReader.readLine();
			String[] names = line.split(";");
			for(String name : names)
			{
				person = new Person(name);
				String serializedObject = JsonUtil.serializeObject(person, Person.class, "");
				String jsonRequest = JsonUtil.serializeRequest(RequestType.INSERT, Person.class, serializedObject, null, null);
				MonitrackGUIFactory.sendRequest(jsonRequest);
			}
			inputStream.close();
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage());
		}
		
	}
	
	private static void generateRandomLocations()
	{
		try 
		{
			InputStream inputStream = MonitrackListener.class.getClassLoader().getResourceAsStream("mocks/locations.csv");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			Location location = null;
			//This will skip the first line which contains the headers
			String line = bufferedReader.readLine();
			
			while ((line = bufferedReader.readLine()) != null) 
			{
				String[] values = line.split(";");
				String name = values[0];
				String center = "";
				int floor = Integer.parseInt(values[2]);
				String wing = values[3];
				int area = Integer.parseInt(values[4]);
				location = new Location(name, center, floor, wing, area);
				String serializedObject = JsonUtil.serializeObject(location, Location.class, "");
				String jsonRequest = JsonUtil.serializeRequest(RequestType.INSERT, Location.class, serializedObject, null, null);
				MonitrackGUIFactory.sendRequest(jsonRequest);
			} 
			inputStream.close();
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage());
		}
	}

}
