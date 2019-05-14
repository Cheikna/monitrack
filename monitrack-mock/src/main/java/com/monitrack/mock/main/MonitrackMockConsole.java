package com.monitrack.mock.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.comparator.SensorByLocationIdComparator;
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
import com.monitrack.util.Util;

public class MonitrackMockConsole {

	private static final Logger log = LoggerFactory.getLogger(MonitrackMockConsole.class);
	private final String alignFormat = "|%-6d| %-15s | %-13s |%-17s|%-17s|%n";
	private final String horizontalBorder      = "+------+-----------------+---------------+-----------------+-----------------+%n";
	private final String header			 	   = "|  ID  |       TYPE      |  LOCATION ID  |     ACTIVITY    | SENDING MESSAGE |%n";

	private Map<Integer, SensorSignal> sensorSignalMap;
	private List<SensorConfiguration> sensors;
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
						Thread.sleep(5000);
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

		showAllSensors();
		System.out.println("\nWhat do you want to do ?");
		System.out.println("1. Generate only active sensors");
		System.out.println("2. Generate not configured sensors");
		System.out.println("3. Enable all sensors (not available)");
		System.out.println("4. Send normal message");
		System.out.println("5. Send danger message");
		System.out.println("6. Send danger message followed by normal message");
		System.out.println("7. Stop sending message");
		System.out.println("8. Send message for a specific location");
		System.out.println("9. Send incorrect passwords to an access control sensor");
		choice = chooseAction(1, 9);

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
			startStopSendingMessage(true, true);
		} else if(choice == 5) {
			startStopSendingMessage(true, false);
		} else if(choice == 6) {
			System.out.print("Enter the id of the sensor : ");
			int sensorId = NumberUtils.toInt(sc.nextLine(), defaultNumber);
			SensorConfiguration sensor = findSensorById(sensorId);
			if(sensor == null) {
				System.out.println("The sensor does not exist");
			}
			else {
				int maxDangerMessage = SensorSensitivity.getNumberOfMessages(sensor.getSensorSensitivity());
				Integer rdn = (int) (Math.random() * 2);
				int dangerMessageToSend = maxDangerMessage - rdn;
				float thresholdMax = sensor.getMaxDangerThreshold();
				for(int i = 0; i < dangerMessageToSend; i++) {
					sendMessage(new Message(sensorId, thresholdMax + rdn.floatValue()));					
				}
				SensorSignal signal = new SensorSignal(sensor.getSensorConfigurationId(), 
						sensor.getMinDangerThreshold(),
						sensor.getMaxDangerThreshold(),
						sensor.getCheckFrequency(), true);
				setSignal(signal, null, null);
			}
		} else if(choice == 7) {
			startStopSendingMessage(false, null);
		} else if(choice == 8) {
			sendMessageForSensorsOfALocation();
		} else if(choice == 8) {
			sendIncorrectPassword();
		}
		displayMainMenu();
	}

	private void generateLocations() {
		locations = MonitrackListener.generateRandomLocations();
		currentLocationId = 0;
		numberOfLocations = locations.size();
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

	private void startStopSendingMessage(boolean sendSignal, Boolean isNormalMessage) {
		//Show all sensors who are sending message
		String action = "";
		if(sendSignal) {			
			action = "Enter the id of the sensor which will send message (or enter 'all' to select all of them) :";
		}
		else {
			action = "Enter the id of the sensor which will stop to send message (or enter 'all' to select all of them) :";			
		}
		System.out.print(action);
		String choice = sc.nextLine();
		if(choice.equalsIgnoreCase("all")) {
			for(Map.Entry<Integer, SensorSignal> entry : sensorSignalMap.entrySet()) {
				entry.getValue().setSendMessage(sendSignal);
			}
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

			if(correct) {
				String action2 = "Enter the threshold : ";
				float minThreshold = sensor.getMinDangerThreshold();
				if(sensor.getSensorType() == SensorType.ACCESS_CONTROL) {	
					isNormalMessage = null;
					action2 = "Enter the password to enter in the room : ";
					do {
						System.out.print(action2);
						minThreshold = NumberUtils.toFloat(sc.nextLine(), defaultNumber);	
					}while(minThreshold == defaultNumber);
				}


				SensorSignal signal = new SensorSignal(sensor.getSensorConfigurationId(), 
						minThreshold,
						sensor.getMaxDangerThreshold(),
						sensor.getCheckFrequency(), isNormalMessage);
				signal.setSendMessage(sendSignal);
				setSignal(signal, sensor.getSensorType(), minThreshold);
			}
		}

	}

	private void sendIncorrectPassword() {
		System.out.print("Enter the ID of a control access sensor : ");
		int id = NumberUtils.toInt(sc.nextLine(), defaultNumber);
		SensorConfiguration sensor = findSensorById(id);
		if(sensor != null && sensor.getSensorType() == SensorType.ACCESS_CONTROL) {
			int maxTries = SensorSensitivity.getNumberOfMessages(sensor.getSensorSensitivity()) + 2;
			for(int i = 0; i < maxTries; i++) {
				sendMessage(new Message(id, 999999f));
			}
		}
		else {
			System.out.println("The sensor does not exist or it is not an access control sensor");
		}
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
		}
		else {	
			sensorSignalMap.put(id, newSignal);
			Thread thread = new Thread(newSignal);
			thread.start();
		}	
	}

	private void sendMessageForSensorsOfALocation() {
		System.out.println();
		List<Location> locations = getLocations();
		Util.displayListElements(locations, "");
		boolean isCorrect = true;
		Integer locationId = null;
		do {
			isCorrect = true;
			System.out.print("Enter the id of the location to send danger message [exit] : ");
			String str = sc.nextLine();
			if(!str.equalsIgnoreCase("exit")) {
				locationId = NumberUtils.toInt(str, defaultNumber);
				if(locationId == defaultNumber)
					isCorrect = false;
				else {
					boolean isIdExists = false;
					for(Location loc : locations) {
						if(loc.getIdLocation() == locationId) {
							isIdExists = true;
							break;
						}
					}

					if(!isIdExists) {
						System.out.println("The location with the id n°" + locationId + " does not exist");
						isCorrect = false;
					}
					else {
						System.out.println("1. Send danger message");
						System.out.println("2. Send normal message");
						choice = chooseAction(1, 2);
						SensorSignal signal = null;
						Boolean isNormalMessage = null;
						if(choice == 1) {
							isNormalMessage = false;
						} else if(choice == 2) {
							isNormalMessage = true;
						}
						for(SensorConfiguration sensor : sensors) {
							if(sensor.getLocationId() == locationId && sensor.getSensorType() != SensorType.ACCESS_CONTROL) {
								signal = new SensorSignal(sensor.getSensorConfigurationId(), 
										sensor.getMinDangerThreshold(),
										sensor.getMaxDangerThreshold(),
										sensor.getCheckFrequency(), isNormalMessage);
								setSignal(signal, null, null);
							}
						}
					}

				}
			}

		}while(!isCorrect);
	}


	private SensorConfiguration findSensorById(int id) {
		for(SensorConfiguration sensor : sensors) {
			if(sensor.getSensorConfigurationId() == id)
				return sensor;
		}
		return null;
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
				System.out.println("10 different sensors will be generated for the location n°"+locationId);
				
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

	@SuppressWarnings("unchecked")
	private List<Location> getLocations(){
		try {
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, Location.class, null, null, null, null, RequestSender.CLIENT);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			return (List<Location>)JsonUtil.deserializeObject(response);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;

	}

}
