package com.monitrack.entity;

import java.sql.Date;

public class Person {

	int id_person;
	String name_person;
	Date createDate;
	
	public Person() {
		
	}
	

	public Person(int id_person, String name_person, Date createDate) {
		super();
		this.id_person = id_person;
		this.name_person = name_person;
		this.createDate = createDate;
	}

	public int getId_person() {
		return id_person;
	}

	public void setId_person(int id_person) {
		this.id_person = id_person;
	}

	public String getName_person() {
		return name_person;
	}

	public void setName_person(String name_person) {
		this.name_person = name_person;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
