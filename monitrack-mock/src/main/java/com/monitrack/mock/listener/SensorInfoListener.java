package com.monitrack.mock.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
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
		if(e.getSource() instanceof JMenuItem) {
			JMenuItem selectedItem = (JMenuItem)e.getSource();
			if(selectedItem == sensorInfoPanel.getAddPerson())
			{
				numberOfPersonInRoom++;
			}
			else if(selectedItem == sensorInfoPanel.getRemovePerson())
			{
				numberOfPersonInRoom = (numberOfPersonInRoom > 0) ? numberOfPersonInRoom-=1 : 0; 
			}
			sensorInfoPanel.getSensor().setCurrentThreshold(numberOfPersonInRoom.floatValue());
		}
		
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
		if(SwingUtilities.isRightMouseButton(e)) {
			sensorInfoPanel.getPopup().show(sensorInfoPanel, e.getX(), e.getY());
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
