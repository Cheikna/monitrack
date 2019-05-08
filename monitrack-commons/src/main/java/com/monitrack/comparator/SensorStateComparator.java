package com.monitrack.comparator;

import java.util.Comparator;

import com.monitrack.enumeration.SensorState;

public class SensorStateComparator implements Comparator<SensorState>{

	@Override
	public int compare(SensorState o1, SensorState o2) {
		if(o1 != o2 && o1 == SensorState.DANGER)
			return -1;
		if(o1 != o2 && o2 == SensorState.DANGER)
			return 1;
		return 0;		
	}

	
}
