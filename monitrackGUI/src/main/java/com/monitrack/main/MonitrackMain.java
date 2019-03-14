package com.monitrack.main;

import java.util.Scanner;

import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.clientsocket.ClientSocket;
import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.shared.MonitrackGUIFactory;

public class MonitrackMain {

	private static Logger log = LoggerFactory.getLogger(MonitrackMain.class);

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
		
		//FIXME The bottom area needs to be deleted
		/*Thread clientServerComm = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ClientSocket clientSocket = new ClientSocket();
				clientSocket.start();
				Scanner sc = new Scanner(System.in);
				while(true)
				{
					System.out.print("Write something : ");
					String requestToSendToServer = sc.nextLine();
					clientSocket.sendRequestToServer(requestToSendToServer);					
				}
				
			}
		});
		clientServerComm.start();*/
	}	
	
}