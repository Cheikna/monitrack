package com.monitrack.entity;

import java.sql.Timestamp;

public class Person {

	private int idPerson;
	private String namePerson;
	private Timestamp creationDate;
	
	public Person() {
		
	}

	/**
	 * Constructor used to retrieved a Person from the database
	 * We cannot call the other constructor in this one, 
	 * otherwise it will replace the creationDate retrieved from the database with the current time
	 * 
	 * @param idPerson
	 * @param namePerson
	 * @param creationDate
	 */
	public Person(int idPerson, String namePerson, Timestamp creationDate) {
		this.idPerson = idPerson;
		this.namePerson = namePerson;
		this.creationDate = creationDate;
	}
  
	/**
	 * Constructor used to register a Person into the database (
	 * 		the id is auto-generated when the Person is inserted into the database
	 * 		and the creation_date is retrieved thanks to a java.sql.Date method
	 * )
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
		return idPerson + "#" + namePerson + " - créée le " + creationDate;
		/*return "Person [idPerson=" + idPerson + ", namePerson=" + namePerson + ", creationDate=" + creationDate
				+ "]";*/
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + idPerson;
		result = prime * result + ((namePerson == null) ? 0 : namePerson.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (idPerson != other.idPerson)
			return false;
		if (namePerson == null) {
			if (other.namePerson != null)
				return false;
		} else if (!namePerson.equals(other.namePerson))
			return false;
		return true;
	}
	
	
	
}
