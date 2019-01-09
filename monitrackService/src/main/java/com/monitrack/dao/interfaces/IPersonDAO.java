package com.monitrack.dao.interfaces;

import java.util.List;

import com.monitrack.entity.Person;

public interface IPersonDAO{

	void create (Person person);
	List<Person> findAll();
	
}
