package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.Configuration;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Location;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.DeprecatedVersionException;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.gui.panel.ConfigurationTab;
import com.monitrack.gui.panel.LocationsTab;
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
					}
				}
			}
			else if(e.getSource() instanceof JButton)
			{
				JButton clickedButton = (JButton)e.getSource();
				if(clickedButton == configurationTab.getShowButton())
				{
					displayLocations();
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
		
	
		@SuppressWarnings("unchecked")
		private void displayLocations() throws NoAvailableConnectionException, IOException, DeprecatedVersionException
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
					field = "ID_SENSOR";
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
				String field = "END_ACTIVITY_TIME";
				if(filter2.equalsIgnoreCase("Début d'activité"))
				{
					field = "START_ACTIVITY_TIME";
				}
				
				int value = NumberUtils.toInt(configurationTab.getFilter2TextField().getText(), -2);
				if(value != -2)
				{
					fields.add(field);
					values.add(String.valueOf(value));
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
