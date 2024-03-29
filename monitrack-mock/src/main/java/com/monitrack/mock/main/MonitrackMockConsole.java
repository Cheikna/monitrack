package com.monitrack.mock.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Location;
import com.monitrack.entity.Message;
import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.ConnectionState;
import com.monitrack.enumeration.RequestSender;
import com.monitrack.enumeration.RequestType;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorSensitivity;
import com.monitrack.enumeration.SensorType;
import com.monitrack.listener.MonitrackListener;
import com.monitrack.mock.runnable.SensorSignal;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.socket.client.ClientSocket;
import com.monitrack.util.JsonUtil;

public class MonitrackMockConsole {

	private static final Logger log = LoggerFactory.getLogger(MonitrackMockConsole.class);
	private Map<Integer, SensorSignal> sensorSignalMap;
	private List<Location> locations;
	private int currentLocationId;
	private int numberOfLocations;
	private Scanner sc;
	private final int defaultNumber = -912345566;
	private float choice = 0;

	public MonitrackMockConsole() {
		currentLocationId = 0;
		numberOfLocations = 0;
		sensorSignalMap = new HashMap<Integer, SensorSignal>();
		sc = new Scanner(System.in);
		System.out.println("Welcome to Monitrack mock");
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
		System.out.println("2: Manipulate sensors");
		System.out.println("3: Indent Json");
		choice = chooseAction(1, 3);		
		if(choice == 1) {
			generateLocations();
		}else if(choice == 2) {
			sensorManipulationMenu();
		}
		else if(choice == 3) {
			System.out.println("Enter the text in Json format below :");
			String json = sc.nextLine();
			try {
				System.out.println("Result :\n" + JsonUtil.indentJsonOutput(json));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		displayMainMenu();
	}



	private void sensorManipulationMenu() {

		System.out.println("\nWhat do you want to do ?");
		System.out.println("1. Generate only active sensors");
		System.out.println("2. Generate not configured sensors");
		System.out.println("3. Enable all sensors (not available)");
		System.out.println("4. Send message");
		System.out.println("5. Stop sending message");
		System.out.println("6. Send a password for an access control");
		choice = chooseAction(1, 6);

		if(choice == 1) {
			System.out.println("This operation may take a long time...");
			generateRandomSensorsConfiguration(true);
		} else if (choice == 2) {
			System.out.println("This operation may take a long time...");
			generateRandomSensorsConfiguration(false);
		} else if (choice == 3) {
			//System.out.println("This operation may take a long time...");
			//enableAllSensors();
		} else if(choice == 4) {
			sendAnonymously(true, false);
		} else if(choice == 5) {
			sendAnonymously(false, false);
		} else if(choice == 6) {
			System.out.print("Enter the id of the access control sensor : ");
			int id = NumberUtils.toInt(sc.nextLine(), defaultNumber);
			System.out.print("Enter the password of the access control sensor : ");
			Float code = NumberUtils.toFloat(sc.nextLine(), defaultNumber);
			if(id != defaultNumber && code != defaultNumber) {
				sendMessage(new Message(id, code));
			}
			else {
				System.out.println("The id of the code is not a number");
			}
			
		}
		displayMainMenu();
	}

	private void sendAnonymously(Boolean sendMessage, Boolean isOneShot) {
		System.out.print("Enter the id of the sensor : ");
		int sensorId = NumberUtils.toInt(sc.nextLine(), defaultNumber);
		if(sensorId != defaultNumber) {
			System.out.println("Enter the min threshold : ");
			float minThreshold = NumberUtils.toFloat(sc.nextLine(), defaultNumber);
			System.out.println("Enter the max threshold : ");
			float maxThreshold = NumberUtils.toFloat(sc.nextLine(), defaultNumber);
			System.out.println("Enter the check frequency in milliseconds : ");
			float frequency = NumberUtils.toFloat(sc.nextLine(), defaultNumber);
			if(minThreshold != defaultNumber && maxThreshold != defaultNumber && frequency != defaultNumber) {
				SensorSignal signal = new SensorSignal(sensorId, 
						minThreshold,
						maxThreshold,
						frequency, true);
				signal.setSendMessage(sendMessage);
				setSignal(signal, null, null);
			}
			else {
				System.out.println("A value is incorrect");
			}
		}
	}

	private void generateLocations() {
		locations = MonitrackListener.generateRandomLocations();
		currentLocationId = 0;
		numberOfLocations = locations.size();
	}
	
	private void setSignal(SensorSignal newSignal, SensorType type, Float code) {
		int id = newSignal.getSensorId();
		SensorSignal signal = null;
		if(type != null && type == SensorType.ACCESS_CONTROL && code != null) {
			sendMessage(new Message(id, code));
		}
		else if(sensorSignalMap.containsKey(id)){
			signal = sensorSignalMap.get(id);
			signal.clone(newSignal);
			sensorSignalMap.put(id, newSignal);
		}
		else {	
			sensorSignalMap.put(id, newSignal);
			Thread thread = new Thread(newSignal);
			thread.start();
		}	
	}

	private void generateRandomSensorsConfiguration(boolean isConfigured)
	{
		try 
		{
			String fileName = "sensors-configurations-configured.csv";
			if(!isConfigured)
				fileName = "sensors-configurations-not-configured.csv";
			InputStream inputStream = MonitrackMockMain.class.getClassLoader().getResourceAsStream(fileName);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			SensorConfiguration sensorConfiguration = null;
			//This will skip the first line which contains the headers
			String line = bufferedReader.readLine();
			/*System.out.print("Enter the number of sensor to generate : ");
			int number = NumberUtils.toInt(sc.nextLine(), 100);
			if(number > 100)
				number = 100;*/
			int i = 0;



			SensorType previousType = null;

			if(currentLocationId < numberOfLocations) {
				Integer locationId = locations.get(currentLocationId).getIdLocation();
				currentLocationId++;
				System.out.println("10 different sensors will be generated for the location n�"+locationId);
				
				if(isConfigured) {

					while ((line = bufferedReader.readLine()) != null && i < 11) 
					{
						String[] values = line.split(",");
						SensorType type = SensorType.valueOf(values[1]);
						if(type != previousType) {
							previousType = type;
							sensorConfiguration = new SensorConfiguration(0,0, SensorActivity.valueOf(values[0]), type,
									SensorSensitivity.valueOf(values[2]), locationId, values[4], values[5], values[6], 
									Float.parseFloat(values[7]), Float.parseFloat(values[8]),
									Timestamp.valueOf(values[9]), null,
									Time.valueOf(values[10]), Time.valueOf(values[11]), Float.valueOf(values[12]),
									values[13], Float.valueOf(values[14]),Float.valueOf(values[15]),
									Float.valueOf(values[16]), Float.valueOf(values[17]));

							String serializedObject = JsonUtil.serializeObject(sensorConfiguration, SensorConfiguration.class, "");
							String jsonRequest = JsonUtil.serializeRequest(RequestType.INSERT, SensorConfiguration.class, serializedObject, null, null, null, RequestSender.CLIENT);
							MonitrackGuiUtil.sendRequest(jsonRequest);
							i++;
						}
					} 
				}
				else {


					while ((line = bufferedReader.readLine()) != null && i < 11) 
					{
						String[] values = line.split(",");
						SensorType type = SensorType.valueOf(values[1]);
						if( type != previousType) {
							previousType = type;
							sensorConfiguration = new SensorConfiguration(0,0, SensorActivity.valueOf(values[0]), type,
									SensorSensitivity.valueOf(values[2]), locationId, values[4], values[5], values[6], 
									Float.parseFloat(values[7]), Float.parseFloat(values[8]),
									Timestamp.valueOf(values[9]), null,
									null, null, null,
									values[13], 0f ,0f,
									Float.valueOf(values[16]), Float.valueOf(values[17]));

							String serializedObject = JsonUtil.serializeObject(sensorConfiguration, SensorConfiguration.class, "");
							String jsonRequest = JsonUtil.serializeRequest(RequestType.INSERT, SensorConfiguration.class, serializedObject, null, null, null, RequestSender.CLIENT);
							MonitrackGuiUtil.sendRequest(jsonRequest);
							i++;
						}
					} 
				}
			}
			else {
				System.out.println("You have to generate some locations");
			}

			inputStream.close();
		} 

		catch (Exception e) 
		{
			log.error(e.getMessage());
		}
	}

	public static boolean sendMessage(Message message) {
		try {
			ClientSocket clientSocket = new ClientSocket();
			ConnectionState connectionState = clientSocket.start();
			if(connectionState == ConnectionState.SUCCESS) {
				String serializedObject = JsonUtil.serializeObject(message, message.getClass(), "");
				//In this case we do not care about the request type because the RequestHandler will see that it comes from a sensor
				String jsonRequest = JsonUtil.serializeRequest(RequestType.INSERT, message.getClass(), serializedObject, null, null,null, RequestSender.SENSOR);
				clientSocket.sendRequestToServer(jsonRequest);		
				return true;
			}
			else {
				log.error("An error occurred during the connection with the server. Perhaps the server is off.");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;

	}

}
