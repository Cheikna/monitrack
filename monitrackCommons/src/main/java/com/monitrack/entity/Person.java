package com.monitrack.entity;

import java.sql.Date;

public class Person {

	private int idPerson;
	private String namePerson;
	private Date createDate;
	
	public Person() {
		
	}

	public Person(int idPerson, String namePerson, Date createDate) {
		this(namePerson, createDate);
		this.idPerson = idPerson;
	}
 
	public Person(String namePerson, Date createDate) {
		super();
		this.namePerson = namePerson;
		this.createDate = createDate;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

	
	
}
