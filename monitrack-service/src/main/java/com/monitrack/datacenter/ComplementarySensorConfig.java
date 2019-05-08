package com.monitrack.datacenter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import com.monitrack.enumeration.SensorType;

public class ComplementarySensorConfig {

	private Map<SensorType, List<SensorType>> sensorsTypeCombinaisons;
	private Map<Integer, String> messagesByComplementarySensorsType;
	private Set<Integer> codes;
	private int finalCode;

	public ComplementarySensorConfig() {
		messagesByComplementarySensorsType = Collections.synchronizedMap(new HashMap<Integer, String>());
		sensorsTypeCombinaisons = Collections.synchronizedMap(new HashMap<SensorType, List<SensorType>>());
		codes = Collections.synchronizedSet(new TreeSet<Integer>());
		finalCode = 1;
		loadCodesMessages();
		loadSensorsTypeCombinaisons();
	}
	
	private void loadCodesMessages() {
		// Smoke sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode()
				* SensorType.FLOW.getDangerCode() * SensorType.TEMPERATURE.getDangerCode(), 
				"The smoke sensor detects an danger alert. Futhermore the door is open, there are people and the temperature is raising. Call the fireman !");
		
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode(), 
				"There are smoke and a door is open. The smoke will spread !");
		
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode()	* SensorType.FLOW.getDangerCode(), 
				"There are smoke and people. Be fast !");

		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.TEMPERATURE.getDangerCode(), 
				"There are smoke and the temperature is hight. Call the fireman !");
		
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode()
				* SensorType.FLOW.getDangerCode(), 
				"There are smoke and people in the room !");

		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode()
				* SensorType.TEMPERATURE.getDangerCode(), 
				"The smoke sensor detects a danger alert. Futhermore there are people and the temperature is raising. Call the fireman !");

		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode()	* SensorType.FLOW.getDangerCode() 
				* SensorType.TEMPERATURE.getDangerCode(), 
				"There are smoke, people and the temperature is high. Call the fireman and bring a doctor !");
		
		//Temperature sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.TEMPERATURE.getDangerCode() * SensorType.WINDOW.getDangerCode(), 
				"The temperature is not normal and the windoww is open.");
		
		//Light sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.LIGHT.getDangerCode() * SensorType.FLOW.getNormalCode(), 
				"A light is on. However, there is no one in this room");
		
		//Gas sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.GAS.getDangerCode() * SensorType.LIGHT.getDangerCode(),
				"There are some harmful gas in the room and the light is on. An explosion can happen");
		messagesByComplementarySensorsType.put(SensorType.GAS.getDangerCode() * SensorType.FLOW.getDangerCode(),
				"There are some harmful gas in the room and there are people sleeping. They are in danger !");
		messagesByComplementarySensorsType.put(SensorType.GAS.getDangerCode() * SensorType.LIGHT.getDangerCode() 
				* SensorType.FLOW.getDangerCode(),
				"There are some harmful gas in the room, people and the light is on. They are in danger !");
		
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
		String message =  messagesByComplementarySensorsType.get(finalCode);
		finalCode = 1;
		codes.clear();		
		return message;
	}
	
	public void addCodeType(int code) {
		boolean added = codes.add(code);
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
}
