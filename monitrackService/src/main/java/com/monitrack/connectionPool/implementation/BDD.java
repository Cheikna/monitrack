package com.monitrack.connectionPool.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class BDD
{
	private static Connection 				connexion;
	private static String 					nomUtilisateur;
	private static String 					motDePasseUtilisateur;

	public static boolean connecter(String nom, String motDePasse)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Connexion locale
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/--ADRESSEBDD--", nom, motDePasse);
			//Connexion serveur ghosup
			//Connection c = DriverManager.getConnection("jdbc:mysql://172.30.200.30/folders", nom, motDePasse);
			//connexion = c;
			BDD.nomUtilisateur = nom;
			BDD.motDePasseUtilisateur = motDePasse;
			return true;
		}
		catch (Exception e)
		{
			//			JOptionPane.showMessageDialog( null,"erreur dans Connecter(String nom, String motDePasse) : "+e.getMessage() );
			connexion = null;
			return false;
		}
	}

	public static void connecter()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://--ADRESSEBDD--", BDD.nomUtilisateur, BDD.motDePasseUtilisateur);
			//Connection c = DriverManager.getConnection("jdbc:mysql://172.30.200.30/folders", BDD.nomUtilisateur, BDD.motDePasseUtilisateur);
			connexion = c;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog( null,"erreur dans Connecter() : "+e.getMessage() );
			connexion = null;
		}
	}

	public static void d�connecter()
	{
		try
		{
			if(connexion != null)
			{
				connexion.close();
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog( null,"erreur dans D�connecter : " +e.getMessage());
		}
	}

	//fournit le r�sultat de requ�te sous forme d'un ResultSet
	public static ResultSet leR�sultat(String requ�te)
	{
		try
		{
			BDD.connecter();
			return connexion.createStatement().executeQuery(requ�te);
		}
		catch (Exception e)
		{
			System.out.println("erreur acc�s BDD.."+e.getMessage());
		}
		return null;
	}
}
