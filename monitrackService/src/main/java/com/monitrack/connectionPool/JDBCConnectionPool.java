package com.monitrack.connectionPool;

import java.sql.Connection;
import java.util.ArrayList;

public class JDBCConnectionPool {

	/**
	 * Attribut collectionnant les connexions physiques instances de la classe connexion
	 */
	private ArrayList<Connection> connections;
	
	public JDBCConnectionPool() {
		// TODO : créer l'instance de l'attribut connections
	}
	
	/**
	 * fillConnectionsList : remplit l'attribut "connections" avec un nombre de connexions égale au paramètre de la méthode
	 * Les connexions à ajouter sont des instances de la classe (= type) Connection
	 * 
	 * @param numberOfConnections :
	 * 		nombre de connexions à ajouter
	 */
	public void fillConnectionsList(int numberOfConnections)
	{
		//TODO : ne pas oublier de vérifier que numberOfConnections n'est pas négatif
	}
	
	/**
	 * 
	 * @return
	 * 		retourne une connexion de l'attribut connections
	 */
	public Connection getConnection()
	{
		//TODO
		return null;
	}
	
	/**
	 * putConnection : remet le paramètre de type Connection dans l'attribut connections
	 * 
	 * @param connection
	 */
	public void putConnection(Connection connection)
	{
		//TODO
	}
	
	/**
	 * closeAllConnections : ferme toutes les connexions de l'attribut connections
	 * 
	 * @return
	 * 		@true si toutes les connexions ont été fermées avec succès
	 * 		@false si il y a eu des erreurs dans la fermeture de certaines connexions
	 */
	public boolean closeAllConnections()
	{
		//TODO : Ici le plus simple serait de faire une boucle foreach car ça permettrait d'éviter de déclarer une variable de type int.
		return true;
	}

}
