package com.monitrack.gui.frame;


import javax.swing.JFrame;

public class MonitrackFrame extends JFrame
{
	public MonitrackFrame()
	{

		this.setTitle("MONITRACK");
		this.setSize(800, 450);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}