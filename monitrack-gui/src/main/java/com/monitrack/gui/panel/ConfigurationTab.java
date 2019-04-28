package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.monitrack.enumeration.Images;
import com.monitrack.listener.ConfigurationTabListener;
import com.monitrack.listener.LocationsTabListener;

public class ConfigurationTab extends JPanel{

	private static final long serialVersionUID = 1L;
	
	/***** Menu Panels *****/
	private JPanel northPanel;
	private JPanel northPanelActionsChoice;
	private JPanel northPanelForCreate;
	private JPanel northPanelForModify;
	private JPanel northPanelForDelete;
	private JPanel northPanelForShow;
	
	private JComboBox<String> actionsCombobox;
	private JComboBox<String> modifyLocationsCombobox;
	private JComboBox<String> deleteLocationsCombobox;
	
	/***** Components of the overview menu *****/
	private JComboBox<String> filter1ForShowCombobox;
	private JComboBox<String> filter2ForShowCombobox;
	private JTextField filter1TextField;
	private JTextField filter2TextField;

	private ConfigurationTabListener listener;

	private JTextArea textArea;


	/***** Buttons for the CRUD (Create, Read, Update and Delete) *****/ 
	private JButton createButton;
	private JButton modifyButton;
	private JButton deleteButton;
	private JButton showButton;

	/***** Dialog for creating a location *****/
	private JPanel createLocationPopupPanel;
	private JTextField newLocationNameTextField;
	private JTextField newFloorTextField;
	private JTextField newLocationSizeTextField;
	private JComboBox<String> newBuildingWingCombobox;

	/***** Dialog for updating a location *****/
	private JPanel modifyLocationPopupPanel;
	private JTextField oldNameTextField;
	private JTextField modifiedNameTextField;
	private JTextField oldFloorTextField;
	private JTextField modifiedFloorTextField;
	private JTextField oldLocationSizeTextField;
	private JTextField modifiedLocationSizeTextField;
	private JTextField oldBuildingWingTextField;
	private JComboBox<String> modifiedBuildingWingCombobox;

	public ConfigurationTab()
	{
		setLayout(new BorderLayout());
		Font textAreaFont = new Font("Calibri", Font.PLAIN, 20);
		listener = new ConfigurationTabListener(this);
		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(textAreaFont);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);		

		setShowMenu();

		
		add(northPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}
	
	private void setShowMenu()
	{
		northPanelForShow = new JPanel(new FlowLayout(FlowLayout.LEFT));

		String[] filters1 = {"-", "Nom", "Aile"};
		String[] filters2 = {"-", "Etage", "Superficie"};
		filter1ForShowCombobox = new JComboBox<>(filters1);
		filter1ForShowCombobox.addActionListener(listener);
		filter2ForShowCombobox = new JComboBox<>(filters2);
		filter2ForShowCombobox.addActionListener(listener);
		filter1TextField = new JTextField(7);
		filter2TextField = new JTextField(7);
		northPanelForShow.add(new JLabel("Chercher par : "));
		northPanelForShow.add(filter1ForShowCombobox);
		northPanelForShow.add(filter1TextField);
		northPanelForShow.add(new JLabel(" Et par : "));
		northPanelForShow.add(filter2ForShowCombobox);
		northPanelForShow.add(filter2TextField);
		showButton = new JButton("Visualiser");
		showButton.setIcon(Images.SEARCH_ICON.getIcon());
		showButton.addActionListener(listener);
		
		northPanelForShow.add(showButton);
	}
}
