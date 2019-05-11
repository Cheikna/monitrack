package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.DeprecatedVersionException;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.gui.panel.ConfigurationTab;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class ConfigurationTabListener implements ActionListener {

	private static final Logger log = LoggerFactory.getLogger(ConfigurationTabListener.class);
	
	private ConfigurationTab configurationTab;
	private List<SensorConfiguration> sensors;
	private List<String> fields;
	private List<String> values;

	public ConfigurationTabListener(ConfigurationTab configurationTab)
	{
		this.configurationTab = configurationTab;
		sensors = new ArrayList<>();
		fields = new ArrayList<String>();
		values = new ArrayList<String>();	
	}

	@SuppressWarnings("rawtypes")
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource() instanceof JComboBox)
			{
				JComboBox combo = (JComboBox)e.getSource();
				if(combo == configurationTab.getActionsCombobox())
				{
					configurationTab.getNorthPanel().removeAll();
					configurationTab.getNorthPanel().add(configurationTab.getNorthPanelActionsChoice());
					
					if(combo.getSelectedItem().equals("Visualiser les capteurs"))
					{
						
						configurationTab.getNorthPanel().add(configurationTab.getNorthPanelForShow());
						
					} else if(combo.getSelectedItem().equals("Configurer un capteur"))
					
					{	
//						setComboboxWithSensors(configurationTab.getConfigureSensorsCombobox());
						configurationTab.getNorthPanel().add(configurationTab.getNorthPanelForConfigure());				
					}
				}
			}
			else if(e.getSource() instanceof JButton)
			{
				JButton clickedButton = (JButton)e.getSource();
				if(clickedButton == configurationTab.getShowButton())
				{
					displaySensors();
				}
				else if(clickedButton == configurationTab.getConfigureButton()){
					configureSensor();
				}
			}
		}
		catch(Exception e1)
		{
			log.error(e1.getClass().getSimpleName() + " : " + e1.getMessage());
		}

		//Updates the Panel
		configurationTab.revalidate();
		configurationTab.repaint();
	}

	
	private void configureSensor() throws NoAvailableConnectionException, IOException, DeprecatedVersionException
	{
		int selectedConfigurationIndex = configurationTab.getConfigureSensorsCombobox().getSelectedIndex();
		if(selectedConfigurationIndex >= 0 && selectedConfigurationIndex < sensors.size())
		{
			SensorConfiguration sensorToConfigure = sensors.get(selectedConfigurationIndex);

			configurationTab.getOldMaxDangerThresholdTextField().setText(String.valueOf(sensorToConfigure.getMaxDangerThreshold()));
			configurationTab.getOldMinDangerThresholdTextField().setText(String.valueOf(sensorToConfigure.getMinDangerThreshold()));
			configurationTab.getOldActivityTextField().setText(String.valueOf(sensorToConfigure.getSensorActivity()));	
			configurationTab.getOldVersionTextField().setText(String.valueOf(sensorToConfigure.getSoftwareVersion()));

			int choice = 0;
			String errorMessage = "";

			errorMessage = "";
			choice = JOptionPane.showConfirmDialog(configurationTab, configurationTab.getConfigureCaptorPopupPanel(), 
					"Configurer un capteur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, Images.CONFIGURER.getIcon());

			String name = configurationTab.getModifiedMaxDangerThresholdTextField().getText().trim();
			if(name.length() <= 0) {
				name = configurationTab.getOldMaxDangerThresholdTextField().getText();
			}
			/**
			String floorText =/**.getText().trim();
			int floor = NumberUtils.toInt(floorText, -10);
			if(floor == -10 || floor < -1 || floor > 3) {
				//We display this error only if the user entered something in the field
				if(floorText.length() > 0)
					errorMessage += "L'étage doit être un nombre compris entre -1 et 3\n";
				floor = sensorToConfigure.getFloor();
			}					

			String areaText = configurationTab.getModifiedLocationSizeTextField().getText().trim();
			int area = NumberUtils.toInt(areaText, 0);
			if(area < 40 || area > 200) {
				//We display this error only if the user entered something in the field
				if(areaText.length() > 0)
					errorMessage += "La superficie doit être comprise entre 40m² et 200m² !";
				area = sensorToConfigure.getArea();
			}	

			String wing = configurationTab.getModifiedBuildingWingCombobox().getSelectedItem().toString();

			if(errorMessage.trim().length() > 0 && choice == 0)
			{
				String message = "Pour certains champs la valeur restera inchangée suite à des erreurs :\n" + errorMessage;
				JOptionPane.showMessageDialog(configurationTab, message, "Erreur", JOptionPane.INFORMATION_MESSAGE);		
			}
*/
			if(choice == 0)
			{
				// Upadates the location which needs to be updated
				sensorToConfigure.setMaxDangerThreshold(null);
				sensorToConfigure.setMinDangerThreshold(null);
				sensorToConfigure.setSensorActivity(null);
				sensorToConfigure.setSoftwareVersion(null);	
			}


			//Send the request if all fields are correct and the user clicked on OK Button
			if(choice == 0)
			{		
				String serializedObject = JsonUtil.serializeObject(sensorToConfigure, SensorConfiguration.class, "");	
				String jsonRequest = JsonUtil.serializeRequest(RequestType.UPDATE, SensorConfiguration.class, serializedObject, null, null, RequestSender.CLIENT);
				MonitrackGuiUtil.sendRequest(jsonRequest);
				JOptionPane.showMessageDialog(configurationTab, "Votre capteur a bien été mis à jour", "Mise à jour réussie", JOptionPane.INFORMATION_MESSAGE);
				setComboboxWithSensors(configurationTab.getConfigureSensorsCombobox());
			}

			
			// Clear the fields
			configurationTab.getOldMaxDangerThresholdTextField().setText("");
			configurationTab.getOldMinDangerThresholdTextField().setText("");
			configurationTab.getOldActivityTextField().setText("");	
			configurationTab.getOldVersionTextField().setText("");


		}
	}
	
	@SuppressWarnings("unchecked")
	private void setComboboxWithSensors(JComboBox<String> combobox)
	{
		String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, null, null, RequestSender.CLIENT);
		try 
		{
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			sensors = (List<SensorConfiguration>)JsonUtil.deserializeObject(response);
			combobox.removeAllItems();
			for(SensorConfiguration sensor : sensors)
			{
				combobox.addItem(sensor.toString());
			}
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage());
		}
	}
	
		@SuppressWarnings("unchecked")
		private void displaySensors() throws NoAvailableConnectionException, IOException, DeprecatedVersionException
		{
			fields.clear();
			values.clear();

			//Filters
			String filter1 = configurationTab.getFilter1ForShowCombobox().getSelectedItem().toString();
			String filter2 = configurationTab.getFilter2ForShowCombobox().getSelectedItem().toString();

			if(!filter1.equals("-"))
			{

				String field = "TYPE";
				
				if(filter1.equalsIgnoreCase("id"))
				{
					field = "ID_SENSOR_CONFIGURATION";
				} else if (filter1.equalsIgnoreCase("activity")) {
					field = "ACTIVITY";
				}
				
				String value = configurationTab.getFilter1TextField().getText().trim();
				
				if(value.length() > 0)
				{
					fields.add(field);
					values.add(value);
				}
			}

			if(!filter2.equals("-")) 
			{
				String field = "ACTIVITY";
				if(filter2.equalsIgnoreCase("Adresse IP"))
				{
					field = "IP_ADDRESS";
				}
				
				String value2 = configurationTab.getFilter2TextField().getText().trim();
				
				if(value2.length() > 0)
				{
					fields.add(field);
					values.add(value2);
				}

			}



			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, fields, values, RequestSender.CLIENT);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			List<SensorConfiguration> sensorToDisplay = (List<SensorConfiguration>)JsonUtil.deserializeObject(response);

			String configurationText = "";
			for(SensorConfiguration sensor : sensorToDisplay)
			{
				configurationText += sensor.toString() + "\n";
			}

			configurationTab.getTextArea().setText(configurationText);
			
			if(configurationText.equals(""))
				JOptionPane.showMessageDialog(configurationTab, "Aucune donnée ne correpsond à vos critères", "Pas de données", JOptionPane.INFORMATION_MESSAGE);

		}
		
	

}
