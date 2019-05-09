package com.monitrack.mock.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import com.monitrack.comparator.SensorByLocationIdComparator;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorSensitivity;
import com.monitrack.enumeration.SensorType;
import com.monitrack.mock.runnable.SensorSignal;
import com.monitrack.util.JsonUtil;

public class MonitrackMockConsole {
	
	private final String alignFormat = "|%-6d| %-9s | %-13s |%-17s|%-17s|%n";
	private final String horizontalBorder      = "+------+-----------+---------------+-----------------+-----------------+%n";
	private final String header			 	   = "|  ID  |    TYPE   |  LOCATION ID  |     ACTIVITY    | SENDING MESSAGE |%n";

	private Map<Integer, SensorSignal> sensorSignalMap;
	private List<SensorConfiguration> sensors;
	private Scanner sc;
	private final int defaultNumber = -912345566;
	private float choice = 0;
	
	public MonitrackMockConsole() {
		sensorSignalMap = new HashMap<Integer, SensorSignal>();
		sensors = new ArrayList<SensorConfiguration>();
		sc = new Scanner(System.in);
		System.out.println("Welcome to Monitrack mock");
		displayMainMenu();
		
	}
	
	public float chooseAction(float minIncluded, float maxIncluded) {
		float choice = defaultNumber;
		boolean isChoiceCorrect = true;do {
			isChoiceCorrect = true;
			System.out.print("Enter your choice : ");
			choice = NumberUtils.toFloat(sc.nextLine(), defaultNumber);
			if(choice == defaultNumber || choice < minIncluded || choice > maxIncluded) {
				System.out.println("The action choosen is incorrect");
				isChoiceCorrect = false;
			}
			
		} while(!isChoiceCorrect);
		return choice;
	}
	
	public void displayMainMenu() {
		System.out.println("\nMenu - Choose an action :");
		System.out.println("1: Generate location");
		System.out.println("2: Generate some person for the Nurse house");
		System.out.println("3: Manipulate sensors");
		System.out.println("4: Indent Json");
		choice = chooseAction(1, 4);		
		if(choice == 1) {
			
		}
		else if(choice == 2) {
			
		} else if(choice == 3) {
			sensorManipulationMenu();
		}
		else if(choice == 4) {
			System.out.println("Enter the text in Json format below :");
			String json = sc.nextLine();
			try {
				System.out.println("Result :\n" + JsonUtil.indentJsonOutput(json));
			} catch(Exception e) {
				e.printStackTrace();
			}
			displayMainMenu();
		}
	}
	
	
	
	public void sensorManipulationMenu() {
		System.out.println("\nWhat do you want to do ?");
		System.out.println("1. Generate only active sensors");
		System.out.println("2. Generate only unactive sensors");
		System.out.println("3. Generate not configured sensors");
		System.out.println("4. Generate sensors randomly");
		System.out.println("5. Enable all sensors");
		System.out.println("6. Disable all sensors");
		System.out.println("7. Start sending message");
		System.out.println("8. Stop sending message");
		System.out.println("9. Show all sensors");
		System.out.println("10. Back to main menu");
		choice = chooseAction(1, 10);		
		if(choice == 1) {
			
		} else if (choice == 5) {
			showAllSensors();
			// Propose which sensor to select
		} else if (choice == 6) {
			showAllSensors();
		} else if(choice == 7) {
			showAllSensors();
			startStopSendingMessage(true);
		}else if(choice == 8) {
			showAllSensors();
			startStopSendingMessage(false);
		}
		displayMainMenu();
	}
	
	public void generatePersons() {
		
		//FIXME Show the persons inserted
	}
	public void generateLocations() {
		
		//FIXME Show the location inserted
	}
	
	public void showAllSensors() {
		SensorType[] types = SensorType.values();
		SensorConfiguration s1 = new SensorConfiguration(0,0, SensorActivity.ENABLED, SensorType.FLOW, SensorSensitivity.HIGH,1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel",  0.0f, 5.0f, 6.23f, 4.94f);
		SensorConfiguration s2 = new SensorConfiguration(0,0, SensorActivity.ENABLED, SensorType.FLOW, SensorSensitivity.HIGH,2, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel", 0.0f, 5.0f, 6.23f, 4.94f);
		SensorConfiguration s3 = new SensorConfiguration(0,0, SensorActivity.ENABLED, SensorType.FLOW, SensorSensitivity.HIGH,3, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel",  0.0f, 5.0f, 6.23f, 4.94f);
		SensorConfiguration s4 = new SensorConfiguration(0,0, SensorActivity.NOT_CONFIGURED, SensorType.FLOW, SensorSensitivity.HIGH,1, "192.168.20.15", "dsfsd", "dsfsdf", 
				1.0f, 2.0f, null, null, null, null, null, 0f, "Decibel",  0.0f, 5.0f, 6.23f, 4.94f);
		sensors = Arrays.asList(s1, s2, s3, s4);
		Collections.sort(sensors, new SensorByLocationIdComparator());
		System.out.println("List of the sensors :");
		System.out.format(horizontalBorder);
		System.out.format(header);
		System.out.format(horizontalBorder);
		boolean sendingMessage = false;
		for(SensorConfiguration sensor : sensors) {
			int id = sensor.getSensorConfigurationId();
			sendingMessage = isSendingMessage(id);
			System.out.format(alignFormat, id, sensor.getSensorType(), sensor.getLocationId(),sensor.getSensorActivity(), sendingMessage);
		}
		System.out.format(horizontalBorder);
	}
	
	public boolean isSendingMessage(int sensorId) {
		SensorSignal signal = sensorSignalMap.get(sensorId);
		if(signal == null)
			return false;
		return signal.isSendMessage();
	}
	
	public void startStopSendingMessage(boolean sendSignal) {
		//Show all sensors who are sending message
		String action = "";
		if(sendSignal) {			
			action = "Enter the id of the sensor which will start to send message (or enter 'all' to select all of them) :";
		}
		else {
			action = "Enter the id of the sensor which will stop to send message (or enter 'all' to select all of them) :";			
		}
		System.out.print(action);
		String choice = sc.nextLine();
		if(choice.equalsIgnoreCase("all")) {
			for(SensorConfiguration sensor : sensors) 
				setSignal(sensor, sendSignal, sensor.getMinDangerThreshold());
		}
		else {
			SensorConfiguration sensor = null;
			boolean correct = true;
			do {
				int id = NumberUtils.toInt(choice, defaultNumber);
				if(id == defaultNumber) {
					System.out.println("You did not write a correct number");
					correct = false;
				}
				else if((sensor =  findSensorById(id)) == null) {
					System.out.println("The sensor with the id n°" + id + " does not exist");
					correct = false;
				}
				else
					correct = true;
				if(!correct) {
					System.out.print("Enter the id [exit] : ");
					choice = sc.nextLine();
				}
				
			} while(!correct && !choice.equalsIgnoreCase("exit"));
			
			if(correct)
				setSignal(sensor, sendSignal, sensor.getMinDangerThreshold());
		}
			
	}
	
	private void setSignal(SensorConfiguration sensor, boolean sendSignal, float threshold) {
		int id = sensor.getSensorConfigurationId();
		SensorSignal signal = null;
		if(sensorSignalMap.containsKey(id)){
			signal = sensorSignalMap.get(id);
			signal.setThresholdReached(threshold);
			signal.setSendMessage(sendSignal);
		}
		else {
			signal = new SensorSignal(id, threshold, sensor.getCheckFrequency().longValue());	
			signal.setSendMessage(sendSignal);		
			sensorSignalMap.put(id, signal);
			Thread thread = new Thread(signal);
			thread.start();
		}	
	}
	
	private SensorConfiguration findSensorById(int id) {
		for(SensorConfiguration sensor : sensors) {
			if(sensor.getSensorConfigurationId() == id)
				return sensor;
		}
		return null;
	}

}
