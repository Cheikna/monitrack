package com.monitrack.comparator;

import java.util.Comparator;

import com.monitrack.entity.SensorConfiguration;

public class SensorByLocationIdComparator implements Comparator<SensorConfiguration> {

	@Override
	public int compare(SensorConfiguration arg0, SensorConfiguration arg1) {
		return arg0.getLocationId().compareTo(arg1.getLocationId());
	}

}
