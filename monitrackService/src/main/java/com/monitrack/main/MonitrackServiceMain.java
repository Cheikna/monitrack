package com.monitrack.main;


import java.sql.Connection;
import com.monitrack.connectionpool.implementation.DataSource;

public class MonitrackServiceMain {

	public static void main(String [] args) {
		DataSource.startConnectionPool();
		int numberOfConnections = DataSource.getRemaningConnections();
		int tooMuchConnections = numberOfConnections + 3;
		Connection connection = null;	
		
		for(int i = 1; i <= tooMuchConnections; i++) {
			System.out.println("-------------------------------- Loop n°" + i + "/" + tooMuchConnections + " --------------------------------");
			connection = DataSource.getConnection();
			if(connection != null)
			{
				System.out.println("The connection n°" + i + " from the DataSource is not null.");
			}
			else
			{
				if(i > numberOfConnections)
				{
					System.out.println("We are try to access to the connection n°" + i + " whereas there were only " + numberOfConnections + " available at the beginning on the DataSource.");
					System.out.println("Normally, the next connection should be null !");
				}
				System.out.println("The connection n°" + i + " from the DataSource is null.");
				System.out.println("------------------------------- End : Results -------------------------------");
				System.out.println("Number of connections in the DataSource : " + numberOfConnections);
				System.out.println("Error when accessing to the connection n°" + i + " of the DataSource");				
				break;
			}
		}
	}
}
