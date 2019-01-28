package com.monitrack.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.gui.frame.MonitrackFrame;

public class MonitrackMain {

	private static Logger log = LoggerFactory.getLogger(MonitrackMain.class);
	
	public static void main(String[] args) {
		log.info("Lancement de Monitrack");
		MonitrackFrame monitrack = new MonitrackFrame();
	}

}
