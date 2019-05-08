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

public class ConfigurationTab extends JPanel{

	private static final long serialVersionUID = 1L;
	
	/***** Menu Panels *****/
	private JPanel northPanel;
	private JPanel northPanelActionsChoice;
	private JPanel northPanelForConfigure;
	private JPanel northPanelForShow;
	
	private JComboBox<String> actionsCombobox;
	private JComboBox<String> configureSensorsCombobox;
	
	/***** Components of the overview menu *****/
	private JComboBox<String> filter1ForShowCombobox;
	private JComboBox<String> filter2ForShowCombobox;
	private JTextField filter1TextField;
	private JTextField filter2TextField;
	
	private JTextField filter3TextField;

	private ConfigurationTabListener listener;

	private JTextArea textArea;


	/***** Buttons for the CRUD (Create, Read, Update and Delete) *****/ 
	private JButton showButton;
	private JButton configureButton;

	/***** Dialog for creating a location *****/
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


		//Panel to choose the CRUD Operation to do
		northPanelActionsChoice = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel actionLabel = new JLabel("Action : ");
		String[] items = {"Visualiser les capteurs","Configurer un capteur"};
		actionsCombobox = new JComboBox<String>(items);
		actionsCombobox.addActionListener(listener);
		northPanelActionsChoice.add(actionLabel);
		northPanelActionsChoice.add(actionsCombobox);
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(textAreaFont);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);		

		setShowMenu();
		setConfigurationMenu();
		
		//setConfigureSensorPopupPanel();

		actionsCombobox.setSelectedItem(items[0]);
		
		add(northPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}
	
	private void setShowMenu()
	{
		northPanelForShow = new JPanel(new FlowLayout(FlowLayout.LEFT));

		String[] filters1 = {"-", "Type", "Id"};
		String[] filters2 = {"-", "Activation", "Adresse IP"};
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
	
	
	private void setConfigurationMenu()
	{
		northPanelForConfigure = new JPanel(new FlowLayout(FlowLayout.LEFT));
		configureSensorsCombobox = new JComboBox<>();
		configureButton = new JButton("Modifier");
		configureButton.addActionListener(listener);
		configureButton.setIcon(Images.MODIFY_ICON.getIcon());
		filter3TextField = new JTextField(7);
		northPanelForConfigure.add(new JLabel("Sélectionner un capteur : "));
		northPanelForConfigure.add(configureSensorsCombobox);
		northPanelForShow.add(filter3TextField);
		northPanelForConfigure.add(configureButton);

	}

	public JPanel getNorthPanel() {
		return northPanel;
	}

	public void setNorthPanel(JPanel northPanel) {
		this.northPanel = northPanel;
	}

	public JPanel getNorthPanelActionsChoice() {
		return northPanelActionsChoice;
	}

	public void setNorthPanelActionsChoice(JPanel northPanelActionsChoice) {
		this.northPanelActionsChoice = northPanelActionsChoice;
	}

	public JPanel getNorthPanelForShow() {
		return northPanelForShow;
	}

	public void setNorthPanelForShow(JPanel northPanelForShow) {
		this.northPanelForShow = northPanelForShow;
	}

	public JComboBox<String> getActionsCombobox() {
		return actionsCombobox;
	}

	public void setActionsCombobox(JComboBox<String> actionsCombobox) {
		this.actionsCombobox = actionsCombobox;
	}

	public JComboBox<String> getFilter1ForShowCombobox() {
		return filter1ForShowCombobox;
	}

	public void setFilter1ForShowCombobox(JComboBox<String> filter1ForShowCombobox) {
		this.filter1ForShowCombobox = filter1ForShowCombobox;
	}

	public JComboBox<String> getFilter2ForShowCombobox() {
		return filter2ForShowCombobox;
	}

	public void setFilter2ForShowCombobox(JComboBox<String> filter2ForShowCombobox) {
		this.filter2ForShowCombobox = filter2ForShowCombobox;
	}

	public JTextField getFilter1TextField() {
		return filter1TextField;
	}

	public void setFilter1TextField(JTextField filter1TextField) {
		this.filter1TextField = filter1TextField;
	}

	public JTextField getFilter2TextField() {
		return filter2TextField;
	}

	public void setFilter2TextField(JTextField filter2TextField) {
		this.filter2TextField = filter2TextField;
	}

	public ConfigurationTabListener getListener() {
		return listener;
	}

	public void setListener(ConfigurationTabListener listener) {
		this.listener = listener;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getShowButton() {
		return showButton;
	}

	public void setShowButton(JButton showButton) {
		this.showButton = showButton;
	}

	public JTextField getNewLocationNameTextField() {
		return newLocationNameTextField;
	}

	public void setNewLocationNameTextField(JTextField newLocationNameTextField) {
		this.newLocationNameTextField = newLocationNameTextField;
	}

	public JTextField getNewFloorTextField() {
		return newFloorTextField;
	}

	public void setNewFloorTextField(JTextField newFloorTextField) {
		this.newFloorTextField = newFloorTextField;
	}

	public JTextField getNewLocationSizeTextField() {
		return newLocationSizeTextField;
	}

	public void setNewLocationSizeTextField(JTextField newLocationSizeTextField) {
		this.newLocationSizeTextField = newLocationSizeTextField;
	}

	public JComboBox<String> getNewBuildingWingCombobox() {
		return newBuildingWingCombobox;
	}

	public void setNewBuildingWingCombobox(JComboBox<String> newBuildingWingCombobox) {
		this.newBuildingWingCombobox = newBuildingWingCombobox;
	}

	public JPanel getModifyLocationPopupPanel() {
		return modifyLocationPopupPanel;
	}

	public void setModifyLocationPopupPanel(JPanel modifyLocationPopupPanel) {
		this.modifyLocationPopupPanel = modifyLocationPopupPanel;
	}

	public JTextField getOldNameTextField() {
		return oldNameTextField;
	}

	public void setOldNameTextField(JTextField oldNameTextField) {
		this.oldNameTextField = oldNameTextField;
	}

	public JTextField getModifiedNameTextField() {
		return modifiedNameTextField;
	}

	public void setModifiedNameTextField(JTextField modifiedNameTextField) {
		this.modifiedNameTextField = modifiedNameTextField;
	}

	public JTextField getOldFloorTextField() {
		return oldFloorTextField;
	}

	public void setOldFloorTextField(JTextField oldFloorTextField) {
		this.oldFloorTextField = oldFloorTextField;
	}

	public JTextField getModifiedFloorTextField() {
		return modifiedFloorTextField;
	}

	public void setModifiedFloorTextField(JTextField modifiedFloorTextField) {
		this.modifiedFloorTextField = modifiedFloorTextField;
	}

	public JTextField getOldLocationSizeTextField() {
		return oldLocationSizeTextField;
	}

	public void setOldLocationSizeTextField(JTextField oldLocationSizeTextField) {
		this.oldLocationSizeTextField = oldLocationSizeTextField;
	}

	public JTextField getModifiedLocationSizeTextField() {
		return modifiedLocationSizeTextField;
	}

	public void setModifiedLocationSizeTextField(JTextField modifiedLocationSizeTextField) {
		this.modifiedLocationSizeTextField = modifiedLocationSizeTextField;
	}

	public JTextField getOldBuildingWingTextField() {
		return oldBuildingWingTextField;
	}

	public void setOldBuildingWingTextField(JTextField oldBuildingWingTextField) {
		this.oldBuildingWingTextField = oldBuildingWingTextField;
	}

	public JComboBox<String> getModifiedBuildingWingCombobox() {
		return modifiedBuildingWingCombobox;
	}

	public void setModifiedBuildingWingCombobox(JComboBox<String> modifiedBuildingWingCombobox) {
		this.modifiedBuildingWingCombobox = modifiedBuildingWingCombobox;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getNorthPanelForConfigure() {
		return northPanelForConfigure;
	}

	public void setNorthPanelForConfigure(JPanel northPanelForConfigure) {
		this.northPanelForConfigure = northPanelForConfigure;
	}

	public JButton getConfigureButton() {
		return configureButton;
	}

	public void setConfigureButton(JButton configureButton) {
		this.configureButton = configureButton;
	}

	public JComboBox<String> getConfigureSensorsCombobox() {
		return configureSensorsCombobox;
	}

	public void setConfigureSensorsCombobox(JComboBox<String> configureSensorsCombobox) {
		this.configureSensorsCombobox = configureSensorsCombobox;
	}
	
	
	
	
}
