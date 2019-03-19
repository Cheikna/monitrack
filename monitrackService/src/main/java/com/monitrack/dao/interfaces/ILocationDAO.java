package com.monitrack.dao.interfaces;

import java.util.List;

import com.monitrack.entity.Location;

public interface ILocationDAO{

	void create (Location location);
	void update (Location location);
	void delete (int locationId);
	List<Location> findAll();
	
}
