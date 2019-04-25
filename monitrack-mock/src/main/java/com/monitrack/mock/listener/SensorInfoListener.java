package com.monitrack.mock.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import org.apache.commons.lang3.math.NumberUtils;

import com.monitrack.mock.panel.SensorInfoPanel;
import com.monitrack.mock.runnable.SensorSignal;

public class SensorInfoListener implements MouseListener, ActionListener  {
	
	private SensorInfoPanel sensorInfoPanel;
	private SensorSignal runnable;
	private Thread thread;
	private Integer numberOfPersonInRoom;

	
	public SensorInfoListener(SensorInfoPanel sensorInfoPanel) {
		this.sensorInfoPanel = sensorInfoPanel;
		numberOfPersonInRoom = 0;
		runnable = new SensorSignal(sensorInfoPanel.getSensor());
		thread = new Thread(runnable);
		thread.start();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sensorInfoPanel.getAddPerson())
		{
			numberOfPersonInRoom++;
		}
		if(e.getSource() == sensorInfoPanel.getRemovePerson())
		{
			numberOfPersonInRoom = (numberOfPersonInRoom > 0) ? numberOfPersonInRoom-- : 0; 
		}
		sensorInfoPanel.getSensor().setCurrentThreshold(numberOfPersonInRoom.floatValue());
		
		if(e.getSource() == sensorInfoPanel.getSendReparator())
		{
			runnable.isInSendingWarningMessageMode(false);
			sensorInfoPanel.getCenterPanel().setBackground(Color.GREEN);
			
		}
		else {
			runnable.setSendMessage(true);
			runnable.isInSendingWarningMessageMode(true);
			sensorInfoPanel.getCenterPanel().setBackground(Color.ORANGE);			
		}
		sensorInfoPanel.updateUI();
		sensorInfoPanel.revalidate();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(sensorInfoPanel.getId());
		if(SwingUtilities.isRightMouseButton(e)) {
			sensorInfoPanel.getPopup().show(sensorInfoPanel, e.getX(), e.getY());
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*float threshold = 0f;
		Sensor sensor = new Sensor(0, SensorActivity.DISABLED, SensorType.FLOW, 1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel", 4.0f,0.0f, 5.0f, 6.23f, 4.94f);
		
		try {
			ClientSocket cl = new ClientSocket();
			
			for (int i = 0; i < 10; i++) {
				sensor.setCurrentThreshold(threshold);
				Message message = new Message(SensorState.DANGER, sensor);
				String serializedObject = JsonUtil.serializeObject(message, Message.class, "");
				String jsonRequest = JsonUtil.serializeRequest(RequestType.INSERT, Message.class, serializedObject,
						null, null, RequestSender.SENSOR);
				if (cl.start() == ConnectionState.SUCCESS) {
					cl.sendRequestToServer(jsonRequest);
				}
				System.out.println("request sended");
				threshold += 1f;
			}
		} catch (Exception e1) {
			// FIXME
			e1.printStackTrace();
		} */
	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
