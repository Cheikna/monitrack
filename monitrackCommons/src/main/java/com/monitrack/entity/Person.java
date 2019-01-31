package com.monitrack.entity;

import java.sql.Timestamp;

public class Person {

	private int idPerson;
	private String namePerson;
	private Timestamp creationDate;
	
	public Person() {
		
	}

	/**
	 * Constructeur utilis� lors de la r�cup�ration 
	 * � partir de la base de donn�es ou d'un JSON
	 * 
	 * @param idPerson
	 * @param namePerson
	 * @param creationDate
	 */
	public Person(int idPerson, String namePerson, Timestamp creationDate) {
		this(namePerson);
		this.idPerson = idPerson;
		this.creationDate = creationDate;
	}
  
	/**
	 * Constructeur utilis� lors de la cr�ation d'une nouvelle instance via l'Interface Homme Machine
	 * Il n'y a que l'attribut namePerson car 'creationDate' est d�fini gr�ce � une fonction java
	 * et 'idPerson' est calcul� directement dans la base 
	 * (c'est une valeur auto-incr�ment�e donc accessible uniquement lors de la cr�ation en base).
	 * Sa valeur n'est pas connue � l'avance
	 * 
	 * @param namePerson
	 */
	public Person(String namePerson) {
		this.namePerson = namePerson;
		this.creationDate = new Timestamp(System.currentTimeMillis());
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getNamePerson() {
		return namePerson;
	}

	public void setNamePerson(String namePerson) {
		this.namePerson = namePerson;
	}	

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", namePerson=" + namePerson + ", creationDate=" + creationDate
				+ "]";
	}
	
}
