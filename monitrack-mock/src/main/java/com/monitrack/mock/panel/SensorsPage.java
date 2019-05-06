package com.monitrack.mock.panel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.monitrack.mock.listener.SensorsTableModel;
import com.monitrack.mock.util.MockUtil;

public class SensorsPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Insets insets = new Insets(5,10,0,0);
	private final Insets defaultInsets = new Insets(0,0,0,0);
	private GridBagConstraints c;

	/***************** Filter bar **********************/
	private JTextField idTextField;
	private JComboBox<String> sensorTypeComboBox;
	private JComboBox<String> sensorActivityComboBox;
	private JTextField locationTextField;
	private JButton validateFiltersButton;
	private JButton loadDatasFromDatabaseButton;
	private JButton loadDatasFromServerCacheButton;

	/****************** Table **************************/
	private JScrollPane sensorsTableScrollPane;
	private JTable sensorsTable;
	private SensorsTableModel sensorsTableModel;

	/******* Changing message value *******/
	private JLabel rateValueLabel;
	private JTextField rateValueTextField;
	private JButton startSendingMessageButton;
	private JButton stopSendingMessageButton;

	public SensorsPage() {
		super(new GridBagLayout());		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		sensorsTable = new JTable();
		sensorsTableModel = new SensorsTableModel(this, sensorsTable);
		sensorsTableScrollPane = new JScrollPane(sensorsTable);

		initFiltersBar();
		c.insets = defaultInsets;
		initSensorsTable();
		c.insets = defaultInsets;
		initRateChoiceBar();
	}

	private void initFiltersBar() {
		idTextField = new JTextField(5);
		sensorTypeComboBox = new JComboBox<String>(MockUtil.getSensorTypeAsStringArray());
		sensorActivityComboBox = new JComboBox<String>(MockUtil.getSensorActivityAsStringArray());
		locationTextField = new JTextField(15);
		validateFiltersButton = new JButton("Validate");
		validateFiltersButton.addActionListener(sensorsTableModel);

		c.insets = insets;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.2;
		add(new JLabel("Find by ID : "), c);
		c.gridx = 1;	 
		add(idTextField, c);

		c.gridx = 2;
		c.insets = insets;
		add(new JLabel("Find by type : "), c);
		c.gridx = 3;
		c.insets = defaultInsets;
		add(sensorTypeComboBox, c);
		c.insets = insets;

		c.gridx = 4;
		add(new JLabel("Find by activity : "), c);
		c.insets = defaultInsets;
		c.gridx = 5;
		add(sensorActivityComboBox, c);
		c.insets = insets;


		c.gridx = 6;
		add(new JLabel("Find by location : "), c);
		c.gridx = 7;
		add(locationTextField, c);

		c.insets = new Insets(0, 10, 0, 10);
		c.gridx = 8;
		add(validateFiltersButton, c);
	}

	private void initSensorsTable() {	
		loadDatasFromDatabaseButton = new JButton("Load datas from database");
		loadDatasFromDatabaseButton.addActionListener(sensorsTableModel);
		loadDatasFromServerCacheButton = new JButton("Load datas from server cache");
		loadDatasFromServerCacheButton.addActionListener(sensorsTableModel);

		c.weighty = 1;
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 8;
		c.gridheight = 2;
		add(sensorsTableScrollPane, c);	
		c.gridwidth = 4;
		c.gridy = 10;
		c.insets = new Insets(0,0,0,30);
		add(loadDatasFromDatabaseButton, c);
		c.gridx = 4;
		add(loadDatasFromServerCacheButton, c);
	}

	private void initRateChoiceBar() {
		c.gridy = 7;
		c.gridx = 0;
		c.gridwidth = 7;
		rateValueLabel = new JLabel("Current threshold : ");
		rateValueLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		rateValueTextField = new JTextField("0");
		rateValueTextField.setFont(new Font("Calibri", Font.PLAIN, 17));
		rateValueTextField.getDocument().addDocumentListener(sensorsTableModel);
		add(rateValueLabel, c);
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(50,0,0,0);
		c.gridwidth = 1;
		JLabel info = new JLabel("Enter the new threshold :  ");
		info.setFont(new Font("Calibri", Font.PLAIN, 17));
		add(info, c);
		c.gridx = 1;
		c.gridwidth = 2;
		add(rateValueTextField, c);
		startSendingMessageButton = new JButton("Start sending or update signal");
		startSendingMessageButton.addActionListener(sensorsTableModel);
		c.gridx = 3;
		c.insets = new Insets(50, 25, 0 , 12);
		add(startSendingMessageButton, c);
		c.gridx = 5;		
		stopSendingMessageButton = new JButton("Stop sending signal");
		stopSendingMessageButton.addActionListener(sensorsTableModel);
		add(stopSendingMessageButton, c);	
		
	}

	public JButton getValidateFiltersButton() {
		return validateFiltersButton;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public JComboBox<String> getSensorTypeComboBox() {
		return sensorTypeComboBox;
	}

	public JComboBox<String> getSensorActivityComboBox() {
		return sensorActivityComboBox;
	}

	public JTextField getLocationTextField() {
		return locationTextField;
	}

	public JTextField getRateValueTextField() {
		return rateValueTextField;
	}

	public JLabel getRateValueLabel() {
		return rateValueLabel;
	}

	public JButton getStartSendingMessageButton() {
		return startSendingMessageButton;
	}

	public JButton getStopSendingMessageButton() {
		return stopSendingMessageButton;
	}

	public JButton getLoadDatasFromDatabaseButton() {
		return loadDatasFromDatabaseButton;
	}

	public JButton getLoadDatasFromServerCacheButton() {
		return loadDatasFromServerCacheButton;
	}	

}
