package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.monitrack.entity.Message;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.gui.panel.SensorInfoPanel;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class SensorInfoListener implements ActionListener {
	
	private SensorInfoPanel sensorInfoPanel;

	public SensorInfoListener(SensorInfoPanel sensorInfoPanel) {
		this.sensorInfoPanel = sensorInfoPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == sensorInfoPanel.getSendReparatorButton()) {
				Message message = new Message(sensorInfoPanel.getSensor().getSensorConfigurationId(), sensorInfoPanel.getSensor().getMinDangerThreshold());
				String serializedObject = JsonUtil.serializeObject(message, message.getClass(), "");
				String jsonRequest = JsonUtil.serializeRequest(RequestType.INSERT, message.getClass(), serializedObject, null, null,null, RequestSender.SENSOR);
				MonitrackGuiUtil.sendRequest(jsonRequest);
				sensorInfoPanel.getSendReparatorButton().setEnabled(false);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
