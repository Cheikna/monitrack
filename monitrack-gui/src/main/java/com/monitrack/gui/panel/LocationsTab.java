package com.monitrack.gui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import com.monitrack.listener.LocationsTabListener;

public class LocationsTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Graphical compenents declaration
	private JPanel north = new JPanel(new FlowLayout());
	//Label
	private JLabel jlName = new JLabel("Nom :");
	//Text field
	private JTextField jtfName  = new JTextField(10);
	//Buttons
	private JButton jbValidate     = new JButton("Créer");
	private JButton jbDelete    = new JButton("Supprimer");
	private JButton jbUpdate    = new JButton("Mettre à jour");
	private JButton jbOverview = new JButton("Tout voir");
	//Text Area
	private JTextArea jTArea = new JTextArea();
	//Scroll for the text area
	private JScrollPane scroll;
	//Listener from LocationsTabListener Class
	private LocationsTabListener listener;
	private Font textAreaFont;

	public LocationsTab()
	{
		setLayout(new BorderLayout());
		textAreaFont = new Font("Calibri", Font.PLAIN, 25);
		listener = new LocationsTabListener(this);
		jbValidate.addActionListener(listener);
		jbOverview.addActionListener(listener);
		jbDelete.addActionListener(listener);
		jbUpdate.addActionListener(listener);
		north.add(jlName);
		north.add(jtfName);
		north.add(jbValidate);
		north.add(jbDelete);
		north.add(jbUpdate);
		this.add(north, BorderLayout.NORTH);
		
		jTArea.setEditable(false);
		jTArea.setFont(textAreaFont);
		scroll = new JScrollPane(jTArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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

	/**
	 * @return the jbDelete
	 */
	public JButton getJbDelete() {
		return jbDelete;
	}

	/**
	 * @return the jbUpdate
	 */
	public JButton getJbUpdate() {
		return jbUpdate;
	}
}
