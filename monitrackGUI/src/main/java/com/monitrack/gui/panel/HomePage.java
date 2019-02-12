package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.monitrack.gui.frame.MonitrackFrame;
import com.monitrack.listener.HomePageListener;

public class HomePage extends JPanel
{
	//Graphical compenents declaration
	private JPanel north = new JPanel(new FlowLayout());
	//Label
	private JLabel jlName = new JLabel("Nom :");
	//Text field
	private JTextField jtfName  = new JTextField(10);
	//Buttons
	private JButton jbValidate     = new JButton("Valider");
	private JButton jbOverview = new JButton("Tout voir");
	//Text Area
	private JTextArea jTArea = new JTextArea();
	//Scroll for the text area
	private JScrollPane scroll = new JScrollPane();
	//Listener from HomePageListener Class
	private HomePageListener listener;

	public HomePage()
	{
		listener = new HomePageListener(this);
		jbValidate.addActionListener(listener);
		jbOverview.addActionListener(listener);
		north.add(jlName);
		north.add(jtfName);
		north.add(jbValidate);
		jTArea.setEditable(false);
		this.add(north, BorderLayout.NORTH);
		scroll.add(jTArea);
		this.add(scroll, BorderLayout.CENTER);
		this.add(jbOverview, BorderLayout.SOUTH);
	}

	/**
	 * @return the jtfName
	 */
	public JTextField getJtfName() {
		return jtfName;
	}

	/**
	 * @return the jbValidate
	 */
	public JButton getJbValidate() {
		return jbValidate;
	}

	/**
	 * @return the jbOverview
	 */
	public JButton getJbOverview() {
		return jbOverview;
	}

	/**
	 * @return the jTArea
	 */
	public JTextArea getjTArea() {
		return jTArea;
	}

}
