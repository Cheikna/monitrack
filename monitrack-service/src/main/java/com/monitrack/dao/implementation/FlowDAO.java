package com.monitrack.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.dao.abstracts.DAO;
import com.monitrack.enumeration.SensorActivity;
import com.monitrack.enumeration.SensorType;

public abstract class FlowDAO<T>  extends DAO<T>{

	public FlowDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

}
