package com.monitrack.dao.abstracts;

import java.util.List;

import com.monitrack.entity.Person;

public interface IPersonDAO{

	void create (Person person);
	void update (Person person);
	void delete (int personId);
	List<Person> findAll();
	
}
