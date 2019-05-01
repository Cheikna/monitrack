package com.monitrack.mock.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;
import com.monitrack.mock.listener.SensorsTableModel;
import com.monitrack.mock.util.MockUtil;

public class SensorsPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Insets insets = new Insets(5,10,0,0);
	private final Insets defaultInsets = new Insets(0,0,0,0);
	private GridBagConstraints c;
	//private SensorsTableListener sensorsTableListener;

	/***************** Filter bar **********************/
	private JTextField idTextField;
	private JComboBox<SensorType> sensorTypeComboBox;
	private JComboBox<SensorActivity> sensorActivityComboBox;
	private JTextField locationTextField;
	private JButton validateFiltersButton;
	
	/****************** Table **************************/
	private JScrollPane sensorsTableScrollPane;
	private JTable sensorsTable;
	private SensorsTableModel sensorsTableModel;
	
	/************* Slider ************************/
	private JLabel sliderLabel;
	private JSlider sensorSlider;
	private int initialSliderValue;
	private int inferiorSliderBound;
	private int superiorSliderBound;

	public SensorsPage() {
		super(new GridBagLayout());		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		sensorsTable = new JTable();
		sensorsTableModel = new SensorsTableModel(this, sensorsTable);
		sensorsTableScrollPane = new JScrollPane(sensorsTable);
		initialSliderValue = 0;
		inferiorSliderBound = 0;
		superiorSliderBound = 20;
		sensorSlider = new JSlider(JSlider.HORIZONTAL, inferiorSliderBound, superiorSliderBound, initialSliderValue);
		sensorSlider.addChangeListener(sensorsTableModel);
		
		initFiltersBar();
		c.insets = defaultInsets;
		initSensorsTable();
		c.gridy = 5;
		c.gridx = 0;
		c.gridwidth = 7;
		c.gridheight = 2;
		updateSensorSlider(inferiorSliderBound, superiorSliderBound, initialSliderValue);
		add(sensorSlider, c);
		/*c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(new JButton("Super"), c);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.weightx = 0.4;
		c.weighty = 1;
		add(sensorsTableScrollPane, c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.1;
		add(new JButton("de"), c);
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.1;
		add(new JButton("la"), c);*/
	}

	private void initFiltersBar() {
		idTextField = new JTextField(5);
		sensorTypeComboBox = new JComboBox<SensorType>(SensorType.values());
		sensorActivityComboBox = new JComboBox<SensorActivity>(SensorActivity.values());
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

		c.weighty = 1;
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 7;
		c.gridheight = 2;
		add(sensorsTableScrollPane, c);
	}
	
	public void updateSensorSlider(int min, int max, int value) {
		sensorSlider.setMinimum(min);
		sensorSlider.setMaximum(max);
		sensorSlider.setValue(value);
		sensorSlider.setMajorTickSpacing(5);
		sensorSlider.setMinorTickSpacing(1);
		sensorSlider.setPaintTicks(true);
		sensorSlider.setPaintLabels(true);
	}

	public JButton getValidateFiltersButton() {
		return validateFiltersButton;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public JComboBox<SensorType> getSensorTypeComboBox() {
		return sensorTypeComboBox;
	}

	public JComboBox<SensorActivity> getSensorActivityComboBox() {
		return sensorActivityComboBox;
	}

	public JTextField getLocationTextField() {
		return locationTextField;
	}

	public JSlider getSensorSlider() {
		return sensorSlider;
	}	
	
	

	/*northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	northPanel.add(new JLabel("Rechercher les capteurs par : "));
	centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
	Sensor sensor = new Sensor(8, SensorActivity.ENABLED, SensorType.FLOW, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
			1.0f, 2.0f, null, null, null, null, null, 2500f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);
	Sensor sensor2 = new Sensor(9, SensorActivity.ENABLED, SensorType.SMOKE, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
			1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel", 4.0f, 0.0f, 5.0f, 6.23f, 4.94f);*/
	//centerPanel.add(new SensorInfoPanel(sensor));
	//centerPanel.add(new SensorInfoPanel(sensor2));		

	//centerPanel = new JPanel(new GridBagLayout());

}
