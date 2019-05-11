package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.monitrack.comparator.AccessControlHistoryByTriggeringDateComparator;
import com.monitrack.connection.pool.implementation.DataSource;
import com.monitrack.entity.AccessControlHistory;
import com.monitrack.enumeration.RequestType;
import com.monitrack.util.Util;

public class AccessControlHistoryDAOTest {

	@Before
	public void init() {
		DataSource.startConnectionPool();
	}
	
	@Test
	public void testInsert() throws Exception {
		Connection connection = DataSource.getConnection();
		AccessControlHistory accessControl = new AccessControlHistory(0, 2, 1, null, null, Util.getCurrentTimestamp(), false);
		AccessControlHistory accessControl2 = (AccessControlHistory) DAOFactory.execute(connection, AccessControlHistory.class, RequestType.INSERT, accessControl, null, null, null);
		DataSource.putConnection(connection);
		System.out.println("inserted with id n°" + accessControl2.getHistoryId());
	}
	
	@Test
	public void testSelect() throws Exception {
		Connection connection = DataSource.getConnection();
		 @SuppressWarnings("unchecked")
		List<AccessControlHistory> accessControls = (List<AccessControlHistory>) DAOFactory.execute(connection, AccessControlHistory.class, RequestType.SELECT, null, null, null, null);
		
		 Collections.sort(accessControls, new AccessControlHistoryByTriggeringDateComparator());
		 DataSource.putConnection(connection);
		Util.displayListElements(accessControls, "===>");
	}

}
