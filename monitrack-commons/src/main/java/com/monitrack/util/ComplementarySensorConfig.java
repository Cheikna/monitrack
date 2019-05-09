package com.monitrack.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.javatuples.Pair;

import com.monitrack.enumeration.SensorType;

public class ComplementarySensorConfig {

	private Map<SensorType, List<SensorType>> sensorsTypeCombinaisons;
	private Map<Integer, Pair<String, String>> messagesByComplementarySensorsType;
	private Map<Integer, Integer> currentDangerBountyByLocation;
	private Set<Integer> codesForServer;
	private Set<Integer> codesForClient;
	private int finalCode;

	public ComplementarySensorConfig() {
		messagesByComplementarySensorsType = Collections.synchronizedMap(new HashMap<Integer, Pair<String, String>>());
		sensorsTypeCombinaisons = Collections.synchronizedMap(new HashMap<SensorType, List<SensorType>>());
		currentDangerBountyByLocation = new HashMap<>();
		codesForServer = Collections.synchronizedSet(new TreeSet<Integer>());
		codesForClient = Collections.synchronizedSet(new TreeSet<Integer>());
		finalCode = 1;
		loadCodesMessages();
		loadSensorsTypeCombinaisons();
		
	}
	
	private void loadCodesMessages() {
		// Smoke sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode()
				* SensorType.FLOW.getDangerCode() * SensorType.TEMPERATURE.getDangerCode(), 
				new Pair<String, String>("The smoke sensor detects an danger alert. Futhermore the door is open, there are people and the temperature is raising. Call the fireman !",
						""));
		
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode(), 
				new Pair<String, String>("There are smoke and a door is open. The smoke will spread !",""));
		
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode()	* SensorType.FLOW.getDangerCode(), 
				new Pair<String, String>("There are smoke and people. Be fast !",""));

		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.TEMPERATURE.getDangerCode(), 
				new Pair<String, String>("There are smoke and the temperature is hight. Call the fireman !",""));
		
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode()
				* SensorType.FLOW.getDangerCode(), 
				new Pair<String, String>("There are smoke and people in the room !",""));

		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode()
				* SensorType.TEMPERATURE.getDangerCode(), 
				new Pair<String, String>("The smoke sensor detects a danger alert. Futhermore there are people and the temperature is raising. Call the fireman !",""));

		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode()	* SensorType.FLOW.getDangerCode() 
				* SensorType.TEMPERATURE.getDangerCode(), 
				new Pair<String, String>("There are smoke, people and the temperature is high. Call the fireman and bring a doctor !",""));
		
		//Temperature sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.TEMPERATURE.getDangerCode() * SensorType.WINDOW.getDangerCode(), 
				new Pair<String, String>("The temperature is not normal and the windoww is open.",""));
		
		//Light sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.LIGHT.getDangerCode() * SensorType.FLOW.getNormalCode(), 
				new Pair<String, String>("A light is on. However, there is no one in this room",
						"La lumière est allumé. Mais il n'y a personne dans la salle"));
		
		//Gas sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.GAS.getDangerCode() * SensorType.LIGHT.getDangerCode(),
				new Pair<String, String>("There are some harmful gas in the room and the light is on. An explosion can happen",""));
		/*messagesByComplementarySensorsType.put(SensorType.GAS.getDangerCode() * SensorType.FLOW.getDangerCode(),
				new Pair<String, String>("There are some harmful gas in the room and there are people sleeping. They are in danger !",""));*/
		messagesByComplementarySensorsType.put(SensorType.GAS.getDangerCode() * SensorType.LIGHT.getDangerCode() 
				* SensorType.FLOW.getDangerCode(),
				new Pair<String, String>("There are some harmful gas in the room, people and the light is on. They are in danger !",""));
		
	}

	private void loadSensorsTypeCombinaisons() {
		sensorsTypeCombinaisons.put(SensorType.SMOKE, Arrays.asList(SensorType.FLOOD, SensorType.DOOR, SensorType.TEMPERATURE));
		sensorsTypeCombinaisons.put(SensorType.TEMPERATURE, Arrays.asList(SensorType.WINDOW, SensorType.SMOKE));
		sensorsTypeCombinaisons.put(SensorType.GAS, Arrays.asList(SensorType.LIGHT, SensorType.FLOW));
		sensorsTypeCombinaisons.put(SensorType.LIGHT, Arrays.asList(SensorType.FLOW));		
		sensorsTypeCombinaisons.put(SensorType.FLOOD, Arrays.asList(SensorType.FLOW));	
		sensorsTypeCombinaisons.put(SensorType.WINDOW, Arrays.asList(SensorType.FLOW));
	}
	
	public String getMessage() {
		Pair<String, String> pair = messagesByComplementarySensorsType.get(finalCode);
		String message = "";
		if(pair != null) {
			message = pair.getValue0();
		}
		finalCode = 1;
		codesForServer.clear();		
		return message;
	}
	
	public void addCodeType(int code) {
		boolean added = codesForServer.add(code);
		if(added) {
			finalCode *= code;
		}
	}
	
	public Boolean isComplementary(SensorType alertLauncher, SensorType complementary) {
		List<SensorType> types = sensorsTypeCombinaisons.get(alertLauncher);
		if(types == null)
			return false;
		return types.contains(complementary);
	}
	
	
	/*********** For the client ********************/
	
	public void resetDangerBountyByLocation() {
		currentDangerBountyByLocation.clear();
		codesForClient.clear();
	}
	
	public void addClientCode(int locationId, int code) {
		Integer bounty = currentDangerBountyByLocation.get(locationId);
		if(bounty == null) {
			currentDangerBountyByLocation.put(locationId, code);
		} else {
			boolean added = codesForClient.add(code);
			if(added) {
				int newBounty = bounty * code;
				currentDangerBountyByLocation.put(locationId, newBounty);				
			}
		}
	}
	
	
	public String getAllMessagesForClient() {
		String result = "";
		for(Map.Entry<Integer, Integer> entry : currentDangerBountyByLocation.entrySet()) {
			result += "\n===>Emplacement n°" + entry.getKey();
			int bounty = entry.getValue();
			// Searchs for all multiple
			for(Map.Entry<Integer, Pair<String, String>> entry2 : messagesByComplementarySensorsType.entrySet()) {
				int code = entry2.getKey();
				if(bounty % code == 0) {
					result +="\n   #" + entry2.getValue().getValue0();
				}				 
			}
			result += "\n";
			
		}		
		return result;
	}
}
