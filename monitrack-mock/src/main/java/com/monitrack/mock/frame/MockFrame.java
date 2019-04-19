package com.monitrack.mock.frame;

import java.awt.CardLayout;

import javax.swing.JFrame;

import com.monitrack.mock.enumeration.Page;
import com.monitrack.mock.panel.MockChoicePage;

public class MockFrame extends JFrame {

	private CardLayout cardLayout;
	
	public MockFrame() {
		super("Monitrack Mock");
		cardLayout = new CardLayout();
		setSize(900,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MockChoicePage(this));
		setVisible(true);		
	}
	
	public void changePage(Page page) {
		cardLayout.show(this, page.getName());
	}

}
