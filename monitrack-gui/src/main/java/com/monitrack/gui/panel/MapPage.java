package com.monitrack.gui.panel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.monitrack.enumeration.Images;
import com.monitrack.listener.LocationsTabListener;
import com.monitrack.listener.MapPageListener;
import com.monitrack.listener.OpeningPageListener;
import com.monitrack.shared.Polygone;
//import com.monitrack.dao.implementation.MapDAO;

public class MapPage extends JPanel{
	private Image planImage;


	//private Image backgroundImage;


	public MapPage(){

		setLayout(new BorderLayout());
		setBackground(new Color(220,220,220));
		//planImage= Images.MAP.getImage();



	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		int planWidth = planImage.getWidth(null);
		int panelWidth = this.getWidth();
		int leftOffset = (panelWidth - planWidth) / 2;

		
		g2.drawImage(planImage, leftOffset, 20, null);
		this.revalidate();


	
	
	}
	
	
	

}















