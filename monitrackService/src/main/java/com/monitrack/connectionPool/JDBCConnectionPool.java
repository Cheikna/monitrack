package com.monitrack.connectionPool;

import java.sql.Connection;
import java.util.ArrayList;

public class JDBCConnectionPool {

	/**
	 * Attribut collectionnant les connexions physiques instances de la classe connexion
	 */
	private ArrayList<Connection> connections;
	
	public JDBCConnectionPool() {
		// TODO : cr�er l'instance de l'attribut connections
	}
	
	/**
	 * fillConnectionsList : remplit l'attribut "connections" avec un nombre de connexions �gale au param�tre de la m�thode
	 * Les connexions � ajouter sont des instances de la classe (= type) Connection
	 * 
	 * @param numberOfConnections :
	 * 		nombre de connexions � ajouter
	 */
	public void fillConnectionsList(int numberOfConnections)
	{
		//TODO : ne pas oublier de v�rifier que numberOfConnections n'est pas n�gatif
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
	 * putConnection : remet le param�tre de type Connection dans l'attribut connections
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
	 * 		@true si toutes les connexions ont �t� ferm�es avec succ�s
	 * 		@false si il y a eu des erreurs dans la fermeture de certaines connexions
	 */
	public boolean closeAllConnections()
	{
		//TODO : Ici le plus simple serait de faire une boucle foreach car �a permettrait d'�viter de d�clarer une variable de type int.
		return true;
	}

}
