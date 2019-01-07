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

	public static void déconnecter()
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
			JOptionPane.showMessageDialog( null,"erreur dans Déconnecter : " +e.getMessage());
		}
	}

	//fournit le résultat de requête sous forme d'un ResultSet
	public static ResultSet leRésultat(String requête)
	{
		try
		{
			BDD.connecter();
			return connexion.createStatement().executeQuery(requête);
		}
		catch (Exception e)
		{
			System.out.println("erreur accès BDD.."+e.getMessage());
		}
		return null;
	}
}
