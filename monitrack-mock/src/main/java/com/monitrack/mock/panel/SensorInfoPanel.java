package com.monitrack.mock.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorType;
import com.monitrack.mock.listener.SensorInfoListener;


public class SensorInfoPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private SensorInfoListener listener;
	
	private Font font;
	private Font borderFont;
	
	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel eastPanel;
	private JPanel southPanel;
	private JLabel sensorStateLabel;
	
	private JPopupMenu popup;
	// When we try to remove the sensor whereas we should not
	private JMenuItem snatchSensor;
	private JMenuItem startFire;
	private JMenuItem startSmoking;	
	private JMenuItem stopSmoking;	
	private JMenuItem sendReparator;
	
	private JMenuItem addPerson;
	private JMenuItem removePerson;
	private JMenuItem removeAllPersons;
	
	private SensorConfiguration sensorConfiguration;
	private boolean timeToChangeColor;
	
	
	public SensorInfoPanel(SensorConfiguration sensorConfiguration) {
		this.sensorConfiguration = sensorConfiguration;
		font = new Font("Calibri", Font.BOLD, 25);
		borderFont = new Font("Calibri", Font.PLAIN, 15);
		listener = new SensorInfoListener(this);
		this.setLayout(new BorderLayout());
		this.addMouseListener(listener);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sensorStateLabel = new JLabel("Etat actuel : /!\\");
		sensorStateLabel.setFont(font);
		setPopupMenu();
		setNorthPanel();
		setWestPanel();
		setCenterPanel();
		setEastPanel();
		setSouthPanel();
	}
	
	public void setNorthPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		JLabel sensorIdLabel = new JLabel("Capteur n°" + sensorConfiguration.getSensorConfigurationId());
		sensorIdLabel.setFont(new Font("Arial", Font.BOLD, 18));
		sensorIdLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		JLabel ipLabel = new JLabel("Adresse IP : " + ((sensorConfiguration.getIpAddress() != null) ? sensorConfiguration.getIpAddress() : ""));
		ipLabel.setFont(borderFont);
		ipLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		JLabel macLabel = new JLabel("Adresse Mac : " + sensorConfiguration.getMacAddress());
		macLabel.setFont(borderFont);
		macLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		northPanel.add(sensorIdLabel);
		northPanel.add(macLabel);
		northPanel.add(ipLabel);
		northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(northPanel, BorderLayout.NORTH);
	}
	
	public void setWestPanel() {
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel label1 = new JLabel("    Type :   ");
		label1.setFont(borderFont);
		label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		JLabel label2 = new JLabel("    " + sensorConfiguration.getSensorType().getFrenchLabel() + "    ");
		label2.setFont(borderFont);
		label2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		westPanel.add(label1);
		westPanel.add(label2);
		this.add(westPanel, BorderLayout.WEST);
	}
	
	public void setCenterPanel() {
		centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		centerPanel.add(sensorStateLabel);
		centerPanel.setBackground(Color.GREEN);
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	public void setEastPanel() {
		eastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel label = new JLabel("    Emplacement :    ");
		label.setFont(borderFont);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		eastPanel.add(label);
		
		this.add(eastPanel, BorderLayout.EAST);
	}
	public void setSouthPanel() {
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel label1 = new JLabel("Information : Le niveau actuel est de " + sensorConfiguration.getCurrentThreshold() + "/" + sensorConfiguration.getMaxDangerThreshold() + " " + sensorConfiguration.getMeasurementUnit());
		label1.setFont(borderFont);
		label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		southPanel.add(label1);
		this.add(southPanel, BorderLayout.SOUTH);
	}
	
	public void setPopupMenu() {

		popup = new JPopupMenu();
		
		SensorType sensorType = sensorConfiguration.getSensorType();
		if(sensorType == SensorType.SMOKE) {
			JMenu cigaretMenu = new JMenu("Cigarette");
			startSmoking = new JMenuItem("Fumer une cigarette");
			startSmoking.addActionListener(listener);
			stopSmoking = new JMenuItem("Arrêter de fumer une cigarette");
			stopSmoking.addActionListener(listener);		
			cigaretMenu.add(startSmoking);
			cigaretMenu.add(stopSmoking);
			popup.add(cigaretMenu);startFire = new JMenuItem("Démarer un feu");
			startFire.addActionListener(listener);
			sendReparator = new JMenuItem("Envoyer une équipe d'intervention");
			sendReparator.addActionListener(listener);
			popup.add(startFire);
			popup.add(sendReparator);
		}
		else if(sensorType == SensorType.FLOW) {
			addPerson = new JMenuItem("Ajouter une personne dans la salle");
			addPerson.addActionListener(listener);
			removePerson = new JMenuItem("Retirer une personne dans la salle");
			removePerson.addActionListener(listener);
			removeAllPersons = new JMenuItem("Vider la salle");
			removeAllPersons.addActionListener(listener);
			popup.add(addPerson);
			popup.add(removePerson);
			popup.add(removeAllPersons);	
		}
		
		

		snatchSensor = new JMenuItem("Arracher le capteur de manière brusque");
		popup.add(snatchSensor);
		this.add(popup);
		Timer timer = new Timer(450, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timeToChangeColor = !timeToChangeColor;
				repaint();
			}
		});
		timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		Color color = (timeToChangeColor) ? Color.GREEN : Color.WHITE;
		centerPanel.setBackground(color);
	}

	public JPopupMenu getPopup() {
		return popup;
	}
	
	public SensorConfiguration getSensor() {
		return sensorConfiguration;
	}

	public JMenuItem getStartFire() {
		return startFire;
	}

	public JMenuItem getStartSmoking() {
		return startSmoking;
	}

	public JMenuItem getSendReparator() {
		return sendReparator;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public JMenuItem getAddPerson() {
		return addPerson;
	}

	public JMenuItem getRemovePerson() {
		return removePerson;
	}

	public JMenuItem getRemoveAllPersons() {
		return removeAllPersons;
	}
	
	

}
