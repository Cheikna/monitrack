package com.monitrack.comparator;

import java.util.Comparator;

import com.monitrack.entity.AccessControlHistory;

public class AccessControlHistoryByTriggeringDateComparator implements Comparator<AccessControlHistory> {

	@Override
	public int compare(AccessControlHistory o1, AccessControlHistory o2) {
		if(o1.getTriggeringDate() != null && o2.getTriggeringDate() == null)
			return -1;
		if(o2.getTriggeringDate() != null && o1.getTriggeringDate() == null)
			return 1;
		if(o1.getTriggeringDate() == null && o2.getTriggeringDate() == null)
			return 0;	
		Long t1 = o1.getTriggeringDate().getTime();
		Long t2 = o2.getTriggeringDate().getTime();	
		if(t1 >= t2)
			return -1;
		if(t2 > t1)
			return 1;
		return 0;
	}

}
