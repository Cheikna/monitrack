package com.monitrack.mock.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import com.monitrack.comparator.SensorByLocationIdComparator;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.mock.runnable.SensorSignal;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class MonitrackMockConsole {
	
	private final String alignFormat = "|%-6d| %-15s | %-13s |%-17s|%-17s|%n";
	private final String horizontalBorder      = "+------+-----------------+---------------+-----------------+-----------------+%n";
	private final String header			 	   = "|  ID  |       TYPE      |  LOCATION ID  |     ACTIVITY    | SENDING MESSAGE |%n";

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
		String jsonRequest = JsonUtil.serializeSensorsFromCacheRequest(RequestSender.CLIENT_FOR_ACTIVE_SENSOR);
		Thread sensorListUpdater = new Thread(new Runnable() {			
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				while(true) {
					try {
						sensors = (List<SensorConfiguration>)JsonUtil.deserializeObject(MonitrackGuiUtil.sendRequest(jsonRequest));
						Thread.sleep(10000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		sensorListUpdater.start();

		displayMainMenu();
		
	}
	
	private int chooseAction(float minIncluded, float maxIncluded) {
		int choice = defaultNumber;
		boolean isChoiceCorrect = true;do {
			isChoiceCorrect = true;
			System.out.print("Enter your choice : ");
			choice = NumberUtils.toInt(sc.nextLine(), defaultNumber);
			if(choice == defaultNumber || choice < minIncluded || choice > maxIncluded) {
				System.out.println("The action choosen is incorrect");
				isChoiceCorrect = false;
			}
			
		} while(!isChoiceCorrect);
		return choice;
	}
	
	private void displayMainMenu() {
		System.out.println("\nMenu - Choose an action :");
		System.out.println("1: Generate location");
		System.out.println("2: Generate some person for the Nurse house");
		System.out.println("3: Manipulate sensors");
		System.out.println("4: Indent Json");
		choice = chooseAction(1, 4);		
		if(choice == 1) {
			generateLocations();
		}
		else if(choice == 2) {
			generatePersons();
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
	
	
	
	private void sensorManipulationMenu() {

		showAllSensors();
		System.out.println("\nWhat do you want to do ?");
		System.out.println("1. Generate only active sensors");
		System.out.println("2. Generate not configured sensors");
		System.out.println("3. Generate sensors randomly");
		System.out.println("4. Enable all sensors");
		System.out.println("5. Start sending message");
		System.out.println("6. Update the threshold sent for sensors");
		System.out.println("7. Stop sending message");
		choice = chooseAction(1, 7);
		
		if(choice == 1) {
			System.out.println("This operation may take a long time...");
			generateSensors(true);
		} else if (choice == 2) {
			System.out.println("This operation may take a long time...");
			generateSensors(false);
		} else if (choice == 3) {
			System.out.println("This operation may take a long time...");
			generateSensors(null);
		} else if (choice == 4) {
			System.out.println("This operation may take a long time...");
			enableAllSensors();
		} else if(choice == 5) {
			startStopSendingMessage(true);
		} else if (choice == 6) {
			updateThresholdSent();
		} else if(choice == 7) {
			startStopSendingMessage(false);
		}
		displayMainMenu();
	}
	
	private void generatePersons() {
		
		//FIXME Show the persons inserted
	}
	
	private void generateLocations() {
		
		//FIXME Show the location inserted
	}
	
	private void generateSensors(Boolean configured) {
		if(configured == null) {
			//FIXME generate random sensor
		}
		else if(configured) {
			// FIXME generate only configured sensor
		}
		else {
			//FIXME generate not configured sensors
		}
	}
	
	private void enableAllSensors() {
		//FIXME
	}
	
	private void showAllSensors() {
		
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
	
	private boolean isSendingMessage(int sensorId) {
		SensorSignal signal = sensorSignalMap.get(sensorId);
		if(signal == null)
			return false;
		return signal.isSendMessage();
	}
	
	private void startStopSendingMessage(boolean sendSignal) {
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
	
	private void updateThresholdSent() {
		System.out.println("\nModify the threshold :");
		System.out.println("1. Set the threshold with predefine values");
		System.out.println("2. Choose your own values");
		int choice = chooseAction(1, 2);
		if(choice == 1) {
			//FIXME
		} else if(choice == 2) {
			boolean isCorrect = true;
			int id = 0;
			float threshold = 0f;
			do {
				isCorrect = true;
				System.out.print("Enter the id of the sensor : ");
				id = NumberUtils.toInt(sc.nextLine(), defaultNumber);
				if(id == defaultNumber) {
					System.out.println("The id entered is incorrect !");
					isCorrect = false;
				}
				else {
					System.out.print("Enter your threshold : ");
					threshold = NumberUtils.toFloat(sc.nextLine(), defaultNumber);
					if(threshold == defaultNumber) {
						System.out.println("The threshold is incorrect !");
						isCorrect = false;
					}					
				}
				
				
			} while(!isCorrect);			
			
			setSignal(id, threshold);
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
	
	private void setSignal(int id, float threshold) {
		if(sensorSignalMap.containsKey(id)){
			sensorSignalMap.get(id).setThresholdReached(threshold);
		}
		else {
			System.out.println("The sensor with the id n°" + id + " must be in sending message mode before changing this value");
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
