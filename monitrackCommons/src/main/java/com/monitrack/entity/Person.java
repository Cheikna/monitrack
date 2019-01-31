package com.monitrack.entity;

import java.sql.Timestamp;

public class Person {

	private int idPerson;
	private String namePerson;
	private Timestamp creationDate;
	
	public Person() {
		
	}

	/**
	 * Constructeur utilisé lors de la récupération 
	 * à partir de la base de données ou d'un JSON
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
	 * Constructeur utilisé lors de la création d'une nouvelle instance via l'Interface Homme Machine
	 * Il n'y a que l'attribut namePerson car 'creationDate' est défini grâce à une fonction java
	 * et 'idPerson' est calculé directement dans la base 
	 * (c'est une valeur auto-incrémentée donc accessible uniquement lors de la création en base).
	 * Sa valeur n'est pas connue à l'avance
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
