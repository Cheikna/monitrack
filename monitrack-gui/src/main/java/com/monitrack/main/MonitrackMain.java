package com.monitrack.main;

import java.util.List;

import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.SensorConfiguration;
import com.monitrack.enumeration.RequestType;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class MonitrackMain {

	private static  final Logger log = LoggerFactory.getLogger(MonitrackMain.class);

	public static void main(String[] args) {		
		/*
		 * The swing objects are not thread-safe which means that some errors can happens when we are accessing to the same data
		 * In order to prevent this from happening, we have to use a special Thread called 'SwingUtilities.invokeLater'
		 */
		SwingUtilities.invokeLater(new Runnable() {
			
			@SuppressWarnings("unused")
			public void run() {
				log.info("Launching of Monitrack");
				//Displays the Graphical User Interface
				MonitrackFrame monitrack = new MonitrackFrame();
			}
		});
		
		
		try {
			//FIXME Cheikna : do not forget to remove this
			String jsonRequest = JsonUtil.serializeRequest(RequestType.SELECT, SensorConfiguration.class, null, null,
					null);
			String response = MonitrackGuiUtil.sendRequest(jsonRequest);
			List<SensorConfiguration> sensors = (List<SensorConfiguration>) JsonUtil.deserializeObject(response);
			for (SensorConfiguration s : sensors) {
				System.out.println("===========> " + s);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}