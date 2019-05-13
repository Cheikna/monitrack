package com.monitrack.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitrack.entity.Location;
import com.monitrack.enumeration.Images;
import com.monitrack.enumeration.RequestType;
import com.monitrack.exception.DeprecatedVersionException;
import com.monitrack.exception.NoAvailableConnectionException;
import com.monitrack.gui.panel.MapPage;
import com.monitrack.gui.panel.MapPage;
import com.monitrack.shared.MonitrackGuiUtil;
import com.monitrack.util.JsonUtil;

public class MapPageListener  {

	private static final Logger log = LoggerFactory.getLogger(MapPageListener.class);

	private MapPage MapPage;
	private List<Location> locations;
	private List<String> fields;
	private List<String> values;

	public MapPageListener(MapPage mapPage)
	{
		this.MapPage = mapPage;
		locations = new ArrayList<>();
		fields = new ArrayList<String>();
		values = new ArrayList<String>();	
	}

}
	