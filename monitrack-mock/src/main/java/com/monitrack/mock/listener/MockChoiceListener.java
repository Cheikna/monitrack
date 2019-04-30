package com.monitrack.mock.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.mock.dialog.SensorDialog;
import com.monitrack.mock.enumeration.Page;
import com.monitrack.mock.frame.MockFrame;
import com.monitrack.mock.panel.MockChoicePage;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class MockChoiceListener implements ActionListener {
	
	private MockFrame parentFrame;
	private MockChoicePage mockChoicePage;

	
	public MockChoiceListener(MockFrame parentFrame, MockChoicePage mockChoicePage) {
		this.mockChoicePage = mockChoicePage;
		this.parentFrame = parentFrame;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mockChoicePage.getSensorOverviewButton()) {
			parentFrame.changePage(Page.SENSOR_OVERVIEW);
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					//String jsonRequest = JsonUtil.serializeSensorsUpdateRequest();
					String jsonRequest = "";
					try {
						while(true) {
							String response = MonitrackGuiUtil.sendRequest(jsonRequest);
							List<SensorConfiguration> sensorConfigurations = (List<SensorConfiguration>)JsonUtil.deserializeObject(response);
							System.err.println("=========================");
							for(SensorConfiguration sensorConfiguration : sensorConfigurations) {
								System.out.println("=======>" + sensorConfiguration);
							}
							Thread.sleep(3000);
						}
					} catch (Exception e) {
					}
					
				}
			});
			thread.start();
			
		}
		else if(e.getSource() == mockChoicePage.getGenerateRandomSensorButton()) {
			parentFrame.changePage(Page.SENSORS_FACTORY);			
		}

	}

}
