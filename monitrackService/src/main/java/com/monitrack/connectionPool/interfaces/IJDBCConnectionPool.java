package com.monitrack.connectionPool.interfaces;

import java.sql.Connection;

public interface IJDBCConnectionPool {
	
	/**
	 * Remplit l'attribut "connections" avec un nombre de connexions �gale au param�tre de la m�thode
	 * Les connexions � ajouter sont des instances de la classe (= type) Connection
	 * 
	 * @param numberOfConnections :
	 * 		nombre de connexions � ajouter
	 */
	public void fillConnectionsList(int numberOfConnections);
	
	
	/**
	 * 
	 * @return
	 * 		retourne une connexion de l'attribut connections
	 */
	public Connection getConnection();
	
	
	/**
	 * Remet le param�tre de type Connection dans l'attribut connections
	 * 
	 * @param connection
	 */
	public void putConnection(Connection connection);
	
	
	/**
	 * Ferme toutes les connexions de l'attribut connections
	 * 
	 * @return
	 * 		@true si toutes les connexions ont �t� ferm�es avec succ�s
	 * 		@false si il y a eu des erreurs dans la fermeture de certaines connexions
	 */
	public boolean closeAllConnections();

}
