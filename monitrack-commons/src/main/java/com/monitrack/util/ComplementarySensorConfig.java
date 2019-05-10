package com.monitrack.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.javatuples.Pair;

import com.monitrack.enumeration.SensorType;

/**
 * Cette classe permettra de connaitre l'état d'un pièce et ainsi d'avoir
 * des messages pour les capteurs qui s'y trouvent et fonctionne donc comme un moyen
 * de communication entre les capteurs
 */
public class ComplementarySensorConfig {

	private Map<Integer, Pair<String, String>> messagesByComplementarySensorsType;
	private Map<Integer, Integer> currentDangerBountyByLocation;
	private Set<Integer> codesForServer;
	private Set<Integer> codesForClient;
	private int finalCode;

	public ComplementarySensorConfig() {
		messagesByComplementarySensorsType = Collections.synchronizedMap(new HashMap<Integer, Pair<String, String>>());
		currentDangerBountyByLocation = new HashMap<>();
		codesForServer = Collections.synchronizedSet(new TreeSet<Integer>());
		codesForClient = Collections.synchronizedSet(new TreeSet<Integer>());
		finalCode = 1;
		loadCodesMessages();
		
	}
	
	private void loadCodesMessages() {
		// Smoke sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode()	* SensorType.FLOW.getDangerCode(), 
				new Pair<String, String>("There are smoke and people. Be fast !",
						"Il y a de la fumée et des personnes. Intervenez rapidement"));
		
		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.DOOR.getDangerCode(), 
				new Pair<String, String>("There are smoke and a door is open. The smoke will spread !",
						"Il y a de la fumée et le porte est ouverte. La fumée risque de se propager rapidement !"));		

		messagesByComplementarySensorsType.put(SensorType.SMOKE.getDangerCode() * SensorType.TEMPERATURE.getDangerCode(), 
				new Pair<String, String>("There are smoke and the temperature is high. Call the fireman !",
						"Il y a de la fummée et le température est élevée. Appelez les pompiers !"));
		
		//Temperature sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.TEMPERATURE.getDangerCode() * SensorType.WINDOW.getDangerCode(), 
				new Pair<String, String>("The temperature is not normal and the window is open.",
						"La température n'est pas corecte. Ceci est surement dû à la fenêtre ouverte !"));
		
		//Light sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.LIGHT.getDangerCode() * SensorType.FLOW.getNormalCode(), 
				new Pair<String, String>("A light is on. However, there is no one in this room",
						"La lumière est allumée. Mais il n'y a personne dans la salle"));
		
		//Gas sensor raises a danger alert
		messagesByComplementarySensorsType.put(SensorType.GAS.getDangerCode() * SensorType.LIGHT.getDangerCode(),
				new Pair<String, String>("There are some harmful gas in the room and the light is on. An explosion can happen",
						"Il y a un dangereux gaz dans la pièce et la lumière est allumée. Une explosion peut arriver à tout moment"));
		
		messagesByComplementarySensorsType.put(SensorType.GAS.getDangerCode() * SensorType.FLOW.getDangerCode(),
				new Pair<String, String>("There are some harmful gas in the room and there are people. They are in danger !",
						"Il y a du gaz dans la pièce et également des personnes. Intervenez rapidement !"));
		
	}
	
	public String getMessageForLocation() {
		String message = "";
		
		for(Map.Entry<Integer, Pair<String, String>> entry : messagesByComplementarySensorsType.entrySet()) {
			int code = entry.getKey();
			if(finalCode % code == 0) {
				message +="   >>> " + entry.getValue().getValue0() + "\n";
			}				 
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
	
	/*public Boolean isComplementary(SensorType alertLauncher, SensorType complementary) {
		List<SensorType> types = sensorsTypeCombinaisons.get(alertLauncher);
		if(types == null)
			return false;
		return types.contains(complementary);
	}*/
	
	
	/*************** For the client ********************/
	
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
		String location = "";
		String alerts = "";
		for(Map.Entry<Integer, Integer> entry : currentDangerBountyByLocation.entrySet()) {
			location = "\n===>Emplacement n°" + entry.getKey();
			int bounty = entry.getValue();
			// Searchs for all divisors
			for(Map.Entry<Integer, Pair<String, String>> entry2 : messagesByComplementarySensorsType.entrySet()) {
				int code = entry2.getKey();
				if(bounty % code == 0) {
					alerts +="\n   #" + entry2.getValue().getValue1();
				}				 
			}
			
			if(alerts.trim().length() > 0) {
				result += location;
				result += alerts;
				result += "\n";
				location = "";
				alerts = "";
			}
		}		
		return result;
	}
}
