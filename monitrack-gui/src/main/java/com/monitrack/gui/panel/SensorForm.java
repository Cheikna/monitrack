package com.monitrack.gui.panel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import com.monitrack.entity.Location;
import com.monitrack.entity.Sensor;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorState;
import com.monitrack.enumeration.SensorType;

import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class SensorForm {

	JFrame frame;
	private JTextField tfX;
	private JTextField tfY;
	private JTextField textField_2;

	private SensorConfiguration sensor;
	private MapPage mapPage;
	private Location location;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SensorForm window = new SensorForm(null, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param mapPage
	 *            TODO
	 */
	public SensorForm(MapPage mapPage, Location location) {
		this.mapPage  = mapPage;
		this.location = location;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		frame = new JFrame();
		frame.setBounds(100, 100, 730, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tfX = new JTextField();
		tfX.setBounds(128, 28, 86, 20);
		frame.getContentPane().add(tfX);
		tfX.setColumns(10);

		JLabel lblName = new JLabel("Coordonnées X");
		lblName.setBounds(65, 31, 46, 14);
		frame.getContentPane().add(lblName);

		JLabel lblPhone = new JLabel("Coordonnées Y");
		lblPhone.setBounds(65, 68, 46, 14);
		frame.getContentPane().add(lblPhone);

		tfY = new JTextField();
		tfY.setBounds(128, 65, 86, 20);
		frame.getContentPane().add(tfY);
		tfY.setColumns(10);

		JButton btnClear = new JButton("Clear");

		btnClear.setBounds(312, 387, 89, 23);
		frame.getContentPane().add(btnClear);

		JLabel lblOccupation = new JLabel("Type capteur");
		lblOccupation.setBounds(65, 288, 67, 14);
		frame.getContentPane().add(lblOccupation);

		JComboBox<String> comboBox = new JComboBox<String>();
		List<Sensor> sensors;
		Set<SensorType> uniques = new HashSet<>();
		uniques.add(SensorType.DOOR);
		uniques.add(SensorType.WINDOW);
		uniques.add(SensorType.ACCESS_CONTROL);
		
		sensors = location.getSensors();
		// comboBox.addItem("Choisir");
		for (SensorType state : SensorType.values()) {
			// vérifier si type courant n'est pas déja dans la location
			for(Sensor sensor : sensors) {
				SensorConfiguration sc = (SensorConfiguration)sensor;
				if(uniques.contains(state)) {
					continue;
				}
			}
			comboBox.addItem(state.name());		
		}

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String typeS = (String) cb.getSelectedItem();
				SensorType sensorType = SensorType.valueOf(typeS);
				sensor.setSensorType(sensorType);
			}
		});
		comboBox.setBounds(180, 285, 91, 20);
		frame.getContentPane().add(comboBox);

		JButton btnSubmit = new JButton("submit");

		btnSubmit.setBackground(Color.BLUE);
		btnSubmit.setForeground(Color.MAGENTA);
		btnSubmit.setBounds(65, 387, 89, 23);
		frame.getContentPane().add(btnSubmit);
		// clic sur validation formulaire
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String xS = tfX.getText();
				if (xS != null) {
					try {
						Float x = Float.valueOf(xS);
						sensor.setPositionX(x);
					} catch (Exception e) {

					}
				}

				String yS = tfY.getText();
				if (yS != null) {
					try {
						Float y = Float.valueOf(yS);
						sensor.setPositionY(y);
					} catch (Exception e) {

					}
				}
				//JOptionPane.showMessageDialog(null, sensor.toString());
				location.getSensors().add(sensor);
				mapPage.showSensor(location, sensor);
				
				
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfY.setText(null);
				textField_2.setText(null);
				tfX.setText(null);
			}
		});

		
		
		sensor = new SensorConfiguration();
		String typeS = (String) comboBox.getSelectedItem();
		SensorType sensorType = SensorType.valueOf(typeS);
		sensor.setSensorType(sensorType);
		sensor.setSensorState(SensorState.NOT_CONFIGURED);
	}

	public MapPage getMapPage() {
		return mapPage;
	}

	public void setMapPage(MapPage mapPage) {
		this.mapPage = mapPage;
	}

}
