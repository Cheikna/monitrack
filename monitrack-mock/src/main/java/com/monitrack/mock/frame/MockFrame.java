package com.monitrack.mock.frame;

import javax.swing.JFrame;

public class MockFrame extends JFrame {

	public MockFrame() {
		super("Monitrack Mock");
		setSize(800,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}

}
