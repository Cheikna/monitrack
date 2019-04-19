package com.monitrack.mock.frame;

import java.awt.CardLayout;

import javax.swing.JFrame;

import com.monitrack.mock.enumeration.Page;
import com.monitrack.mock.panel.MockChoicePage;
import com.monitrack.mock.panel.SensorsPage;

public class MockFrame extends JFrame {

	private CardLayout cardLayout;
	
	public MockFrame() {
		super("Monitrack Mock");
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		setSize(900,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MockChoicePage(this), Page.SENSOR_OVERVIEW.getName());
		add(new SensorsPage(), Page.SENSOR_OVERVIEW.getName());
		setVisible(true);		
	}
	
	public void changePage(Page page) {
		cardLayout.show(this.getContentPane(), page.getName());
	}

}
